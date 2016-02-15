package org.irods.jargon.formbot;

public class FormBotExecutionResult {
	FormBotExecutionEnum code;
	String message;
	
	public FormBotExecutionEnum getCode() {
		return code;
	}
	public void setCode(FormBotExecutionEnum code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public FormBotExecutionResult(FormBotExecutionEnum c, String m) {
		this.code = c;
		this.message = m;
	}
}
