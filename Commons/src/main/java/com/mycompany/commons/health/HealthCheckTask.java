package com.mycompany.commons.health;
import com.codahale.metrics.health.HealthCheck;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.api.IServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HealthCheckTask extends HealthCheck {
    private final Logger log = LoggerFactory.getLogger(HealthCheckTask.class);
    private final IAPI api;
    

    public HealthCheckTask(IAPI api) {
        this.api = api;

    }

    @Override
    protected Result check() throws Exception {
        this.log.info("checking");
        
        IServiceInfo resp = this.api.getServiceInfo();
        
        if(resp.getName().isEmpty()) {
            return Result.unhealthy("ServiceName is empty for: "
                    +api.getClass().getName());
        }
        if(resp.getDescription().isEmpty()) {
            return Result.unhealthy("ServiceDescription is empty for: "
                    +api.getClass().getName());
        }
        return Result.healthy();
    }
}
