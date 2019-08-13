package org.irods.jargon.extensions.searchplugin;

import org.irods.jargon.extensions.searchplugin.model.Indexes;

public class SearchIndexInventoryEntry {

	private final String endpointUrl;
	private final long lastScanTimeInMillis;
	private final Indexes indexInformation;

	public SearchIndexInventoryEntry(String endpointUrl, long lastScanTimeInMillis, Indexes indexInformation) {
		super();
		this.endpointUrl = endpointUrl;
		this.lastScanTimeInMillis = lastScanTimeInMillis;
		this.indexInformation = indexInformation;
	}

	public synchronized String getEndpointUrl() {
		return endpointUrl;
	}

	public synchronized long getLastScanTimeInMillis() {
		return lastScanTimeInMillis;
	}

	public synchronized Indexes getIndexInformation() {
		return indexInformation;
	}

	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchIndexInventoryEntry [endpointUrl=").append(endpointUrl).append(", lastScanTimeInMillis=")
				.append(lastScanTimeInMillis).append(", indexInformation=").append(indexInformation).append("]");
		return builder.toString();
	}

}