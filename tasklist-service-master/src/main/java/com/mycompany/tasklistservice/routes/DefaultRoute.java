package com.mycompany.tasklistservice.routes;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.io.CharStreams;
import com.google.common.util.concurrent.Service;
import com.mycompany.tasklistservice.configurations.IConfigurator;
import com.mycompany.tasklistservice.resources.ServiceInfo;
import com.mycompany.tasklistservice.resources.Task;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DefaultRoute {
    
    private final IConfigurator configurator;
    Logger log = LoggerFactory.getLogger(DefaultRoute.class);
    
    public DefaultRoute(IConfigurator configurator) {
        this.configurator = configurator;
    }
    
    @GET
    @Timed
    public ServiceInfo info() {        
        return new ServiceInfo.Builder(this.configurator).build();
    }
}
