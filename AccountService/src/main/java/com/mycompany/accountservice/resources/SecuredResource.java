package com.mycompany.accountservice.resources;

import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.google.common.base.Throwables;
import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.api.Token;
import com.mycompany.accountservice.dao.IDAOFactory;
import com.mycompany.accountservice.dao.IUserDAO;
import com.mycompany.accountservice.dao.helper.DAOHelper;
import com.mycompany.accountservice.resources.helpers.JwtHelper;
import io.dropwizard.auth.Auth;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.security.Principal;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.joda.time.DateTime;

@Path("/jwt")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class SecuredResource {

    private final Integer expiration;
    private final IDAOFactory daoFactory;
    private final byte[] tokenSecret;

    public SecuredResource(byte[] tokenSecret,
            Integer expiration,
            IDAOFactory daoFactory) {
        this.tokenSecret = tokenSecret;
        this.expiration = expiration;
        this.daoFactory = daoFactory;
    }

    @GET
    @Path("/generate-expired-token")
    public Token generateExpiredToken(@PathParam("email") String email) {
        if (null == email) {
            throw new WebApplicationException("Credential params not found",
                    Response.Status.NOT_FOUND);
        }

        final HmacSHA512Signer signer = new HmacSHA512Signer(tokenSecret);
        final JsonWebToken token = JsonWebToken.builder()
                .header(JsonWebTokenHeader.HS512())
                .claim(JsonWebTokenClaim.builder()
                        .subject(email)
                        .issuedAt(new DateTime().plusHours(1))
                        .build())
                .build();

        final String signedToken = signer.sign(token);

        return new Token.Builder()
                .withToken(signedToken)
                .withStatus(Token.Status.VALID)
                .build();

    }

    @GET
    @Path("/generate-valid-token")
    public Token generateValidToken(@QueryParam("email") String email,
            @QueryParam("password") String password) {

        if (null == email) {
            throw new WebApplicationException("Credential params not found",
                    Response.Status.NOT_FOUND);
        }

        try {
            // Check right user and password
            new DAOHelper().getUser(email, password, daoFactory);
        } catch (DAOHelper.UserNotFound ex) {
            throw new WebApplicationException("User unauthorized",
                       Response.Status.UNAUTHORIZED);
        }
        
        JwtHelper helper = new JwtHelper();
        helper.setSecret(tokenSecret);
        return helper.getSignedToken(email, this.expiration);

    }

    @GET
    @Path("/check-token")
    public MyUser checkToken(@Auth Principal user) {
        if (null == user) {
            throw new WebApplicationException("Credential params not found",
                    Response.Status.NOT_FOUND);
        }
        return new MyUser(((MyUser) user).getId(), user.getName());
    }

}
