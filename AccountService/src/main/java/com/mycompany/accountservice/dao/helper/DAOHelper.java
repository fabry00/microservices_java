package com.mycompany.accountservice.dao.helper;

import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.dao.IDAO;
import com.mycompany.accountservice.dao.IDAOFactory;
import com.mycompany.accountservice.dao.IUserDAO;

import org.slf4j.LoggerFactory;

public class DAOHelper {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(DAOHelper.class);

    public static class UserNotFound extends Exception {
    };

    public MyUser getUser(String email, String password, IDAOFactory daoFactoy) throws UserNotFound {

        IUserDAO userDao = daoFactoy.createUserDAO();
        try {
            return userDao.getUser(email, password);
        } catch (IDAO.DaoException ex) {
            log.info("User not fond");
            throw new UserNotFound();
        }

    }

}
