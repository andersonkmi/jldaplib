package org.codecraftlabs.ldap.exception;

/**
 * Exception class that represents an error when the bind password is not valid.
 * @author Anderson Ito
 * @see LDAPException
 */
public class InvalidBindPasswordException extends LDAPException {

	private static final long serialVersionUID = -5579825939532059065L;

	/**
	 * Default constructor.
	 */
	public InvalidBindPasswordException() {
	}

	/**
	 * Class constructor that accepts a string containing the error message of the exception.
	 * @param message
	 */
	public InvalidBindPasswordException(final String message) {
		super(message);
	}

	/**
	 * Constructor that accepts the original exception.
	 * @param cause Exception object.
	 */
	public InvalidBindPasswordException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor that accepts a string containing the error message and the original exception.
	 * @param message String containing the error message.
	 * @param cause Original exception.
	 */
	public InvalidBindPasswordException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
