var s11 = document.getElementById('m1r');
var s12 = document.getElementById('m1c');
var s21 = document.getElementById('m2r');
var s22 = document.getElementById('m2c');
var firstMat = document.getElementById('matrix1');
var secondMat = document.getElementById('matrix2');
firstMat.style.top = '10%';
firstMat.style.left = '35%';
secondMat.style.top = '10%';
secondMat.style.left = '75%';

function checkInput(input) {
    input.value = input.value.replace(/[^\d,]/g, '')
};

function buildMatrix(size1, size2, id, matrix) {
    var sz1 = size1.value;
    var sz2 = size2.value;
    size1.style.width = size1.value.toString() != '' ? (size1.value.toString().length*14).toString() + 'px': '14px';
    size2.style.width = size2.value.toString() != '' ? (size2.value.toString().length*14).toString() + 'px': '14px';
    s21.style.width = s11.style.width;
    s22.style.width = s12.style.width;
    s11.style.width = s21.style.width;
    s12.style.width = s22.style.width;
    var tableHTML = '';
    for (var i = 1; i <= sz1; i++){
        row = '<tr>';
        for (var j = 1; j <= sz2; j++) {
            row += '<td><input value="" type="text" maxlength="50" size="5" id="' +
                id.toString() + i.toString() + j.toString() +
                '" name="' + id.toString() + i.toString() + j.toString() +
                '" onkeyup="return checkInput(this);" onchange="return checkInput(this);"></td>';
        }
        row += '</tr>';
        tableHTML += row;
    }
    matrix.innerHTML = tableHTML;
    matrix.createCaption().innerHTML = "<b>Матрица " + id.toString() + "</b>";
}
buildMatrix(s11,s12,1,firstMat);
buildMatrix(s21,s22,2,secondMat);

function changeState(arg, state1, state2) {
    arg.add(state2);
    arg.remove(state1);
}

cbx.onclick = function() {
    if(document.body.className != "Change") {
        changeState(document.account.classList,"Unchange","Change");
        changeState(document.body.classList,"Unchange","Change");
        changeState(document.mtx.classList,"Unchange","Change");
        firstMat.caption.style.color = "white";
        secondMat.caption.style.color = "white";
        return;
    }
    changeState(document.body.classList,"Change","Unchange");
    changeState(document.account.classList,"Change","Unchange");
    changeState(document.mtx.classList,"Change","Unchange");
    firstMat.caption.style.color = "black";
    secondMat.caption.style.color = "black";
}