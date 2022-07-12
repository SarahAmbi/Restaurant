package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.User;
import jakarta.annotation.security.PermitAll;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


/**
 * services for authentication and authorization of users
 */
@Path("user")
public class UserService {

    /**
     * login-service
     *
     * @param username the username
     * @param password the password
     * @return Response
     */
    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus;
        User user = DataHandler.readUser(username,password);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        Response response = Response.
                status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    @PermitAll
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {
        Response response = Response
                .status(200)
                .entity("")
                .build();
        return response;
    }

}