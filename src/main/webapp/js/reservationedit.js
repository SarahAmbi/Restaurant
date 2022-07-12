/**
 * view-controller for reservationedit.html
 * @author Sarah Ambi
 */
document.addEventListener("DOMContentLoaded", () => {
    readClients();
    readReservation();

    document.getElementById("reservationeditForm").addEventListener("submit", saveReservation);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
 */
function saveReservation(event) {
    event.preventDefault();

    const reservationForm = document.getElementById("reservationeditForm");
    const formData = new FormData(reservationForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/reservation/";
    const reservationUUID = getQueryParam("uuid");
    if (reservationUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a book
 */
function readReservation() {
    const reservationUUID = getQueryParam("uuid");
    fetch("./resource/reservation/read?uuid=" + reservationUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showReservation(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a book
 * @param data  the book-data
 */
function showReservation(data) {
    document.getElementById("reservationUUID").value = data.reservationUUID;
    document.getElementById("date").value = data.title;
    document.getElementById("time").value = data.author;
    document.getElementById("client").value = data.personUUID;
    document.getElementById("numberOfPersons").value = data.numberOfPersons;
    document.getElementById("regularCustumer").value = data.regularCustumer;
}

/**
 * reads all publishers as an array
 */
function readClients() {

    fetch("./resource/person/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showClients(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows all person as a dropdown
 * @param data
 */
function showClient(data) {
    let dropdown = document.getElementById("client");
    data.forEach(person => {
        let option = document.createElement("option");
        option.text = person.publisherName;
        option.value = publisher.publisherUUID;
        dropdown.add(option);
    })
}

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./reservation.html";
}