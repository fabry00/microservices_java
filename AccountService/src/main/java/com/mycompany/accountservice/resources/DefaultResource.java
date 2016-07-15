package com.mycompany.accountservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.accountservice.AccountServiceConfiguration;
import com.mycompany.accountservice.api.ServiceInfo;
import com.mycompany.accountservice.resources.builder.ServiceInfoBuilder;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DefaultResource {

    @GET
    @Timed
    public ServiceInfo serviceInfo() {
        return new ServiceInfoBuilder()
                    .withName(AccountServiceConfiguration.SERVICE_NAME)
                    .withDesc(AccountServiceConfiguration.SERVICE_DESC)
                    .build();
    }
}