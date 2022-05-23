package ch.bzz.restaurant.model;

import ch.bzz.restaurant.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private String reservationUUID;
    private String date;
    private String time;
    private int numberOfPersons;
    private boolean regularCustumer;

    /**
     * gets personUUID
     *
     * @return the value of personUUID
     */
    public String getPersonUUID() {
        return getClient().getPersonUUID();
    }

    /**
     * sets personUUID
     *
     * @param personUUID the value to set
     */
    public void setPersonUUID(String personUUID) {
        setClient(new Person());
        Person person = DataHandler.getInstance().readPersonByUUID(personUUID);
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

    public boolean isRegularCustumer() {
        return regularCustumer;
    }

    public void setRegularCustumer(boolean regularCustumer) {
        this.regularCustumer = regularCustumer;
    }
}
