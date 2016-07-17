package com.mycompany.accountservice.resources;

import com.google.common.collect.ImmutableMap;
import com.mycompany.accountservice.api.MyUser;
import com.mycompany.accountservice.api.Token;
import com.mycompany.accountservice.dao.IDAOFactory;
import com.mycompany.accountservice.dao.impl.inmemory.MemoryDaoFactory;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 *
 * @author fabry
 */
public class SecuredResourceTest {

    private static final String URI = "http://localhost:8080/jwt/";

    private final IDAOFactory daoFactory = new MemoryDaoFactory();
    @Rule
    public ResourceTestRule resource;

    public SecuredResourceTest() {
        daoFactory.setConnectionProperties(ImmutableMap.of());

        resource =ResourceTestRule.builder()
                .addResource(new SecuredResource("asdasdas".getBytes(),
                        5, daoFactory)).build();
    }

    @Test
    public void testCheckTokenInvalidRequest() {

        //Obtain client from @Rule.
        Client client = resource.client();
        //Get WebTarget from client using URI of root resource.
        WebTarget helloTarget = client.target(URI + "check-token");
        //To invoke response we use Invocation.Builder
        //and specify the media type of representation asked from resource.
        Invocation.Builder builder = helloTarget.request(MediaType.APPLICATION_JSON_TYPE);
        //Obtain response.
        Response response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());

    }

    @Test
    public void testCheckTokenWrongUser() {

        //Obtain client from @Rule.
        Client client = resource.client();
        WebTarget getToken = client
                .target(URI + "generate-valid-token")
                .queryParam("email", "aaaa@324sad.it")
                .queryParam("password", "aaaaaa");

        //To invoke response we use Invocation.Builder
        //and specify the media type of representation asked from resource.
        Invocation.Builder builder = getToken.request(MediaType.APPLICATION_JSON_TYPE);
        //Obtain response.
        Response response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.UNAUTHORIZED, response.getStatusInfo());

    }
    
    
    @Test
    public void testCheckTokenRightUser() {

        //Obtain client from @Rule.
        Client client = resource.client();
        WebTarget getToken = client
                .target(URI + "generate-valid-token")
                .queryParam("email", "user@test.it")
                .queryParam("password", "test");

        //To invoke response we use Invocation.Builder
        //and specify the media type of representation asked from resource.
        Invocation.Builder builder = getToken.request(MediaType.APPLICATION_JSON_TYPE);
        //Obtain response.
        Response response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.OK, response.getStatusInfo());

        Token actual = response.readEntity(Token.class);

        assertNotNull(actual.getToken());
        assertEquals(Token.Status.VALID, actual.getStatus());
        
        //Get WebTarget from client using URI of root resource.
        WebTarget checkTarget = client.target(URI+"check-token");
        checkTarget.request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, actual.getToken());

        
        builder = checkTarget.request(MediaType.APPLICATION_JSON_TYPE);
        //Obtain response.
        response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.OK, response.getStatusInfo());
        
        MyUser actualUser = response.readEntity(MyUser.class);

        assertEquals(0,actualUser.getId());
  
        
    }
}
