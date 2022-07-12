/**
 *  view-controller for login-form
 */
document.cookie = "userRole=guest; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
document.addEventListener("DOMContentLoaded", () => {
/**
 * view-controller for login-form
 */
document.cookie = "userRole=guest; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
document.addEventListener("DOMContentLoaded", () => {
    showNav("guest");
    document.getElementById("loginForm").addEventListener("submit", loginUser);
});

function loginUser(event) {
    event.preventDefault();
    const loginForm = document.getElementById("loginForm");
    const formData = new FormData(loginForm);
    const data = new URLSearchParams(formData);

    fetch("./resource/user/login",
        {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: data
            })
            .then(function (response) {
                if (!response.ok) {
                    showMessage("Benutzername/Passwort")
                } else loginSuccess(response)
            })
            .catch(function (error) {
                console.log(error);
            });
}

/**
 * save the JWToken and redirect
 * @param response
 */
function loginSuccess(response) {
    //saveToken(response.headers);
    window.location.href = "./restaurant.html";
}
    document.getElementById("loginForm").addEventListener("submit", loginUser);
});