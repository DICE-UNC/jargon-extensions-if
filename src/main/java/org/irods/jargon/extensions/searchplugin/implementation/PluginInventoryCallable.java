/**
 * 
 */
package org.irods.jargon.extensions.searchplugin.implementation;

import java.util.concurrent.Callable;

import org.irods.jargon.extensions.searchplugin.SearchIndexInventory;
import org.irods.jargon.extensions.searchplugin.SearchIndexInventoryEntry;
import org.irods.jargon.extensions.searchplugin.SearchPluginRegistrationConfig;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Callable unit of work that accesses an endpoint to gather information on the
 * capabilities of the target index
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class PluginInventoryCallable implements Callable<Indexes> {

	private final SearchPluginRegistrationConfig searchPluginRegistrationConfig;
	private final String endpointUrl;
	private final AbstractJwtIssueService jwtIssueService;
	private final SearchIndexInventory searchIndexInventory;

	public static final Logger log = LogManager.getLogger(PluginInventoryCallable.class);

	public PluginInventoryCallable(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			final String endpointUrl, final AbstractJwtIssueService jwtIssueService,
			final SearchIndexInventory searchIndexInventory) {

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		if (searchIndexInventory == null) {
			throw new IllegalArgumentException("null searchIndexInventory");
		}

		this.searchPluginRegistrationConfig = searchPluginRegistrationConfig;
		this.endpointUrl = endpointUrl;
		this.jwtIssueService = jwtIssueService;
		this.searchIndexInventory = searchIndexInventory;
	}

	@Override
	public Indexes call() throws Exception {
		log.info("call()");
		IndexInventoryUtility indexUtility = new IndexInventoryUtility();
		Indexes index = indexUtility.inventoryEndpoint(searchPluginRegistrationConfig, endpointUrl, jwtIssueService);
		log.debug("got an index, updating the inventory:{}", index);
		SearchIndexInventoryEntry entry = new SearchIndexInventoryEntry(endpointUrl, System.currentTimeMillis(), index);
		searchIndexInventory.setLastScanTimeInMillis(System.currentTimeMillis());
		searchIndexInventory.getIndexInventoryEntries().put(endpointUrl, entry);
		log.info("entry updated");
		return index;
	}

}
