/**
 * Created by alexander on 20.08.17.
 */

import {SupportingHandlers} from './supportingHandlers'
import {Table} from './table'
import {Cookies} from 'aurelia-plugins-cookies';

import * as $ from 'jquery';
import 'jquery-ui-dist';


export class MainHandlers {
  constructor(){
  }

  submitClick() {
    window.supportingHadlers = new SupportingHandlers;

    var text_x_result = window.supportingHadlers.checkButtonsX();
    var text_r_result = window.supportingHadlers.checkButtonsR();
    var text_y_result = window.supportingHadlers.checkTextY();
    if (text_x_result && text_y_result && text_r_result) {

      var r = Number(document.getElementById('hidden_r').value);
      var x = Number(document.getElementById('hidden_x').value);
      var y = window.supportingHadlers.findCoordinate(Number(document.getElementById('y').value));

      y = window.supportingHadlers.makeOriginalCoordinates(y);

      this.sendSinglePoint(x, y, r);
    }
    return;
  }

  click(event) {
    window.supportingHadlers = new SupportingHandlers;

    if (supportingHadlers.checkButtonsR() == 0)
      return;


    var left = document.getElementById("interactive-area").getBoundingClientRect().left;
    var top = document.getElementById("interactive-area").getBoundingClientRect().top;

    var r = Number(document.getElementById('hidden_r').value);
    var x = supportingHadlers.makeOriginalCoordinates(event.clientX - left);
    var y = -supportingHadlers.makeOriginalCoordinates(event.clientY - top);

    this.sendSinglePoint(x, y, r);
  }

  radiusChangedHandler() {

    window.supportingHadlers = new SupportingHandlers;
    window.table = new Table;

    if (!window.supportingHadlers.checkButtonsR())
      return;

    var currentRadius = Number(document.getElementById('hidden_r').value);

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
          if (color[i])
            color_palette = "green"
          else
            color_palette = "red"

          window.interactiveArea.drawPoint
          (window.supportingHadlers.findCoordinate(X[i]),
            400 - window.supportingHadlers.findCoordinate(Y[i]), color_palette);

          document.getElementById("answer").innerHTML
            += window.table.tableAddRow(X[i].toFixed(2), Y[i].toFixed(2), R[i].toFixed(2), isInside[i], color_palette);
        }
      }
    });
  }

  sendSinglePoint(x, y, r) {

    $.ajax({
      url: "/add_point",
      type: "GET",
      data: {
        x: x,
        y: y,
        r: r
      },
      success: function (response) {

        window.table = new Table;
        window.supportingHadlers = new SupportingHandlers;

        var color_palette;
        if (response.color)
          color_palette = "green";
        else
          color_palette = "red";

        document.getElementById("answer").innerHTML +=
          window.table.tableAddRow(response.X.toFixed(2), response.Y.toFixed(2), response.R.toFixed(2), response.color, color_palette)

        window.interactiveArea.drawPoint
        (window.supportingHadlers.findCoordinate(response.X),
          400 - window.supportingHadlers.findCoordinate(response.Y), color_palette);
      }
    });

    return;
  }

  deletePoints() {

    window.table = new Table;
    document.getElementById("answer").innerHTML = window.table.tablePrintHeader();

    $.ajax({
      url: "/remove_points",
      type: "GET",
      data: {},
      success: function (response) {
        window.mainHadlers = new MainHandlers();
        window.mainHadlers.radiusChangedHandler();
      }
    });
  }
}
