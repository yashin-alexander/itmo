import {Router} from "aurelia-router";
import {activationStrategy} from 'aurelia-router';
import {Cookies} from 'aurelia-plugins-cookies';



export class App {
  static inject() {return [Router];}

  constructor(router) {
    this.router = router;
    this.router.configure(config => {
      config.title = 'Aurelia';
      config.map([
        {
          route: ["", "login"], name: 'login',
          moduleId: './login', nav: true, title: 'login'
        },
        {
          route: "register", name: 'register',
          moduleId: './register', nav: true, title: 'register'
        },
        {
          route: "mainApplication", name: 'mainApplication',
          moduleId: './mainApplication', nav: true, title: 'mainApplication'
        },
        {
          route: "logout", name: 'logout',
          moduleId: './logout', nav: true, title: 'logout'
        }
      ]);
    })
  }
    determineActivationStrategy() {
      return activationStrategy.replace;
  }
}
