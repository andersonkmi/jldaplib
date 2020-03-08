package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidInitialContextFactoryException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class InitialContextVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidInitialContextFactoryException {
        String context = resource.getInitialContextFactory();

        if(context == null) {
            throw new InvalidInitialContextFactoryException("Null initial context factory");
        } else if(context.isEmpty()) {
            throw new InvalidInitialContextFactoryException("Empty initial context factory");
        }
    }
}
