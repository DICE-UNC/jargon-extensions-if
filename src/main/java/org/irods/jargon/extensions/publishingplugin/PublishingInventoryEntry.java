package org.irods.jargon.extensions.publishingplugin;

import org.irods.jargon.extensions.publishingplugin.model.PublishingEndpointDescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PublishingInventoryEntry {
	
	public static final Logger log = LogManager.getLogger(PublishingInventoryEntry.class);

	private final String endpointUrl;
	private final long lastScanTimeInMillis;
	private final PublishingEndpointDescription publishingEndpointDescription;
	
	public PublishingInventoryEntry(String endpointUrl, Long lastScanTimeInMillis, PublishingEndpointDescription publishingEndpointDescription) {
		super();
		this.endpointUrl = endpointUrl;
		this.lastScanTimeInMillis = lastScanTimeInMillis;
		this.publishingEndpointDescription = publishingEndpointDescription;
	}
	
	public synchronized String getEndpointUrl() {
		return endpointUrl;
	}

	public synchronized long getLastScanTimeInMillis() {
		return lastScanTimeInMillis;
	}

	public synchronized PublishingEndpointDescription getPublishingEndpointDescription() {
		return publishingEndpointDescription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchIndexInventoryEntry [endpointUrl=").append(endpointUrl).append(", lastScanTimeInMillis=")
				.append(lastScanTimeInMillis).append(", publishingEndpointDescription=").append(publishingEndpointDescription).append("]");
		return builder.toString();
	}
}
