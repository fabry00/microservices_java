package com.mycompany.accountservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.commons.api.BaseServiceAPI;
import com.mycompany.commons.api.SystemUnreachable;
import com.mycompany.commons.headers.Header;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceAPI extends BaseServiceAPI{
    private final Logger log = LoggerFactory.getLogger(AccountServiceAPI.class);
    private static final String JWT = "/jwt/";
    private static final String CHECK_TOKEN = "check-token";
    private static final String GENERATE_TOKEN = "generate-valid-token";
    
    public AccountServiceAPI(URI uri){
        super(uri);
    }
    
    public MyUser checkToken(String token) throws SystemUnreachable{
        try {
           
            
            HttpGet request = new HttpGet(getUri()+JWT+CHECK_TOKEN);
            request.setHeader(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            HttpResponse response = getClient().execute(request);
            ObjectMapper mapper = new ObjectMapper();
            MyUser myObject = mapper.readValue(response.getEntity().getContent(), 
                                              MyUser.class);
            return myObject;
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        
        throw new SystemUnreachable("unable to connect");
    }
    
    
    public Token getValidToken(String email, String password) throws SystemUnreachable{
        try {
             URIBuilder builder = new URIBuilder(getUri()+JWT+GENERATE_TOKEN);
             builder.setParameter("email", email);
             builder.setParameter("password", password);
            HttpGet request = new HttpGet(builder.build());
            request.setHeader(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON);
           
            HttpResponse response = getClient().execute(request);
            ObjectMapper mapper = new ObjectMapper();
            Token myObject = mapper.readValue(response.getEntity().getContent(), 
                                              Token.class);
            return myObject;
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } catch (URISyntaxException ex) {
            log.error(ex.getMessage());
        }
        
        throw new SystemUnreachable("unable to connect");
    }
    
    
}
