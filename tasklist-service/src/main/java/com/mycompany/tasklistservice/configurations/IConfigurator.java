package com.mycompany.tasklistservice.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;


public interface IConfigurator {
    @JsonProperty
    public int getMaxLength();

    @JsonProperty
    public String getServiceName();

    @JsonProperty
    public String getServiceDesc();
    
    public String getTemplate();

}
