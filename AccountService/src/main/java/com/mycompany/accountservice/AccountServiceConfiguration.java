package com.mycompany.accountservice;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.Configuration;
import java.io.UnsupportedEncodingException;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Config example https://github.com/dropwizard/dropwizard/tree/master/dropwizard-example
 * @author fabry
 */
public class AccountServiceConfiguration extends Configuration {
    public static final String SERVICE_NAME = "AccountService";
    public static final String SERVICE_DESC = "User Account management";
    public static final int TOKEN_EXPIRATION = 60;
    public static final String REALM = "secret_real_string";
    
    @NotEmpty
    private final String jwtTokenSecret = "asdasdasdasdasdasds";
  
    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }
    
    ImmutableMap<String, String> getConnectionParams() {
        // FIXME get from configuration
        return ImmutableMap.of();
    }
    
    
}
