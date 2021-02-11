package org.irods.jargon.extensions.publishingplugin.implementation;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.extensions.publishingplugin.exception.PublishingPluginUnavailableException;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublishDownloadCallable implements Callable<String> {

	private final AbstractJwtIssueService jwtIssueService;
	private final PublishDownloadRequest publishDownloadRequest;

	public static final Logger log = LoggerFactory.getLogger(PublishDownloadCallable.class);

	public PublishDownloadCallable(final AbstractJwtIssueService jwtIssueService,
			final PublishDownloadRequest publishDownloadRequest) {
		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		if (publishDownloadRequest == null) {
			throw new IllegalArgumentException("null publishDownloadRequest");
		}

		this.jwtIssueService = jwtIssueService;
		this.publishDownloadRequest = publishDownloadRequest;
	}

	@Override
	public String call() throws Exception {
		log.info("call()");
		String bearerToken = jwtIssueService.issueJwtToken(publishDownloadRequest.getPublishPrincipal());

		// formulate the query to the endpoint
		StringBuilder sb = new StringBuilder();
		sb.append(publishDownloadRequest.getEndpointUrl());

		if (publishDownloadRequest.getEndpointUrl().endsWith("/")) {
		} else {
			sb.append("/");
		}
		sb.append("publisher/");
		sb.append(URLEncoder.encode(publishDownloadRequest.getPublishPrincipal(), "UTF-8"));
		sb.append(URLEncoder.encode("/test1-metalnx-cart.dat", "UTF-8"));

		String stringJsonResponse = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(sb.toString());

		httpPost.addHeader(new BasicHeader("Authorization", "Bearer " + bearerToken));
		httpPost.addHeader(new BasicHeader("accept", "application/json"));
		httpPost.addHeader(new BasicHeader("Content-type", "application/json"));

		StringBuilder requestBody = new StringBuilder();
		requestBody.append("{");
		requestBody.append("\"additionalProperties\":{},");
		requestBody.append("\"id\":\"\"");
		requestBody.append("}");
		log.debug("requestbody:{}", requestBody);
		httpPost.setEntity(new StringEntity(requestBody.toString()));

		try {
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			log.info("statusCode:{}", statusCode);
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				log.warn("endpoint not found:{}", publishDownloadRequest.getEndpointUrl());
				throw new PublishingPluginUnavailableException();
			} else if (statusCode != HttpStatus.SC_OK) {
				log.error("error in request to endpoint:{}", publishDownloadRequest.getEndpointUrl());
				throw new PublishingPluginUnavailableException();
			}

			log.info("response:{}", response);
			HttpEntity entity = response.getEntity();
			stringJsonResponse = MiscIRODSUtils.convertStreamToString(entity.getContent());

		} catch (IOException e) {
			log.error("error accessing endpoint:{}", publishDownloadRequest.getEndpointUrl(), e);
			throw new PublishingPluginUnavailableException();
		}
		log.info("entry updated");
		return stringJsonResponse;
	}
}
