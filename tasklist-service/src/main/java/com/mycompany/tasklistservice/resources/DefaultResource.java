package com.mycompany.tasklistservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.tasklistservice.configurations.IConfigurator;
import com.mycompany.tasklistservice.resources.ServiceInfo;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DefaultResource {
    
    private final IConfigurator configurator;
    Logger log = LoggerFactory.getLogger(DefaultResource.class);
    
    public DefaultResource(IConfigurator configurator) {
        this.configurator = configurator;
    }
    
    @GET
    @Timed
    public ServiceInfo info() {        
        return new ServiceInfo.Builder(this.configurator).build();
    }
}
