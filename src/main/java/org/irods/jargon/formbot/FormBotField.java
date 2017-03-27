package org.irods.jargon.formbot;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.metadatatemplate.ElementTypeEnum;

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
	private ElementTypeEnum type = ElementTypeEnum.RAW_STRING;

	/**
	 * The preferred form element for display. Note that this is only a hint for
	 * the Javascript FormBotRenderer, not a guarantee of behavior.
	 */
	private FormElementEnum formElement = FormElementEnum.ANY;

	/**
	 * Contains the CURRENT value of this element.
	 */
	private List<String> currentValue = new ArrayList<String>();
	
	/**
	 * Contains the DISPLAY value of this element.
	 */
	private List<String> displayValue = new ArrayList<String>();
	
	/**
	 * Contains the DEFAULT value of this element.
	 */
	private List<String> defaultValue = new ArrayList<String>();
	
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

	public ElementTypeEnum getType() {
		return type;
	}

	public void setType(ElementTypeEnum type) {
		this.type = type;
	}

	public FormElementEnum getFormElement() {
		return formElement;
	}

	public void setFormElement(FormElementEnum formElement) {
		this.formElement = formElement;
	}

	public List<String> getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(List<String> currentValue) {
		this.currentValue = currentValue;
	}
	
	public List<String> getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(List<String> displayValue) {
		this.displayValue = displayValue;
	}
	
	public List<String> getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(List<String> defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}
}
