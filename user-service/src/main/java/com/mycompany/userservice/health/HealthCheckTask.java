package com.mycompany.userservice.health;
import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Optional;
import com.mycompany.userservice.resources.ServiceInfo;
import com.mycompany.userservice.routes.DefaultRoute;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HealthCheckTask extends HealthCheck {
    private final Logger log = LoggerFactory.getLogger(HealthCheckTask.class);
    private final String template;
    

    public HealthCheckTask(String template) {
        this.template = template;

    }

    @Override
    protected Result check() throws Exception {
        this.log.info("check");
        final String saying = String.format(template, "TEST-User-service");
        if (!saying.contains("TEST-User-service")) {
            return Result.unhealthy("template doesn't include a name");
        }
        
        /*Optional<String> param = Optional.fromNullable("");
        final List<Task> tasks = this.taskRoute.listTasks(param);
         this.log.info("tasks size: " +tasks.size());
        if(tasks.isEmpty()) {
            return Result.healthy("Task list empty");
        }*/
        return Result.healthy();
    }
}
