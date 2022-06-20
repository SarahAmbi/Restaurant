package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Reservation;

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
@Path("reservation")
public class ReservationService {

    /**
     * reads a list of all reservations
     *
     * @return reservations as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReservations() {
        List<Reservation> reservationList = DataHandler.readAllReservations();
        return Response
                .status(200)
                .entity(reservationList)
                .build();
    }

    /**
     * reads a reservation identified by the uuid
     *
     * @param reservationUUID
     * @return reservation
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readReservation(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String reservationUUID
    ) {
        int httpStatus = 200;
        Reservation reservation = DataHandler.readReservationByUUID(reservationUUID);
        if (reservation == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(reservation)
                .build();
    }

    /**
     * inserts a new reservation
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertReservation(
            @Valid @BeanParam Reservation reservation
    ) {
        reservation.setReservationUUID(UUID.randomUUID().toString());

        DataHandler.insertReservation(reservation);
        return Response
                .status(200)
                .entity("")
                .build();

    }

    /**
     * updates a reservation
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReservation(
            @Valid @BeanParam Reservation reservation
    ) {
        int httpStatus = 200;
        Reservation oldReservation = DataHandler.readReservationByUUID(reservation.getReservationUUID());
        if (oldReservation != null) {
            oldReservation.setReservationUUID(reservation.getReservationUUID());
            oldReservation.setDate(reservation.getDate());
            oldReservation.setTime(reservation.getTime());
            oldReservation.setNumberOfPersons(reservation.getNumberOfPersons());
            oldReservation.setRegularCustumer(reservation.isRegularCustumer());
            oldReservation.setPersonUUID(reservation.getPersonUUID());
            DataHandler.updateReservation();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
    /**
     * deletes a reservation identified by its uuid
     * @param reservationUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String reservationUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteReservation(reservationUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * sets the attributes for the reservation-object
     * @param reservation  the reservation-object
     * @param date  the date
     * @param time  the time
     * @param numberOfPersons  the number of persons
     * @param regularCustumer  the regularCustumer
     * @param personUUID  ths uuid of the person
     */

    private void setAttributes(
            Reservation reservation,
            String date,
            String time,
            int numberOfPersons,
            boolean regularCustumer,
            String personUUID
    ) {
        reservation.setDate(date);
        reservation.setTime(time);
        reservation.setNumberOfPersons(numberOfPersons);
        reservation.setRegularCustumer(regularCustumer);
        reservation.setPersonUUID(personUUID);
    }
}