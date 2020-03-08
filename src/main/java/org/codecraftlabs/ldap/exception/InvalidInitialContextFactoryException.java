package org.codecraftlabs.ldap.exception;

/**
 * This class represents an exception that is raised when there is problem related
 * to the initial context factory information.
 * @author Anderson Ito
 * @see LDAPException
 *
 */
public class InvalidInitialContextFactoryException extends LDAPException {

	private static final long serialVersionUID = 6234126441000310255L;

	/**
	 * Default constructor
	 */
	public InvalidInitialContextFactoryException() {
	}

	/**
	 * Constructor that accepts a string containing an error message.
	 * @param message String containing an error message.
	 */
	public InvalidInitialContextFactoryException(final String message) {
		super(message);
	}

	/**
	 * Constructor that accepts the original exception.
	 * @param cause Object representing the original exception.
	 */
	public InvalidInitialContextFactoryException(final Throwable cause) {
		super(cause);
	}
	

	/**
	 * Constructor that accepts a string containing an error message and the original exception.
	 * @param message String containing an error message.
	 * @param cause Original exception.
	 */
	public InvalidInitialContextFactoryException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
