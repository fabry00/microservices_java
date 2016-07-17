package com.mycompany.accountservice.auth;

import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.validator.ExpiryValidator;
import com.google.common.base.Optional;
import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.dao.IDAO;
import com.mycompany.accountservice.dao.IDAOFactory;
import com.mycompany.accountservice.dao.IUserDAO;
import com.mycompany.accountservice.dao.impl.inmemory.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import java.math.BigDecimal;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabry
 */
public class MyAuthenticator implements Authenticator<JsonWebToken, MyUser> {
    private final Logger log = LoggerFactory.getLogger(MyAuthenticator.class);

    private final IDAOFactory daoFactory;
    
    public MyAuthenticator(IDAOFactory daoFactory){
        log.debug("init");
        this.daoFactory = daoFactory;
    }
    
    @Override
    public Optional<MyUser> authenticate(JsonWebToken token) throws AuthenticationException{
        log.info("###################### authenticate "+token);
        final JsonWebTokenValidator expiryValidator = new ExpiryValidator();

        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified
        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.
        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.
        expiryValidator.validate(token);

        IUserDAO dao = this.daoFactory.createUserDAO();
        try {
            MyUser user = dao.getUser(token.claim().subject());
            return Optional.of(user);
        } catch (IDAO.DaoException ex) {
           log.info(token.claim().subject()+" User not found");
        }
        return Optional.absent();
        
    }
}
