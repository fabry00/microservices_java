package com.mycompany.accountservice;

import com.mycompany.accountservice.api.AccountServiceAPI;
import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.resources.SecuredResource;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.health.HealthCheckTask;
import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import com.github.toastshaman.dropwizard.auth.jwt.JWTAuthFilter;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Verifier;
import com.github.toastshaman.dropwizard.auth.jwt.parser.DefaultJsonWebTokenParser;
import com.mycompany.accountservice.auth.MyAuthenticator;
import com.mycompany.accountservice.dao.IDAOFactory;
import com.mycompany.accountservice.dao.impl.inmemory.MemoryDaoFactory;

import java.security.Principal;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class AccountServiceApplication extends Application<AccountServiceConfiguration> {

    private IDAOFactory daoFactory;

    public static void main(final String[] args) throws Exception {
        new AccountServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return AccountServiceConfiguration.SERVICE_NAME;
    }

    @Override
    public void initialize(final Bootstrap<AccountServiceConfiguration> bootstrap) {
        daoFactory = new MemoryDaoFactory();

    }

    @Override
    public void run(final AccountServiceConfiguration configuration,
            final Environment environment) throws URISyntaxException, UnsupportedEncodingException {

        daoFactory.setConnectionProperties(configuration.getConnectionParams());

        environment.healthChecks().register(AccountServiceConfiguration.SERVICE_NAME,
                getHealthCheck(configuration, environment));

        environment.jersey().register(getDefault());

        environment.jersey().register(getAuthenticator(configuration.getJwtTokenSecret()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new SecuredResource(
                configuration.getJwtTokenSecret(),
                AccountServiceConfiguration.TOKEN_EXPIRATION,
                daoFactory));
    }

    private AuthDynamicFeature getAuthenticator(byte[] tokenSecret) {
        final JsonWebTokenParser tokenParser = new DefaultJsonWebTokenParser();
        final HmacSHA512Verifier tokenVerifier = new HmacSHA512Verifier(tokenSecret);
        
        AuthDynamicFeature auth = new AuthDynamicFeature(
                new JWTAuthFilter.Builder<MyUser>()
                .setTokenParser(tokenParser)
                .setTokenVerifier(tokenVerifier)
                .setRealm(AccountServiceConfiguration.REALM)
                .setPrefix("Bearer")
                .setAuthenticator(new MyAuthenticator(daoFactory) {})                        
                .buildAuthFilter());

        return auth;
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
