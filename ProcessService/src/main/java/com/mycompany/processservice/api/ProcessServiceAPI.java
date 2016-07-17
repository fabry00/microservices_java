package com.mycompany.processservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.commons.api.BaseServiceAPI;
import com.mycompany.commons.api.SystemUnreachable;
import com.mycompany.commons.headers.Header;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessServiceAPI extends BaseServiceAPI {

    private final Logger log = LoggerFactory.getLogger(ProcessServiceAPI.class);
    public static final String TASK = "/task/";
    public static final String TASK_LIST = "task-list";

    public ProcessServiceAPI(URI uri) {
        super(uri);
    }

    public Task[] getTasks() throws SystemUnreachable {
        try {
            HttpGet request = new HttpGet(getUri() + TASK + TASK_LIST);
            request.setHeader(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            HttpResponse response = getClient().execute(request);
            ObjectMapper mapper = new ObjectMapper();
            Task[] myObject = mapper.readValue(response.getEntity().getContent(),
                    Task[].class);
            return myObject;
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        throw new SystemUnreachable("unable to connect");
    }
}
