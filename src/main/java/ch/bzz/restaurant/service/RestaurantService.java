package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Restaurant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting restaurants
 *
 * @author Sarah Ambi
 * @since 2022-05-19
 * @version 1.0
 */
@Path("restaurant")
public class RestaurantService {

    /**
     * reads a list of all restaurants
     * @return  restaurants as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRestaurants() {
        List<Restaurant> restaurantList = DataHandler.getInstance().readAllRestaurants();
        return Response
                .status(200)
                .entity(restaurantList)
                .build();
    }

    /**
     * reads a restaurant identified by the uuid
     * @param restaurantUUID
     * @return restaurant
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readRestaurant(
            @QueryParam("uuid") String restaurantUUID
    ) {
        int httpStatus = 200;
        Restaurant restaurant = DataHandler.getInstance().readRestaurantByUUID(restaurantUUID);
        if (restaurant == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(restaurant)
                .build();
    }
}
