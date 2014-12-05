/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.List;

/**
 * Metadata template based on displayable data elements
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */
public class FormBasedMetadataTemplate extends MetadataTemplate {

	/**
	 * Describes the list of elements in the metadata template
	 */
	private final List<MetadataElement> metadataElements = new ArrayList<MetadataElement>();

	/**
	 * 
	 */
	public FormBasedMetadataTemplate() {

	}

}
