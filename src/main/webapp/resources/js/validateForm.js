var uname = document.getElementById("name");
var usurname = document.getElementById("surname");
var ulogin = document.getElementById("login");
var upassword = document.getElementById("password");
var ucopyPassword = document.getElementById("copyPassword");
var ubirth = document.getElementById("birth");
var button = document.getElementById("registration");

function validateEmail(email) {
    var pattern  = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(String(email).toLowerCase());
}

function validatePassword(password) {
    var pattern1  = /[a-z]/;
    var pattern2  = /[A-Z]/;
    var pattern3  = /[0-9]/;
    return pattern1.test(String(password)) && pattern2.test(String(password)) && pattern3.test(String(password)) && password.length >= 8;
}

function checkForm() {
    button.disabled = uname.value && usurname.value && ulogin.value && upassword.value &&
    ucopyPassword.value && validateEmail(ulogin.value) && validatePassword(upassword.value) ? false : true;
}








