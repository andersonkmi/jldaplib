package org.codecraftlabs.ldap.util;

public enum DistinguishedNameNodeType {
	
	CN("cn", "Common name"),
	DC("dc", "Domain component"),
	UID("uid", "User id"),
	OU("ou", "Organizational unit");
	
	private String code;
	private String description;
	
	DistinguishedNameNodeType(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
}
