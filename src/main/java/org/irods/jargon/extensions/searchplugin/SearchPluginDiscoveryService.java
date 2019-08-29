/**
 * 
 */
package org.irods.jargon.extensions.searchplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.extensions.searchplugin.exception.SearchPluginUnavailableException;
import org.irods.jargon.extensions.searchplugin.implementation.PluginInventoryCallable;
import org.irods.jargon.extensions.searchplugin.implementation.SchemaAttributesCallable;
import org.irods.jargon.extensions.searchplugin.model.IndexSchemaDescription;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.extensions.searchplugin.model.SearchAttributes;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.LoggerFactory;

/**
 * Service to interrogate registered search plugin endpoints
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchPluginDiscoveryService {

	private final SearchPluginRegistrationConfig searchPluginRegistrationConfig;
	private final AbstractJwtIssueService jwtIssueService;
	public static final org.slf4j.Logger log = LoggerFactory.getLogger(SearchPluginDiscoveryService.class);

	/**
	 * Constructor requiring configuration information
	 * 
	 * @param searchPluginRegistrationConfig {@link SearchPluginRegistrationConfig}
	 */
	public SearchPluginDiscoveryService(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			AbstractJwtIssueService jwtIssueService) {

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		this.searchPluginRegistrationConfig = searchPluginRegistrationConfig;
		this.jwtIssueService = jwtIssueService;
	}

	public String textSearch(final String queryText, final String endpointUrl, final String schemaId, final int offset,
			final int length) throws SearchPluginUnavailableException {

		log.info("textSearch()");

		if (queryText == null || queryText.isEmpty()) {
			throw new IllegalArgumentException("null or empty queryText");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (schemaId == null || schemaId.isEmpty()) {
			throw new IllegalArgumentException("null or empty schemaId");
		}

		log.info("queryText:{}", queryText);
		log.info("endpointUrl:{}", endpointUrl);
		log.info("schemaId:{}", schemaId);
		log.info("offset:{}", offset);
		log.info("length:{}", length);
		return null;

	}

	public SearchAttributes queryAttributes(final String endpointUrl, final String schemaId,
			final SearchIndexInventory searchIndexInventory) throws SearchPluginUnavailableException {
		log.info("queryAttributes()");

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (schemaId == null || schemaId.isEmpty()) {
			throw new IllegalArgumentException("null or empty schemaId");
		}

		if (searchIndexInventory == null) {
			throw new IllegalArgumentException("null searchIndexInventory");
		}

		log.info("endpointUrl:{}", endpointUrl);
		log.info("schemaId:{}", schemaId);

		// TODO: look at collapsing this into synch cache check method?

		log.debug("check for cache hit and bail if endpoint or schema is missing");
		SearchIndexInventoryEntry entry = searchIndexInventory.getIndexInventoryEntries().get(endpointUrl);
		if (entry == null) {
			log.warn("no entry found for endpoint:{}", endpointUrl);
			return null;
		}

		IndexSchemaDescription descriptionFound = null;

		for (IndexSchemaDescription description : entry.getIndexInformation().getIndexes()) {
			if (description.getId().equals(schemaId)) {
				descriptionFound = description;
				break;
			}
		}

		if (descriptionFound == null) {
			log.warn("schema not found");
			return null;
		}

		/*
		 * now see if the attributes are available, if they are not, than go ahead and
		 * query for them, updating the registry
		 */

		SearchAttributes attributes = entry.getSearchAttributesMap().get(schemaId);

		if (attributes != null) {
			log.debug("attributes found:{}", attributes);
			return attributes;
		}

		log.info("gathering attributes at endpoint for schema:{}", schemaId);

		ExecutorService executor = Executors.newCachedThreadPool();
		List<Callable<SearchAttributes>> callables = new ArrayList<>();

		callables.add(new SchemaAttributesCallable(searchPluginRegistrationConfig, endpointUrl, schemaId,
				jwtIssueService, searchIndexInventory));

		List<Future<SearchAttributes>> attribs = null;

		try {
			if (searchPluginRegistrationConfig.getEndpointAccessTimeout() > 0) {
				log.debug("getting endpoints with a timeout");

				attribs = executor.invokeAll(callables, searchPluginRegistrationConfig.getEndpointAccessTimeout(),
						TimeUnit.MILLISECONDS);

			} else {
				attribs = executor.invokeAll(callables);
			}

			awaitTerminationAfterShutdown(executor);

			return attribs.get(0).get();

		} catch (InterruptedException | ExecutionException e) {
			log.error("Runtime error pinging endpoint", e);
			throw new JargonRuntimeException("Runtime error accessing endpoint", e);
		} finally {

			executor.shutdownNow();
			log.info("shutdown finished");
		}

	}

	/**
	 * Given an endpoint URL that is a search plugin, gather the descriptive
	 * metadata about that search service.
	 * 
	 * @param endpointUrls         {@link List<String>} with the URLs of the service
	 *                             endpoints, this should be down to the version and
	 *                             leave off the /indexes or other resource
	 *                             endpoints, these will be added by underlying
	 *                             services
	 * @param searchIndexInventory {@link SearchIndexInventory} which maintains a
	 *                             canonical reference to attached endpoints,
	 *                             schema, and attributes.
	 * @return {@link List<Indexes>} that describes the given endpoint capabilities
	 * @throws SearchPluginUnavailableException {@link SearchPluginUnavailableException}
	 * 
	 */
	public void queryEndpoints(final List<String> endpointUrls, final SearchIndexInventory searchIndexInventory)
			throws SearchPluginUnavailableException {

		log.info("queryEndpoint()");
		if (endpointUrls == null || endpointUrls.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpoint urls");
		}

		log.info("endpointUrls:{}", endpointUrls);

		ExecutorService executor = Executors.newCachedThreadPool();
		List<Callable<Indexes>> callables = new ArrayList<>();

		for (String endpointUrl : endpointUrls) {
			callables.add(new PluginInventoryCallable(searchPluginRegistrationConfig, endpointUrl, jwtIssueService,
					searchIndexInventory));
		}

		try {
			if (searchPluginRegistrationConfig.getEndpointAccessTimeout() > 0) {
				log.debug("getting endpoints with a timeout");

				executor.invokeAll(callables, searchPluginRegistrationConfig.getEndpointAccessTimeout(),
						TimeUnit.MILLISECONDS);

			} else {
				executor.invokeAll(callables);
			}

			awaitTerminationAfterShutdown(executor);

		} catch (InterruptedException e) {
			log.error("Runtime error pinging endpoint", e);
			throw new JargonRuntimeException("Runtime error accessing endpoint", e);
		} finally {

			executor.shutdownNow();
			log.info("shutdown finished");
		}

	}

	private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
				log.warn("all threads did not terminate successfully");
				threadPool.shutdownNow();
			}
		} catch (InterruptedException ex) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	public SearchPluginRegistrationConfig getSearchPluginRegistrationConfig() {
		return searchPluginRegistrationConfig;
	}

}
