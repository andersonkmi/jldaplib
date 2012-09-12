package org.sharpsw.ldap.exception;

public class LDAPUserRemovalException extends LDAPException {
	private static final long serialVersionUID = 8575482915557375548L;

	public LDAPUserRemovalException(String message) {
		super(message);
	}

	public LDAPUserRemovalException(Throwable cause) {
		super(cause);
	}

	public LDAPUserRemovalException(String message, Throwable cause) {
		super(message, cause);
	}

}
