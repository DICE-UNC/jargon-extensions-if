package org.irods.jargon.extensions.searchplugin.unittest;

import org.irods.jargon.extensions.searchplugin.SearchPluginDiscoveryServiceTest;
import org.irods.jargon.extensions.searchplugin.implementation.IndexInventoryUtilityTest;
import org.irods.jargon.extensions.searchplugin.implementation.TextSearchCallableTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Functional test suite for searchrequiring collaborating services and extended
 * configuration (elastic search, grid pluggable search containers running and
 * configured)
 * 
 * @author Mike Conway - NIEHS
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ IndexInventoryUtilityTest.class, SearchPluginDiscoveryServiceTest.class, TextSearchCallableTest.class })
public class SearchFunctionalTests {

}
