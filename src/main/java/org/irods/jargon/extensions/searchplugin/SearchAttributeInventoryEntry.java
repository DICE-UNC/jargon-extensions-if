/**
 * 
 */
package org.irods.jargon.extensions.searchplugin;

import org.irods.jargon.extensions.searchplugin.model.IndexSearchAttributes;

/**
 * Represents a set of search attriburtes associated with one schema at one
 * endpoint (searches are unique by endpoint/schema id)
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchAttributeInventoryEntry {
	/**
	 * unique id for this schema within the endpoint
	 */
	private final String schemaId;

	/**
	 * Returned model of attributes associated with the given schema. While this
	 * model is not internally synchronized, it is treated here as an immutable
	 * entry and is only accessed in read mode once initialized in the concurrent
	 * safe collection of gathered attributes
	 */
	private final IndexSearchAttributes indexSearchAttributes;

	/**
	 * Constructor for (mostly) immutable instance
	 * 
	 * @param schemaId              {@code String} with the unique id for this
	 *                              search schema
	 * @param indexSearchAttributes {@link IndexSearchAttributes} retrieved for this
	 *                              schema
	 */
	private SearchAttributeInventoryEntry(String schemaId, IndexSearchAttributes indexSearchAttributes) {
		super();
		this.schemaId = schemaId;
		this.indexSearchAttributes = indexSearchAttributes;
	}

	public String getSchemaId() {
		return schemaId;
	}

	public IndexSearchAttributes getIndexSearchAttributes() {
		return indexSearchAttributes;
	}

	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchAttributeInventoryEntry [schemaId=").append(schemaId).append(", indexSearchAttributes=")
				.append(indexSearchAttributes).append("]");
		return builder.toString();
	}

}
