/**
 * 
 */
package org.irods.jargon.extensions.searchplugin.implementation;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.extensions.searchplugin.exception.SearchPluginUnavailableException;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Callable unit of work that accesses an endpoint to gather information on the
 * capabilities of the target index
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class TextSearchCallable implements Callable<String> {

	private final AbstractJwtIssueService jwtIssueService;
	private final TextSearchRequest textSearchRequest;

	public static final Logger log = LoggerFactory.getLogger(TextSearchCallable.class);

	/**
	 * Callable to execute a text search
	 * 
	 * @param jwtIssueService   {@link AbstractJwtIssueService} to create bearer
	 *                          tokens
	 * @param textSearchRequest {@link TextSearchRequest} encapsulating the search
	 */
	public TextSearchCallable(final AbstractJwtIssueService jwtIssueService,
			final TextSearchRequest textSearchRequest) {

		if (jwtIssueService == null) {
			throw new IllegalArgumentException("null jwtIssueService");
		}

		if (textSearchRequest == null) {
			throw new IllegalArgumentException("null textSearchRequest");
		}

		this.textSearchRequest = textSearchRequest;
		this.jwtIssueService = jwtIssueService;
	}

	@Override
	public String call() throws Exception {
		log.info("call()");

		log.debug("principal for call:{}", textSearchRequest);
		String bearerToken = jwtIssueService.issueJwtToken(textSearchRequest.getSearchPrincipal());

		// formulate the query to the endpoint

		StringBuilder sb = new StringBuilder();
		sb.append(textSearchRequest.getEndpointUrl());

		if (textSearchRequest.getEndpointUrl().endsWith("/")) {
		} else {
			sb.append("/");
		}

		sb.append("search?");
		sb.append("index_name=");
		sb.append(URLEncoder.encode(textSearchRequest.getSearchSchema(), "UTF-8"));
		sb.append("&search_query=");
		sb.append(URLEncoder.encode(textSearchRequest.getSearchText(), "UTF-8"));

		String stringJsonResponse = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(sb.toString());

		httpPost.addHeader(new BasicHeader("Authorization", "Bearer " + bearerToken));
		httpPost.addHeader(new BasicHeader("accept", "application/json"));

		try {
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			log.info("statusCode:{}", statusCode);
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				log.warn("endpoint not found:{}", textSearchRequest.getEndpointUrl());
				throw new SearchPluginUnavailableException();
			} else if (statusCode != HttpStatus.SC_OK) {
				log.error("error in request to endpoint:{}", textSearchRequest.getEndpointUrl());
				throw new SearchPluginUnavailableException();
			}

			log.debug("response:{}", response);
			HttpEntity entity = response.getEntity();
			stringJsonResponse = MiscIRODSUtils.convertStreamToString(entity.getContent());
			log.info("stringJsonResponse:{}", stringJsonResponse);

		} catch (IOException e) {
			log.error("error accessing endpoint:{}", textSearchRequest.getEndpointUrl(), e);
			throw new SearchPluginUnavailableException();
		}

		log.info("entry updated");
		return stringJsonResponse;
	}

}
