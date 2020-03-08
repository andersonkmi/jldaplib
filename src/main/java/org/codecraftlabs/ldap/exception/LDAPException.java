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
//*** Jan/12/2010	andersonkmi		Code documentation
//****************************************************************************

package org.codecraftlabs.ldap.exception;

/**
 * GeneralLDAPException class represents a generic LDAP error.
 * @author andersonkmi
 *
 */
public class LDAPException extends Exception {
	private int errorCode = 999;

	private static final long serialVersionUID = 6268443170142937641L;

	/**
	 * Default class constructor.
	 */
	public LDAPException() {
		super();
	}

	/**
	 * Class constructor that accepts an error message.
	 * @param message String containing the exception message.
	 */
	public LDAPException(final String message) {
		super(message);		
	}

	/**
	 * Class constructor that accepts a <code>Throwable</code> instance.
	 * @param cause An instance of the original exception.
	 */
	public LDAPException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Class constructor that accepts a <code>Throwable</code> instance and a message.
	 * @param message String containing the exception message.
	 * @param cause Original exception instance.
	 */
	public LDAPException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Returns the exception code number.
	 * @return Exception code number.
	 */
	public final int getErrorCode() {
		return this.errorCode;
	}
}
