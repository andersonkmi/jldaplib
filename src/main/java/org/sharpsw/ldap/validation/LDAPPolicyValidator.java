package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class LDAPPolicyValidator implements LDAPPolicyVerifier {
    private Set<LDAPVerificationPolicy> policies = new HashSet<>();

    public LDAPPolicyValidator(Set<LDAPVerificationPolicy> policies) {
        this.policies = policies;
    }

    @Override
    public void verify(@Nonnull LDAPResource resource) throws LDAPException {
        for (LDAPVerificationPolicy policy : policies) {
            policy.verify(resource);
        }
    }
}
