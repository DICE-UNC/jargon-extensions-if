package org.irods.jargon.metadatatemplate;

/**
 * 
 * Indicates the result of a call to <code>validate</code> this element's value.
 * 
 * @author rskarbez
 * 
*/

public enum ValidationReturnEnum {
	SUCCESS, VALUE_NOT_EQUAL, BAD_TYPE, VALUE_IS_REQUIRED, VALUE_NOT_IN_LIST, VALUE_NOT_IN_RANGE, REGEX_SYNTAX_ERROR, REGEX_FAILED, NOT_VALIDATED;
	
	@Override
	public String toString() {
		switch(this) {
		case SUCCESS: return "Validation successful";
		case VALUE_NOT_EQUAL: return "Validation failed: Value not equal to required value";
		case BAD_TYPE: return "Validation failed: Value of wrong type";
		case VALUE_IS_REQUIRED: return "Validation failed: This attribute requires a non-empty value";
		case VALUE_NOT_IN_LIST: return "Validation failed: Value is not in the specified list";
		case VALUE_NOT_IN_RANGE: return "Validation failed: Value is not in the specified range";
		case REGEX_SYNTAX_ERROR: return "Not validated: There is an error in the validation regex";
		case REGEX_FAILED: return "Validation failed: Value does not match the validation regex";
		case NOT_VALIDATED: return "Not validated: No validation behavior specified";
		default: throw new IllegalArgumentException();
		}
	}
}
