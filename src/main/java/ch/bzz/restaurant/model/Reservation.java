package ch.bzz.restaurant.model;

import ch.bzz.restaurant.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * a reservation in our restaurant
 *
 * @author Sarah Ambi
 * @since 2022-05-18
 * @version 1.0
 */
public class Reservation {
    @JsonIgnore
    private Person client;

    @FormParam("uuid")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String reservationUUID;

    @FormParam("date")
    @NotEmpty
    private String date;

    @FormParam("time")
    @NotEmpty
    private String time;

    @FormParam("numberOfPersons")
    @NotNull
    @DecimalMin(value="1")
    @DecimalMax(value="30")
    private int numberOfPersons;

    @FormParam("regularCustumer")
    @NotNull
    private boolean regularCustumer;

    /**
     * gets personUUID
     *
     * @return the value of personUUID
     */
    public String getPersonUUID() {
        if (getClient() == null) {
            return null;
        }
        return getClient().getPersonUUID();
    }

    /**
     * sets personUUID
     *
     * @param personUUID the value to set
     */
    public void setPersonUUID(String personUUID) {
        setClient(new Person());
        Person person = DataHandler.readPersonByUUID(personUUID);
        getClient().setPersonUUID(personUUID);
        getClient().setFirstname(person.getFirstname());
        getClient().setLastname(person.getLastname());

    }

    /**
     * gets client
     *
     * @return the value of client
     */
    public Person getClient() {
        return client;
    }

    /**
     * sets client
     *
     * @param client the value to set
     */
    public void setClient(Person client) {
        this.client = client;
    }

    /**
     * gets restaurantUUID
     *
     * @return value of reservationUUID
     */
    public String getReservationUUID() {
        return reservationUUID;
    }

    /**
     * sets restaurantUUID
     *
     * @param reservationUUID the value to set
     */
    public void setReservationUUID(String reservationUUID) {
        this.reservationUUID = reservationUUID;
    }

    /**
     * gets date
     *
     * @return the value of booked date
     */
    public String getDate() {
        return date;
    }

    /**
     * sets date
     *
     * @param date the value to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * gets time
     *
     * @return the value of booked time
     */
    public String getTime() {
        return time;
    }

    /**
     * sets time
     *
     * @param time the value to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * gets number of persons
     *
     * @return the value of number of persons
     */
    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    /**
     * sets number of persons
     *
     * @param numberOfPersons the value to set
     */
    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

     /**
     * gets regularCustumer
     *
     * @return the value of regularCustumer
     */
    public boolean isRegularCustumer() {
        return regularCustumer;
    }

    
     /**
     * sets regularCustumer
     *
     * @param regularCustumer the value to set
     */
    public void setRegularCustumer(boolean regularCustumer) {
        this.regularCustumer = regularCustumer;
    }
}
