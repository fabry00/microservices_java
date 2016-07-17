package com.mycompany.accountservice.dao.impl.inmemory;

import com.google.common.collect.ImmutableMap;

public class InMemoryDB {

    private ImmutableMap<String, String> connectionProperties;
    private enum PARAMS {MAX_ELEMENTS};
    
    InMemoryDB(ImmutableMap<String, String> connectionProperties) {
        this.connectionProperties  = connectionProperties;
        addDefaultConnectionProperties();
    }

    private void addDefaultConnectionProperties() {
        if(!connectionProperties.containsKey(PARAMS.MAX_ELEMENTS.toString())) {
            connectionProperties = ImmutableMap.<String, String>builder()
                    .putAll(connectionProperties)
                    .put(PARAMS.MAX_ELEMENTS.toString(),"100")
                    .build();
        }
    }
    
    private int getMaxElements() {
        return Integer.parseInt(connectionProperties
                .get(PARAMS.MAX_ELEMENTS.toString()));
    }
    
}
