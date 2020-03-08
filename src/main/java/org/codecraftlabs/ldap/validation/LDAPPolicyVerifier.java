package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public interface LDAPPolicyVerifier {
    void verify(@Nonnull LDAPResource resource) throws LDAPException;
}
