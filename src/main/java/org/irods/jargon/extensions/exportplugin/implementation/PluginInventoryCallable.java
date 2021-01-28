package org.irods.jargon.extensions.exportplugin.implementation;

import java.util.concurrent.Callable;

import org.irods.jargon.extensions.exportplugin.ExportIndexInventory;
import org.irods.jargon.extensions.exportplugin.ExportPluginRegistrationConfig;
import org.irods.jargon.extensions.exportplugin.model.Indexes;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginInventoryCallable implements Callable<Indexes>{

	public static final Logger log = LoggerFactory.getLogger(PluginInventoryCallable.class);

	private final ExportPluginRegistrationConfig exportPluginRegistrationConfig;
	private final String endpointUrl;
	private final AbstractJwtIssueService jwtIssueService;
	private final ExportIndexInventory exportIndexInventory;

	public PluginInventoryCallable(final ExportPluginRegistrationConfig exportPluginRegistrationConfig,
			final String endpointUrl, final AbstractJwtIssueService jwtIssueService,
			final ExportIndexInventory exportIndexInventory) {

		if (exportPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null exportPluginRegistrationConfig");
		}
		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}
		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}
		if (exportIndexInventory == null) {
			throw new IllegalArgumentException("null exportIndexInventory");
		}

		this.exportPluginRegistrationConfig = exportPluginRegistrationConfig;
		this.endpointUrl = endpointUrl;
		this.jwtIssueService = jwtIssueService;
		this.exportIndexInventory = exportIndexInventory;
	}

	@Override
	public Indexes call() throws Exception {
		// TODO Auto-generated method stub
		log.info("call()");
		
		return null;
	}
}
