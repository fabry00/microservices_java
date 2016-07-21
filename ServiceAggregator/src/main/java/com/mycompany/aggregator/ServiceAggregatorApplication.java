package com.mycompany.aggregator;

import com.google.common.collect.ImmutableSet;
import com.mycompany.processservice.api.ProcessServiceAPI;
import com.mycompany.accountservice.api.AccountServiceAPI;
import com.mycompany.aggregator.health.HealthCheckTask;
import com.mycompany.aggregator.resources.AccountResource;
import com.mycompany.aggregator.resources.ProcessReource;
import com.mycompany.aggregator.resources.SystemStatusResource;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.Set;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class ServiceAggregatorApplication extends Application<ServiceAggregatorConfiguration> {

    private Set<IAPI> apis;
    private AccountServiceAPI accountApi;
    private ProcessServiceAPI processApi;

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

        enableCors(environment);
        buildApis();

        environment.healthChecks().register(ServiceAggregatorConfiguration.SERVICE_NAME,
                getHealthCheck());

        environment.jersey().register(getDefault());
        environment.jersey().register(getSystemStatus());
        environment.jersey().register(getAccountResource());
        environment.jersey().register(getProcessResource());

    }

    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(ServiceAggregatorConfiguration.SERVICE_NAME)
                .withDesc(ServiceAggregatorConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }

    private AccountResource getAccountResource() {
        return new AccountResource.Builder().withAccountApi(accountApi).build();
    }

    private ProcessReource getProcessResource() {
        return new ProcessReource.Builder().withAccountApi(processApi).build();
    }

    private SystemStatusResource getSystemStatus() {
        SystemStatusResource systemStatus = new SystemStatusResource.Builder()
                .withApis(apis)
                .withName(ServiceAggregatorConfiguration.SERVICE_NAME)
                .withDesc(ServiceAggregatorConfiguration.SERVICE_DESC)
                .build();
        return systemStatus;
    }

    private HealthCheckTask getHealthCheck() {

        HealthCheckTask checker = new HealthCheckTask(apis);
        return checker;
    }

    private void buildApis() throws URISyntaxException {

        ImmutableSet.Builder<IAPI> builder
                = new ImmutableSet.Builder<>();

        // FIXME retreive data from configurations
        URI uri = new URI("http://localhost:9084");
        accountApi = new AccountServiceAPI(uri);
        builder.add(accountApi);

        uri = new URI("http://localhost:9082");
        processApi = new ProcessServiceAPI(uri);
        builder.add(processApi);

        apis = builder.build();
    }

    private void enableCors(Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors
                = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
