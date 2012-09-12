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
package org.sharpsw.ldap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sharpsw.ldap.exception.InvalidParameterException;

public class UserTestCase {

	private User userTest;

	@Before
	public final void setUp() {
		this.userTest = new User("teste", "teste", "teste", "Common name");
	}

	@Test
	public final void testUserCreationOK() {
		User user = new User("teste", "teste", "teste", "common name");
		Assert.assertEquals("teste", user.getId());
		Assert.assertEquals("teste", user.getDisplayName());
		Assert.assertEquals("teste", user.getDn());
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserCreationNullIdFail() {
		new User(null, "teste", "teste", "common name");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserCreationEmptyIdFail() {
		new User("", "teste", "teste", "common name");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserCreationNullDNFail() {
		new User("teste", "teste", null, "common name");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserCreationEmptyDNFail() {
		new User("teste", "teste", "", "common name");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserSetNullIdFail() {
		this.userTest.setId(null);
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserSetEmptyIdFail() {
		this.userTest.setId("");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserSetNullDNFail() {
		this.userTest.setDn(null);
	}

	@Test(expected = InvalidParameterException.class)
	public final void testUserSetEmptyDNFail() {
		this.userTest.setDn("");
	}
}
