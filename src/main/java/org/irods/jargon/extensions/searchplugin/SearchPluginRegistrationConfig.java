/**
 * 
 */
package org.irods.jargon.extensions.searchplugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration used to gather and access registered search plugin endpoints
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchPluginRegistrationConfig {

	/**
	 * List of fully qualified URLS to the endpoint of the search API, this should
	 * be down to the version number and leave off the actual resource such as
	 * /indexes. For example, https://foo.com/v1/. This is used to poll each of the
	 * endpoints to gather a list of indexes and attributes
	 */
	private List<String> endpointRegistryList = new ArrayList<String>();

	/**
	 * Subject to use when creating a JWT to access the back end service
	 */
	private String endpointAccessSubject = "searchPlugin";

	/**
	 * Timeout value to use for access descriptive methods (e.g. schema
	 * description). Leave at 0 for no time-out. This is distinct from a timeout
	 * value for search endpoints.
	 */
	private long endpointAccessTimeout = 5000L;

	/**
	 * TImeout value to use for search methods. Leave at 0 for no time-out.
	 */
	private long endpointSearchAccessTimeout = 30000L;

	/**
	 * Algo to use to create JWT
	 */
	private String jwtAlgo = "";

	/**
	 * Issuer of jwt requests to endpoints
	 */
	private String jwtIssuer = "";

	/**
	 * Shared secret key for jwt requests to endpoints
	 */
	private String jwtSecret = "";

	public List<String> getEndpointRegistryList() {
		return endpointRegistryList;
	}

	public void setEndpointRegistryList(List<String> endpointRegistryList) {
		this.endpointRegistryList = endpointRegistryList;
	}

	public String getEndpointAccessSubject() {
		return endpointAccessSubject;
	}

	public void setEndpointAccessSubject(String endpointAccessSubject) {
		this.endpointAccessSubject = endpointAccessSubject;
	}

	public long getEndpointAccessTimeout() {
		return endpointAccessTimeout;
	}

	public void setEndpointAccessTimeout(long endpointAccessTimeout) {
		this.endpointAccessTimeout = endpointAccessTimeout;
	}

	public long getEndpointSearchAccessTimeout() {
		return endpointSearchAccessTimeout;
	}

	public void setEndpointSearchAccessTimeout(long endpointSearchAccessTimeout) {
		this.endpointSearchAccessTimeout = endpointSearchAccessTimeout;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("SearchPluginRegistrationConfig [endpointRegistryList=")
				.append(endpointRegistryList != null
						? endpointRegistryList.subList(0, Math.min(endpointRegistryList.size(), maxLen))
						: null)
				.append(", endpointAccessSubject=").append(endpointAccessSubject).append(", endpointAccessTimeout=")
				.append(endpointAccessTimeout).append(", endpointSearchAccessTimeout=")
				.append(endpointSearchAccessTimeout).append(", jwtAlgo=").append(jwtAlgo).append(", jwtIssuer=")
				.append(jwtIssuer).append("]");
		return builder.toString();
	}

	public String getJwtAlgo() {
		return jwtAlgo;
	}

	public void setJwtAlgo(String jwtAlgo) {
		this.jwtAlgo = jwtAlgo;
	}

	public String getJwtIssuer() {
		return jwtIssuer;
	}

	public void setJwtIssuer(String jwtIssuer) {
		this.jwtIssuer = jwtIssuer;
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	/**
	 * Handy method to return the single line, comma delimited set of endpoints into
	 * a string list
	 * 
	 * @param endpoints {@code String} with the endpoint property value
	 * @return {@code List<String>} with parsed endpoints
	 */
	public static List<String> convertEndpointListToArray(final String endpoints) {
		if (endpoints == null || endpoints.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpoints");
		}
		List<String> endpointList = new ArrayList<>();
		String[] split = endpoints.split(",");
		for (String item : split) {
			endpointList.add(item);
		}

		return endpointList;

	}

}
