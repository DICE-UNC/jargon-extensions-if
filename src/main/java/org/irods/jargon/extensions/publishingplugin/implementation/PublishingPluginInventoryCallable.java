package org.irods.jargon.extensions.publishingplugin.implementation;

import java.util.concurrent.Callable;

import org.irods.jargon.extensions.publishingplugin.PublishingIndexInventory;
import org.irods.jargon.extensions.publishingplugin.PublishingInventoryEntry;
import org.irods.jargon.extensions.publishingplugin.PublishingPluginRegistrationConfig;
import org.irods.jargon.extensions.publishingplugin.model.PublishingEndpointDescription;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PublishingPluginInventoryCallable implements Callable<PublishingEndpointDescription> {

	public static final Logger log = LogManager.getLogger(PublishingPluginInventoryCallable.class);

	private final PublishingPluginRegistrationConfig publishingPluginRegistrationConfig;
	private final String endpointUrl;
	private final AbstractJwtIssueService jwtIssueService;
	private final PublishingIndexInventory publishingInventory;

	public PublishingPluginInventoryCallable(
			final PublishingPluginRegistrationConfig publishingPluginRegistrationConfig, final String endpointUrl,
			final AbstractJwtIssueService jwtIssueService, final PublishingIndexInventory publishingInventory) {

		if (publishingPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null publishingPluginRegistrationConfig");
		}
		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}
		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}
		if (publishingInventory == null) {
			throw new IllegalArgumentException("null publishingInventory");
		}

		this.publishingPluginRegistrationConfig = publishingPluginRegistrationConfig;
		this.endpointUrl = endpointUrl;
		this.jwtIssueService = jwtIssueService;
		this.publishingInventory = publishingInventory;
	}

	@Override
	public PublishingEndpointDescription call() throws Exception {
		log.info("call()");

		PublishingIndexInventoryUtility publishingIndexInventoryUtility = new PublishingIndexInventoryUtility();
		PublishingEndpointDescription publishingEndpointDescription = publishingIndexInventoryUtility
				.inventoryEndpoint(publishingPluginRegistrationConfig, endpointUrl, jwtIssueService);

		log.debug("got an index, updating the inventory:{}", publishingEndpointDescription);
		PublishingInventoryEntry entry = new PublishingInventoryEntry(endpointUrl, System.currentTimeMillis(),
				publishingEndpointDescription);

		publishingInventory.setLastScanTimeInMillis(System.currentTimeMillis());
		publishingInventory.getPublishingInventoryEntries().put(endpointUrl, entry);

		log.info("entry updated");

		return publishingEndpointDescription;
	}
}
