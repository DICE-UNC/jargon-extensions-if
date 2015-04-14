package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 
 * Provides the methods to validate a <code>MetadataElement</code> or a
 * <code>MetadataTemplate</code>.
 * <p/>
 * Defined as a singleton because there should never be a need for multiple
 * validators.
 * <p/>
 * Example:<br/>
 * <code>ValidatorSingleton v = ValidatorSingleton.VALIDATOR;<br/>
 * v.validate(me);<br/>
 * v.validate(mt);</code>
 * 
 * @author rskarbez
 *
 */

public final class ValidatorSingleton {
	public final static ValidatorSingleton VALIDATOR = new ValidatorSingleton();

	private ValidatorSingleton() {
		// Exists only to defeat instantiation
	}
	
	/**
	 * Perform validation on a metadata element.
	 * 
	 * @param me
	 * 			  {@link MetadataElement} to be validated
	 * @return {@link ValidationReturnEnum} reporting validation result
	 */
	public ValidationReturnEnum validate(MetadataElement me) {
		if (me.isRequired() == true & me.getCurrentValue().isEmpty()) {
			return ValidationReturnEnum.VALUE_IS_REQUIRED;
		}
		
		if (me.getValidationStyle() == ValidationStyleEnum.DO_NOT_VALIDATE) {
			return ValidationReturnEnum.NOT_VALIDATED;
		}

		// Default behavior is to validate type only
		if (me.getValidationStyle() == ValidationStyleEnum.DEFAULT) {
			if (me.getType() == TypeEnum.STRING) {
				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == TypeEnum.TEXT) {
				try {
					Float.parseFloat(me.getCurrentValue());
				} catch (NumberFormatException e) {
					// Current value is not a number, so assume it is text
					return ValidationReturnEnum.SUCCESS;
				}

				return ValidationReturnEnum.BAD_TYPE;
			}

			if (me.getType() == TypeEnum.INT) {
				try {
					Integer.parseInt(me.getCurrentValue());
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == TypeEnum.FLOAT) {
				try {
					Float.parseFloat(me.getCurrentValue());
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == TypeEnum.BOOLEAN) {
				if (me.getCurrentValue().equalsIgnoreCase("true")
						|| me.getCurrentValue().equalsIgnoreCase("false")
						|| me.getCurrentValue().equalsIgnoreCase("0")
						|| me.getCurrentValue().equalsIgnoreCase("1")) {
					return ValidationReturnEnum.SUCCESS;
				} else {
					return ValidationReturnEnum.BAD_TYPE;
				}
			}

			if (me.getType() == TypeEnum.DATE) {
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
		
		if (me.getValidationStyle() == ValidationStyleEnum.IS) {
			if (me.getValidationOptions().get(0).equalsIgnoreCase(me.getCurrentValue())) {
				return ValidationReturnEnum.SUCCESS;
			}
			
			return ValidationReturnEnum.VALUE_NOT_EQUAL;
		}

		if (me.getValidationStyle() == ValidationStyleEnum.IN_LIST) {
			for (String s : me.getValidationOptions()) {
				if (s.equalsIgnoreCase(me.getCurrentValue())) {
					return ValidationReturnEnum.SUCCESS;					
				}
			}

			return ValidationReturnEnum.VALUE_NOT_IN_LIST;
		}

		if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE ||
				me.getValidationStyle() == ValidationStyleEnum.IN_RANGE_EXCLUSIVE) {
			/* Should be exactly two values (endpoints of range) in validationOptions
			 * 
			 * But there must be AT LEAST 2. If more than 2, other values are ignored.
			 */
			if (me.getValidationOptions().size() < 2) {
				return ValidationReturnEnum.NOT_VALIDATED;
			}
			
			/*
			 * IN_RANGE validation only makes sense for well-ordered values
			 * (i.e. numbers and dates)
			 * 
			 * Need to check type, and whether endpoints are valid, before
			 * validating.
			 */
			
			if (me.getType() == TypeEnum.INT) {
				int curVal;
				try {
					curVal = Integer.parseInt(me.getCurrentValue());
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}
				
				int minVal;
				try {
					minVal = Integer.parseInt(me.getValidationOptions().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				int maxVal;
				try {
					maxVal = Integer.parseInt(me.getValidationOptions().get(1));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				// Be lenient with the order
				if (minVal > maxVal) {
					int temp = minVal;
					minVal = maxVal;
					maxVal = temp;
				}
				
				if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE) {
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
			
			if (me.getType() == TypeEnum.FLOAT) {
				float curVal;
				try {
					curVal = Float.parseFloat(me.getCurrentValue());
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}
				
				float minVal;
				try {
					minVal = Float.parseFloat(me.getValidationOptions().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				float maxVal;
				try {
					maxVal = Float.parseFloat(me.getValidationOptions().get(1));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}
				
				// Be lenient with the order
				if (minVal > maxVal) {
					float temp = minVal;
					minVal = maxVal;
					maxVal = temp;
				}
				
				if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE) {
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
			
			if (me.getType() == TypeEnum.DATE) {
				/**
				 * XXX Not yet implemented
				 */
				return ValidationReturnEnum.NOT_VALIDATED;
			}
			
			// All other types, IN_RANGE is ill-defined.
			return ValidationReturnEnum.NOT_VALIDATED;
				
		}
		
		if (me.getValidationStyle() == ValidationStyleEnum.REGEX) {
			if (me.getValidationOptions().size() == 0) {
				return ValidationReturnEnum.NOT_VALIDATED;
			}
			
			Boolean test = false;
			
			try {
				test = Pattern.matches(me.getValidationOptions().get(0), me.getCurrentValue());
			} catch (PatternSyntaxException e) {
				return ValidationReturnEnum.REGEX_SYNTAX_ERROR;
			}
			
			if (test) {
				return ValidationReturnEnum.SUCCESS;
			}
			else {
				return ValidationReturnEnum.REGEX_FAILED;
			}
		}

		// Failsafe
		return ValidationReturnEnum.NOT_VALIDATED;
	}
	
	/**
	 * Perform validation on all metadata elements in a template
	 * 
	 * @param mt
	 * 			  {@link FormBasedMetadataTemplate} to be validated
	 * @return List<{@link ValidationReturnEnum}> reporting validation results
	 */
	
	public List<ValidationReturnEnum> validate (FormBasedMetadataTemplate mt) {
		List<ValidationReturnEnum> returnList = new ArrayList<ValidationReturnEnum>();
		for (MetadataElement me : mt.getElements()) {
			returnList.add(validate(me));
		}
		
		return returnList;
	}
}
