package com.mycompany.aggregator.api.systemstatus;


import java.util.Set;


public interface ISystemStatusInfo extends ISubSystemStatusInfo{
    
    public Set<ISubSystemStatusInfo> getSubSystemsInfo();
    public void addSubSystemStatus(ISubSystemStatusInfo info);
}
