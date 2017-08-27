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
    var text_x_result = checkButtonsX();
    var text_r_result = checkButtonsR();
    var text_y_result = checkTextY();
    if (text_x_result && text_y_result && text_r_result) {

      var r = Number(document.getElementById('hidden_r').value);
      var x = Number(document.getElementById('hidden_x').value);
      var y = findCoordinate(Number(document.getElementById('y').value));

      y = makeOriginalCoordinates(y);

      sendSinglePoint(x, y, r);
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
        r: currentRadius,
        user: Cookies.get("login")
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
            += window.table.tableAddRow(X[i], Y[i], R[i], isInside[i], color_palette);
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
        r: r,
        username: Cookies.get("login"),
        password: Cookies.get("password")
      },
      success: function (response) {
        if (response.status == 1) {
          alert("access deny");
          document.location.replace("/");
          return;
        }

        window.table = new Table;
        window.supportingHadlers = new SupportingHandlers;

        var color_palette;
        if (response.color)
          color_palette = "green";
        else
          color_palette = "red";

        document.getElementById("answer").innerHTML +=
          window.table.tableAddRow(response.X, response.Y, response.R, response.color, color_palette)

        window.interactiveArea.drawPoint
        (window.supportingHadlers.findCoordinate(response.X),
          400 - window.supportingHadlers.findCoordinate(response.Y), color_palette);
      }
    });

    return;
  }

  deletePoints() {

    document.getElementById("answer").innerHTML = tablePrintHeader();

    $.ajax({
      url: "/remove_points",
      type: "GET",
      data: {},
      success: function (response) {
        radiusChangedHandler();
      }
    });
  }
}
