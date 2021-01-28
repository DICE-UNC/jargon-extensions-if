package org.irods.jargon.extensions.exportplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.extensions.exportplugin.exception.ExportPluginUnavailableException;
import org.irods.jargon.extensions.exportplugin.implementation.PluginInventoryCallable;
import org.irods.jargon.extensions.exportplugin.model.Indexes;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to interrogate registered export plugin endpoints
 */
public class ExportPluginDiscoveryService {

	private static final Logger log = LoggerFactory.getLogger(ExportPluginDiscoveryService.class);

	private final ExportPluginRegistrationConfig exportPluginRegistrationConfig;
	private final AbstractJwtIssueService jwtIssueService;

	/**
	 * Constructor requiring configuration information
	 * 
	 */
	public ExportPluginDiscoveryService(final ExportPluginRegistrationConfig exportPluginRegistrationConfig,
			AbstractJwtIssueService jwtIssueService) {
		if (exportPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null exportPluginRegistrationConfig");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		this.exportPluginRegistrationConfig = exportPluginRegistrationConfig;
		this.jwtIssueService = jwtIssueService;
	}

	public void queryEndpoints(final List<String> endpointUrls, final ExportIndexInventory exportIndexInventory)
			throws ExportPluginUnavailableException {

		log.info("queryEndpoints()");
		if (endpointUrls == null || endpointUrls.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpoint urls");
		}

		log.info("endpointsUrls:{}", endpointUrls);

		ExecutorService executor = Executors.newCachedThreadPool();
		List<Callable<Indexes>> callables = new ArrayList<>();

		for (String endpointUrl : endpointUrls) {
			callables.add(new PluginInventoryCallable(exportPluginRegistrationConfig, endpointUrl, jwtIssueService,
					exportIndexInventory));
		}

		try {
			if (exportPluginRegistrationConfig.getEndpointAccessTimeout() > 0) {
				log.debug("getting endpoints with a timeout");

				executor.invokeAll(callables, exportPluginRegistrationConfig.getEndpointAccessTimeout(),
						TimeUnit.MILLISECONDS);
			} else {
				executor.invokeAll(callables);
			}

			awaitTerminationAfterShutdown(executor);

		} catch (InterruptedException e) {
			log.error("Runtime error pinging endpoint", e);
			throw new JargonRuntimeException("Runtime error accessing endpoint", e);
		} finally {

			executor.shutdownNow();
			log.info("shutdown finished");
		}

	}

	private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
				log.warn("all threads did not terminate successfully");
				threadPool.shutdownNow();
			}
		} catch (InterruptedException ex) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
