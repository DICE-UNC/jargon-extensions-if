package org.irods.jargon.formbot;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.metadatatemplate.TypeEnum;

/**
 * Java representation of a formbot field
 * 
 * @author Rick Skarbez
 *
 */

public class FormBotField {
	/**
	 * Descriptive display name for field, e.g. "Creator", "Latitude"
	 */
	private String name = "";

	/**
	 * Unique identifier linking field to data object, i.e. UUID for Metadata
	 * Templates and Elements
	 */
	private String uniqueId = "";

	/**
	 * Cue or help text
	 */
	private String description = "";

	/**
	 * The type of data for the element, useful for validation and creation of
	 * user elements
	 */
	private TypeEnum type = TypeEnum.STRING;

	/**
	 * The preferred form element for display. Note that this is only a hint for
	 * the Javascript FormBotRenderer, not a guarantee of behavior.
	 */
	private FormElementEnum formElement = FormElementEnum.ANY;

	/**
	 * Contains the CURRENT value of this element.
	 */
	private String currentValue = "";
	
	/**
	 * Contains the list of parameters for this element.
	 * 
	 * These can be, for example, the endpoints of a range, or the elements of a list
	 */
	private List<String> paramList = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueID) {
		this.uniqueId = uniqueID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public FormElementEnum getFormElement() {
		return formElement;
	}

	public void setFormElement(FormElementEnum formElement) {
		this.formElement = formElement;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}

}
