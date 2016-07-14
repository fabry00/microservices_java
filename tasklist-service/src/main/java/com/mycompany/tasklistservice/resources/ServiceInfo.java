package com.mycompany.tasklistservice.resources;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.tasklistservice.configurations.IConfigurator;

public class ServiceInfo {
    private final String name;
    private final String description;
    
    private ServiceInfo( String name, String description){
        this.name = name;
        this.description = description;
    }
    @JsonProperty
    public String getName() {
        return this.name;
    }
    
    @JsonProperty
    public String getDescription() {
        return this.description;
    }
    
    public static class Builder{
        IConfigurator configurator;
        
        public Builder(IConfigurator configurator) {
            this.configurator = configurator;
        }
        
        public ServiceInfo build() {
            return new ServiceInfo(configurator.getServiceName(), 
                                   configurator.getServiceDesc());
        }
    }
}
