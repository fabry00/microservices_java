package com.mycompany.userservice;

import com.mycompany.userservice.configurations.IConfigurator;
import com.mycompany.userservice.configurations.UserServiceConfiguration;
import com.mycompany.userservice.health.HealthCheckTask;
import com.mycompany.userservice.routes.TaskListRoute;
import com.mycompany.userservice.routes.DefaultRoute;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class UserServiceApplication extends Application<UserServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new UserServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "task-list-service";
    }

    @Override
    public void initialize(Bootstrap<UserServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(UserServiceConfiguration configuration,
                    Environment environment) {
        // register resource now
        IConfigurator myConfigs = (IConfigurator) configuration;
        final TaskListRoute resource = new TaskListRoute(myConfigs.getMaxLength());
        final DefaultRoute defaultRoute = new DefaultRoute(myConfigs);

        final HealthCheckTask healthCheck = new HealthCheckTask(resource,myConfigs.getTemplate());
      
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(defaultRoute);
    }

}