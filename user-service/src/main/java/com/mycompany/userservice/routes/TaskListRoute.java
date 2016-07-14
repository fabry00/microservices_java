package com.mycompany.userservice.routes;

import com.mycompany.userservice.resources.Task;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.google.common.io.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bartoszjedrzejewski on 03/01/2016.
 */
@Path("/task-list")
@Produces(MediaType.APPLICATION_JSON)
public class TaskListRoute {
    private final int maxLength;
    private final AtomicLong counter;
    //SLF4J is provided with dropwizard
    Logger log = LoggerFactory.getLogger(TaskListRoute.class);

    public TaskListRoute(int maxLength) {
        this.maxLength = maxLength;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    // /taks-list
    // /taks-list?contains=string
    public List<Task> listTasks(@QueryParam("contains") Optional<String> contains) {
        List<Task> tasks = new ArrayList<Task>();
        
        String query = contains.or("");
        log.info("Query: "+query);
        
        try {
            //Get processes from the terminal
            Process p = Runtime.getRuntime().exec("ps -e");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            //Dropwizard comes with google guava
            List<String> lines = CharStreams.readLines(input);
            //First line contains no data so it is omitted
            for(int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                //filter the processes depending on the ?contains= from the url
                if(line.contains(query)) {
                    //trim the processes according to the maxLength
                    Task task = new Task.Builder()
                                        .withId(counter.getAndIncrement())
                                        .withContent(line.substring(0, Math.min(line.length(), maxLength))).build();
                    tasks.add(task);
                }
            }
            input.close();
        } catch (Exception e) {
            log.error("Exception in listTasks method.", e);
        }
        return tasks;
    }
}