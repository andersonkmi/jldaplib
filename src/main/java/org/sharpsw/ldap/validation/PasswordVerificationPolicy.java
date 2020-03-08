package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidBindPasswordException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

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
