package ch.bzz.restaurant.model;

/**
 * restaurant which can be booked
 *
 * @author Sarah Ambi
 * @since 2022-05-18
 * @version 1.0
 *
 */
public class Restaurant {
    private String restaurantUUID;
    private String name;
    private String place;
    private int numberOfSeats;

    /**
     * gets restaurantUUID
     *
     * @return value of restaurantUUID
     */
    public String getRestaurantUUID() {
        return restaurantUUID;
    }

    /**
     * sets restaurantUUID
     *
     * @param restaurantUUID the value to set
     */
    public void setRestaurantUUID(String restaurantUUID) {
        this.restaurantUUID = restaurantUUID;
    }

    /**
     * gets restaurant name
     *
     * @return value of restaurant name
     */
    public String getName() {
        return name;
    }

    /**
     * sets restaurant name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets restaurant place
     *
     * @return value of restaurant place
     */
    public String getPlace() {
        return place;
    }

    /**
     * sets restaurant place
     *
     * @param place the value to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * gets number of seats
     *
     * @return value of the number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * sets number of seats
     *
     * @param numberOfSeats the value to set
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


}
