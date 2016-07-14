package com.mycompany.userservice.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bartoszjedrzejewski on 03/01/2016.
 */
public class Task {
    private long id;

    private String content;

    private Task() {
        // Jackson deserialization
    }

    private Task(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
    
    
    public static class Builder {
        
        private long id;
        private String content;
        
        public Builder withId(long id) {
            this.id = id;
            return this;
        }
        
        public Builder withContent(String content){
            this.content = content;
            return this;
        }
        
        public Task build() {
            return new Task(this.id, this.content);
        }
    }
}
