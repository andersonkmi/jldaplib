
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
package org.sharpsw.ldap.exception;

/**
 * @author anderson
 *
 */
public class UserAlreadyExistsLDAPException extends Exception {

	private static final long serialVersionUID = 8132169386830185108L;

	/**
	 * Default class constructor.
	 */
	public UserAlreadyExistsLDAPException() {
	}

	/**
	 * Constructor.
	 * @param message Exception message
	 */
	public UserAlreadyExistsLDAPException(final String message) {
		super(message);
	}

	/**
	 * Class constructor.
	 * @param cause Original exception
	 */
	public UserAlreadyExistsLDAPException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 * @param message Exception message
	 * @param cause Original exception
	 */
	public UserAlreadyExistsLDAPException(final String message,
			                              final Throwable cause) {
		super(message, cause);
	}

}
