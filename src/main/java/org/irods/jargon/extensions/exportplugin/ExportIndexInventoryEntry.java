package org.irods.jargon.extensions.exportplugin;

import org.irods.jargon.extensions.exportplugin.model.Indexes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportIndexInventoryEntry {

	public static final Logger log = LoggerFactory.getLogger(ExportIndexInventoryEntry.class);

	private final String endpointUrl;
	private final long lastScanTimeInMillis;
	private final Indexes indexInformation;

	public ExportIndexInventoryEntry(String endpointUrl, Long lastScanTimeInMillis, Indexes indexInformation) {
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchIndexInventoryEntry [endpointUrl=").append(endpointUrl).append(", lastScanTimeInMillis=")
				.append(lastScanTimeInMillis).append(", indexInformation=").append(indexInformation).append("]");
		return builder.toString();
	}
}
