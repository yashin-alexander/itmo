/**
 * Created by alexander on 23.08.17.
 */

import {Cookies} from 'aurelia-plugins-cookies';

export class Logout{

  constructor(){}

  attached() {
    alert("login = " + Cookies.get("login"));
    Cookies.removeAll();
    document.location.href = "/";
  }
}
