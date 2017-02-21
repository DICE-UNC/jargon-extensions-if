package org.irods.jargon.formbot;

//import java.util.List;

public interface FormBotService {
//	FormBotForm buildFormBotForm(String json);	
//	
//	FormBotValidationResult validateFormBotField(String json);
//	List<FormBotValidationResult> validateFormBotForm(String json);
//		
//	FormBotExecutionResult executeFormBotField(String json);
//	List<FormBotExecutionResult> executeFormBotForm(String json);
	String buildFormBotForm(String json);	
	
	String validateFormBotField(String json);
	String validateFormBotForm(String json);
		
	String executeFormBotField(String json);
	String executeFormBotForm(String json);
}

