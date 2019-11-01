var publicValue = document.getElementById("publicValue");

function diffieHellman(num,exp) {
    var key = BigInt(BigInt(Math.pow(num,exp)) % 983);
    return key;
}

var privateKey = BigInt(Math.random() * 100);
var publicKey = diffieHellman(1000,privateKey);
var resultKey = diffieHellman(publicValue.value,privateKey);
publicValue.value = publicKey;