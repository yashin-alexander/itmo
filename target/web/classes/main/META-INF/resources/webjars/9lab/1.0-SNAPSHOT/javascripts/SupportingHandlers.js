/**
 * Created by alexander on 04.07.17.
 */

function findCoordinate(val){
    var r = Number(document.getElementById('hidden_r').value);
    if(val<0)
        return 200-40*Math.abs(val);
    if(val>0)
        return 200+40*val;
    if(val==0)
        return 200;
}
function makeOriginalCoordinates(val) {
    var r=Number(document.getElementById('hidden_r').value);
    if(0<=val&&val<200)
        return -r*((200-val)/ (r*40));
    if(200<val&&val<=400)
        return r * ((val - 200) /(r*40));
    if(val==200)
        return 0;
}

function addErrorMessage(input, message){
    input.innerHTML = message;
}
function isNumeric(val) {
    return /^[-+]?[0-9]+(\.[0-9]+)?$/.test(val);
}
function checkTextY(){
    var y_text = document.getElementById("y").value;
    var message = "";
    if(y_text.length == 0)
        message = "Заполните это поле!";
    else if(!isNumeric(y_text) || y_text<=-5 || y_text>=3)
        message = "Введено некорректное значение у!";
    addErrorMessage(document.getElementById("text_fail_y"), message);
    return (message.length == 0);
}

function setX(pressed_button){
    var button_array = document.getElementsByClassName("button_pressed_x");
    document.getElementById("hidden_x").value = pressed_button.value;
    for(var i = 0; i < button_array.length; ++i)
        button_array[i].className = "button_x";
    pressed_button.className = "button_pressed_x";
    pressed_button.blur();
}

function checkButtonsX(){
    var message = "";
    if(document.getElementById("hidden_x").value == "" ){
        message = "Значение X не выбрано!";
    }
    addErrorMessage(document.getElementById("x_fail"), message);
    return (message.length == 0);
}

function setR(pressed_button){
    var button_array = document.getElementsByClassName("button_pressed_r");
    document.getElementById("hidden_r").value = pressed_button.value;
    for(var i = 0; i < button_array.length; ++i)
        button_array[i].className = "button_r";
    pressed_button.className = "button_pressed_r";
    pressed_button.blur();
}

function checkButtonsR(){
    var message = "";
    if(document.getElementById("hidden_r").value == "" ){
        message = "Значение R не выбрано!";
    }
    addErrorMessage(document.getElementById("r_fail"), message);
    return (message.length == 0);
}