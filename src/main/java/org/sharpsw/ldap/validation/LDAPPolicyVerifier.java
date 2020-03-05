package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public interface LDAPPolicyVerifier {
    void verify(@Nonnull LDAPResource resource) throws LDAPException;
}
