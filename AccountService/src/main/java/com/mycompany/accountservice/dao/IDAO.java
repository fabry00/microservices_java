package com.mycompany.accountservice.dao;

import java.util.Collection;

public interface IDAO {

    public static class DaoException extends Exception {
    };

    public void create(final IDAO object) throws DaoException;

    public Collection retrieve(final String queryString) throws
            DaoException;

    public void update(final IDAO object) throws DaoException;

    public void delete(final IDAO object) throws DaoException;

    public void close() throws DaoException;
}
