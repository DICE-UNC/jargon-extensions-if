/**
 * 
 */
package org.irods.jargon.extensions.searchplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.extensions.searchplugin.exception.SearchPluginUnavailableException;
import org.irods.jargon.extensions.searchplugin.implementation.PluginInventoryCallable;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.irodsext.jwt.JwtIssueServiceImpl;
import org.irods.jargon.irodsext.jwt.JwtServiceConfig;
import org.slf4j.LoggerFactory;

/**
 * Service to interrogate registered search plugin endpoints
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchPluginDiscoveryService {

	private final SearchPluginRegistrationConfig searchPluginRegistrationConfig;
	private final JwtIssueServiceImpl jwtIssueService;
	public static final org.slf4j.Logger log = LoggerFactory.getLogger(SearchPluginDiscoveryService.class);

	/**
	 * Constructor requiring configuration information
	 * 
	 * @param searchPluginRegistrationConfig {@link SearchPluginRegistrationConfig}
	 */
	public SearchPluginDiscoveryService(final SearchPluginRegistrationConfig searchPluginRegistrationConfig) {
		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		this.searchPluginRegistrationConfig = searchPluginRegistrationConfig;
		JwtServiceConfig jwtServiceConfig = new JwtServiceConfig();
		jwtServiceConfig.setAlgo(searchPluginRegistrationConfig.getJwtAlgo());
		jwtServiceConfig.setIssuer(searchPluginRegistrationConfig.getJwtIssuer());
		jwtServiceConfig.setSecret(searchPluginRegistrationConfig.getJwtSecret());

		jwtIssueService = new JwtIssueServiceImpl(jwtServiceConfig);
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

		List<Future<Indexes>> futures;
		try {
			if (searchPluginRegistrationConfig.getEndpointAccessTimeout() > 0) {
				log.debug("getting endpoints with a timeout");

				futures = executor.invokeAll(callables, searchPluginRegistrationConfig.getEndpointAccessTimeout(),
						TimeUnit.MILLISECONDS);

			} else {
				futures = executor.invokeAll(callables);
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

}
