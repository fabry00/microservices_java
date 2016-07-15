package com.mycompany.accountservice.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class AccountServiceClient {
    
    private final Client client;
    private final String basePath;
    public AccountServiceClient(String host, int port){
        client = Client.create();
        basePath = "http://"+host+":"+port+"/";
    }   
    
    
    public String getServiceInfo() {
        WebResource webResource = client.resource(basePath);
        ClientResponse response = webResource.accept("application/json")
                                            .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                 + response.getStatus());
        }
        String output = response.getEntity(String.class);
        return output;
    }
            
}
