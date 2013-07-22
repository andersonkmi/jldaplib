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

//*****************************************************************************
//*** Modification history
//***
//*** Date			Author			Description
//*** ====			======			===========
//*** Jul/31/2011	andersonkmi		Code documentation
//******************************************************************************
package org.sharpsw.ldap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Base class for LDAP objects handled by the library.
 * @author Anderson Ito
 *
 */
public abstract class BaseLDAPElement {
	private String dn;
	private String id;
	private String commonName;	
	private Set<String> objectClassAttributeValues;
		
	/**
	 * Constructor that accepts the distinguished name.
	 * @param distinguishedName String containing the distinguished name information.
	 */
	public BaseLDAPElement(final String distinguishedName) {
		this.dn = distinguishedName;
		this.objectClassAttributeValues = new HashSet<String>();
	}	
	
	/**
	 * Constructor that accepts the distinguished name and the identification information.
	 * @param distinguishedName String containing the distinguished name.
	 * @param identifier String containing the identification information.
	 */
	public BaseLDAPElement(final String distinguishedName, final String identifier) {
		this(distinguishedName);
		this.id = identifier;
	}
	
	/**
	 * Constructor that accepts the distinguished name, the identification and the common name information.
	 * @param distinguishedName String containing the distinguished name information.
	 * @param identifier String containing the identification information.
	 * @param commonName String containing the common name information.
	 */
	public BaseLDAPElement(final String distinguishedName, final String identifier, final String commonName) {
		this(distinguishedName, identifier);
		this.commonName = commonName;
	}
	
	/**
	 * Returns the current value associated with the distinguished name attribute.
	 * @return Current value of the distinguished name.
	 */
	public final String getDn() {
		return this.dn;
	}
	
	/**
	 * Return the id information.
	 * @return String containing the identification information.
	 */
	public final String getId() {
		return this.id;
	}
	
	/**
	 * Returns the common name currently configured.
	 * @return String containing the common name value.
	 */
	public final String getCommonName() {
		return this.commonName;
	}
	
	/**
	 * Sets the distinguished name information.
	 * @param value String containing the distinguished name.
	 */
	public final void setDn(final String value) {
		this.dn = value;
	}
	
	/**
	 * Sets the identification information.
	 * @param value String containing the identification information.
	 */
	public final void setId(final String value) {
		this.id = value;
	}
	
	/**
	 * Sets the common name information.
	 * @param value String containing the common name information.
	 */
	public final void setCommonName(final String value) {
		this.commonName = value;
	}
	
	/**
	 * Adds the an object class attribute value information.
	 * @param value String containing the name of the object class attribute value.
	 */
	public final void addObjectClass(final String value) {
		this.objectClassAttributeValues.add(value);
	}
	
	/**
	 * Returns a list containing the object class attributes.
	 * @return List containing strings of the object class attributes.
	 */
	public final List<String> getObjectClassAttrbutes() {
		List<String> elements = new ArrayList<String>();
		for(String item : this.objectClassAttributeValues) {
			elements.add(item);
		}
		return elements;
	}
	
	public abstract String toString();
	
	public abstract int hashCode();
	
	public abstract boolean equals(Object other);
}
