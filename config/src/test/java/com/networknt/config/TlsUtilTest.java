package com.networknt.config;

import com.networknt.config.TlsUtil;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyStore;

import static junit.framework.TestCase.fail;

public class TlsUtilTest {
    private final static String KEYSTORE_NAME = "client.keystore";
    private final static String INVALID_KEYSTORE_NAME = "invalid.keystore";
    private final static String OTHER_EXTENTION = "client.jks";
    private final static String TRUSTSTORE_NAME = "client.truststore";
    private final static String INVALID_TRUST_NAME = "invalid.truststore";
    private final static char[] PASSWORD = "password".toCharArray();

    @Test
    public void testLoadValidKeyStore() {
        KeyStore keyStore = TlsUtil.loadKeyStore(KEYSTORE_NAME, PASSWORD);
        Assert.assertNotNull(keyStore);
    }

    @Test
    public void testLoadInvalidKeyStore() {
        try {
            KeyStore keyStore = TlsUtil.loadKeyStore(INVALID_KEYSTORE_NAME, PASSWORD);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Unable to load stream for keystore " + INVALID_KEYSTORE_NAME);
        }
        try {
            KeyStore keyStore = TlsUtil.loadKeyStore(OTHER_EXTENTION, PASSWORD);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Unable to load stream for keystore " + OTHER_EXTENTION);
        }
    }

    @Test
    public void testLoadTrustStore() {
        KeyStore keyStore = TlsUtil.loadKeyStore(TRUSTSTORE_NAME, PASSWORD);
        Assert.assertNotNull(keyStore);
    }

    @Test
    public void testLoadInvalidTrustStore() {
        try {
            KeyStore keyStore = TlsUtil.loadKeyStore(INVALID_TRUST_NAME, PASSWORD);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Unable to load stream for keystore " + INVALID_TRUST_NAME);
        }
    }

    @Test
    public void testLoadPKCS12TrustStore() {
        KeyStore keyStore = TlsUtil.loadKeyStore("clientpkcs12.truststore", PASSWORD);
        Assert.assertNotNull(keyStore);
    }

    @Test
    public void testLoadPKCS12KeyStore() {
        KeyStore keyStore = TlsUtil.loadKeyStore("serverpkcs12.keystore", PASSWORD);
        Assert.assertNotNull(keyStore);
    }

}
