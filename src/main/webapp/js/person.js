/**
 * view-controller for person.html
 * @author Sarah Ambi
 */

document.addEventListener("DOMContentLoaded", () => {
    readPersons();
});

/**
 * reads all persons
 */
function readPersons() {
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
            showPersonlist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the reservationlist as a table
 * @param data  the reservations
 */
function showPersonlist(data) {
    let tBody = document.getElementById("personlist");
    data.forEach(person => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = person.firstname;
        row.insertCell(-1).innerHTML = person.lastname;
        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editPerson";
        button.setAttribute("data-personuuid", person.personUUID);
        button.addEventListener("click",editPerson);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deletePerson";
        button.setAttribute("data-personuuid", person.personUUID);
        button.addEventListener("click", deletePerson);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editPerson(event) {
    const button = event.target;
    const personUUID = button.getAttribute("data-personuuid");
    window.location.href = "./personedit.html?uuid=" + personUUID;
}

/**
 * deletes a reservation
 * @param event  the click-event
 */
function deletePerson(event) {
    const button = event.target;
    const personUUID = button.getAttribute("data-personuuid");

    fetch("./resource/person/delete?uuid=" + personUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./person.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}