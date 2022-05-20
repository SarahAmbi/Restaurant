package ch.bzz.restaurant.service;

import ch.bzz.restaurant.data.DataHandler;
import ch.bzz.restaurant.model.Reservation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<Reservation> reservationList = DataHandler.getInstance().readAllReservations();
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
            @QueryParam("uuid") String reservationUUID
    ) {
        int httpStatus = 200;
        Reservation reservation = DataHandler.getInstance().readReservationByUUID(reservationUUID);
        if (reservation == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(reservation)
                .build();
    }
}