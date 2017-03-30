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
public abstract class ConfigurableVirtualCollection extends AbstractVirtualCollection {

	/**
	 *
	 */
	private String queryString = "";

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(final String queryString) {
		this.queryString = queryString;
	}

}
