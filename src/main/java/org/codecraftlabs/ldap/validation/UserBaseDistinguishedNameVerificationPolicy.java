package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidUserBaseDNException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class UserBaseDistinguishedNameVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidUserBaseDNException {
        String dn = resource.getUserBaseDN();

        if (dn == null) {
            throw new InvalidUserBaseDNException("Null user base dn supplied");
        }

        if (dn.isEmpty()) {
            throw new InvalidUserBaseDNException("Empty user base dn supplied");
        }
    }
}
