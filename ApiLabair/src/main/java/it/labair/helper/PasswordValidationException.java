package it.labair.helper;

public class PasswordValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public final String message = "Password inadeguata";
	
	
	public String getMessage() {
		return message;
	}
	
}
