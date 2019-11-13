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
import org.irods.jargon.extensions.searchplugin.model.SearchAttributes;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
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
	private Gson gson = new Gson();

	public SearchAttributes inventoryAttributes(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			final String endpointUrl, final String schemaId, final AbstractJwtIssueService jwtIssueService)
			throws SearchPluginUnavailableException {

		log.info("inventoryAttributes()");

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (schemaId == null || schemaId.isEmpty()) {
			throw new IllegalArgumentException("null or empty schemaId");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		log.info("searchPluginRegistrationConfig:{}", searchPluginRegistrationConfig);
		log.info("endpointUrl:{}", endpointUrl);
		log.info("schemaId:{}", schemaId);

		/*
		 * Obtain a jwt for the call
		 */

		String bearerToken = jwtIssueService.issueJwtToken(searchPluginRegistrationConfig.getEndpointAccessSubject());

		StringBuilder sb = new StringBuilder();
		sb.append(endpointUrl);

		if (!endpointUrl.endsWith("/")) {
			sb.append("/");
		}

		sb.append("attributes/");
		sb.append(schemaId);
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
			SearchAttributes searchAttributes = gson.fromJson(reader, SearchAttributes.class);

			// objectMapper.readValue(entity.getContent(), Indexes.class);
			log.debug("indexes:{}", searchAttributes);
			return searchAttributes;
		} catch (IOException e) {
			log.error("error accessing endpoint:{}", endpointUrl, e);
			throw new SearchPluginUnavailableException();
		}

	}

	/**
	 * Query the given endpoint and return a data structure that describes the
	 * schema supported at this endpoint.
	 * 
	 * @param searchPluginRegistrationConfig {@link SearchPluginRegistrationConfig}
	 *                                       that describes the various config
	 *                                       options for plugin search
	 * @param endpointUrl                    {@code String} with the URL that
	 * @param jwtIssueService                {@link AbstractJwtIssueService} which
	 *                                       is a dependency required to generate a
	 *                                       JWT to talk to an endpoint
	 * @return {@link Indexes} that describes the schema supported at the endpoint
	 * @throws SearchPluginUnavailableException {@link SearchPluginUnavailableException}
	 *                                          if any error contacting the plugin
	 */
	public Indexes inventoryEndpoint(final SearchPluginRegistrationConfig searchPluginRegistrationConfig,
			final String endpointUrl, final AbstractJwtIssueService jwtIssueService)
			throws SearchPluginUnavailableException {

		log.info("inventoryEndpoint()");

		if (searchPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null searchPluginRegistrationConfig");
		}

		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
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
