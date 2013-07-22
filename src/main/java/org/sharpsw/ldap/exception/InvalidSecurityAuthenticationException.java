package org.sharpsw.ldap.exception;

public class InvalidSecurityAuthenticationException extends LDAPException {

	private static final long serialVersionUID = -8933886576838096309L;

	public InvalidSecurityAuthenticationException() {
	}

	public InvalidSecurityAuthenticationException(final String message) {
		super(message);
	}

	public InvalidSecurityAuthenticationException(final Throwable cause) {
		super(cause);
	}

	public InvalidSecurityAuthenticationException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

}
