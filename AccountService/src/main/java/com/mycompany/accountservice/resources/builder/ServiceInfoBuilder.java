package com.mycompany.accountservice.resources.builder;

import com.mycompany.accountservice.api.ServiceInfo;

/**
 *
 * @author fabry
 */
public class ServiceInfoBuilder {
    
    private String name;
    private String description;
    
    public ServiceInfoBuilder withName(String name) {
            this.name = name;
            return this;
    }
    
    public ServiceInfoBuilder withDesc(String desc) {
            this.description = desc;
            return this;
    }
        
    public ServiceInfo build() {
        ServiceInfo info = new ServiceInfo();
        info.setName(name);
        info.setDescription(description);
        return info;
    }
}
