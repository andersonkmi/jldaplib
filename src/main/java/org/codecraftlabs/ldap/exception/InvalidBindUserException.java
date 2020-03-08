/******************************************************************************
    JLdapLib - Simple LDAP library for Java.
    Copyright (C) 2010  Anderson Ito (andersonkmi@acm.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
******************************************************************************/
package org.codecraftlabs.ldap.exception;

/**
 * This class represents an exception that is raised when the bind user is not correct.
 * @author Anderson Ito
 * @see LDAPException
 */
public class InvalidBindUserException extends LDAPException {

	private static final long serialVersionUID = 4196337956192765461L;

	/**
	 * Default constructor
	 */
	public InvalidBindUserException() {
	}

	/**
	 * Constructor that accepts a string containing the error message.
	 * @param arg0 String containing the error message.
	 */
	public InvalidBindUserException(final String arg0) {
		super(arg0);
	}

	/**
	 * Constructor that accepts the original exception.
	 * @param arg0 Object corresponding to the original exception.
	 */
	public InvalidBindUserException(final Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructor that accepts a string containing an error message and the original exception.
	 * @param arg0 String containing an error message.
	 * @param arg1 Original exception.
	 */
	public InvalidBindUserException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

}
