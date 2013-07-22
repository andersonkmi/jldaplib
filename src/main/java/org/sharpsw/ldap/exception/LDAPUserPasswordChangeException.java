package org.sharpsw.ldap.exception;

public class LDAPUserPasswordChangeException extends LDAPException {
	private static final long serialVersionUID = 8522798374852666517L;

	public LDAPUserPasswordChangeException(String message) {
		super(message);
	}

	public LDAPUserPasswordChangeException(Throwable cause) {
		super(cause);
	}

	public LDAPUserPasswordChangeException(String message, Throwable cause) {
		super(message, cause);
	}

}
