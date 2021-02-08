package org.irods.jargon.extensions.publishingplugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Represents the known publishing and their endpoints 
 */

public class PublishingIndexInventory {
	
	private long lastScanTimeInMillis = 0L;
	private final Map<String, PublishingInventoryEntry> publishInventoryEntries;
	
	public PublishingIndexInventory() {
		this.publishInventoryEntries = new ConcurrentHashMap<String, PublishingInventoryEntry>();
	}
	
	public synchronized long getLastScanTimeInMillis() {
		return lastScanTimeInMillis;
	}

	public synchronized void setLastScanTimeInMillis(long lastScanTimeInMillis) {
		this.lastScanTimeInMillis = lastScanTimeInMillis;
	}
	
	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PublishingInventory [lastScanTimeInMillis=").append(lastScanTimeInMillis)
				.append(", getLastScanTimeInMillis()=").append(getLastScanTimeInMillis()).append(", getClass()=")
				.append(getClass()).append(", hashCode()=").append(hashCode()).append(", toString()=")
				.append(super.toString()).append("]");
		return builder.toString();
	}
	
	public Map<String, PublishingInventoryEntry> getPublishingInventoryEntries() {
		return publishInventoryEntries;
	}
}
