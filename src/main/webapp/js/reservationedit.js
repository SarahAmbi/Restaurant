/**
 * view-controller for reservationedit.html
 * @author Sarah Ambi
 */
document.addEventListener("DOMContentLoaded", () => {
    readPublishers();
    readBook();

    document.getElementById("reservationeditForm").addEventListener("submit", saveBook);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
 */
function saveBook(event) {
    event.preventDefault();

    const bookForm = document.getElementById("bookeditForm");
    const formData = new FormData(bookForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/book/";
    const bookUUID = getQueryParam("uuid");
    if (bookUUID == null) {
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
function readBook() {
    const bookUUID = getQueryParam("uuid");
    fetch("./resource/book/read?uuid=" + bookUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showBook(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a book
 * @param data  the book-data
 */
function showBook(data) {
    document.getElementById("bookUUID").value = data.bookUUID;
    document.getElementById("title").value = data.title;
    document.getElementById("author").value = data.author;
    document.getElementById("publisher").value = data.publisherUUID;
    document.getElementById("price").value = data.price;
    document.getElementById("isbn").value = data.isbn;
}

/**
 * reads all publishers as an array
 */
function readPublishers() {

    fetch("./resource/publisher/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showPublishers(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows all person as a dropdown
 * @param data
 */
function showPersons(data) {
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