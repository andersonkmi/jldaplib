package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.LDAPException;

public interface LDAPPolicyVerifier {
    void verify() throws LDAPException;
}
