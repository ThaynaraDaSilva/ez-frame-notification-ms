package br.duosilva.tech.solutions.ez.frame.notification.ms.frameworks.exception;

public class ErrorResponse {

	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
