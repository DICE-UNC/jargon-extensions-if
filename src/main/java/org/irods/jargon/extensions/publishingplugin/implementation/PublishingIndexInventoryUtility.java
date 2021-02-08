package org.irods.jargon.extensions.publishingplugin.implementation;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.irods.jargon.extensions.publishingplugin.PublishingPluginRegistrationConfig;
import org.irods.jargon.extensions.publishingplugin.exception.PublishingPluginUnavailableException;
import org.irods.jargon.extensions.publishingplugin.model.PublishingEndpointDescription;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/*
 * Utility to access an publishing endpoint to get inventory data
 */

public class PublishingIndexInventoryUtility {
	public static final Logger log = LoggerFactory.getLogger(PublishingIndexInventoryUtility.class);
	private Gson gson = new Gson();

	public PublishingEndpointDescription inventoryEndpoint(
			final PublishingPluginRegistrationConfig publishingPluginRegistrationConfig, final String endpointUrl,
			final AbstractJwtIssueService jwtIssueService) throws PublishingPluginUnavailableException {

		log.info("inventoryEndpoint");

		if (publishingPluginRegistrationConfig == null) {
			throw new IllegalArgumentException("null publishingPluginRegistrationConfig");
		}
		if (endpointUrl == null || endpointUrl.isEmpty()) {
			throw new IllegalArgumentException("null or empty endpointUrl");
		}

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}
		log.info("publishingPluginRegistrationConfig:{}", publishingPluginRegistrationConfig);
		log.info("endpointUrl:{}", endpointUrl);

		// get a jwt for the call

		String bearerToken = jwtIssueService
				.issueJwtToken(publishingPluginRegistrationConfig.getEndpointAccessSubject());

		StringBuilder sb = new StringBuilder();
		sb.append(endpointUrl);

		if (!endpointUrl.endsWith("/")) {
			sb.append("/");
		}

		sb.append("info");
		String targetUrl = sb.toString();

		log.info("accessing:{}", targetUrl);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(targetUrl);
		
		httpGet.addHeader(new BasicHeader("Authorization", "Bearer " + bearerToken));
		httpGet.addHeader(new BasicHeader("accept", "application/json"));

		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				log.warn("endpoint not found:{}", targetUrl);
				throw new PublishingPluginUnavailableException();
			} else if (statusCode != HttpStatus.SC_OK) {
				log.error("error in request to endpoint:{}", targetUrl);
			}
			log.debug("response:{}", response);
			HttpEntity entity = response.getEntity();
			InputStreamReader reader = new InputStreamReader(entity.getContent());

			PublishingEndpointDescription publishingEndpointDescription = gson.fromJson(reader,
					PublishingEndpointDescription.class);
			log.info("publishingEndpointDescription:{}", publishingEndpointDescription);
			return publishingEndpointDescription;

		} catch (IOException e) {
			log.error("error accessing endpoint:{}", endpointUrl, e);
			throw new PublishingPluginUnavailableException();
		}

	}
}
