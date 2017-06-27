package com.licenta.tripp.Resources;

import com.licenta.tripp.service.UserService;
import com.licenta.tripp.transfer.ErrorMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.Random;
import java.math.BigInteger;

/**
 * Created by Tudor on 6/26/2017.
 */
@Path("/auth")
public class LoginResource {

    private UserService userService = new UserService();

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) {

        try{
            boolean isLoggedIn = userService.login(username,password);

            if(!isLoggedIn){
                return Response.status(401).entity(new ErrorMessage("Wrong username or password")).build();
            }

            issueToken(username);

            return Response.status(Response.Status.OK).entity("Successfully logged in").build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void issueToken(String username){

        Random random = new SecureRandom();
        String key = new BigInteger(130, random).toString(20);

        userService.insertToken(username, key);

    }

}
