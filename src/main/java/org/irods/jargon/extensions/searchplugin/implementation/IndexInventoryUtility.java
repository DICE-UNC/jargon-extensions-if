/**
 * 
 */
package org.irods.jargon.extensions.searchplugin.implementation;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
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

import com.google.gson.Gson;

/**
 * Utility to access an index endpoint to get inventory data
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IndexInventoryUtility {

	public static final Logger log = LoggerFactory.getLogger(IndexInventoryUtility.class);
	// private ObjectMapper objectMapper = new ObjectMapper();
	private Gson gson = new Gson();

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
			int statusCode = response.getStatusLine().getStatusCode();
			log.info("statusCode:{}", statusCode);
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				log.warn("endpoint not found:{}", targetUrl);
				throw new SearchPluginUnavailableException();
			} else if (statusCode != HttpStatus.SC_OK) {
				log.error("error in request to endpoint:{}", targetUrl);
				throw new SearchPluginUnavailableException();
			}

			log.debug("response:{}", response);
			HttpEntity entity = response.getEntity();
			InputStreamReader reader = new InputStreamReader(entity.getContent());
			Indexes indexes = gson.fromJson(reader, Indexes.class);

			// objectMapper.readValue(entity.getContent(), Indexes.class);
			log.debug("indexes:{}", indexes);
			return indexes;
		} catch (IOException e) {
			log.error("error accessing endpoint:{}", endpointUrl, e);
			throw new SearchPluginUnavailableException();
		}

	}

}
