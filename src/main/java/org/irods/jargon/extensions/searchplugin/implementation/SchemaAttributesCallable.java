package org.irods.jargon.extensions.searchplugin.implementation;

import java.util.concurrent.Callable;

import org.irods.jargon.extensions.searchplugin.SearchIndexInventory;
import org.irods.jargon.extensions.searchplugin.SearchPluginRegistrationConfig;
import org.irods.jargon.extensions.searchplugin.model.SearchAttributes;
import org.irods.jargon.irodsext.jwt.JwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Callable to obtain information
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SchemaAttributesCallable implements Callable<SearchAttributes> {

	private final SearchPluginRegistrationConfig searchPluginRegistrationConfig;
	private final String endpointUrl;
	private final JwtIssueService jwtIssueService;
	private final SearchIndexInventory searchIndexInventory;
	private final String schemaId;

	public static final Logger log = LoggerFactory.getLogger(PluginInventoryCallable.class);

	public SchemaAttributesCallable(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			final String endpointUrl, final String schemaId, final JwtIssueService jwtIssueService,
			final SearchIndexInventory searchIndexInventory) {

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (schemaId == null || schemaId.isEmpty()) {
			throw new IllegalArgumentException("null or empty schemaId");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		if (searchIndexInventory == null) {
			throw new IllegalArgumentException("null searchIndexInventory");
		}

		this.searchPluginRegistrationConfig = searchPluginRegistrationConfig;
		this.endpointUrl = endpointUrl;
		this.schemaId = schemaId;
		this.jwtIssueService = jwtIssueService;
		this.searchIndexInventory = searchIndexInventory;
	}

	@Override
	public SearchAttributes call() throws Exception {
		log.info("call()");
		IndexInventoryUtility indexUtility = new IndexInventoryUtility();
		SearchAttributes searchAttributes = indexUtility.inventoryAttributes(searchPluginRegistrationConfig,
				endpointUrl, schemaId, jwtIssueService);
		log.debug("got attribs:{}", searchAttributes);
		searchIndexInventory.getIndexInventoryEntries().get(endpointUrl).getSearchAttributesMap().put(schemaId,
				searchAttributes);
		log.info("attribs updated in schema registry");
		return searchAttributes;
	}

}
