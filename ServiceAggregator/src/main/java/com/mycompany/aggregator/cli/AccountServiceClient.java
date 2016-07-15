package com.mycompany.aggregator.cli;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.mycompany.accountservice.api.ServiceInfo;
import io.dropwizard.client.HttpClientBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.util.EntityUtils;

public class AccountServiceClient {
    
    private final HttpClient httpClient;
    private final String basePath;
    public AccountServiceClient(String host, int port){
        
        MetricRegistry metric = new MetricRegistry();
        httpClient = new HttpClientBuilder(metric).build(getClass().getName());
        basePath = "http://"+host+":"+port+"/";
    }   
    
    
    public ServiceInfo getServiceInfo() {        
        Optional<String> result = Optional.absent();
        try {
            HttpGet request = new HttpGet(this.basePath);
            request.setHeader("Content-type", "application/json");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            result = Optional.of(httpClient.execute(request,responseHandler));
            HttpResponse response = httpClient.execute(request);
            ObjectMapper mapper = new ObjectMapper();
            ServiceInfo myObject = mapper.readValue(response.getEntity().getContent(), 
                                                    ServiceInfo.class);
            return myObject;
        } catch (IOException ex) {
            Logger.getLogger(AccountServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
            
}
