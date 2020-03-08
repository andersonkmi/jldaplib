package org.codecraftlabs.ldap.services.openldap;

import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.util.DistinguishedNameBuilder;
import org.codecraftlabs.ldap.services.LDAPConnection;

public abstract class BaseService {
	private LDAPConnection connection;
	private LDAPResource resource;
	protected DistinguishedNameBuilder builder;
	
	private String userIdAttribute = UID;
	private String groupIdAttribute = CN;
	private String userObjectClass = "person";
	private String groupObjectClass = "posixGroup";
	
	protected static final String UID = "uid";
	protected static final String GID = "gid";
	protected static final String DISPLAY_NAME = "displayName";
	protected static final String DN = "DN";
	protected static final String CN = "cn";
	protected static final String OBJECT_CLASS = "objectClass";
	protected static final String UNIQUE_MEMBER_UID = "memberUid";
	protected static final String UNIQUE_MEMBER_CN = "member";
	protected static final String USER_SEARCH_FILTER = "objectClass=person";
	protected static final String GROUP_SEARCH_FILTER = "objectClass=posixGroup";
	
	@SuppressWarnings("unused")
	private BaseService() {
		// Nothing is performed
	}
	
	public BaseService(final LDAPConnection connection, final LDAPResource resource, DistinguishedNameBuilder builder) {
		this.connection = connection;
		this.resource = resource;
		this.builder = builder;
	}
	
	public void setUserIdAttribute(String uid) {
		this.userIdAttribute = uid;
	}
	
	public String getUserIdAttribute() {
		return this.userIdAttribute;
	}
	
	public void setGroupIdAttribute(String groupId) {
		this.groupIdAttribute = groupId;
	}
	
	public String getGroupIdAttribute() {
		return this.groupIdAttribute;
	}
	
	public void setUserObjectClass(String name) {
		this.userObjectClass = name;
	}
	
	public String getUserObjectClass() {
		return this.userObjectClass;
	}
	
	public void setGroupObjectClass(String name) {
		this.groupObjectClass = name;
	}
	
	public String getGroupObjectClass() {
		return this.groupObjectClass;
	}
	
	protected String getUserSearchFilter() {
		return OBJECT_CLASS + "=" + this.getUserObjectClass();
	}
	
	protected String getGroupSearchFilter() {
		return OBJECT_CLASS + "=" + this.getGroupObjectClass();
	}
	
	protected LDAPConnection getLDAPConnection() {
		return this.connection;
	}
	
	protected LDAPResource getLDAPResource() {
		return this.resource;
	}
}
