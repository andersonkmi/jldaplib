package org.sharpsw.ldap.exception;

public class InvalidUserBaseDNException extends LDAPException {

	private static final long serialVersionUID = 7172774854982492438L;

	public InvalidUserBaseDNException() {
	}

	public InvalidUserBaseDNException(final String message) {
		super(message);
	}

	public InvalidUserBaseDNException(final Throwable cause) {
		super(cause);
	}

	public InvalidUserBaseDNException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
