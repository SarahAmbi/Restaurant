package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Person;
import ch.bzz.restaurant.model.Reservation;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing and deleting restaurants
 *
 * @author Sarah Ambi
 * @since 2022-05-19
 * @version 1.0
 */
@Path("person")
public class PersonService {

    /**
     * reads a list of all persons
     *
     * @return persons as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPersons() {
        List<Person> personList = DataHandler.readAllPersons();
        return Response
                .status(200)
                .entity(personList)
                .build();
    }

    /**
     * reads a person identified by the uuid
     *
     * @param personUUID
     * @return person
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPerson(
            @QueryParam("uuid") String personUUID
    ) {
        int httpStatus = 200;
        Person person = DataHandler.readPersonByUUID(personUUID);
        if (person == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(person)
                .build();
    }

    /**
     * inserts a new person
     * @param firstname the firstname
     * @param lastname the lastname
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPerson(
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname
    ) {
        Person person = new Person();
        person.setPersonUUID(UUID.randomUUID().toString());
        person.setFirstname(firstname);
        person.setLastname(lastname);

        DataHandler.insertPerson(person);
        return Response
                .status(200)
                .entity("")
                .build();

    }

    /**
     * updates a person
     * @param personUUID the key
     * @param firstname the date
     * @param lastname the time
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePerson(
            @FormParam("personUUID") String personUUID,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname
    ) {
        int httpStatus = 200;
        Person person = DataHandler.readPersonByUUID(personUUID);
        if (person != null) {
            person.setFirstname(firstname);
            person.setLastname(lastname);

            DataHandler.updatePerson();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
    /**
     * deletes a person identified by its uuid
     * @param personUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePerson(
            @QueryParam("uuid") String personUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deletePerson(personUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}