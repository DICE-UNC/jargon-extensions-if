/**
 * 
 */
package org.irods.jargon.vircoll;


/**
 * Virtual collection as stored in a JSON format
 * 
 * @author Mike Conway - DICE
 *
 */
public class ConfigurableVirtualCollection extends AbstractVirtualCollection {

	private String queryString = "";

	/**
	 * 
	 */
	public ConfigurableVirtualCollection() {
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
