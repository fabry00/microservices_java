package com.mycompany.aggregator.resources;

import com.mycompany.commons.api.IServiceInfo;
import com.codahale.metrics.annotation.Timed;
import com.mycompany.aggregator.ServiceAggregatorConfiguration;
import com.mycompany.aggregator.api.systemstatus.ISubSystemStatusInfo;
import com.mycompany.aggregator.api.systemstatus.ISystemStatusInfo;
import com.mycompany.aggregator.api.systemstatus.SubSystemStatusInfo;
import com.mycompany.aggregator.api.systemstatus.SystemStatus;
import com.mycompany.aggregator.api.systemstatus.SystemStatusInfo;
import com.mycompany.commons.api.IAPI;
import com.mycompany.commons.api.SystemUnreachable;
import java.util.Set;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/"+ServiceAggregatorConfiguration.API_V)
@Produces(MediaType.APPLICATION_JSON)
public class SystemStatusResource {

    private final Logger log = LoggerFactory.getLogger(SystemStatusResource.class);
    
    private Set<IAPI> apis;
    private final String serviceName;
    private final String serviceDesc;
    private final String apiV;

    private SystemStatusResource(String name, String desc, String apiV) {
        this.serviceName = name;
        this.serviceDesc = desc;
        this.apiV = apiV;
    }

    public void setApis(Set<IAPI> apis) {
        this.apis = apis;
    }

    @GET
    @Timed
    @Path("/system-status")
    public IServiceInfo serviceInfo() {

        ISystemStatusInfo systemStatusInfo = new SystemStatusInfo();
        systemStatusInfo.setName(this.serviceName);
        systemStatusInfo.setDescription(this.serviceDesc);

        for (IAPI api : this.apis) {
            log.info("Checking API: " + api.getClass().getName());
            ISubSystemStatusInfo subSystemInfo = new SubSystemStatusInfo();

            // Http request
            try {
                IServiceInfo resp = api.getServiceInfo();

                subSystemInfo.setName(resp.getName());
                subSystemInfo.setDescription(resp.getDescription());

                if (resp.getName().isEmpty()
                        || resp.getDescription().isEmpty()) {
                    log.warn("API: " + api.getClass().getName() + " NOT AVAILABLE");

                    break;
                }
                log.info("API: " + resp.getName() + " AVAILABLE");
                subSystemInfo.setStauts(SystemStatus.HEALTHY);
            } catch (SystemUnreachable ex) {
                log.warn("API: " + api.getClass().getName() + " " + ex.getMessage());
                subSystemInfo.setName(api.getClass().getSimpleName());
                subSystemInfo.setStauts(SystemStatus.UNHEALTHY);
            }

            systemStatusInfo.addSubSystemStatus(subSystemInfo);
        }

        return systemStatusInfo;
    }

    public static class Builder {

        private Set<IAPI> apis;
        private String name;
        private String desc;
        private String apiV;
        
        public Builder withApis(Set<IAPI> apis) {
            this.apis = apis;
            return this;
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withDesc(String desc) {
            this.desc = desc;
            return this;
        }
        
        public Builder withApiVersion(String apiVersion) {
            this.apiV = apiVersion;
            return this;
        }

        public SystemStatusResource build() {
            SystemStatusResource resource 
                    = new SystemStatusResource(this.name,
                                               this.desc,
                    this.apiV);
            resource.setApis(apis);
            return resource;
        }
    }
}
