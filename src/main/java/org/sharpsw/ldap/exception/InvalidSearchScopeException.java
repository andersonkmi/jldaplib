package org.sharpsw.ldap.exception;

public class InvalidSearchScopeException extends LDAPException {

	private static final long serialVersionUID = -2866326377491126941L;

	public InvalidSearchScopeException() {
	}

	public InvalidSearchScopeException(final String message) {
		super(message);
	}

	public InvalidSearchScopeException(final Throwable cause) {
		super(cause);
	}

	public InvalidSearchScopeException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
