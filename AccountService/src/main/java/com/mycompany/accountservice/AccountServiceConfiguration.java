package com.mycompany.accountservice;

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
    
    @NotEmpty
    private final String jwtTokenSecret = "bfsdgs5ty756h4w5g5bg6w_56-45w7.3";
    @NotEmpty
    private final int tokenExpirationMin = 60;

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }
    
    public int getTokenExpiration() {
        return this.tokenExpirationMin;
    }
}
