package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidInitialContextFactoryException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class InitialContextVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws LDAPException {
        String context = resource.getInitialContextFactory();

        if(context == null) {
            throw new InvalidInitialContextFactoryException("Null initial context factory");
        } else if(context.isEmpty()) {
            throw new InvalidInitialContextFactoryException("Empty initial context factory");
        }
    }
}
