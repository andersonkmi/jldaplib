package org.codecraftlabs.ldap.services.msad;

import org.codecraftlabs.ldap.services.LDAPConnection;
import org.codecraftlabs.ldap.services.LDAPResource;

public abstract class BaseService {
	protected static final String UID = "sAMAccountName";
	protected static final String DISPLAY_NAME = "displayName";
	protected static final String DN = "DN";
	protected static final String CN = "CN";
	protected static final String OBJECT_CLASS = "objectClass";
	protected static final String USER_SEARCH_FILTER = "objectClass=person";

	private LDAPConnection connection;
	private LDAPResource resource;
	private String userIdAttribute = UID;

	@SuppressWarnings("unused")
	private BaseService() {
		
	}
	
	public BaseService(final LDAPConnection connection, final LDAPResource resource) {
		this.connection = connection;
		this.resource = resource;
	}
	
	public void setUserIdAttribute(final String uid) {
		this.userIdAttribute = uid;
	}
	
	public String getUserIdAttribute() {
		return this.userIdAttribute;
	}
	
	protected LDAPConnection getLDAPConnection() {
		return this.connection;
	}
	
	protected LDAPResource getLDAPResource() {
		return this.resource;
	}
}
