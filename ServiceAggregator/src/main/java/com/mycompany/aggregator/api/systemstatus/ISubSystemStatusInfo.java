package com.mycompany.aggregator.api.systemstatus;

import com.mycompany.commons.api.IServiceInfo;

/**
 *
 * @author fabry
 */
public interface ISubSystemStatusInfo  extends IServiceInfo{
    public SystemStatus getStatus();
    
    public void setStauts(SystemStatus status);
}
