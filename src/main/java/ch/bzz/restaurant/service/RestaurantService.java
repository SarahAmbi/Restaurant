package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Restaurant;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

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
        List<Restaurant> restaurantList = DataHandler.readAllRestaurants();
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String restaurantUUID
    ) {
        int httpStatus = 200;
        Restaurant restaurant = DataHandler.readRestaurantByUUID(restaurantUUID);
        if (restaurant == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(restaurant)
                .build();
    }

    /**
     * inserts a new restaurant
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertRestaurant(
            @Valid @BeanParam Restaurant restaurant
    ) {
        restaurant.setRestaurantUUID(UUID.randomUUID().toString());
        DataHandler.insertRestaurant(restaurant);

        return Response
                .status(200)
                .entity("")
                .build();

    }

    /**
     * updates a restaurant
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRestaurant(
            @Valid @BeanParam Restaurant restaurant
    ) {
        int httpStatus = 200;
        Restaurant oldRestaurant = DataHandler.readRestaurantByUUID(restaurant.getRestaurantUUID());
        if (oldRestaurant != null) {
            oldRestaurant.setName(restaurant.getName());
            oldRestaurant.setPlace(restaurant.getPlace());
            oldRestaurant.setNumberOfSeats(restaurant.getNumberOfSeats());

            DataHandler.updateRestaurant();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
    /**
     * deletes a restaurant identified by its uuid
     * @param restaurantUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRestaurant(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String restaurantUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteRestaurant(restaurantUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}