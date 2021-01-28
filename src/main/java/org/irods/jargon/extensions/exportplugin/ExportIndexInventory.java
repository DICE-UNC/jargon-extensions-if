package org.irods.jargon.extensions.exportplugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Represents the known export indexes and their endpoints 
 */
public class ExportIndexInventory {
	
	private long lastScanTimeInMillis = 0L;
	private final Map<String, ExportIndexInventoryEntry> indexInventoryEntries;
	
	public ExportIndexInventory() {
		this.indexInventoryEntries = new ConcurrentHashMap<String, ExportIndexInventoryEntry>();
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
		builder.append("ExportIndexInventory [lastScanTimeInMillis=").append(lastScanTimeInMillis)
				.append(", getLastScanTimeInMillis()=").append(getLastScanTimeInMillis()).append(", getClass()=")
				.append(getClass()).append(", hashCode()=").append(hashCode()).append(", toString()=")
				.append(super.toString()).append("]");
		return builder.toString();
	}
	
	public Map<String, ExportIndexInventoryEntry> getIndexInventoryEntries() {
		return indexInventoryEntries;
	}
}
