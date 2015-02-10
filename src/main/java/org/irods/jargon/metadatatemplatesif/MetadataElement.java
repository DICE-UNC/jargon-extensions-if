/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.text.DateFormat;
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
	public MetadataElement() {
	}

	public ValidationReturnEnum validate() {
		if (required == true & currentValue.isEmpty()) {
			return ValidationReturnEnum.VALUE_IS_REQUIRED;
		}
		
		if (validationStyle == ValidationStyleEnum.DO_NOT_VALIDATE) {
			return ValidationReturnEnum.NOT_VALIDATED;
		}

		// Default behavior is to validate type only
		if (validationStyle == ValidationStyleEnum.DEFAULT) {
			if (type == TypeEnum.STRING) {
				return ValidationReturnEnum.SUCCESS;
			}

			if (type == TypeEnum.TEXT) {
				try {
					Float.parseFloat(currentValue);
				} catch (NumberFormatException e) {
					// Current value is not a number, so assume it is text
					return ValidationReturnEnum.SUCCESS;
				}

				return ValidationReturnEnum.BAD_TYPE;
			}

			if (type == TypeEnum.INT) {
				try {
					Integer.parseInt(currentValue);
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (type == TypeEnum.FLOAT) {
				try {
					Float.parseFloat(currentValue);
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (type == TypeEnum.BOOLEAN) {
				if (currentValue.equalsIgnoreCase("true")
						|| currentValue.equalsIgnoreCase("false")
						|| currentValue.equalsIgnoreCase("0")
						|| currentValue.equalsIgnoreCase("1")) {
					return ValidationReturnEnum.SUCCESS;
				} else {
					return ValidationReturnEnum.BAD_TYPE;
				}
			}

			if (type == TypeEnum.DATE) {
				/**
				 * XXX Not yet implemented
				 */
				/*
				 * if (value is convertible to date) return SUCCESS else return
				 * BAD_TYPE
				 */
				return ValidationReturnEnum.NOT_VALIDATED;
			}
		}

		if (validationStyle == ValidationStyleEnum.IN_LIST) {
			for (String s : validationOptions) {
				if (s.equalsIgnoreCase(currentValue))
					;
				return ValidationReturnEnum.SUCCESS;
			}

			return ValidationReturnEnum.VALUE_NOT_IN_LIST;
		}

		if (validationStyle == ValidationStyleEnum.IN_RANGE ||
				validationStyle == ValidationStyleEnum.IN_RANGE_EXCLUSIVE) {
			/* Should be exactly two values (endpoints of range) in validationOptions
			 * 
			 * But there must be AT LEAST 2. If more than 2, other values are ignored.
			 */
			if (validationOptions.size() < 2) {
				return ValidationReturnEnum.NOT_VALIDATED;
			}
			
			/*
			 * IN_RANGE validation only makes sense for well-ordered values
			 * (i.e. numbers and dates)
			 * 
			 * Need to check type, and whether endpoints are valid, before
			 * validating.
			 */
			
			if (type == TypeEnum.INT) {
				int curVal;
				try {
					curVal = Integer.parseInt(currentValue);
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}
				
				int minVal;
				try {
					minVal = Integer.parseInt(validationOptions.get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				int maxVal;
				try {
					maxVal = Integer.parseInt(validationOptions.get(1));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				// Be lenient with the order
				if (minVal > maxVal) {
					int temp = minVal;
					minVal = maxVal;
					maxVal = temp;
				}
				
				if (validationStyle == ValidationStyleEnum.IN_RANGE) {
					if (curVal >= minVal && curVal <= maxVal) {
						return ValidationReturnEnum.SUCCESS;
					}
					else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}				
				}
				else { 
					// Already checked style was either IN_RANGE or IN_RANGE_EXCLUSIVE
					if (curVal > minVal && curVal < maxVal) {
						return ValidationReturnEnum.SUCCESS;
					}
					else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				}
			}
			
			if (type == TypeEnum.FLOAT) {
				float curVal;
				try {
					curVal = Float.parseFloat(currentValue);
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}
				
				float minVal;
				try {
					minVal = Float.parseFloat(validationOptions.get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				float maxVal;
				try {
					maxVal = Float.parseFloat(validationOptions.get(1));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				// Be lenient with the order
				if (minVal > maxVal) {
					float temp = minVal;
					minVal = maxVal;
					maxVal = temp;
				}
				
				if (validationStyle == ValidationStyleEnum.IN_RANGE) {
					if (curVal >= minVal && curVal <= maxVal) {
						return ValidationReturnEnum.SUCCESS;
					}
					else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}				
				}
				else { 
					// Already checked style was either IN_RANGE or IN_RANGE_EXCLUSIVE
					if (curVal > minVal && curVal < maxVal) {
						return ValidationReturnEnum.SUCCESS;
					}
					else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				}
			}
			
			if (type == TypeEnum.DATE) {
				/**
				 * XXX Not yet implemented
				 */
				return ValidationReturnEnum.NOT_VALIDATED;
			}
			
			// All other types, IN_RANGE is ill-defined.
			return ValidationReturnEnum.NOT_VALIDATED;
				
		}
		
		if (validationStyle == ValidationStyleEnum.REGEX) {
			/**
			 * XXX Not yet implemented
			 */
			return ValidationReturnEnum.NOT_VALIDATED;
		}

		// Failsafe
		return ValidationReturnEnum.NOT_VALIDATED;
	}

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
}
