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
package org.sharpsw.ldap.services.exception;

public class ServiceBuildException extends Exception {

	private static final long serialVersionUID = -3121173618337531784L;

	public ServiceBuildException() {
		super();
	}

	public ServiceBuildException(final String arg0) {
		super(arg0);
	}

	public ServiceBuildException(final Throwable arg0) {
		super(arg0);
	}

	public ServiceBuildException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

}
