package ch.bzz.restaurant.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * the person who booked the reservation
 *
 * @author Sarah Ambi
 * @since 2022-05-18
 * @version 1.0
 */
public class Person {
    @FormParam("uuid")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String personUUID;

    @FormParam("firstname")
    @NotEmpty
    @Size(min=3, max=40)
    private String firstname;

    @FormParam("lastname")
    @NotEmpty
    @Size(min=3, max=40)
    private String lastname;

    /**
     * gets personUUID
     *
     * @return value of personUUID
     */
    public String getPersonUUID() {
        return personUUID;
    }

    /**
     * sets personUUID
     *
     * @param personUUID the value to set
     */
    public void setPersonUUID(String personUUID) {
        this.personUUID = personUUID;
    }

    /**
     *  gets firstname
     *
     * @return the first name of person
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * sets firstname
     *
     * @param firstname the value to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * gets lastname of the person
     *
     * @return the last name of person
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * sets lastname
     *
     * @param lastname the value to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
