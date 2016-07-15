package com.mycompany.accountservice.health;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Optional;
import com.mycompany.accountservice.client.AccountServiceClient_2;
import io.dropwizard.client.ConfiguredCloseableHttpClient;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.client.HttpClientConfiguration;
import org.apache.http.impl.client.CloseableHttpClient;

public class HealthCheckTask extends HealthCheck {
    private final Logger log = LoggerFactory.getLogger(HealthCheckTask.class);
    private final String template;
    private final AccountServiceClient_2 client;

    public HealthCheckTask(AccountServiceClient_2 client,String template) {
        this.template = template;
        this.client = client;
       
    }

    @Override
    protected Result check() throws Exception {
        this.log.info("check");
        /*final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }*/
        
        final String info = this.client.getServiceInfo();
        this.log.info("INFO: " +info);
        if(info.isEmpty()) {
            return Result.healthy("Task list empty");
        }
        return Result.healthy();
    }
}
