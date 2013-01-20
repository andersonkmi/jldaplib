package org.sharpsw.ldap.services.apacheds;

import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPResource;

/**
 * Base service for the other implementations for Apache Directory Server.
 * @author Anderson Ito
 *
 */
public abstract class BaseService {
	protected static final String UID = "uid";
	protected static final String DISPLAY_NAME = "displayName";
	protected static final String DN = "DN";
	protected static final String CN = "cn";
	protected static final String SN = "sn";
	protected static final String OBJECT_CLASS = "objectClass";
	protected static final String UNIQUE_MEMBER = "uniqueMember";
	protected static final String USER_SEARCH_FILTER = "objectClass=person";
	protected static final String GROUP_SEARCH_FILTER = "objectClass=groupOfUniqueNames";

	private LDAPConnection connection;
	private LDAPResource resource;
	private String userIdentificationAttribute = UID;
	private String userObjectClass = "person";
	private String groupObjectClass = "groupOfUniqueNames";
	
	@SuppressWarnings("unused")
	private BaseService() {
		
	}
	
	/**
	 * Constructor that accepts the ldap connection and the resource information.
	 * @param connection Instance of the class <code>LDAPConnection</code>.
	 * @param resource Instance of the class <code>LDAPResource</code>
	 */
	public BaseService(final LDAPConnection connection, final LDAPResource resource) {
		this.connection = connection;
		this.resource = resource;
	}
	
	/**
	 * Sets the user id attribute when it is different from the default.
	 * @param uid String containing the user id attribute name.
	 */
	public void setUserIdentificationAttribute(final String uid) {
		this.userIdentificationAttribute = uid;
	}
	
	/**
	 * Returns the user id attribute name.
	 * @return String containing the user id attribute name.
	 */
	public String getUserIdentificationAttribute() {
		return this.userIdentificationAttribute;
	}
	
	public void setUserObjectClass(String value) {
		this.userObjectClass = value;
	}
	
	public String getUserObjectClass() {
		return this.userObjectClass;
	}
	
	public void setGroupObjectClass(String value) {
		this.groupObjectClass = value;
	}
	
	public String getGroupObjectClass() {
		return this.groupObjectClass;
	}
	
	/**
	 * Returns the ldap connection object.
	 * @return Instance of the class <code>LDAPConnection</code>.
	 */
	protected LDAPConnection getLDAPConnection() {
		return this.connection;
	}
	
	/**
	 * Returns the object holding the configurations.
	 * @return Instance of the class <code>LDAPResource</code>.
	 */
	protected LDAPResource getLDAPResource() {
		return this.resource;
	}
	
	protected String getUserSearchFilter() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(OBJECT_CLASS).append("=").append(this.getUserSearchFilter());
		return buffer.toString();
	}
	
	protected String getGroupSearchFilter() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(OBJECT_CLASS).append("=").append(this.getGroupObjectClass());
		return buffer.toString();
	}
}
