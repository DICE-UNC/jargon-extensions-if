/**
 * 
 */
package org.irods.jargon.extensions.searchplugin;

import org.irods.jargon.extensions.searchplugin.exception.SearchPluginUnavailableException;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to interrogate registered search plugin endpoints
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SearchPluginDiscoveryService {

	private final SearchPluginRegistrationConfig searchPluginRegistrationConfig;
	public static final Logger log = LoggerFactory.getLogger(SearchPluginDiscoveryService.class);

	/**
	 * Constructor requiring configuration information
	 * 
	 * @param searchPluginRegistrationConfig {@link SearchPluginRegistrationConfig}
	 */
	public SearchPluginDiscoveryService(final SearchPluginRegistrationConfig searchPluginRegistrationConfig) {
		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		this.searchPluginRegistrationConfig = searchPluginRegistrationConfig;
	}

	/**
	 * Given an endpoint URL that is a search plugin, gather the descriptive
	 * metadata about that search service.
	 * 
	 * @param endpointUrl {@link String} with the URL of the service, this should be
	 *                    down to the version and leave off the /indexes or other
	 *                    resource endpoints, these will be added by underlying
	 *                    services
	 * @return {@link Indexes} that describes the given singular endpoint
	 * @throws SearchPluginUnavailableException {@link SearchPluginUnavailableException}
	 * 
	 */
	public Indexes queryEndpoint(final String endpointUrl) throws SearchPluginUnavailableException {

		log.info("queryEndpoint()");
		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpoint url");
		}

		log.info("endpointUrl:{}", endpointUrl);
		return null;

	}

}
