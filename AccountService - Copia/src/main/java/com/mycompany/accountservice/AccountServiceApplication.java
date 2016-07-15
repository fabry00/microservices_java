package com.mycompany.accountservice;

import com.mycompany.accountservice.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
                    final Environment environment) {
         environment.jersey().register(new HelloResource());
         
    }

}
