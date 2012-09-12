package org.sharpsw.ldap.exception;

/**
 * This class represents an exception raised when the group base DN has a problem.
 * @author Anderson Ito
 * @see org.sharpsw.ldap.exception.LDAPException
 *
 */
public class InvalidGroupBaseDNException extends LDAPException {

	private static final long serialVersionUID = 4632540193689846285L;

	/** 
	 * Default constructor
	 */
	public InvalidGroupBaseDNException() {
	}

	/**
	 * Constructor that accepts a string containing an error message.
	 * @param message String containing an error message.
	 */
	public InvalidGroupBaseDNException(final String message) {
		super(message);
	}

	/**
	 * Constructor that accepts an exception.
	 * @param cause Object representing the original exception.
	 */
	public InvalidGroupBaseDNException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor that accepts a string containing an error message and the original exception.
	 * @param message String containing the error message.
	 * @param cause The object pointing to the original exception.
	 */
	public InvalidGroupBaseDNException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
