package com.mycompany.tasklistservice;

import com.mycompany.tasklistservice.configurations.IConfigurator;
import com.mycompany.tasklistservice.configurations.TaskListServiceConfiguration;
import com.mycompany.tasklistservice.routes.TaskListRoute;
import com.mycompany.tasklistservice.routes.DefaultRoute;
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
        final TaskListRoute resource = new TaskListRoute(myConfigs.getMaxLength());
        final DefaultRoute defaultRoute = new DefaultRoute(myConfigs);
        
        environment.jersey().register(resource);
        environment.jersey().register(defaultRoute);
    }

}