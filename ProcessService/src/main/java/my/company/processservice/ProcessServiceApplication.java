package my.company.processservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
                    final Environment environment) {
        // TODO: implement application
    }

}
