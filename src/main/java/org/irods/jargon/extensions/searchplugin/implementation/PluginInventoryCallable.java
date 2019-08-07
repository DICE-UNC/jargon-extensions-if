/**
 * 
 */
package org.irods.jargon.extensions.searchplugin.implementation;

import java.util.concurrent.Callable;

import org.irods.jargon.extensions.searchplugin.SearchPluginRegistrationConfig;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.irodsext.jwt.JwtIssueServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Callable unit of work that accesses an endpoint to gather information on the
 * capabilities of the target index
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class PluginInventoryCallable implements Callable<Indexes> {

	private final SearchPluginRegistrationConfig searchPluginRegistrationConfig;
	private final String endpointUrl;
	private final JwtIssueServiceImpl jwtIssueService;

	public static final Logger log = LoggerFactory.getLogger(PluginInventoryCallable.class);

	public PluginInventoryCallable(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			final String endpointUrl, final JwtIssueServiceImpl jwtIssueService) {

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		this.searchPluginRegistrationConfig = searchPluginRegistrationConfig;
		this.endpointUrl = endpointUrl;
		this.jwtIssueService = jwtIssueService;
	}

	@Override
	public Indexes call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
