package com.mycompany.aggregator.resources;

import com.mycompany.accountservice.api.AccountServiceAPI;
import com.mycompany.accountservice.api.Token;
import com.mycompany.aggregator.ServiceAggregatorConfiguration;
import com.mycompany.commons.api.SystemUnreachable;
import java.security.Principal;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/"+ServiceAggregatorConfiguration.API_V)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private AccountServiceAPI accountAPI;

    public void setApis(AccountServiceAPI accountAPI) {
        this.accountAPI = accountAPI;
    }

    @GET
    @Path("/login")
    public Token login(Principal user) {
        log.info("login : " + user);
        if (null == user) {
            throw new WebApplicationException("Credential params not found",
                    Response.Status.NOT_FOUND);
        }
        try {
            return accountAPI.getValidToken("user@test.it", "test");

        } catch (SystemUnreachable ex) {
            log.error("Service unreachable");
        }

        throw new WebApplicationException("Login error",
                Response.Status.UNAUTHORIZED);
    }

    /*
    try {
            URI uri = new URI("http://localhost:9084");
            AccountServiceAPI client = new AccountServiceAPI(uri);
            Token token = client.getValidToken("user@test.it", "test");
            System.out.println(token.getToken()+" "+token.getStatus());
            MyUser user = client.checkToken(token.getToken());
            System.out.println("User:"+user.getName());
        } catch (SystemUnreachable ex) {
            Logger.getLogger(ServiceAggregatorApplication.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    public static class Builder {

        private AccountServiceAPI accountAPI;

        public Builder withAccountApi(AccountServiceAPI api) {
            this.accountAPI = api;
            return this;
        }

        public AccountResource build() {
            AccountResource resource = new AccountResource();
            resource.setApis(accountAPI);
            return resource;
        }
    }
}
