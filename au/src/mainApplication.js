/**
 * Created by alexander on 20.08.17.
 */
import {InteractiveArea} from './interactiveArea';
import {MainHandlers} from './mainHandlers';
import {Table} from './table';
import {SupportingHandlers} from './supportingHandlers';
import {Cookies} from 'aurelia-plugins-cookies';

export class mainApplication {
  constructor() {
    this.userId = Cookies.get('login');
    this.login = Cookies.get('login');

    if (this.userId == null) {
      document.location.href = "/";
    }

    this.todoDescription = '';
    this.rvalues = [];

    this.clickOnCanvas = e =>{
      window.mainHadlers = new MainHandlers;
      mainHadlers.click(e);
    }

    this.radiusChange = e =>{
      window.mainHadlers = new MainHandlers;
      mainHadlers.radiusChangedHandler(e);
    }
  }

  attached() {
    var canvas = document.getElementById("interactive-area");
    window.table = new Table;
    window.interactiveArea = new InteractiveArea(
      0,
      canvas.getContext("2d"),
      400,
      400
    );
    interactiveArea.drawArea();
    document.getElementById("interactive-area").addEventListener("click", this.clickOnCanvas );
    document.getElementById("hidden_r").addEventListener("change", this.radiusChange);

	  this.radiusCha();
    //document.getElementById("answer").innerHTML += window.table.tablePrintHeader();
  }

  pressedR(button){

    window.supportingHadlers = new SupportingHandlers;
    window.mainHadlers = new MainHandlers;

    supportingHadlers.setR(button);
    supportingHadlers.checkButtonsR();
    mainHadlers.radiusChangedHandler();
  }
  pressedX(button){

    window.supportingHadlers = new SupportingHandlers;
    window.mainHadlers = new MainHandlers;

    supportingHadlers.setX(button);
    supportingHadlers.checkButtonsX();
  }
  checkText(){
    window.supportingHadlers = new SupportingHandlers;
    window.supportingHadlers.checkTextY();
  }
  submitClick(){
    window.mainHadlers = new MainHandlers;
    window.mainHadlers.submitClick();
  }
  deletePoints(){
    window.mainHadlers = new MainHandlers;
    window.mainHadlers.deletePoints();
  }
  logout(){
    location.href = '#/logout';
  }

	 radiusCha() { 
		var currentRadius = 1;
		     window.supportingHadlers = new SupportingHandlers;
		     window.table = new Table;

		     window.interactiveArea.setRadius(currentRadius);

		     $.ajax({
			           url: "/change_r",
			           type: "GET",
			           data: {
					           r: currentRadius
					         },
			           success: function (response) {
					           var X = JSON.parse(response.X);
					           var Y = JSON.parse(response.Y);
					           var R = JSON.parse(response.R);
					           var isInside = JSON.parse(response.isInside);
					           var color = JSON.parse(response.color);
					           var color_palette;

					           document.getElementById("answer").innerHTML = window.table.tablePrintHeader();

					           for (var i = 0; i < X.length; i++) {
								color_palette = "-"

							             document.getElementById("answer").innerHTML
							               += window.table.tableAddRow(X[i].toFixed(2), Y[i].toFixed(2), R[i].toFixed(2), isInside[i], color_palette);
							           }
					         }
			         });
		   }

}

