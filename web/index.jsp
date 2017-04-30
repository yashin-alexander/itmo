<%--
  Created by IntelliJ IDEA.
  User: yashin alexander
  Date: 29.04.17
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
  body {
    background-size: cover;
    width: 900px; /* Ширина слоя */
    height: 700px;
    align: center;
    margin-left: 10%;
    background-image: url("pic.png");
  }
  #x_checks > p {
    display: inline;
    font-family: Georgia, "Times New Roman",fantasy;
  }
  #r_buttons > p {
    display: inline;
    font-family: Georgia, "Times New Roman", fantasy;
  }
  .submit{
    transition-duration: 0.4s;
  }
  .submit:hover{
    background-color: #4cbaa4;
    color: white;
  }
  input[type="text"],input[type="button"]{
    width: 100px;
    margin: 1px 1px;
    background: #D3D3D3;
    border: 2px solid #fff;
    text-align: center;
    display: inline-block;
    padding: 2px;
    font-weight: bold;
    border-radius: 12px;
    outline: none;
    font-size: 15px;
  }
  input[type="radio"]{
      height: 15px;
      margin: 5px 5px;
  }
  input.button_pressed{
    margin: 1px 1px;
    background: #66608B;
    border: 2px solid #fff;
    display: inline-block;
    text-align: center;
    padding: 2px;
    font-weight: bold;
    border-radius: 12px;
    outline: none;
    font-size: 15px;
    transition-duration: 0.4s;
  }
  [id$="fail"],[id$="text_fail_r"],[id$="text_fail_y"]{
    height: 4px;
    color: #EC1946;
    font-style: italic;
    font-size: 15px;
  }
  table, td, th {
    border: 2px solid black;
  }
  table {
    border-collapse: collapse;
    width: 600px;
    background-color: #FDFF9E;
  }
  th, td {
    vertical-align: top;
    text-align: center;
    padding: 3px;
    height: 20px;
    font-family: "Ubuntu Condensed", cursive ;
  }
  td[rowspan] {
    vertical-align:top;
  }

  td[input] {
    width: 400px;
    height: 400px;
    vertical-align:top;
  }
  tr:nth-child(even) {
    background-color: #f2f2f2;
    text-align: center;
  }
  #answer {
    border: solid 1px black;
    overflow: scroll !important;
    padding: 3px;
    max-width: 587px;
    max-height: 300px;
  }
  #scrollable_table {
    padding: 3px;
    max-width: 650px;
    max-height: 650px;
    overflow: auto;
  }
  #header > p {
    font-family: "Ubuntu Condensed", cursive ;
  }
  input[type="radio"] {
      display: inline-block;
      width: 17px;
      height: 17px;
      /*margin: 15px 0 0 0;*/
      border: 10px;
  }


</style>

<script type="text/javascript">

    var x=false;
    var z=false;
    function imgchange1(obj,imgX,imgY) {
        if  (z){
            obj.src=imgX;
        } else {
            obj.src=imgY;
        }
        z=!z;
    }
    function imgchange2(obj,imgX,imgY) {
        imgchange1(obj,imgX,imgY);
        setTimeout(imgchange1,500,obj,imgX,imgY);
    }
    function imgchange3(obj,imgX,imgY) {
        if  (x){
            obj.src=imgX;
        } else {
            obj.src=imgY;
        }
        x=!x;
    }
    function imgchange4(obj,imgX,imgY) {
        imgchange3(obj,imgX,imgY);
        setTimeout(imgchange3,500,obj,imgX,imgY);
    }

    function validateForm(){
        var X=findSelectionX();
        var Y=document.getElementById('y').value;
        var R=document.getElementById('r').value;

        var radio_result = checkRadio();
        var text_r_result = checkTextR();
        var text_y_result = checkTextY();
        if (radio_result&&text_r_result&&text_y_result){
//            imgchange2(obj,"areas.png","happy.jpg");
            var req = new XMLHttpRequest();
            req.onreadystatechange = function() {
                if(req.readyState == 4 && req.status == 200) {
                    document.getElementById("answer").innerHTML += this.responseText;
                }
            }
            req.open("GET", "checkar.php?X="+ X + "&Y=" + Y + "&R=" + R, true);
            req.send();
            return false;
        }
//        imgchange4(obj,"areas.png","gray.png");
        return false;
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
        else if(!isNumeric(y_text) || y_text<=-3 || y_text>=3)
            message = "Введено некорректное значение у!";
        addErrorMessage(document.getElementById("text_fail_y"), message);
        return (message.length == 0);
    }
    function checkTextR(){
        var r_text = document.getElementById("r").value;
        var message = "";
        if(r_text.length == 0)
            message = "Заполните это поле!";
        else if(!isNumeric(r_text) || r_text<=-3 || r_text>=3)
            message = "Введено некорректное значение r!";
        addErrorMessage(document.getElementById("text_fail_r"), message);
        return (message.length == 0);
    }
    function findSelectionX() {
        var radios = document.getElementsByName('x');
        for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                return (radios[i].value);
            }
        }
    }
    function checkRadio(){
        var message = "";
        if(!(document.getElementById("x=-2").checked || document.getElementById("x=-1.5").checked || document.getElementById("x=-1").checked || document.getElementById("x=-0.5").checked || document.getElementById("x=0").checked || document.getElementById("x=0.5").checked || document.getElementById("x=1").checked || document.getElementById("x=1.5").checked || document.getElementById("x=2").checked )){
            message = "Значение X не выбрано!";
        }
        addErrorMessage(document.getElementById("radio_fail"), message);
        return (message.length == 0);

    }

