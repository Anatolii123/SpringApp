var login = document.getElementById("login");
var password = document.getElementById("password");
var form = document.getElementById("form");

function validateEmail(email) {
    var pattern  = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(String(email).toLowerCase());
}

function validatePassword(password) {
    var pattern1  = /[\d]/;
    var pattern2  = /[a-z]/;
    var pattern3  = /[A-Z]/;
    return pattern1.test(String(password)) && pattern2.test(String(password)) && pattern3.test(String(password));
}

form.addEventListener("submit", function() {
    if (!validateEmail(login.value) || !validatePassword(password.value)) {
        if (!validateEmail(login.value)) {
            alert("Электронный адрес введён неверно! Попробуйте ещё раз.");
        }
        if (!validatePassword(password.value) || password.value.length < 8) {
            alert("Неправильный пароль! Пароль должен состоять минимум из 8 символов, содержать строчные и " +
                "прописные буквы латинского алфавита, а также содержать минимум одну цифру.");
        }
        login.value = '';
        password.value = '';

    }

});
