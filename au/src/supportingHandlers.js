/**
 * Created by alexander on 20.08.17.
 */

export class SupportingHandlers {
  constructor(){

  }

  findCoordinate(val) {
    var r = Number(document.getElementById('hidden_r').value);

    if (val < 0)
      return 200 - 40 * Math.abs(val);
    if (val > 0)
      return 200 + 40 * val;
    if (val == 0)
      return 200;
  }

  makeOriginalCoordinates(val) {
    var r = Number(document.getElementById('hidden_r').value);
    if (0 <= val && val < 200)
      return -r * ((200 - val) / (r * 40));
    if (200 < val && val <= 400)
      return r * ((val - 200) / (r * 40));
    if (val == 200)
      return 0;
  }

  isNumeric(val) {
    return /^[-+]?[0-9]+(\.[0-9]+)?$/.test(val);
  }

  checkTextY() {
    var y_text = document.getElementById("y").value;
    var message = "";
    if (y_text.length == 0)
      message = "Please fill the Y-field";
    else if (!this.isNumeric(y_text) || y_text <= -5 || y_text >= 3)
      message = "Incorrect y-value format!";
    document.getElementById("text_fail_y").innerHTML = message;
    return (message.length == 0);
  }

  setX(pressed_button) {
    var button_array = document.getElementsByClassName("button_pressed_x");
    document.getElementById("hidden_x").value = pressed_button;
    for(var i = 0; i < button_array.length; ++i)
      button_array[i].className = "button_x";
    document.getElementById("x="+pressed_button).className = "button_pressed_x";
  }

  checkButtonsX() {
    var message = "";
    if (document.getElementById("hidden_x").value == "") {
      message = "Please choose x-value!";
    }
    document.getElementById("x_fail").innerHTML = message;
    return (message.length == 0);
  }

  setR(pressed_button) {
    var button_array = document.getElementsByClassName("button_pressed_r");
    document.getElementById("hidden_r").value = pressed_button;
    for(var i = 0; i < button_array.length; ++i)
        button_array[i].className = "button_r";
    document.getElementById("r="+pressed_button).className = "button_pressed_r";
  }

  checkButtonsR() {
    var message = "";
    if (document.getElementById("hidden_r").value == "") {
      message = "Please choose r-value!";
    }
    document.getElementById("r_fail").innerHTML = message;
    return (message.length == 0);
  }
}
