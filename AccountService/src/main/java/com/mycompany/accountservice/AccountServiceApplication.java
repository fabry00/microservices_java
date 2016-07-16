package com.mycompany.accountservice;

import com.mycompany.accountservice.api.AccountServiceAPI;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.health.HealthCheckTask;
import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.jetty.server.Connector;

public class AccountServiceApplication extends Application<AccountServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AccountServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return AccountServiceConfiguration.SERVICE_NAME;
    }

    @Override
    public void initialize(final Bootstrap<AccountServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final AccountServiceConfiguration configuration,
            final Environment environment) throws URISyntaxException {

        environment.healthChecks().register(AccountServiceConfiguration.SERVICE_NAME,
                getHealthCheck(configuration,environment));
        
        
        environment.jersey().register(getDefault());

    }

    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(AccountServiceConfiguration.SERVICE_NAME)
                .withDesc(AccountServiceConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }

    private HealthCheckTask getHealthCheck(final AccountServiceConfiguration configuration,
            final Environment environment) throws URISyntaxException {
        
        /*URI uri = configuration.getServerFactory()
                .build(environment)
                .getURI();
        */
        // FIXME --> get from config or discover it
        URI uri = new URI("http://localhost:9084");
        IAPI api = new AccountServiceAPI(uri);
        HealthCheckTask checker = new HealthCheckTask(api);
        return checker;
    }
}
