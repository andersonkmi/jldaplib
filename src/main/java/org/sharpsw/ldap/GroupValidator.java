package org.sharpsw.ldap;

import org.sharpsw.ldap.exception.InvalidParameterException;

public class GroupValidator {
    public static void validate(String name) {
        if(name == null) {
            throw new InvalidParameterException("Null user name");
        } else if(name.isEmpty()) {
            throw new InvalidParameterException("Empty name provided");
        }
    }

    public static void validate(final User user) {
        if(user == null) {
            throw new InvalidParameterException("Null user");
        }
    }
}
