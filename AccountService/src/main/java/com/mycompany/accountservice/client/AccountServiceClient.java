package com.mycompany.accountservice.client;

import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Optional;
import io.dropwizard.client.HttpClientBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

public class AccountServiceClient {
    
    private final HttpClient httpClient;
    private final String basePath;
    public AccountServiceClient(String host, int port){
        
        MetricRegistry metric = new MetricRegistry();
        httpClient = new HttpClientBuilder(metric).build(getClass().getName());
        basePath = "http://"+host+":"+port+"/";
    }   
    
    
    public String getServiceInfo() {        
        Optional<String> result = Optional.absent();
        try {
            HttpGet request = new HttpGet(this.basePath);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            result = Optional.of(httpClient.execute(request,responseHandler));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(AccountServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result.get();
    }
            
}
