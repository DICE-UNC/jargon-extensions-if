/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Describes a metadata element in a template
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MetadataElement {

	/**
	 * Descriptive display name for metadata element, e.g. Author
	 * <p/>
	 * XXX @ is a reserved character; if @ is present as the first character,
	 * that indicates that information for this metadata element will be
	 * retrieved from a special list of elements. This is NOT YET SUPPORTED.
	 */
	private String elementName = "";

	/**
	 * i18n property for element name, e.g. dc.author
	 */
	private String i18nElementName = "";

	/**
	 * Other name(s) this attribute could be known as in, e.g. a data
	 * dictionary.
	 * <p/>
	 * XXX NOT YET SUPPORTED
	 */
	private List<String> aliases = new ArrayList<String>();

	/**
	 * Cue or help text
	 */
	private String description = "";

	/**
	 * Cue or help text as an i18n property
	 */
	private String i18nDescription = "";

	/**
	 * Indicates whether this value is required or optional
	 */
	private boolean required = false;

	/**
	 * The type of data for the element, useful for validation and creation of
	 * user elements
	 */
	private TypeEnum type = TypeEnum.STRING;

	/**
	 * If options are specified, then entry will not be free form, and interface
	 * components should display a list or dropdown.
	 * <p/>
	 * This is used with the <code>ValidationStyleEnum</code>, so that a range
	 * would have two options, and a 'in list' would have an arbitrary list of
	 * options. The options are strings by default, and may be coerced into a
	 * type in combination with the <code>TypeEnum</code> value.
	 */
	private List<String> validationOptions = new ArrayList<String>();

	/**
	 * Enum indicates the kind of validation to do (by type, or in combination
	 * with the <code>validationOptions</code> to derive a range between two
	 * values or a list that the entry must be part of
	 */
	private ValidationStyleEnum validationStyle = ValidationStyleEnum.DEFAULT;

	/**
	 * Defines a default value (if desired) for this element.
	 * <p/>
	 * Note that the default value is stored as a string; no validation is
	 * provided at compile time. If the specified value fails validation, this
	 * will only be detected at runtime.
	 */
	private String defaultValue = "";

	/**
	 * Contains the CURRENT value of this element.
	 */
	private String currentValue = "";

	/**
	 * Provides hints to the interface builder about how to display this
	 * element. Note that these are not guaranteed to be supported by every user
	 * interface in every situation.
	 * <p/>
	 * XXX NOT YET DESIGNED OR IMPLEMENTED
	 */
	private List<String> renderingOptions = new ArrayList<String>();

	/**
	 * 
	 */

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getI18nElementName() {
		return i18nElementName;
	}

	public void setI18nElementName(String i18nElementName) {
		this.i18nElementName = i18nElementName;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getI18nDescription() {
		return i18nDescription;
	}

	public void setI18nDescription(String i18nDescription) {
		this.i18nDescription = i18nDescription;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public List<String> getValidationOptions() {
		return validationOptions;
	}

	public void setValidationOptions(List<String> validationOptions) {
		this.validationOptions = validationOptions;
	}

	public ValidationStyleEnum getValidationStyle() {
		return validationStyle;
	}

	public void setValidationStyle(ValidationStyleEnum validationStyle) {
		this.validationStyle = validationStyle;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public List<String> getRenderingOptions() {
		return renderingOptions;
	}

	public void setRenderingOptions(List<String> renderingOptions) {
		this.renderingOptions = renderingOptions;
	}
	
	public MetadataElement() {
	}
}
