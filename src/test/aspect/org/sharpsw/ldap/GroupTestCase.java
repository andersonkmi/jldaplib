package org.sharpsw.ldap;


import org.junit.Assert;
import org.junit.Test;
import org.sharpsw.ldap.exception.InvalidParameterException;

public class GroupTestCase {

	@Test
	public final void testGroupCreation() {
		Group group = new Group("group", "dn");
		Assert.assertEquals("group", group.getId());
		Assert.assertEquals("dn", group.getDn());
	}

	@Test(expected = InvalidParameterException.class)
	public final void testGroupCreationNullIdFail() {
		new Group(null, "dn");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testGroupCreationEmptyIdFail() {
		new Group("", "dn");
	}

	@Test(expected = InvalidParameterException.class)
	public final void testGroupCreationNullDNFail() {
		new Group("teste", null);
	}

	@Test(expected = InvalidParameterException.class)
	public final void testGroupCreationEmptyDNFail() {
		new Group("teste", "");
	}
}
