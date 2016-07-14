package com.mycompany.userservice.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;


public interface IConfigurator {
    
    @JsonProperty
    public String getServiceName();

    @JsonProperty
    public String getServiceDesc();
    
    public String getTemplate();

}
