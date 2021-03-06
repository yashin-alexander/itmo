/**
 * Created by alexander on 19.06.17.
 */

function submitClick (){
    var text_x_result = checkButtonsX();
    var text_r_result = checkButtonsR();
    var text_y_result = checkTextY();
    if(text_x_result&&text_y_result&&text_r_result){

        var r = Number(document.getElementById('hidden_r').value);
        var x = Number(document.getElementById('hidden_x').value);
        var y = findCoordinate(Number(document.getElementById('y').value));

        y = makeOriginalCoordinates(y);

        sendSinglePoint(x,y,r);
    }
    return;
}

function click(event){
    if (checkButtonsR() == 0)
        return;

    var r = Number(document.getElementById('hidden_r').value);
    var x = makeOriginalCoordinates( event.clientX - 258);
    var y = -makeOriginalCoordinates( event.clientY - 105);

    sendSinglePoint(x,y,r);
}

function radiusChangedHandler() {
    if (!checkButtonsR())
        return;

    var currentRadius = Number(document.getElementById('hidden_r').value);

    window.interactiveArea.setRadius( currentRadius );

    $.ajax({
        url:"/change_r",
        type:"GET",
        data:{
            r: currentRadius
        },
        success:function(response){
            var X = JSON.parse(response.X);
            var Y = JSON.parse(response.Y);
            var R = JSON.parse(response.R);
            var isInside = JSON.parse(response.isInside);
            var color = JSON.parse(response.color);
            var color_palette;

            document.getElementById("answer").innerHTML = tablePrintHeader();

            for(var i = 0; i<X.length; i++) {


                if(color[i])
                    color_palette = "green"
                else
                    color_palette = "red"

                window.interactiveArea.drawPoint
                    (findCoordinate(X[i]),400-findCoordinate(Y[i]),color_palette);

                document.getElementById("answer").innerHTML
                    += tableAddRow(X[i], Y[i], R[i], isInside[i], color_palette);
            }
        }
    });
}

function sendSinglePoint(x,y,r) {

    $.ajax({
        url:"/add_point",
        type:"GET",
        data:{
            x:x,
            y:y,
            r:r
        },
        success:function(response){
            if(response.Owner=="access deny"){
                alert("access deny");
                document.location.replace("/");
                return;
            }

            var color_palette;
            if(response.color)
                color_palette="green";
            else
                color_palette="red";

            document.getElementById("answer").innerHTML +=
                tableAddRow(response.X,response.Y, response.R, response.color, color_palette)

            window.interactiveArea.drawPoint
            (findCoordinate(response.X),400-findCoordinate(response.Y),color_palette);
        }
    });

    return;
}

function deletePoints(){

    document.getElementById("answer").innerHTML = tablePrintHeader();

    $.ajax({
        url:"/remove_points",
        type:"GET",
        data:{
        },
        success:function(response){
            radiusChangedHandler();
        }
    });


}
