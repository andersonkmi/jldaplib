package org.sharpsw.ldap.exception;

public class InvalidParameterException extends RuntimeException {
	private static final long serialVersionUID = 7127726074978241617L;

	public InvalidParameterException() {
		super();
	}
	
	public InvalidParameterException(final String message) {
		super(message);
	}
	
	public InvalidParameterException(final Throwable exception) {
		super(exception);
	}
	
	public InvalidParameterException(final String message, final Throwable exception) {
		super(message, exception);
	}
}
