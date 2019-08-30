/**
 * 
 */
package org.irods.jargon.extensions.searchplugin.implementation;

/**
 * @author Mike Conway - NIEHS
 *
 */
public class TextSearchRequest {

	/**
	 * Principal for the search (who is actually calling search, this should be the
	 * iRODS account)
	 */
	private String searchPrincipal = "";

	/**
	 * Free text search
	 */
	private String searchText = "";
	/**
	 * Schema id from index inventory
	 */
	private String searchSchema = "";
	/**
	 * Endpoint that will do the search
	 */
	private String endpointUrl = "";
	/**
	 * Optional paging offset (if supported)
	 */
	private int offset = 0;
	/**
	 * Optional record limit (if supported)
	 */
	private int limit = 0;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchSchema() {
		return searchSchema;
	}

	public void setSearchSchema(String searchSchema) {
		this.searchSchema = searchSchema;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TextSearchRequest [searchPrincipal=").append(searchPrincipal).append(", searchText=")
				.append(searchText).append(", searchSchema=").append(searchSchema).append(", endpointUrl=")
				.append(endpointUrl).append(", offset=").append(offset).append(", limit=").append(limit).append("]");
		return builder.toString();
	}

	public String getSearchPrincipal() {
		return searchPrincipal;
	}

	public void setSearchPrincipal(String searchPrincipal) {
		this.searchPrincipal = searchPrincipal;
	}

}
