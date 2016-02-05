/**
 * 
 */
package org.irods.jargon.vircoll;

import org.irods.jargon.vircoll.AbstractVirtualCollection;

/**
 * Virtual collection as stored in a JSON format
 * 
 * @author Mike Conway - DICE
 *
 */
public abstract class ConfigurableVirtualCollection extends AbstractVirtualCollection {

	/**
	 * 
	 */
	private String queryString = "";	
	
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigurableVirtualCollection [");
		builder.append(super.toString());
		builder.append(", queryString=");
		builder.append(queryString);
		builder.append("]");
		return builder.toString();
	}
}
