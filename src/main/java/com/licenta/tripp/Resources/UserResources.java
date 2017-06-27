package com.licenta.tripp.Resources;

import com.licenta.tripp.model.User;
import com.licenta.tripp.service.UserService;
import com.licenta.tripp.transfer.ErrorMessage;
import com.licenta.tripp.util.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Tudor on 6/22/2017.
 */

@Path("/user")
public class UserResources {

    private UserService userService = new UserService();

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response register(@QueryParam("username") String username,
                             @QueryParam("password") String password){


        User user = userService.findByUsername(username);
        if(user != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage("Username is already in use ")).build();
        }

        if(!username.isEmpty() && !password.isEmpty()){
            user.setUsername(username);
            user.setPassword(password);
        }


        userService.registerUser(user);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("You have successfully created an account!").build();
    }

    @PUT
    @Secured
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response changePassword(@QueryParam("username") String username,
                                   @QueryParam("newPassword") String newPassword){

        User user = userService.findByUsername(username);
        if(user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage("User does not exist")).build();
        }

        userService.changePassword(username, newPassword);

        return Response.status(Response.Status.OK).entity("You have successfully changed your password").build();

    }

}
