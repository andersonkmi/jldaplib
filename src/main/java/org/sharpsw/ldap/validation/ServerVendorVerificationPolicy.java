package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidServerVendorException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

import static org.sharpsw.ldap.services.LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER;
import static org.sharpsw.ldap.services.LDAPServiceProviderType.MS_ACTIVE_DIRECTORY_2003_SERVICE_PROVIDER;
import static org.sharpsw.ldap.services.LDAPServiceProviderType.OPEN_LDAP_SERVICE_PROVIDER;

public class ServerVendorVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws LDAPException {
        String vendor = resource.getServerVendor();

        if (vendor == null) {
            throw new InvalidServerVendorException("Null vendor name supplied");
        }

        if (vendor.isEmpty()) {
            throw new InvalidServerVendorException("Empty vendor name supplied");
        }

        // check the value
        if (!(vendor.equals(APACHEDS_SERVICE_PROVIDER.toString()) ||
                vendor.equals(MS_ACTIVE_DIRECTORY_2003_SERVICE_PROVIDER.toString()) ||
                vendor.equals(OPEN_LDAP_SERVICE_PROVIDER.toString()))) {
            throw new InvalidServerVendorException("Invalid vendor supplied '" + vendor + "'");
        }
    }
}
