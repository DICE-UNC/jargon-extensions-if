package org.irods.jargon.extensions.searchplugin.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.irods.jargon.extensions.searchplugin.unittest.TestConstants;
import org.irods.jargon.irodsext.jwt.AbstractJwtIssueService;
import org.irods.jargon.irodsext.jwt.JwtIssueServiceImpl;
import org.irods.jargon.irodsext.jwt.JwtServiceConfig;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextSearchCallableTest {

	private static Properties testingProperties = new Properties();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
	}

	@Test
	public void testTextSearch() throws Exception {
		String endpointList = (String) testingProperties.get(TestConstants.ENDPOINT_REGISTRY_LIST);
		String jwtAlgo = (String) testingProperties.get(TestConstants.ENDPOINT_JWT_ALGO);
		String jwtIssuer = (String) testingProperties.get(TestConstants.ENDPOINT_JWT_ISSUER);
		String jwtSecret = (String) testingProperties.get(TestConstants.ENDPOINT_JWT_SECRET);
		String[] endpoints = endpointList.split(",");
		List<String> derivedEndpoints = new ArrayList<>();
		for (String endpoint : endpoints) {
			derivedEndpoints.add(endpoint);
		}

		JwtServiceConfig jwtServiceConfig = new JwtServiceConfig();
		jwtServiceConfig.setAlgo(jwtAlgo);
		jwtServiceConfig.setIssuer(jwtIssuer);
		jwtServiceConfig.setSecret(jwtSecret);

		AbstractJwtIssueService jwtIssueService = new JwtIssueServiceImpl(jwtServiceConfig);
		TextSearchRequest request = new TextSearchRequest();
		request.setEndpointUrl(derivedEndpoints.get(0));
		request.setSearchPrincipal("test");
		request.setSearchSchema("projects");
		request.setSearchText("genome");
		TextSearchCallable textSearchCallable = new TextSearchCallable(jwtIssueService, request);
		String json = textSearchCallable.call();
		Assert.assertFalse("json empty", json.isEmpty());

	}

}
