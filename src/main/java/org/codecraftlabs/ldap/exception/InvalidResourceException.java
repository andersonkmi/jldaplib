package org.codecraftlabs.ldap.exception;

public class InvalidResourceException extends LDAPException {

	private static final long serialVersionUID = -604182130336515989L;

	public InvalidResourceException() {
	}

	public InvalidResourceException(final String message) {
		super(message);
	}

	public InvalidResourceException(final Throwable cause) {
		super(cause);
	}

	public InvalidResourceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
