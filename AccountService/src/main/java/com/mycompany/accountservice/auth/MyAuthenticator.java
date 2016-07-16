package com.mycompany.accountservice.auth;

import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.validator.ExpiryValidator;
import com.mycompany.accountservice.api.MyUser;
import java.math.BigDecimal;
import java.util.Optional;

/**
 *
 * @author fabry
 */
public class MyAuthenticator implements io.dropwizard.auth.Authenticator<JsonWebToken, MyUser> {

    @Override
    public Optional<MyUser> authenticate(JsonWebToken token) {
        final JsonWebTokenValidator expiryValidator = new ExpiryValidator();

        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified
        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.
        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.
        expiryValidator.validate(token);

        if ("good-guy".equals(token.claim().subject())) {
            return Optional.of(new MyUser(BigDecimal.ONE, "good-guy"));
        }

        return Optional.empty();
    }
}
