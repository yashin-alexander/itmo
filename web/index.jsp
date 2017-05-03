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
            var y = findCoordinate(-Number(document.getElementById('y').value));

            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4) {
//                    resp = xmlhttp.responseText;

                    var parser = new DOMParser();
                    var tableDOM = parser.parseFromString("<html><body>" + this.responseText + "</body></html>", "text/html");
                    var rows = tableDOM.getElementsByTagName("tr");
                    var currentResult = rows[rows.length - 1].getElementsByTagName("td");
                    //alert(currentResult[3].innerHTML);

                    var C = document.createElementNS("http://www.w3.org/2000/svg","circle");
                    C.setAttributeNS(null, "cx", x.toString());
                    C.setAttributeNS(null, "cy", y.toString());
                    C.setAttributeNS(null, "r", "4");
                    C.setAttributeNS(null, "fill", currentResult[3].innerHTML);
                    document.getElementById("svg").appendChild(C);
                    document.getElementById("answer").innerHTML = this.responseText;


                }
            }
            //alert(Number(document.getElementById('y').value).toString());
            xmlhttp.open("GET", "/7lab_war_exploded/controller_servlet?&clear=" +  "false" + "&changer=" + "false" + "&x=" + (findSelectionX()).toString() + "&y="
                + (Number(document.getElementById('y').value).toString()) + "&r=" + r.toString(), false);
            xmlhttp.send();
        }
        return false;
    }

    function findCoordinate(val){
        var r=Number(document.getElementById('r').value);
            if(val<0)
                return 200-40*Math.abs(val);
            if(val>0)
                return 200+40*val;
            if(val==0)
                return 200;
    }

    function makeOriginalCoordinates(val) {
        var r=Number(document.getElementById('r').value);
        if(0<=val&&val<200)
            return -r*((200-val)/ (r*40));
        if(200<val&&val<=400)
            return r * ((val - 200) /(r*40));
        if(val==200)
            return 0;
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
                var parser = new DOMParser();
                var tableDOM = parser.parseFromString("<html><body>" + this.responseText + "</body></html>", "text/html");
                var rows = tableDOM.getElementsByTagName("tr");
                var currentResult = rows[rows.length - 1].getElementsByTagName("td");



//                alert(currentResult[0].innerText);
//                alert(findCoordinate(currentResult[0].innerText));

                var step = InteractiveArea.DEFAULT_STEP;

                window.interactiveArea.drawPoint(
                    findCoordinate(currentResult[0].innerText),
                    400-findCoordinate(currentResult[1].innerText),
                    (currentResult[3].innerText));
                document.getElementById("answer").innerHTML = this.responseText;
            }
        }

        var x = makeOriginalCoordinates( event.clientX - 258);
        var y = -makeOriginalCoordinates( event.clientY - 45);

        xmlhttp.open("GET", "/7lab_war_exploded/controller_servlet?&clear=" + "false"+ "&changer=" + "false" +"&x=" + x.toString() + "&y="
            + y.toString() + "&r=" + r.toString() , false);
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
        else if(!isNumeric(r_text) || r_text<1 || r_text>4)
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
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4) {
                alert(this.responseText);
                resp = xmlhttp.responseText;
                document.getElementById("answer").innerHTML = this.responseText;
                //alert(this.responseText);
                //el.parentNode.removeChild(i);
                //location.reload(true);
            }
        }
        xmlhttp.open("GET", "/7lab_war_exploded/controller_servlet?&clear=" + "", false);
        xmlhttp.send();
    }

    function radiusChangedHandler() {


        if (!checkTextR()){
            return;
        }

        var currentRadius = document.getElementById("r").value;

        window.interactiveArea.clearArea();
        window.interactiveArea.setRadius( currentRadius );

        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if ( xhr.readyState == 4 ) {

                alert(this.responseText);
                var parser = new DOMParser();
                var tableDOM = parser.parseFromString("<html><body>" + this.responseText + "</body></html>", "text/html");

                var rows = tableDOM.getElementsByTagName("tr");

                var currentRow;
                for( var i = 1; i < rows.length ; i++ ) {
                    currentRow = rows[i];
                    var currentResult = currentRow.getElementsByTagName("td");

                    alert(currentResult[3].innerText);
                    alert(currentResult[4].innerText);
                    window.interactiveArea.drawPoint(
                        findCoordinate(currentResult[0].innerText),
                        400-findCoordinate(currentResult[1].innerText),
                        (currentResult[3].innerText));
                }
            }
        };

        var r = document.getElementById("r").value;

//        alert("alarm");
        xhr.open("GET", "/7lab_war_exploded/controller_servlet?&changer=" + "" + "&clear=" + "false" + "&r=" + r.toString() , false);
//        alert("sosi");
        xhr.send();
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

    <td>
      <div id="interactive-block">
          <canvas id="interactive-area" height="400px" width="400px"></canvas>
          <script src="InteractiveArea.js"></script>
          <script src="GeometryUtil.js"></script>
          <script src="Handlers.js"></script>
          <script>
              var canvas = document.getElementById("interactive-area");
              //replaceOriginOfCoordinatesToCenter( canvas.getContext("2d"), canvas.width, canvas.height );
              window.interactiveArea = new InteractiveArea(
                  0,
                  canvas.getContext("2d"),
                  canvas.width,
                  canvas.height
              );

              interactiveArea.drawArea();
              canvas.addEventListener("click", click );
              document.getElementById("r").addEventListener("change", radiusChangedHandler);

          </script>
      </div>
    </td>
    <td  rowspan='3' >
      <div id="scrollable_table">
          <table id="answer" style='border-spacing: 50px 0; overflow: scroll;' >

          </table>
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
              <p><input id="r" name="r" placeholder="1<r<4" type="text" style='vertical-align:top'  tabindex="11" oninput="radiusChangedHandler();"></p>
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
