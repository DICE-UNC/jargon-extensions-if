package org.irods.jargon.extensions.publishingplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.extensions.publishingplugin.exception.PublishingPluginUnavailableException;
import org.irods.jargon.extensions.publishingplugin.implementation.PublishingPluginInventoryCallable;
import org.irods.jargon.extensions.publishingplugin.model.PublishingEndpointDescription;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to interrogate registered publishing plugin endpoints
 */
public class PublishingPluginDiscoveryService {
	
	private static final Logger log = LoggerFactory.getLogger(PublishingPluginDiscoveryService.class);

	private final PublishingPluginRegistrationConfig publishingPluginRegistrationConfig;
	private final AbstractJwtIssueService jwtIssueService;

	/**
	 * Constructor requiring configuration information
	 * 
	 */
	public PublishingPluginDiscoveryService(final PublishingPluginRegistrationConfig publishingPluginRegistrationConfig,
			AbstractJwtIssueService jwtIssueService) {
		if (publishingPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null publishingPluginRegistrationConfig");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		this.publishingPluginRegistrationConfig = publishingPluginRegistrationConfig;
		this.jwtIssueService = jwtIssueService;
	}

	public void queryEndpoints(final List<String> endpointUrls, final PublishingIndexInventory publishingInventory)
			throws PublishingPluginUnavailableException {

		log.info("queryEndpoints()");
		if (endpointUrls == null || endpointUrls.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpoint urls");
		}

		log.info("endpointsUrls:{}", endpointUrls);

		ExecutorService executor = Executors.newCachedThreadPool();
		List<Callable<PublishingEndpointDescription>> callables = new ArrayList<>();

		for (String endpointUrl : endpointUrls) {
			callables.add(new PublishingPluginInventoryCallable(publishingPluginRegistrationConfig, endpointUrl,
					jwtIssueService, publishingInventory));
		}
		
		try {
			if (publishingPluginRegistrationConfig.getEndpointAccessTimeout() > 0) {
				log.debug("getting endpoints with a timeout");
				
				executor.invokeAll(callables, publishingPluginRegistrationConfig.getEndpointAccessTimeout(),
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
