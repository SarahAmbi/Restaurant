package ch.bzz.restaurant.data;

import ch.bzz.restaurant.model.Person;
import ch.bzz.restaurant.model.Reservation;
import ch.bzz.restaurant.model.Restaurant;
import ch.bzz.restaurant.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Reservation> reservationList;
    private List<Person> personList;
    private List<Restaurant> restaurantList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setReservationList(new ArrayList<>());
        readReservationJSON();
        setPersonList(new ArrayList<>());
        readPersonJSON();
        setRestaurantList(new ArrayList<>());
        readRestaurantJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all reservations
     * @return list of reservations
     */
    public List<Reservation> readAllReservations() {
        return getReservationList();
    }

    /**
     * reads a reservations by its uuid
     * @param reservationUUID
     * @return the Reservation (null=not found)
     */
    public Reservation readReservationByUUID(String reservationUUID) {
        Reservation reservation = null;
        for (Reservation entry : getReservationList()) {
            if (entry.getReservationUUID().equals(reservationUUID)) {
                reservation = entry;
            }
        }
        return reservation;
    }

    /**
     * reads all Persons
     * @return list of Persons
     */
    public List<Person> readAllPersons() {

        return getPersonList();
    }

    /**
     * reads a person by its uuid
     * @param personUUID
     * @return the Person (null=not found)
     */
    public Person readPersonByUUID(String personUUID) {
        Person person = null;
        for (Person entry : getPersonList()) {
            if (entry.getPersonUUID().equals(personUUID)) {
                person = entry;
            }
        }
        return person;
    }

    /**
     * reads all restaurants
     * @return list of restaurants
     */
    public List<Restaurant> readAllRestaurants() {

        return getRestaurantList();
    }

    /**
     * reads a restaurant by its uuid
     * @param restaurantUUID
     * @return the Restaurant (null=not found)
     */
    public Restaurant readRestaurantByUUID(String restaurantUUID) {
        Restaurant restaurant = null;
        for (Restaurant entry : getRestaurantList()) {
            if (entry.getRestaurantUUID().equals(restaurantUUID)) {
                restaurant = entry;
            }
        }
        return restaurant;
    }

    /**
     * reads the reservations from the JSON-file
     */
    private void readReservationJSON() {
        try {
            String path = Config.getProperty("reservationJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Reservation[] reservations = objectMapper.readValue(jsonData, Reservation[].class);
            for (Reservation reservation : reservations) {
                getReservationList().add(reservation);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the persons from the JSON-file
     */
    private void readPersonJSON() {
        try {
            String path = Config.getProperty("personJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Person[] persons = objectMapper.readValue(jsonData, Person[].class);
            for (Person person : persons) {
                getPersonList().add(person);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the restaurants from the JSON-file
     */
    private void readRestaurantJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("restaurantJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Restaurant[] restaurants = objectMapper.readValue(jsonData, Restaurant[].class);
            for (Restaurant restaurant : restaurants) {
                getRestaurantList().add(restaurant);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets reservationList
     *
     * @return value of reservationList
     */
    private List<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * sets reservationList
     *
     * @param reservationList the value to set
     */
    private void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    /**
     * gets personList
     *
     * @return value of personList
     */
    private List<Person> getPersonList() {
        return personList;
    }

    /**
     * sets personList
     *
     * @param personList the value to set
     */
    private void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    /**
     * gets restaurantList
     *
     * @return value of restaurantList
     */
    private List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    /**
     * sets restaurantList
     *
     * @param restaurantList the value to set
     */
    private void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

}