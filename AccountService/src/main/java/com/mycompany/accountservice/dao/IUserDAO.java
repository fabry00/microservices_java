package com.mycompany.accountservice.dao;

import com.mycompany.accountservice.api.MyUser;

public interface IUserDAO extends IDAO{
    
    public MyUser getUser(String email) throws DaoException;
    public MyUser getUser(String email, String password) throws DaoException;
}
