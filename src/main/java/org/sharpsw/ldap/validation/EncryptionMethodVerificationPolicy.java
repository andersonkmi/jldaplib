package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidEncryptionMethodException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

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
