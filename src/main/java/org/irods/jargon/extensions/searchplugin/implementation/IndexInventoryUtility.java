/**
 * 
 */
package org.irods.jargon.extensions.searchplugin.implementation;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.irods.jargon.extensions.searchplugin.SearchPluginRegistrationConfig;
import org.irods.jargon.extensions.searchplugin.exception.SearchPluginUnavailableException;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.irodsext.jwt.JwtIssueServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility to access an index endpoint to get inventory data
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IndexInventoryUtility {

	public static final Logger log = LoggerFactory.getLogger(IndexInventoryUtility.class);

	public Indexes inventoryEndpoint(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			final String endpointUrl, final JwtIssueServiceImpl jwtIssueService)
			throws SearchPluginUnavailableException {

		log.info("inventoryEndpoint()");

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		log.info("searchPluginRegistrationConfig:{}", searchPluginRegistrationConfig);
		log.info("endpointUrl:{}", endpointUrl);

		/*
		 * Obtain a jwt for the call
		 */

		String bearerToken = jwtIssueService.issueJwtToken(searchPluginRegistrationConfig.getEndpointAccessSubject());

		StringBuilder sb = new StringBuilder();
		sb.append(endpointUrl);

		if (!endpointUrl.endsWith("/")) {
			sb.append("/");
		}

		sb.append("indexes");
		String targetUrl = sb.toString();

		log.info("accessing:{}", targetUrl);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(targetUrl);

		httpGet.addHeader(new BasicHeader("Authorization", "Bearer " + bearerToken));
		httpGet.addHeader(new BasicHeader("accept", "application/json"));

		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			log.debug("response:{}", response);

		} catch (IOException e) {
			log.error("error accessing endpoint:{}", endpointUrl, e);
			throw new SearchPluginUnavailableException();
		}

		return null; // TODO: implement

	}

}
