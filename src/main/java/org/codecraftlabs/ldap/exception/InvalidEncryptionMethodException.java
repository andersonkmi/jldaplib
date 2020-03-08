package org.codecraftlabs.ldap.exception;

/**
 * This class represents an error when the encryption method is not valid.
 * @author Anderson Ito
 * @see LDAPException
 */
public class InvalidEncryptionMethodException extends LDAPException {

	private static final long serialVersionUID = 7274536557237989697L;

	/**
	 * Default constructor
	 */
	public InvalidEncryptionMethodException() {
	}

	/**
	 * Constructor that accepts a string containing the error message.
	 * @param message String containing an error message.
	 */
	public InvalidEncryptionMethodException(final String message) {
		super(message);
	}

	/**
	 * Class constructor that accepts the original exception.
	 * @param cause Object representing the original exception.
	 */
	public InvalidEncryptionMethodException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor that accepts a string with an error message and the original exception.
	 * @param message String containing an error message.
	 * @param cause Original exception
	 */
	public InvalidEncryptionMethodException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
