package com.mycompany.accountservice.resources.helpers;

import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.mycompany.accountservice.api.Token;
import org.joda.time.DateTime;

/**
 *
 * @author fabry
 */
public class JwtHelper {

    private byte[] secret;

    public void setSecret(byte[] secret) {
        this.secret = secret;
    }

    public Token getSignedToken(String object, int expirationInMin) {
        DateTime expirationDate = getExpirationDate(expirationInMin);
        final HmacSHA512Signer signer = new HmacSHA512Signer(this.secret);
        final JsonWebToken token = JsonWebToken.builder()
                .header(JsonWebTokenHeader.HS512())
                .claim(JsonWebTokenClaim.builder()
                        .subject(object)
                        .issuedAt(DateTime.now())
                        .expiration(expirationDate)
                        .build())
                .build();
        final String signedToken = signer.sign(token);

        return new Token.Builder()
                .withToken(signedToken)
                .withStatus(Token.Status.VALID)
                .build();
    }

    private DateTime getExpirationDate(int expirationInMin) {
        DateTime expirationDate = new DateTime();
        expirationDate.plusMinutes(expirationInMin);
        return expirationDate;
    }

}
