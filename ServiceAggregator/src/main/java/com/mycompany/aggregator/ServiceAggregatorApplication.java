package com.mycompany.aggregator;

import com.google.common.collect.ImmutableSet;
import com.mycompany.processservice.api.ProcessServiceAPI;
import com.mycompany.accountservice.api.AccountServiceAPI;
import com.mycompany.aggregator.health.HealthCheckTask;
import com.mycompany.aggregator.resources.SystemStatusResource;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

public class ServiceAggregatorApplication extends Application<ServiceAggregatorConfiguration> {

    private Set<IAPI> apis;
    
    public static void main(final String[] args) throws Exception {
        new ServiceAggregatorApplication().run(args);
    }

    @Override
    public String getName() {
        return ServiceAggregatorConfiguration.SERVICE_NAME;
    }

    @Override
    public void initialize(final Bootstrap<ServiceAggregatorConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ServiceAggregatorConfiguration configuration,
                    final Environment environment) throws URISyntaxException {
        
        buildApis();
                
        environment.healthChecks().register(ServiceAggregatorConfiguration.SERVICE_NAME,
                                    getHealthCheck());
        
        environment.jersey().register(getDefault());
        environment.jersey().register(getSystemStatus());
        
       /* AccountServiceAPI client = new AccountServiceAPI("localhost",9084);
        IServiceInfo resp = client.getServiceInfo();
        System.out.println("########### "+resp);*/
        /*AccountServiceAPI client = new AccountServiceAPI("localhost",9080);
        ServiceInfo resp = client.getServiceInfo();
        System.out.println("########### "+resp);*/
    }
    
    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(ServiceAggregatorConfiguration.SERVICE_NAME)
                .withDesc(ServiceAggregatorConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }
    
    
    private SystemStatusResource getSystemStatus() {
        SystemStatusResource systemStatus = new SystemStatusResource.Builder()
                .withName(ServiceAggregatorConfiguration.SERVICE_NAME)
                .withDesc(ServiceAggregatorConfiguration.SERVICE_DESC)
                .withApis(apis)
                .build();
        return systemStatus;
    }
    
    private HealthCheckTask getHealthCheck(){
        
        HealthCheckTask checker = new HealthCheckTask(apis);
        return checker;
    }
    
    
    private void buildApis() throws URISyntaxException {
        
        ImmutableSet.Builder<IAPI> builder
                = new ImmutableSet.Builder<>();
        
        // FIXME retreive data from configurations
        URI uri = new URI("http://localhost:9084");
        builder.add(new AccountServiceAPI(uri));
        
        uri = new URI("http://localhost:9082");
        builder.add(new ProcessServiceAPI(uri));
        
        apis = builder.build();
    }

}
