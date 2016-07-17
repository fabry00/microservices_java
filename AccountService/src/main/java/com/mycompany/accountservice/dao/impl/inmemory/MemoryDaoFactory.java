package com.mycompany.accountservice.dao.impl.inmemory;

import com.google.common.collect.ImmutableMap;
import com.mycompany.accountservice.dao.IDAOFactory;
import com.mycompany.accountservice.dao.IUserDAO;

public class MemoryDaoFactory implements IDAOFactory{

    private static InMemoryDB db;

    @Override
    public IUserDAO createUserDAO() {
        return new UserDAO(db);
    }

    @Override
    public void setConnectionProperties(ImmutableMap<String, String> connectionProperties) {
        MemoryDaoFactory.db = new InMemoryDB(connectionProperties);
    }
    
}
