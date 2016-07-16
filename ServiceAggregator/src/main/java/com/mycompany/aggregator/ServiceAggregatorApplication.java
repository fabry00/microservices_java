package com.mycompany.aggregator;


import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
                    final Environment environment) {
        
        environment.jersey().register(getDefault());
        
        /*AccountServiceClient client = new AccountServiceClient("localhost",9080);
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

}
