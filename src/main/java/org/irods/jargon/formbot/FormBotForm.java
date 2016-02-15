package org.irods.jargon.formbot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.irods.jargon.metadatatemplate.ExporterEnum;
import org.irods.jargon.metadatatemplate.MetadataElement;
import org.irods.jargon.metadatatemplate.MetadataTemplate;
import org.irods.jargon.metadatatemplate.SourceEnum;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Java representation of a formbot form
 * 
 * @author Rick Skarbez
 *
 */

public class FormBotForm {
	/**
	 * Public name for a metadata template, represents a generic template of a
	 * type (e.g. Dublin Core) of which there may be multiple unique
	 * representations.
	 * 
	 * By convention, the template name is the same as the filename, for
	 * example, a template named DC would be stored as a file named DC.json.
	 */
	@JsonProperty("name")
	private String name = "";

	/**
	 * Unique identifier linking field to data object, i.e. UUID for Metadata
	 * Templates and Elements
	 */
	private String uniqueId = "";

	/**
	 * description, helpful text
	 */
	@JsonProperty("description")
	private String description = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private List<FormBotField> fields = new ArrayList<FormBotField>();

	public List<FormBotField> getFields() {
		return fields;
	}

	public FormBotForm() {
	}

	public FormBotForm(FormBotForm fb) {
		this.setName(fb.getName());
		this.setUniqueId(fb.getUniqueId());
		this.setDescription(fb.getDescription());
	}

}
