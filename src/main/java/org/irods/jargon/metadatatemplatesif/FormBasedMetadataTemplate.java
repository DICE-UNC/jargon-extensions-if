/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Metadata template based on displayable data elements
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class FormBasedMetadataTemplate extends MetadataTemplate {

	/**
	 * Describes the list of elements in the metadata template
	 */
	private List<MetadataElement> elements = new ArrayList<MetadataElement>();

	public List<MetadataElement> getElements() {
		return elements;
	}

	/**
	 * 
	 */
	public FormBasedMetadataTemplate() {

	}
	
	public FormBasedMetadataTemplate(FormBasedMetadataTemplate mt) {
		this.setName(mt.getName());
		
		for (MetadataElement me: mt.getElements()) {
			elements.add(me);
		}
	}

}
