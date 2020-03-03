package org.sharpsw.ldap.services;

import org.sharpsw.ldap.exception.LDAPException;

public interface LDAPPolicyVerifier {
    void verify() throws LDAPException;
}
