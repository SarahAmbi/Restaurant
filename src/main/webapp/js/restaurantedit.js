/**
 * view-controller for restaurantedit.html
 * @author Sarah
 */
//const userRole = getCookie("userRole");
document.addEventListener("DOMContentLoaded", () => {
    //showNav(userRole);
    readRestaurant();

    document.getElementById("restauranteditForm").addEventListener("submit", saveRestaurant);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a author
 */
function saveRestaurant(event) {
    event.preventDefault();
    showMessage("", "info");

    const restaurantForm = document.getElementById("restauranteditForm");
    const formData = new FormData(restaurantForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/restaurant/";
    const restaurantUUID = getQueryParam("uuid");
    if (restaurantUUID == null) {
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
                "Content-Type": "application/x-www-form-urlencoded",
                "Authorization": "Bearer " + readStorage("token")
            },
            body: data
        })
        .then(function (response) {
            if (response.ok) {
                return response;
            } else if (response.status === 401) {
                window.location.href = "./";
            } else {
                showMessage("Error while saving", "error");
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a author
 */
function readRestaurant() {
    const restaurantUUID = getQueryParam("uuid");
    fetch("./resource/restaurant/read?uuid=" + restaurantUUID, {
        headers: { "Authorization": "Bearer " + readStorage("token")}
    })
        .then(function (response) {
            if (response.ok) {
                return response;
            } else if (response.status === 401){
                window.location.href = "./";
            } else{
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showRestaurant(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a author
 * @param data  the author-data
 */
function showRestaurant(data) {
    document.getElementById("restaurantUUID").value = data.restaurantUUID;
    document.getElementById("place").value = data.place;
    document.getElementById("numberOfSeats").value = data.numberOfSeats;

    const locked =  !(userRole === "user" || userRole === "admin");
    lockForm("restauranteditForm", locked);
}

/**
 * redirects to the authorlist
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./restaurant.html";
}