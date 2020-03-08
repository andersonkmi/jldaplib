package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidEncryptionMethodException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class EncryptionMethodVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidEncryptionMethodException {
        String flag = resource.getEncryption();

        if(flag == null) {
            throw new InvalidEncryptionMethodException("Encryption information is null");
        } else if(flag.isEmpty()) {
            throw new InvalidEncryptionMethodException("Encryption information is empty");
        } else if(!flag.equals("none") && !flag.equals("ssl") && !flag.equals("tls")) {
            throw new InvalidEncryptionMethodException("Encryption is invalid: " + flag);
        }
    }
}
