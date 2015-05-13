/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Metadata template based on displayable data elements
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormBasedMetadataTemplate extends MetadataTemplate {

	/**
	 * Describes the list of elements in the metadata template
	 */
	// TODO Should probably end up as a hashmap, not a list
	@JsonProperty("elements")
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
		this.setAuthor(mt.getAuthor());
		this.setName(mt.getName());
		this.setFqName(mt.getFqName());
		this.setDescription(mt.getDescription());
		this.setRequired(mt.isRequired());
		this.setSource(mt.getSource());
		this.setUuid(mt.getUuid());
		this.setVersion(mt.getVersion());

		for (MetadataElement me : mt.getElements()) {
			elements.add(me);
		}
	}

	public FormBasedMetadataTemplate deepCopy() {
		return new FormBasedMetadataTemplate(this);
	}

	@Override
	public String toString() {
		String nameStr = "";
		String descriptionStr = "";
		String authorStr = "";
		String sourceStr = "";
		String uuidStr = "";
		String versionStr = "";
		String requiredStr = "";
		String elementsStr = "";

		if (!this.getName().isEmpty()) {
			if (this.getFqName().isEmpty()) {
				nameStr = this.getName().toUpperCase();
			} else {
				nameStr = String.format("\n%s (%s)",
						this.getName().toUpperCase(), this.getFqName());
			}

			int tmp = nameStr.length();
			nameStr += String.format("\n%s\n",
					new String(new char[tmp]).replace('\0', '='));
		}

		if (!this.getDescription().isEmpty()) {
			descriptionStr = String.format("\"%s\"\n", this.getDescription());
		}

		if (!this.getAuthor().isEmpty()) {
			authorStr = String.format("Author: %s\n", this.getAuthor());
		}

		sourceStr = String.format("Data Source: %s\n", this.getSource());

		uuidStr = String.format("UUID: %s\n", this.getUuid());

		versionStr = String.format("Version: %s\n", this.getVersion());

		if (this.isRequired()) {
			requiredStr = "*** REQUIRED ***\n";
		}

		requiredStr += "\n";

		if (!this.getElements().isEmpty()) {
			String elemStr = "";
			for (MetadataElement e : this.getElements()) {
				elemStr += String.format("\t%s,\n", e);
			}
			elementsStr = String.format("Elements {\n%s}", elemStr);
		}

		return String.format("%s%s%s%s%s%s%s%s", nameStr, descriptionStr,
				authorStr, sourceStr, uuidStr, versionStr, requiredStr,
				elementsStr);
	}

}
