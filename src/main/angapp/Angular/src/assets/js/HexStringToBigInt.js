function strToBI(string) {
    var result = BigInt('0x' + string);
    return result;
}