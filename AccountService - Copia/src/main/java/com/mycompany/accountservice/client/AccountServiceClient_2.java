package com.mycompany.accountservice.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author fabry
 */
public class AccountServiceClient_2 {
    private final Client client;
    
    public AccountServiceClient_2(ClientConfig configuration) {
        client = ClientBuilder.newClient();
        
    }
    
    public String getServiceInfo() {
        WebTarget target = client.target("http://localhost:9080").path("/");
        //Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        Response response = target.request(MediaType.TEXT_PLAIN).get();
        return response.toString();
    }
    
    
}
