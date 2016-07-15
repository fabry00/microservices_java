package com.mycompany.tasklistservice;

import com.mycompany.tasklistservice.configurations.IConfigurator;
import com.mycompany.tasklistservice.configurations.TaskListServiceConfiguration;
import com.mycompany.tasklistservice.health.HealthCheckTask;
import com.mycompany.tasklistservice.resources.TaskListResource;
import com.mycompany.tasklistservice.resources.DefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TaskListServiceApplication extends Application<TaskListServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new TaskListServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "task-list-service";
    }

    @Override
    public void initialize(Bootstrap<TaskListServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TaskListServiceConfiguration configuration,
                    Environment environment) {
        // register resource now
        IConfigurator myConfigs = (IConfigurator) configuration;
        final TaskListResource resource = new TaskListResource(myConfigs.getMaxLength());
        final DefaultResource defaultRoute = new DefaultResource(myConfigs);

        final HealthCheckTask healthCheck = new HealthCheckTask(resource,myConfigs.getTemplate());
      
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(defaultRoute);
    }

}