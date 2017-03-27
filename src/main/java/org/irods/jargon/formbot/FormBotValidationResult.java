package org.irods.jargon.formbot;

/**
 * Wrapper for FormBotValidationEnum that enables the validator to pass a
 * message back to the front end.
 * 
 * @author rskarbez
 *
 */

public class FormBotValidationResult {
	FormBotValidationEnum code;
	String message;

	public FormBotValidationEnum getCode() {
		return code;
	}

	public void setCode(FormBotValidationEnum code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public FormBotValidationResult() {
	}

	public FormBotValidationResult(FormBotValidationEnum c, String m) {
		this.code = c;
		this.message = m;
	}
}
