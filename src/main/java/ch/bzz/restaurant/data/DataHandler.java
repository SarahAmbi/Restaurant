package ch.bzz.restaurant.data;

import ch.bzz.restaurant.model.Person;
import ch.bzz.restaurant.model.Reservation;
import ch.bzz.restaurant.model.Restaurant;
import ch.bzz.restaurant.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private static List<Reservation> reservationList;
    private static List<Person> personList;
    private static List<Restaurant> restaurantList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * initializes the lists
     */
    public static void initLists() {
        DataHandler.setReservationList(null);
        DataHandler.setPersonList(null);
        DataHandler.setRestaurantList(null);
    }


    /**
     * reads all reservations
     * @return list of reservations
     */

    public static List<Reservation> readAllReservations() {
        return getReservationList();
    }


    /**
     * reads a reservations by its uuid
     * @param reservationUUID
     * @return the Reservation (null=not found)
     */

    public static Reservation readReservationByUUID(String reservationUUID) {
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
    public static List<Person> readAllPersons() {

        return getPersonList();
    }

    /**
     * reads a person by its uuid
     * @param personUUID
     * @return the Person (null=not found)
     */
    public static Person readPersonByUUID(String personUUID) {
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
    public static List<Restaurant> readAllRestaurants() {

        return getRestaurantList();
    }


    /**
     * reads a restaurant by its uuid
     * @param restaurantUUID
     * @return the Restaurant (null=not found)
     */

    public static Restaurant readRestaurantByUUID(String restaurantUUID) {
        Restaurant restaurant = null;
        for (Restaurant entry : getRestaurantList()) {
            if (entry.getRestaurantUUID().equals(restaurantUUID)) {
                restaurant = entry;
            }
        }
        return restaurant;
    }

    /**
     * inserts a new reservation into the reservationList
     *
     * @param reservation the reservation to be saved
     */
    public static void insertReservation(Reservation reservation) {
        getReservationList().add(reservation);
        writeReservationJSON();
    }

    /**
     * updates the reservationList
     */
    public static void updateReservation() {
        writeReservationJSON();
    }

    /**
     * deletes a book identified by the bookUUID
     * @param reservationUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteReservation(String reservationUUID) {
        Reservation reservation = readReservationByUUID(reservationUUID);
        if (reservation != null) {
            getReservationList().remove(reservation);
            writeReservationJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the reservations from the JSON-file
     */

    private static void readReservationJSON() {
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
     * writes the reservationList to the JSON-file
     */
    private static void writeReservationJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String restaurantPath = Config.getProperty("reservationJSON");
        try {
            fileOutputStream = new FileOutputStream(restaurantPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getReservationList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * inserts a new person into the personList
     *
     * @param person the person to be saved
     */
    public static void insertPerson(Person person) {
        getPersonList().add(person);
        writePersonJSON();
    }

    /**
     * updates the personList
     */
    public static void updatePerson() {
        writePersonJSON();
    }

    /**
     * deletes a person identified by the personUUID
     * @param personUUID  the key
     * @return  success=true/false
     */
    public static boolean deletePerson(String personUUID) {
        Person person = readPersonByUUID(personUUID);
        if (person != null) {
            getPersonList().remove(person);
            writePersonJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the persons from the JSON-file
     */
    private static void readPersonJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(Config.getProperty("personJSON"))
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
     * writes the personList to the JSON-file
     */
    private static void writePersonJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String restaurantPath = Config.getProperty("personJSON");
        try {
            fileOutputStream = new FileOutputStream(restaurantPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getPersonList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * inserts a new restaurant into the restaurantList
     *
     * @param restaurant the restaurant to be saved
     */
    public static void insertRestaurant(Restaurant restaurant) {
        getRestaurantList().add(restaurant);
        writeRestaurantJSON();
    }

    /**
     * updates the restaurantList
     */
    public static void updateRestaurant() {
        writeRestaurantJSON();
    }

    /**
     * deletes a restaurant identified by the restaurantUUID
     * @param restaurantUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteRestaurant(String restaurantUUID) {
        Restaurant restaurant = readRestaurantByUUID(restaurantUUID);
        if (restaurant != null) {
            getRestaurantList().remove(restaurant);
            writeRestaurantJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * reads the restaurants from the JSON-file
     */

    private static void readRestaurantJSON() {
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
     * writes the restaurantList to the JSON-file
     */
    private static void writeRestaurantJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String restaurantPath = Config.getProperty("restaurantJSON");
        try {
            fileOutputStream = new FileOutputStream(restaurantPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getRestaurantList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * gets reservationList
     *
     * @return value of reservationList
     */

    private static List<Reservation> getReservationList() {
        if (reservationList == null) {
            setReservationList(new ArrayList<>());
            readReservationJSON();
        }
        return reservationList;
    }


    /**
     * sets reservationList
     *
     * @param reservationList the value to set
     */

    private static void setReservationList(List<Reservation> reservationList) {
        DataHandler.reservationList = reservationList;
    }


    /**
     * gets personList
     *
     * @return value of personList
     */
    private static List<Person> getPersonList() {
        if (personList == null) {
            setPersonList(new ArrayList<>());
            readPersonJSON();
        }
        return personList;
    }

    /**
     * sets personList
     *
     * @param personList the value to set
     */
    private static void setPersonList(List<Person> personList) {
        DataHandler.personList = personList;
    }

    /**
     * gets restaurantList
     *
     * @return value of restaurantList
     */

    private static List<Restaurant> getRestaurantList() {
        if (restaurantList == null) {
            setRestaurantList(new ArrayList<>());
            readRestaurantJSON();
        }
        return restaurantList;
    }

    /**
     * sets restaurantList
     *
     * @param restaurantList the value to set
     */

    private static void setRestaurantList(List<Restaurant> restaurantList) {
        DataHandler.restaurantList = restaurantList;
    }

}