package org.irods.jargon.extensions.searchplugin.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.irods.jargon.extensions.searchplugin.SearchPluginRegistrationConfig;
import org.irods.jargon.extensions.searchplugin.model.Indexes;
import org.irods.jargon.extensions.searchplugin.unittest.TestConstants;
import org.irods.jargon.irodsext.jwt.JwtIssueServiceImpl;
import org.irods.jargon.irodsext.jwt.JwtServiceConfig;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IndexInventoryUtilityTest {

	private static Properties testingProperties = new Properties();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
	}

	@Test
	public void testInventoryEndpoint() throws Exception {
		String endpointList = (String) testingProperties.get(TestConstants.ENDPOINT_REGISTRY_LIST);
		String jwtAlgo = (String) testingProperties.get(TestConstants.ENDPOINT_JWT_ALGO);
		String jwtIssuer = (String) testingProperties.get(TestConstants.ENDPOINT_JWT_ISSUER);
		String jwtSecret = (String) testingProperties.get(TestConstants.ENDPOINT_JWT_SECRET);
		String testSubject = (String) testingProperties.get(TestConstants.ENDPOINT_ACCESS_SUBJECT);
		SearchPluginRegistrationConfig registrationConfig = new SearchPluginRegistrationConfig();
		String[] endpoints = endpointList.split(",");
		List<String> derivedEndpoints = new ArrayList<>();
		for (String endpoint : endpoints) {
			derivedEndpoints.add(endpoint);
		}

		registrationConfig.setEndpointAccessSubject(testSubject);
		registrationConfig.setEndpointRegistryList(derivedEndpoints);
		registrationConfig.setJwtAlgo(jwtAlgo);
		registrationConfig.setJwtIssuer(jwtIssuer);
		registrationConfig.setJwtSecret(jwtSecret);

		JwtServiceConfig jwtServiceConfig = new JwtServiceConfig();
		jwtServiceConfig.setAlgo(jwtAlgo);
		jwtServiceConfig.setIssuer(jwtIssuer);
		jwtServiceConfig.setSecret(jwtSecret);

		JwtIssueServiceImpl jwtIssueService = new JwtIssueServiceImpl(jwtServiceConfig);
		IndexInventoryUtility indexInventoryUtil = new IndexInventoryUtility();
		Indexes index = indexInventoryUtil.inventoryEndpoint(registrationConfig, derivedEndpoints.get(0),
				jwtIssueService);
		Assert.assertNotNull("null index", index);
		// the test assumes, if you are running it,that there should be at least one
		// index entry
		Assert.assertFalse("no indexes found", index.getIndexes().isEmpty());

	}

}
