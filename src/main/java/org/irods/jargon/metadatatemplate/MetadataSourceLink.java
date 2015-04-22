/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import java.util.Properties;

/**
 * Represents a metadata source in an abstract fashion. It is up to the
 * individual resolver to map abstract names of metadata sources to an actual
 * metadata source, for example, use HIVE and the UAT vocabulary as a source of
 * a metadaa element)
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */
public class MetadataSourceLink {

	/**
	 * Public name for a metadata source
	 */
	private final String name = "";

	/**
	 * Optional qualifier for a metadata source, such as the location for a
	 * particular HIVE
	 */
	private final String qualifier = "";

	/**
	 * Optional properties for a source
	 */
	private final Properties properties = new Properties();

	/**
	 * 
	 */
	public MetadataSourceLink() {
	}

}
