/**
 * view-controller for reservation.html
 * @author Sarah Ambi
 */

document.addEventListener("DOMContentLoaded", () => {
    readReservations();
});

/**
 * reads all reservations
 */
function readReservations() {
    fetch("./resource/reservation/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showReservationlist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the reservationlist as a table
 * @param data  the reservations
 */
function showReservationlist(data) {
    let tBody = document.getElementById("reservationList");
    data.forEach(reservation => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = reservation.date;
        row.insertCell(-1).innerHTML = reservation.time;
        row.insertCell(-1).innerHTML = reservation.numberOfPersons;
        row.insertCell(-1).innerHTML = reservation.regularCustumer;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editReservation";
        button.setAttribute("data-reservationuuid", reservation.reservationUUID);
        button.addEventListener("click",editReservation);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteReservation";
        button.setAttribute("data-reservationuuid", reservation.reservationUUID);
        button.addEventListener("click", deleteReservation);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editReservation(event) {
    const button = event.target;
    const reservationUUID = button.getAttribute("data-reservationuuid");
    window.location.href = "./reservationedit.html?uuid=" + reservationUUID;
}

/**
 * deletes a reservation
 * @param event  the click-event
 */
function deleteReservation(event) {
    const button = event.target;
    const reservationUUID = button.getAttribute("data-reservationuuid");

    fetch("./resource/reservation/delete?uuid=" + reservationUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./reservation.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}