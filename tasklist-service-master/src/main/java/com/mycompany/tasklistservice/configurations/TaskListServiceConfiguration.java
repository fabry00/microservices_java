package com.mycompany.tasklistservice.configurations;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskListServiceConfiguration extends Configuration implements IConfigurator {

    private int maxLength;
    private String serviceName;
    private String serviceDesc;
    
    @JsonProperty
    public int getMaxLength() {
        return maxLength;
    }

    @JsonProperty
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    @JsonProperty
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    @JsonProperty
    public String getServiceDesc() {
        return serviceDesc;
    }

    @JsonProperty
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }
}