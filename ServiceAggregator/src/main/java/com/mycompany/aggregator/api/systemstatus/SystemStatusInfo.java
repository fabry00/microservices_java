package com.mycompany.aggregator.api.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import java.util.Objects;
import java.util.Set;

public class SystemStatusInfo implements ISystemStatusInfo{

    private String name;
    private String description;
    private SystemStatus status = SystemStatus.HEALTHY;
    private Set<ISubSystemStatusInfo> subSystemInfo 
            = ImmutableSet.of();

    public SystemStatusInfo() {
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
    public Set<ISubSystemStatusInfo> getSubSystemsInfo() {
        return subSystemInfo;
    }
    
    @Override
    public void addSubSystemStatus(ISubSystemStatusInfo info) {
        if (info.getStatus().equals(SystemStatus.UNHEALTHY) && 
                status.equals(SystemStatus.HEALTHY)) {
            status = SystemStatus.UNHEALTHY;
        }
        
        subSystemInfo = new ImmutableSet.Builder<ISubSystemStatusInfo>()
                .addAll(subSystemInfo)
                .add(info)
                .build();        
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
        if (!SystemStatusInfo.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SystemStatusInfo other = (SystemStatusInfo) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        
        return this.description.equals(other.description);
    }

    @Override
    public int hashCode() {
        int hash = 256;
        hash = 229 * hash + Objects.hashCode(this.name);
        hash = 229 * hash + Objects.hashCode(this.description);
        return hash;
    }
}
