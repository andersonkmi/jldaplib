package org.codecraftlabs.ldap.exception;

public class InvalidServerVendorException extends LDAPException {

	private static final long serialVersionUID = 7804545020443179999L;

	public InvalidServerVendorException() {
	}

	public InvalidServerVendorException(final String message) {
		super(message);
	}

	public InvalidServerVendorException(final Throwable cause) {
		super(cause);
	}

	public InvalidServerVendorException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
