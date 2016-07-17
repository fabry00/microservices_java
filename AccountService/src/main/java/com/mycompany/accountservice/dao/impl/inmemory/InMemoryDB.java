package com.mycompany.accountservice.dao.impl.inmemory;

import com.google.common.collect.ImmutableMap;
import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.dao.IDAO;
import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryDB {

    private ImmutableMap<String, String> connectionProperties;

    private enum PARAMS {MAX_ELEMENTS};
    private ConcurrentMap<BigDecimal, MyUserDB> userCache;
    
    public InMemoryDB(ImmutableMap<String, String> connectionProperties) {
        this.connectionProperties  = connectionProperties;
        addDefaultConnectionProperties();
        
        init();
    }
    
    public MyUser getUser(String email, String password)  throws IDAO.DaoException{
        for(Entry<BigDecimal,MyUserDB> user : userCache.entrySet()) {
            if(user.getValue().getName().equals(email) && 
                    user.getValue().getPassword().equals(password) ){
                
                return new MyUser.Builder()
                        .withID(user.getValue().getId())
                        .withName(user.getValue().getName())
                        .build();
                        
            }
        }
        throw  new IDAO.DaoException();
    }
    
    public MyUser getUser(String email)  throws IDAO.DaoException{
        for(Entry<BigDecimal,MyUserDB> user : userCache.entrySet()) {
            if(user.getValue().getName().equals(email) ){
                
                return new MyUser.Builder()
                        .withID(user.getValue().getId())
                        .withName(user.getValue().getName())
                        .build();
                        
            }
        }
        throw  new IDAO.DaoException();
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
    
    
    private void init() {
      InitialValues initials = new InitialValues();
      userCache = new ConcurrentHashMap<>(initials.getUsers());
         
    }
}
