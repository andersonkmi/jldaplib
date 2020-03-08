package org.codecraftlabs.ldap.services;

public enum SearchScope {
	SUBTREE("subtree"),
	ONE_LEVEL("onelevel"),
	OBJECT("object"),
	NONE("none");

	private final String code;

	SearchScope(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static SearchScope findByCode(String code) {
		for (SearchScope scope : values()) {
			if (scope.code.equals(code)) {
				return scope;
			}
		}
		return NONE;
	}
}
