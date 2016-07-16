package com.mycompany.commons.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public interface IServiceInfo {

    @JsonProperty
    public String getName();

    public void setName(String name);

    @JsonProperty
    public String getDescription();

    public void setDescription(String description);

}
