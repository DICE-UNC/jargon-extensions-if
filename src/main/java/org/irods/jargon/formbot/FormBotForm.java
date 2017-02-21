package org.irods.jargon.formbot;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Java representation of a Formbot form
 * 
 * @author Rick Skarbez
 *
 */

public class FormBotForm {
	/**
	 * Public name for a form. By convention, it would be the same as the name
	 * property of the object it is representing. (E.g. a Metadata Template)
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
