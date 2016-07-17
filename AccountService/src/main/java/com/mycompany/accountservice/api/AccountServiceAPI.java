package com.mycompany.accountservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.commons.api.BaseServiceAPI;
import com.mycompany.commons.api.SystemUnreachable;
import com.mycompany.commons.headers.Header;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

public class AccountServiceAPI extends BaseServiceAPI{
    private static final String JWT = "/jwt/";
    private static final String CHECK_TOKEN = "check-token";
    
    public AccountServiceAPI(URI uri){
        super(uri);
    }
    
    public Token checkToken() throws SystemUnreachable{
        try {
            HttpGet request = new HttpGet(getUri()+JWT+CHECK_TOKEN);
            request.setHeader(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            HttpResponse response = getClient().execute(request);
            ObjectMapper mapper = new ObjectMapper();
            Token myObject = mapper.readValue(response.getEntity().getContent(), 
                                              Token.class);
            return myObject;
        } catch (IOException ex) {
            Logger.getLogger(BaseServiceAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new SystemUnreachable("unable to connect");
    }
}
