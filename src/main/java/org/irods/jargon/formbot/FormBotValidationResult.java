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

	public void setCode(final FormBotValidationEnum code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public FormBotValidationResult() {
	}

	public FormBotValidationResult(final FormBotValidationEnum c, final String m) {
		code = c;
		message = m;
	}
}
