/**
 * 
 */
package org.irods.jargon.extensions.searchplugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents the known search indexes and their endpoints.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchIndexInventory {

	private long lastScanTimeInMillis = 0L;
	private final Map<String, SearchIndexInventoryEntry> indexInventoryEntries;

	public SearchIndexInventory() {
		this.indexInventoryEntries = new ConcurrentHashMap<String, SearchIndexInventoryEntry>();
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
		builder.append("SearchIndexInventory [lastScanTimeInMillis=").append(lastScanTimeInMillis)
				.append(", getLastScanTimeInMillis()=").append(getLastScanTimeInMillis()).append(", getClass()=")
				.append(getClass()).append(", hashCode()=").append(hashCode()).append(", toString()=")
				.append(super.toString()).append("]");
		return builder.toString();
	}

	public Map<String, SearchIndexInventoryEntry> getIndexInventoryEntries() {
		return indexInventoryEntries;
	}

}
