/**
 * Created by alexander on 28.08.17.
 */

import {Cookies} from 'aurelia-plugins-cookies';
import {HttpClient, json} from 'aurelia-fetch-client';
import {Router} from "aurelia-router";
import {activationStrategy} from 'aurelia-router';

let http = new HttpClient();

export class LoginApplication {
  // static inject() {return [Router];}

  constructor(router) {
    if (Cookies.get('login') != null) {
      document.location.href = "/#/mainApplication";
      location.reload();
    }

    this.bio = "Sashcha";
    this.group = "3202";
    this.var = "102";

    this.name = "";
    this.pass = "";
    this.API = "http://localhost:9000";
    this.router = router;


  }

  determineActivationStrategy() {
    return activationStrategy.replace;
  }

  process() {}

  signup() {
    var name = this.getName();
    var pass = this.getPass();

    if (name == null || pass == null) {
      return;
    }

    $.ajax({
      url: "/register",
      type: "GET",
      data: {
        username: name,
        password: pass
      },
      success: function (response) {
        var status = JSON.parse(response.status);

        if(status==1)
          document.getElementById("wrong").innerHTML = "User already exist";
        else if(status==2)
          document.getElementById("wrong").innerHTML = "Incorrect Login";
        else if(status==3)
          document.getElementById("wrong").innerHTML = "Incorrect Password, 4 symbols at least";
        else {
          Cookies.put('login', response.login);
          window.location.replace("#/mainApplication");
          window.location.reload(false);
        }
      }
    });
  }

  getName() {
    if (this.name.length == 0) {
      document.getElementById("wrong").innerHTML = "Please enter your login and password";
      document.getElementById("wrong").style.color="red";
      return null;
    }
    document.getElementById("wrong").innerHTML = "";

    return this.name;
  }

  getPass() {
    if (this.pass.length == 0) {
      document.getElementById("wrong").innerHTML = "Please enter your login and password";
      document.getElementById("wrong").style.color="red";
      return null;
    }
    document.getElementById("wrong").innerHTML = "";

    return this.pass;
  }
}
