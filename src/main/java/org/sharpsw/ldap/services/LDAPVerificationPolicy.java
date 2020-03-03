package org.sharpsw.ldap.services;

import org.sharpsw.ldap.exception.LDAPException;

import javax.annotation.Nonnull;

public interface LDAPVerificationPolicy {
    void verify(@Nonnull final LDAPResource resource) throws LDAPException;
}
