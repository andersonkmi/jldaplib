package org.sharpsw.ldap.exception;

public class InvalidServerException extends LDAPException {

	private static final long serialVersionUID = -5162504822974048027L;

	public InvalidServerException() {
	}

	public InvalidServerException(final String message) {
		super(message);
	}

	public InvalidServerException(final Throwable cause) {
		super(cause);
	}

	public InvalidServerException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
