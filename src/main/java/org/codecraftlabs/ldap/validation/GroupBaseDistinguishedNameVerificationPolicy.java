package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidGroupBaseDNException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class GroupBaseDistinguishedNameVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidGroupBaseDNException {
        String dn = resource.getGroupBaseDN();

        if (dn == null) {
            throw new InvalidGroupBaseDNException("Null group base dn supplied");
        }

        if (dn.isEmpty()) {
            throw new InvalidGroupBaseDNException("Empty group base dn supplied");
        }
    }
}
