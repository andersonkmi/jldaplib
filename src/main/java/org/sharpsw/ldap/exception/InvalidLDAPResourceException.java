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

//***************************************************************************
//*** Modification history
//***
//*** Date			Author			Description
//*** ====			======			===========
//*** Jan/12/2010	andersonkmi		Code documentation and inserted error code
//****************************************************************************

package org.sharpsw.ldap.exception;

/**
 * InvalidLDAPResourceException - this class represents an exception when there
 * is a problem with the resource file used by the library.
 * @author andersonkmi
 */
public class InvalidLDAPResourceException extends Exception {

	private int errorCode = 102;
	private static final long serialVersionUID = 6519238402834200414L;

	/**
	 * Default class constructor.
	 */
	public InvalidLDAPResourceException() {
		super();
	}

	/**
	 * Class constructor accepting a message.
	 * @param arg0
	 *            String containing the exception message.
	 */
	public InvalidLDAPResourceException(final String arg0) {
		super(arg0);
	}

	/**
	 * Class constructor that wrapping the original exception instance.
	 * @param arg0
	 *            Original exception instance.
	 */
	public InvalidLDAPResourceException(final Throwable arg0) {
		super(arg0);
	}

	/**
	 * Class constructor that accepts a exception message and wraps the original
	 * exception instance.
	 * @param arg0
	 *            Exception string message.
	 * @param arg1
	 *            Original exception instance.
	 */
	public InvalidLDAPResourceException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Returns the associated exception error code.
	 * @return Exception error code.
	 */
	public final int getErrorCode() {
		return this.errorCode;
	}
}
