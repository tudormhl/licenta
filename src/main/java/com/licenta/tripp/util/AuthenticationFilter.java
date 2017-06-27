package com.licenta.tripp.util;






import com.licenta.tripp.model.User;
import com.licenta.tripp.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Tudor on 6/26/2017.
 */

@Secured
@Provider
@Priority(1000)
public class AuthenticationFilter implements ContainerRequestFilter {

    private UserService userService = new UserService();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        String authorizationHeader =
                containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        if(!validateToken(token)){
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build()
            );
        }

    }

    private boolean validateToken(String token) {

        User user = userService.verifyToken(token);
        if (!user.getToken().isEmpty() && user.getToken().equalsIgnoreCase(token)) {
            return true;
        }

        return false;
    }
}
