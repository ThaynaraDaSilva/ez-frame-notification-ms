package br.duosilva.tech.solutions.ez.frame.notification.ms.frameworks.exception;

public class BusinessRuleException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	
	

	public BusinessRuleException(String message) {
        super(message);
    }
	
	public BusinessRuleException(String message, Throwable cause) {
	    super(message, cause);
	}

}
