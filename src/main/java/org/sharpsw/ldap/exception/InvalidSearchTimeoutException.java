package org.sharpsw.ldap.exception;

public class InvalidSearchTimeoutException extends LDAPException {

	private static final long serialVersionUID = 9175510258523734296L;

	public InvalidSearchTimeoutException() {
	}

	public InvalidSearchTimeoutException(final String message) {
		super(message);
	}

	public InvalidSearchTimeoutException(final Throwable cause) {
		super(cause);
	}

	public InvalidSearchTimeoutException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
