package com.mycompany.accountservice.dao.impl.inmemory;


import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author fabry
 */
public class InitialValues {

    public ConcurrentHashMap<BigDecimal, MyUserDB> getUsers() {
        ConcurrentHashMap<BigDecimal, MyUserDB> map = new ConcurrentHashMap<>();

        BigDecimal id = new BigDecimal(0);
        map.put(id, new MyUserDB.Builder()
                .withID(id)
                .withName("user@test.it")
                .withPassword("test")
                .build());
        
        return map;
    }

}
