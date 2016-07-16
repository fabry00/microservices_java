package com.mycompany.accountservice.resources;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.api.Token;
import io.dropwizard.auth.Auth;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

@Path("/jwt")
@Produces(APPLICATION_JSON)
public class SecuredResource {

    private final byte[] tokenSecret;

    public SecuredResource(byte[] tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @GET
    @Path("/generate-expired-token")
    public Token generateExpiredToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(-20);
        claims.setSubject("good-guy");

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return new Token.Builder()
                    .withToken(jws.getCompactSerialization())
                    .withStatus(Token.Status.VALID)
                    .build();
        }
        catch (JoseException e) { throw Throwables.propagate(e); }
    }

    @GET
    @Path("/generate-valid-token")
    public Token generateValidToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject("good-guy");
        claims.setExpirationTimeMinutesInTheFuture(30);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            
            return new Token.Builder()
                    .withToken(jws.getCompactSerialization())
                    .withStatus(Token.Status.VALID)
                    .build();
        }
        catch (JoseException e) { throw Throwables.propagate(e); }
    }

    @GET
    @Path("/check-token")
    public Map<String, Object> get(@Auth Principal user) {
        return ImmutableMap.<String, Object>of("username", user.getName(), "id", ((MyUser) user).getId());
    }
}