package org.irods.jargon.formbot;

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
	
	public FormBotValidationResult(FormBotValidationEnum c, String m) {
		this.code = c;
		this.message = m;
	}
}
