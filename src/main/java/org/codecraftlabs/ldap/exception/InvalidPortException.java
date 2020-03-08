package org.codecraftlabs.ldap.exception;

public class InvalidPortException extends LDAPException {

	private static final long serialVersionUID = 5350805225946120896L;

	public InvalidPortException() {
	}

	public InvalidPortException(final String message) {
		super(message);
	}

	public InvalidPortException(final Throwable cause) {
		super(cause);
	}

	public InvalidPortException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
