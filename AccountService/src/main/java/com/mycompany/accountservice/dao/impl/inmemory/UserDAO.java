package com.mycompany.accountservice.dao.impl.inmemory;

import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.dao.IDAO;
import com.mycompany.accountservice.dao.IUserDAO;
import java.util.Collection;

public class UserDAO implements IUserDAO {

    private final InMemoryDB db;

    protected UserDAO(InMemoryDB db) {
        this.db = db;
    }

    @Override
    public void create(IDAO object) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection retrieve(String queryString) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(IDAO object) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(IDAO object) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public MyUser getUser(String email, String password) throws DaoException{
        return db.getUser(email, password);
        
    }

}
