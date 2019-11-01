var publicValue = document.getElementById("publicValue");
var password = document.getElementById("password");
var form = document.getElementById("form");
function diffieHellman(num, exp) {
    var key = BigInt(BigInt(BigInt(num) ** BigInt(exp) % BigInt(983)));
    return key;
}


var privateKey = BigInt(parseInt((Math.random() * 1000).toString(), 10));
var publicKey = diffieHellman(1000, privateKey);
var resultKey = diffieHellman(publicValue.value, privateKey);
publicValue.value = publicKey;

function string2Bin(s) {
    var b = new Array();
    var last = s.length;
    for (var i = 0; i < last; i++) {
        b[i] = BigInt(s.charCodeAt(i)) ^ resultKey;
    }
    return b;
}

function bin2String(array) {
    var result = "";
    for (var i = 0; i < array.length; i++) {
        result += String.fromCharCode(parseInt(array[i], 10));
    }
    return result;
}

form.addEventListener("submit", function() {
    var arr = [];
    arr = string2Bin(password.value);
    password.value = bin2String(arr);
});






