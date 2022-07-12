/**
 * view-controller for restaurant.html
 * @author Sarah Ambi
 */

document.addEventListener("DOMContentLoaded", () => {
    readRestaurants();
});

/**
 * reads all restaurants
 */
function readRestaurants() {
    fetch("./resource/restaurant/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showRestaurantlist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the restaurantlist as a table
 * @param data  the restaurants
 */
function showRestaurantlist(data) {
    let tBody = document.getElementById("restaurantlist");
    data.forEach(restaurant => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = restaurant.name;
        row.insertCell(-1).innerHTML = restaurant.place;
        row.insertCell(-1).innerHTML = restaurant.numberOfSeats;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editRestaurant";
        button.setAttribute("data-restaurantuuid", restaurant.restaurantUUID);
        button.addEventListener("click", editRestaurant);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteRestaurant";
        button.setAttribute("data-restaurantuuid", restaurant.restaurantUUID);
        button.addEventListener("click", deleteRestaurant);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editRestaurant(event) {
    const button = event.target;
    const restaurantUUID = button.getAttribute("data-restaurantuuid");
    window.location.href = "./restaurantedit.html?uuid=" + restaurantUUID;
}

/**
 * deletes a restaurant
 * @param event  the click-event
 */
function deleteRestaurant(event) {
    const button = event.target;
    const restaurantUUID = button.getAttribute("data-restaurantuuid");

    fetch("./resource/restaurant/delete?uuid=" + restaurantUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./restaurant.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}