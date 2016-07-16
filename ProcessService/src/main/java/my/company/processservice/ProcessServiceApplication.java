package my.company.processservice;

import com.mycompany.commons.resource.DefaultResource;
import com.mycompany.commons.resource.IDefaultResource;
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
       
        environment.jersey().register(getDefault());

    }

    private IDefaultResource getDefault() {
        IDefaultResource defaultRes = new DefaultResource.Builder()
                .withName(ProcessServiceConfiguration.SERVICE_NAME)
                .withDesc(ProcessServiceConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }
}
