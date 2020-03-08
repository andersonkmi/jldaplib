package org.codecraftlabs.ldap.exception;

public class LDAPUserAddException extends LDAPException {
	private static final long serialVersionUID = 9085343373732118467L;

	public LDAPUserAddException(String message) {
		super(message);
	}

	public LDAPUserAddException(Throwable cause) {
		super(cause);
	}

	public LDAPUserAddException(String message, Throwable cause) {
		super(message, cause);
	}
}
