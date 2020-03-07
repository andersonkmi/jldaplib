package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidGroupBaseDNException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class GroupBaseDistinguishedNameVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws LDAPException {
        String dn = resource.getGroupBaseDN();

        if (dn == null) {
            throw new InvalidGroupBaseDNException("Null group base dn supplied");
        }

        if (dn.isEmpty()) {
            throw new InvalidGroupBaseDNException("Empty group base dn supplied");
        }
    }
}
