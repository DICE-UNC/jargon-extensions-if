package org.irods.jargon.extensions.searchplugin;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.extensions.searchplugin.model.SearchAttributes;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This data structure is a 'mostly immutable' representation of the schema
 * information and associated attributes for search schema associated
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchIndexInventoryEntry {

	private final String endpointUrl;
	private final long lastScanTimeInMillis;
	private final Indexes indexInformation;
	private final Map<String, SearchAttributes> searchAttributesMap;
	public static final Logger log = LogManager.getLogger(SearchIndexInventoryEntry.class);

	public SearchIndexInventoryEntry(String endpointUrl, long lastScanTimeInMillis, Indexes indexInformation) {
		super();
		this.endpointUrl = endpointUrl;
		this.lastScanTimeInMillis = lastScanTimeInMillis;
		this.indexInformation = indexInformation;
		this.searchAttributesMap = new ConcurrentHashMap<String, SearchAttributes>();
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
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("SearchIndexInventoryEntry [endpointUrl=").append(endpointUrl).append(", lastScanTimeInMillis=")
				.append(lastScanTimeInMillis).append(", indexInformation=").append(indexInformation)
				.append(", searchAttributesMap=")
				.append(searchAttributesMap != null ? toString(searchAttributesMap.entrySet(), maxLen) : null)
				.append("]");
		return builder.toString();
	}

	public Map<String, SearchAttributes> getSearchAttributesMap() {
		return searchAttributesMap;
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0) {
				builder.append(", ");
			}
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

}
