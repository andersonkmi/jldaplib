package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidBindPasswordException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class PasswordVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidBindPasswordException {
        String password = resource.getPassword();

        if(password == null) {
            throw new InvalidBindPasswordException("Null password supplied");
        } else if(password.isEmpty()) {
            throw new InvalidBindPasswordException("Password is empty");
        }
    }
}
