package com.mycompany.accountservice.dao;

import com.google.common.collect.ImmutableMap;


public interface IDAOFactory {
    public IUserDAO createUserDAO();
    void setConnectionProperties(ImmutableMap<String,String> connectionProperties);
}
