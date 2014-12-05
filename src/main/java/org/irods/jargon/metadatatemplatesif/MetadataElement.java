/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a metadata element in a template
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */
public class MetadataElement {

	/**
	 * Descriptive display name for metadata element, e.g. Author
	 */
	private final String elementName = "";

	/**
	 * i18n property for element name, e.g. dc.author
	 */
	private final String i18nElementName = "";

	/**
	 * Cue or help text
	 */
	private final String description = "";

	/**
	 * Cue or help text as an i18n property
	 */
	private final String i18nDescription = "";

	/**
	 * Indicates whether this value is required or optional
	 */
	private final boolean required = false;

	/**
	 * The type of data for the element, useful for validation and creation of
	 * user elements
	 */
	private final TypeEnum type = TypeEnum.STRING;

	/**
	 * If options are specified, then entry will not be free form, and interface
	 * components should display a list or dropdown
	 * <p/>
	 * This is used with the <code>ValidationStyleEnum</code>, so that a range
	 * would have two options, and a 'in list' would have an arbitrary list of
	 * options. The options are strings by default, and may be coerced into a
	 * type in combination with the <code>TypeEnum</code> value.
	 * 
	 */
	private final List<String> options = new ArrayList<String>();

	/**
	 * Enum indicates the kind of validation to do (by type, or in combination
	 * with the <code>options</code> to derive a range between two values or a
	 * list that the entry must be part of
	 */
	private final ValidationStyleEnum validationStyle = ValidationStyleEnum.DEFAULT;

	/**
	 * 
	 */
	public MetadataElement() {
	}

}
