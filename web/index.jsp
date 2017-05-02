<%--
  Created by IntelliJ IDEA.
  User: yashin alexander
  Date: 29.04.17
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script language="JavaScript" type="text/javascript">

    sessionStorage.r = "";
    sessionStorage.x = "";
    sessionStorage.y = "";

    function send(){
        var radio_result = checkRadio();
        var text_r_result = checkTextR();
        var text_y_result = checkTextY();

        if(radio_result&&text_y_result&&text_r_result){
            var r=Number(document.getElementById('r').value);
            var x = findCoordinate(findSelectionX());
            var y = findCoordinate(Number(document.getElementById('y').value));

            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4) {
                    resp = xmlhttp.responseText;
                    var C = document.createElementNS("http://www.w3.org/2000/svg","circle");
                    C.setAttributeNS(null, "cx", x.toString());
                    C.setAttributeNS(null, "cy", y.toString());
                    C.setAttributeNS(null, "r", "4");
                    C.setAttributeNS(null, "fill", resp.toString());
                    document.getElementById("svg").appendChild(C);
                    document.getElementById("answer").innerHTML = this.responseText;
                }
            }
            xmlhttp.open("GET", "/7lab_war_exploded/controller_servlet?x=" + (findSelectionX()).toString() + "&y="
                + (Number(document.getElementById('y').value).toString()) + "&r=" + r.toString() + "&clicked=1", false);
            xmlhttp.send();
        }
        return false;
    }

    function findCoordinate(val){
        var r=Number(document.getElementById('r').value);

        if(r<Math.abs(val)){
            if(val<0)
                return 25;
            if(val>0)
                return 375;
        }else{
            if(val<0)
                return (200-((Math.abs(val)/r)*150));
            if(val>0)
                return 200+((val/r)*150);
            if(val==0)
                return 200;
        }
    }


    function click(event){

        if (checkTextR() == 0){
            return ;
        }


        var r = document.getElementById("r").value;
        var resp = "";

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4) {
                resp = xmlhttp.responseText;
                var C = document.createElementNS("http://www.w3.org/2000/svg","circle");
                C.setAttributeNS(null, "cx", (event.clientX - 861.0).toString());
                C.setAttributeNS(null, "cy", (event.clientY - 47.0).toString());
                C.setAttributeNS(null, "r", "4");
                C.setAttributeNS(null, "fill", resp.toString());
                document.getElementById("svg").appendChild(C);
                document.getElementById("answer").innerHTML = this.responseText;
            }
        }
        alert(((event.clientX - 861)/400 * 4).toString());
        alert(((-event.clientY + 45)/400 * 4).toString());
        xmlhttp.open("GET", "/7lab_war_exploded/controller_servlet?x=" + ((event.clientX - 861.0)/400.0 * 4.0).toString() + "&y="
            + ((-event.clientY + 45.0)/400.0 * 4.0).toString() + "&r=" + r.toString() + "&clicked=1" + "&set=", false);
        xmlhttp.send();
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
    function checkTextR(){
        var r_text = document.getElementById("r").value;
        var message = "";
        if(r_text.length == 0)
            message = "Заполните это поле!";
        else if(!isNumeric(r_text) || r_text<=1 || r_text>=4)
            message = "Введено некорректное значение r!";
        addErrorMessage(document.getElementById("text_fail_r"), message);
        return (message.length == 0);
    }
    function findSelectionX() {
        var radios = document.getElementsByName('x');
        for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                return (Number(radios[i].value));
            }
        }
    }
    function checkRadio(){
        var message = "";
        if(!(document.getElementById("x=-4").checked || document.getElementById("x=-3").checked ||
            document.getElementById("x=-2").checked || document.getElementById("x=-1").checked ||
            document.getElementById("x=0").checked || document.getElementById("x=1").checked ||
            document.getElementById("x=2").checked || document.getElementById("x=3").checked ||
            document.getElementById("x=4").checked )){
            message = "Значение X не выбрано!";
        }
        addErrorMessage(document.getElementById("radio_fail"), message);
        return (message.length == 0);

    }

    function deletePoints(){

    }

