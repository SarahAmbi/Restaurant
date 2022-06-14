package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Person;
import ch.bzz.restaurant.model.Restaurant;

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
     * @param name the name
     * @param place the place
     * @param numberOfSeats the number of seats
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertRestaurant(
            @FormParam("name") String name,
            @FormParam("place") String place,
            @FormParam("numberOfSeats") int numberOfSeats
    ) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantUUID(UUID.randomUUID().toString());
        restaurant.setName(name);
        restaurant.setPlace(place);
        restaurant.setNumberOfSeats(numberOfSeats);

        DataHandler.insertRestaurant(restaurant);
        return Response
                .status(200)
                .entity("")
                .build();

    }

    /**
     * updates a restaurant
     * @param restaurantUUID the restaurantUUID
     * @param name the name
     * @param place the place
     * @param numberOfSeats the numberOfSeats
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRestaurant(
            @FormParam("restaurantUUID") String restaurantUUID,
            @FormParam("name") String name,
            @FormParam("place") String place,
            @FormParam("numberOfSeats") int numberOfSeats
    ) {
        int httpStatus = 200;
        Restaurant restaurant = DataHandler.readRestaurantByUUID(restaurantUUID);
        if (restaurant != null) {
            restaurant.setName(name);
            restaurant.setPlace(place);
            restaurant.setNumberOfSeats(numberOfSeats);

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
