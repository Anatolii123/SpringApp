var publicValue = document.getElementById("publicValue");

function diffieHellman(num,exp) {
    var key = BigInt(BigInt(BigInt(num)**BigInt(exp) % BigInt(983)));
    return key;
}

var privateKey = BigInt(parseInt((Math.random() * 1000).toString(),10));
var publicKey = diffieHellman(1000,privateKey);
var resultKey = diffieHellman(publicValue.value,privateKey);
publicValue.value = publicKey;
print(publicValue.value);