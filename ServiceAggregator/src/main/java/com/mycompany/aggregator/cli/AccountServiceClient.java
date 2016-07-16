package com.mycompany.aggregator.cli;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.aggregator.api.ServiceInfo;
import com.mycompany.commons.api.IServiceInfo;
import com.mycompany.commons.headers.Header;
import io.dropwizard.client.HttpClientBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class AccountServiceClient {
    
    private final HttpClient httpClient;
    private final String basePath;
    public AccountServiceClient(String host, int port){
        
        MetricRegistry metric = new MetricRegistry();
        httpClient = new HttpClientBuilder(metric).build(getClass().getName());
        basePath = "http://"+host+":"+port+"/";
    }   
    
    
    public IServiceInfo getServiceInfo() {        
        try {
            HttpGet request = new HttpGet(this.basePath);
            request.setHeader(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            HttpResponse response = httpClient.execute(request);
            ObjectMapper mapper = new ObjectMapper();
            IServiceInfo myObject = mapper.readValue(response.getEntity().getContent(), 
                                                    ServiceInfo.class);
            return myObject;
        } catch (IOException ex) {
            Logger.getLogger(AccountServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
            
}
