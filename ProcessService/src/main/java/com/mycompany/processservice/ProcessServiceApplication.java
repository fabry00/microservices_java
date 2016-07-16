package com.mycompany.processservice;

import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.health.HealthCheckTask;
import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URI;
import java.net.URISyntaxException;
import com.mycompany.processservice.api.ProcessServiceAPI;

public class ProcessServiceApplication extends Application<ProcessServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProcessServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "ProcessService";
    }

    @Override
    public void initialize(final Bootstrap<ProcessServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ProcessServiceConfiguration configuration,
                    final Environment environment) throws URISyntaxException {
       
        environment.healthChecks().register(ProcessServiceConfiguration.SERVICE_NAME,
                                    getHealthCheck(configuration,environment));
        environment.jersey().register(getDefault());

    }

    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(ProcessServiceConfiguration.SERVICE_NAME)
                .withDesc(ProcessServiceConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }
    
    private HealthCheckTask getHealthCheck(final ProcessServiceConfiguration configuration,
            final Environment environment) throws URISyntaxException {
        
        /*URI uri = configuration.getServerFactory()
                .build(environment)
                .getURI();
        */
        // FIXME --> get from config or discover it
        URI uri = new URI("http://localhost:9082");
        IAPI api = new ProcessServiceAPI(uri);
        HealthCheckTask checker = new HealthCheckTask(api);
        return checker;
    }
}
