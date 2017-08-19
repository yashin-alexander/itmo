import {HttpClient, json} from 'aurelia-fetch-client';
import {InteractiveArea} from './interactiveArea';

export class App {
  constructor() {
    this.mainInfo = 'This page is for authenticated users only. Welcome!';
    this.todoDescription = '';
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
  }
}