</script>

<head>
  <meta charset="utf-8">
  <title>Лабораторная 7 Яшин</title>
</head>
<body>
<table id='main' >
  <tr id="header" >
    <td colspan='2'>
      <div style="font-size: 20px">
        <!--<p><img src="wojak.png" width="50" height="50" onclick=imgchange(this,"wojak.png","wojac.jpeg")>-->
        <p>Яшин А.П.
          P3202
          Вариант 41900</p>
        <!--<img src="wojac1.png" width="50" height="50" onclick=imgchange1(this,"wojac1.png","pepe.jpg")></p>-->
      </div>
    </td>
  </tr>

  <tr style='height:200px; ' >
    <td  rowspan='3' >
      <div id="scrollable_table">
        <table id="answer" style='border-spacing: 50px 0; overflow: scroll;' >
          <div >
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
      <%--<p style='margin-top:0%'>--%>
        <%--<img name = "area" class="pic" src="areas.png" width="270" title="Область" height="270">--%>
      <%--</p>--%>
      <div id="interactive-block">
        <canvas id="area" height="400px" width="400px"></canvas>
          <script src="Area.js"></script>
          <script src="GeomitryUtil.js"></script>
          <script src="Handlers.js"></script>

          <script type="text/javascript">


              var areaz = document.getElementById("area");
              ctx = areaz.getContext('2d');
              window.area = new Area(
                  5,
                  areaz.getContext("2d"),
                  areaz.width,
                  areaz.height
              );
//              alert("fef");

              area.drawArea();
          </script>
        <%--<script>--%>

            <%--var areaz = document.getElementById("area"),--%>
            <%--ctx = areaz.getContext('2d');--%>

            <%--ctx.fillStyle = "orange";--%>
            <%--ctx.fillRect(0, 0, areaz.width, areaz.height);--%>

            <%--var mid_x=200;--%>
            <%--var mid_y=200;--%>

            <%--ctx.beginPath();--%>

            <%--ctx.moveTo(0, mid_y);--%>
            <%--ctx.lineTo(areaz.width, mid_y); //absyss--%>
            <%--ctx.lineTo(areaz.width-5,mid_y-3);--%>
            <%--ctx.moveTo(areaz.width, mid_y);--%>
            <%--ctx.lineTo(areaz.width-5,mid_y+3);//streloch'ka--%>

            <%--ctx.moveTo(mid_x,areaz.height);--%>
            <%--ctx.lineTo(mid_x,0);            //ordinate--%>
            <%--ctx.lineTo(mid_x-3,5);--%>
            <%--ctx.lineTo(mid_x,0);--%>
            <%--ctx.lineTo(mid_x+3,5);  //streloch'ka--%>

            <%--ctx.fillStyle = "blue";--%>
            <%--ctx.fillRect(mid_x,mid_y,75,150);--%>


            <%--ctx.stroke();--%>
        <%--</script>--%>
      </div>
    </td>
  </tr>
  <tr>
    <td>
      <form class="get_form" method="get" action="checkar.php" style='vertical-align:top;' onsubmit="return validateForm()">
        <table id="input">
          <tr>
            <p> <b>X=</b>
                <input id="x=-2" type="radio" name="x" value="-2" onchange="checkRadio()">-2 </input>
                <input id="x=-1.5" type="radio" name="x" value="-1.5" onchange="checkRadio()">-1.5 </input>
                <input id="x=-1" type="radio" name="x" value="-1" onchange="checkRadio()">-1 </input>
                <input id="x=-0.5" type="radio" name="x" value="-0.5" onchange="checkRadio()">-0.5 </input>
                <input id="x=0" type="radio" name="x" value="0" onchange="checkRadio()">0 </input>
                <input id="x=0.5" type="radio" name="x" value="0.5" onchange="checkRadio()">0.5 </input>
                <input id="x=1" type="radio" name="x" value="1" onchange="checkRadio()">1 </input>
                <input id="x=1.5" type="radio" name="x" value="1.5" onchange="checkRadio()">1.5 </input>
                <input id="x=2" type="radio" name="x" value="2" onchange="checkRadio()">2 </input>
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
              <input class="submit" type="button" id="submit" value="Отправить" onclick=validateForm()>
            </p>
          </tr>
        </table>
      </form>
    </td>
  </tr>
</table>
</body>
</html>
