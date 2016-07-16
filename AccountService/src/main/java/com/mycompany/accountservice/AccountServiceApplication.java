package com.mycompany.accountservice;

import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
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

        environment.jersey().register(getDefault());

    }

    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(AccountServiceConfiguration.SERVICE_NAME)
                .withDesc(AccountServiceConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }
}
