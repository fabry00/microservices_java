package com.mycompany.aggregator;

import com.mycompany.processservice.api.ProcessServiceAPI;
import com.mycompany.accountservice.api.AccountServiceAPI;
import com.mycompany.aggregator.health.HealthCheckTask;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAggregatorApplication extends Application<ServiceAggregatorConfiguration> {

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
        
        environment.healthChecks().register(ServiceAggregatorConfiguration.SERVICE_NAME,
                                    getHealthCheck(configuration,environment));
        
        environment.jersey().register(getDefault());
        
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
    
    
    private HealthCheckTask getHealthCheck(final ServiceAggregatorConfiguration configuration,
            final Environment environment) throws URISyntaxException {
        
        /*URI uri = configuration.getServerFactory()
                .build(environment)
                .getURI();
        */
        
        List<IAPI> apis = new ArrayList<>();
        // FIXME --> get from config or discover it
        URI uri = new URI("http://localhost:9084");
        apis.add(new AccountServiceAPI(uri));
        
        uri = new URI("http://localhost:9082");
        apis.add(new ProcessServiceAPI(uri));
        
        HealthCheckTask checker = new HealthCheckTask(apis);
        return checker;
    }

}
