package org.sharpsw.ldap.services;

import java.security.cert.Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public class TLSHostnameVerifies implements HostnameVerifier {

	@Override
	public boolean verify(String hostname, SSLSession session) {
		try {
			Certificate[] certs = session.getPeerCertificates();
			for(int index = 0; index < certs.length; index++) {
				
				//Certificate certificate = certs[index];				
			}			
		} catch (SSLPeerUnverifiedException exception) {
		}
		return true;
	}

}
