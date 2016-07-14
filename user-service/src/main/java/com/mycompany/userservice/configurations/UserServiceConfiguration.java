package com.mycompany.userservice.configurations;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserServiceConfiguration extends Configuration implements IConfigurator {

    private String serviceName;
    private String serviceDesc;
    private String template;
    
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
    public String getTemplate() {
        return this.template;
    }
    
    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }
}