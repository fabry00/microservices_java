package com.mycompany.accountservice;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.Configuration;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Config example https://github.com/dropwizard/dropwizard/tree/master/dropwizard-example
 * @author fabry
 */
public class AccountServiceConfiguration extends Configuration {
    public static final String SERVICE_NAME = "AccountService";
    public static final String SERVICE_DESC = "User Account management";
    
    @NotEmpty
    private final String jwtTokenSecret = "asdasdasdasdasdasds";
    @NotEmpty
    private final int tokenExpirationMin = 60;

    
    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }
    
    public int getTokenExpiration() {
        return this.tokenExpirationMin;
    }

    ImmutableMap<String, String> getConnectionPArams() {
        // FIXME get from configuration
        return ImmutableMap.of();
    }
    
    
}
