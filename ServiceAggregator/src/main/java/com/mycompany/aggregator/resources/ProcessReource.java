package com.mycompany.aggregator.resources;

import com.google.common.base.Optional;
import com.mycompany.aggregator.ServiceAggregatorConfiguration;
import com.mycompany.commons.api.SystemUnreachable;
import com.mycompany.processservice.api.ProcessServiceAPI;
import com.mycompany.processservice.api.Task;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/"+ServiceAggregatorConfiguration.API_V)
@Produces(MediaType.APPLICATION_JSON)
public class ProcessReource {

    private final Logger log = LoggerFactory.getLogger(ProcessReource.class);

    private ProcessServiceAPI processAPI;

    public void setApis(ProcessServiceAPI processAPI) {
        this.processAPI = processAPI;
    }

    @GET
    @Path("/" + ProcessServiceAPI.TASK_LIST)
    /*@QueryParam("contains") Optional<String> contains*/
    public Task[] getTaskList() {
        log.info("getTaskList");

        try {
            Optional<String> op = Optional.of("");
            return processAPI.getTasks(op);

        } catch (SystemUnreachable ex) {
            log.error("Service unreachable");
        }

        throw new WebApplicationException("Service unreachable",
                Response.Status.REQUEST_TIMEOUT);
    }

    public static class Builder {

        private ProcessServiceAPI processAPI;

        public Builder withAccountApi(ProcessServiceAPI api) {
            this.processAPI = api;
            return this;
        }

        public ProcessReource build() {
            ProcessReource resource = new ProcessReource();
            resource.setApis(processAPI);
            return resource;
        }
    }
}
