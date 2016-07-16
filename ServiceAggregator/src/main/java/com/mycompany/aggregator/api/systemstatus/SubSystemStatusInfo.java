package com.mycompany.aggregator.api.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.commons.api.IServiceInfo;
import java.util.Objects;

public class SubSystemStatusInfo implements ISubSystemStatusInfo{

    private String name;
    private String description;
    private SystemStatus status;

    public SubSystemStatusInfo() {
    }

    @JsonProperty
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    @JsonProperty
    @Override
    public SystemStatus getStatus() {
        return status;
    }    
    
    @Override
    public void setStauts(SystemStatus status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return name+": "+description+" status:"+status;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!SubSystemStatusInfo.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SubSystemStatusInfo other = (SubSystemStatusInfo) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        
        return this.description.equals(other.description);
    }

    @Override
    public int hashCode() {
        int hash = 25;
        hash = 529 * hash + Objects.hashCode(this.name);
        hash = 529 * hash + Objects.hashCode(this.description);
        return hash;
    }
}
