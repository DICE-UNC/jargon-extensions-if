package org.irods.jargon.formbot;

/**
 * Wrapper for FormBotExecutionEnum that enables the executor to pass a message
 * back to the front end.
 *
 * @author rskarbez
 *
 */

public class FormBotExecutionResult {
	FormBotExecutionEnum code;
	String message;

	public FormBotExecutionEnum getCode() {
		return code;
	}

	public void setCode(final FormBotExecutionEnum code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public FormBotExecutionResult() {
	}

	public FormBotExecutionResult(final FormBotExecutionEnum c, final String m) {
		code = c;
		message = m;
	}
}