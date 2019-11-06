var uname = document.getElementById("name");
var lname = document.getElementById("lname");
var usurname = document.getElementById("surname");
var lsurname = document.getElementById("lsurname");
var ulogin = document.getElementById("login");
var lemail = document.getElementById("lemail");
var upassword = document.getElementById("password");
var lpassword = document.getElementById("lpassword");
var ucopyPassword = document.getElementById("copyPassword");
var lcopyPassword = document.getElementById("lcopyPassword");
var ubirth = document.getElementById("birth");
var lbirth = document.getElementById("lbirth");

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
    button.disabled =
        !(uname.value && usurname.value && ulogin.value && upassword.value && ubirth.value && ucopyPassword.value && ucopyPassword.value == upassword.value
        && validateEmail(ulogin.value) && validatePassword(upassword.value));
    lname.style.color = uname.value  ? "black" : "red";
    lsurname.style.color = usurname.value ? "black" : "red";
    lemail.style.color = ulogin.value && validateEmail(ulogin.value)  ? "black" : "red";
    lpassword.style.color = upassword.value && validatePassword(upassword.value)  ? "black" : "red";
    lcopyPassword.style.color = ucopyPassword.value && ucopyPassword.value == upassword.value  ? "black" : "red";
    lbirth.style.color = ubirth.value ? "black" : "red";
}








