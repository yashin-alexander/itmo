import {HttpClient, json} from 'aurelia-fetch-client';
import {InteractiveArea} from './interactiveArea';
import {MainHandlers} from './mainHandlers';
import {SupportingHandlers} from './supportingHandlers';

export class App {
  constructor() {
    this.mainInfo = 'This page is for authenticated users only. Welcome!';
    this.todoDescription = '';
    this.rvalues = [];
  }

  attached() {
    var canvas = document.getElementById("interactive-area");
    window.interactiveArea = new InteractiveArea(
      0,
      canvas.getContext("2d"),
      canvas.width,
      canvas.height
    );
    interactiveArea.drawArea();
    interactiveArea.setRadius(2);
    interactiveArea.drawArea();
  }

  pressedR(button){

    // alert(button);

    window.supportingHadlers = new SupportingHandlers;
    window.mainHadlers = new MainHandlers;

    supportingHadlers.setR(button);
    supportingHadlers.checkButtonsR();
    mainHadlers.radiusChangedHandler();
  }
}