</script>

<head>
    <title>Лабораторная 7 Яшин</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="style.css" type="text/css" />

</head>
<body>
<table id='main' >
  <tr id="header" >
    <td colspan='2'>
      <div style="font-size: 20px">
        <p>Яшин А.П.
          P3202
          Вариант 41900</p>
      </div>
    </td>
  </tr>

  <tr style='height:200px; ' >
    <td  rowspan='3' >
      <div id="scrollable_table">
        <table id="answer" style='border-spacing: 50px 0; overflow: scroll;' >
          <div  id="results-block">
              <tr>
              <th>X</th>
              <th>Y</th>
              <th>R</th>
              <th>Is inside?</th>
              <th>Time</th>
              <th>Working time</th>
            </tr>
          </div>
        </table>
      </div>
    </td>
    <td>
      <div id="interactive-block">
            <svg class="svg" id="svg" width="400" height="400" onclick=click(event)>
                <symbol id="s-crown">
                    <polygon points="197 8, 200 0, 203 8" stroke="black" fill="transparent" stroke-width="2"/>
                    <polygon points="392 197, 400 200, 392 203" stroke="black" fill="transparent" stroke-width="2"/>

                    <path d="M125 200
                     A 75, 75, 0, 0, 0, 200 275
                     L 200 350 L 275 350 L 275 200 L 200 50 L 200 200 L 125 200 Z"
                          fill="skyblue" stroke="#1d1e1e" stroke-dasharray="1" stroke-width="3"/>
                    <line x1="200" y1="400" x2="200" y2="0" stroke="black" stroke-width="2"/>
                    <line x1="0" y1="200" x2="400" y2="200" stroke="black" stroke-width="2"/>

              </symbol>

              <use xlink:href="#s-crown" x="0" y="0"/>
          </svg>

      </div>
    </td>
  </tr>
  <tr>
    <td>
      <form class="get_form" method="get" action="controller_servlet" style='vertical-align:top;'>
        <table id="input">
          <tr>
            <p> <b>X=</b>
                <input id="x=-4" type="radio" name="x" value="-4" onchange="checkRadio()">-4 </input>
                <input id="x=-3" type="radio" name="x" value="-3" onchange="checkRadio()">-3 </input>
                <input id="x=-2" type="radio" name="x" value="-2" onchange="checkRadio()">-2 </input>
                <input id="x=-1" type="radio" name="x" value="-1" onchange="checkRadio()">-1 </input>
                <input id="x=0" type="radio" name="x" value="0" onchange="checkRadio()">0 </input>
                <input id="x=1" type="radio" name="x" value="1" onchange="checkRadio()">1 </input>
                <input id="x=2" type="radio" name="x" value="2" onchange="checkRadio()">2 </input>
                <input id="x=3" type="radio" name="x" value="3" onchange="checkRadio()">3 </input>
                <input id="x=4" type="radio" name="x" value="4" onchange="checkRadio()">4 </input>
            </p>
              <p id="radio_fail">
          </tr>
          <tr>
            <p>Введите значение Y:</p>
            <p><input id="y" name="y" placeholder="-5<y<3" type="text" style='vertical-align:top'  tabindex="10" oninput="checkTextY();"></p>
            <p id="text_fail_y"><p>
          </tr>
          <tr>
            <p>Выберите значение R:</p>
              <p><input id="r" name="r" placeholder="1<r<4" type="text" style='vertical-align:top'  tabindex="11" oninput="checkTextR();"></p>
              <p id="text_fail_r"><p>
          </tr>
          <tr>
            <p style='margin-top:20px'>
                <input class="submit" type="button" id="submit" value="Отправить" onclick=send()>
                <input class="submit" type="button" id="clear" value="Очистить" onclick=deletePoints()>
            </p>
          </tr>
        </table>
      </form>
    </td>
  </tr>
</table>
</body>
</html>
