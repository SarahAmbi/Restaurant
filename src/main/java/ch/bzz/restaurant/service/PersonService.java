package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Person;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import jakarta.annotation.security.RolesAllowed;


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
    @RolesAllowed({"admin", "user"})
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
    @RolesAllowed({"admin", "user"})
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPerson(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
     * @param person
     * @return Response
     */
    @RolesAllowed({"admin", "user"})
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPerson(
            @Valid @BeanParam Person person
    ) {
        person.setPersonUUID(UUID.randomUUID().toString());
        DataHandler.insertPerson(person);

        return Response
                .status(200)
                .entity("")
                .build();

    }


    /**
     * updates a person
     * @param person
     * @param personUUID
     * @return Response
     */
    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePerson(
            @Valid @BeanParam Person person,
            @FormParam("personUUID") String personUUID
    ) {
        int httpStatus = 200;
        Person oldperson = DataHandler.readPersonByUUID(personUUID);
        if (oldperson != null) {
            oldperson.setPersonUUID(personUUID);
            oldperson.setFirstname(person.getFirstname());
            oldperson.setLastname(person.getLastname());

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
    @RolesAllowed({"admin"})
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