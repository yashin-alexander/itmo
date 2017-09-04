define("app",["exports","aurelia-router","aurelia-plugins-cookies"],function(t,e,n){"use strict";function i(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0}),t.App=void 0;t.App=function(){function t(e){i(this,t),this.router=e,this.router.configure(function(t){t.title="Aurelia",t.map([{route:["","login"],name:"login",moduleId:"./login",nav:!0,title:"login"},{route:"register",name:"register",moduleId:"./register",nav:!0,title:"register"},{route:"mainApplication",name:"mainApplication",moduleId:"./mainApplication",nav:!0,title:"mainApplication"},{route:"logout",name:"logout",moduleId:"./logout",nav:!0,title:"logout"}])})}return t.inject=function(){return[e.Router]},t.prototype.determineActivationStrategy=function(){return e.activationStrategy.replace},t}()}),define("aurelia-plugins-cookies",["exports"],function(t){"use strict";function e(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});t.Cookies=function(){function t(){e(this,t)}return t.get=function(t){var e=this.getAll();return e&&e[t]?e[t]:null},t.getAll=function(){return this._parse(document.cookie)},t.getObject=function(t){var e=this.get(t);return e?JSON.parse(e):e},t.put=function(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:{},i=n.expires;null==e&&(i="Thu, 01 Jan 1970 00:00:01 GMT"),"string"==typeof i&&(i=new Date(i));var o=this._encode(t)+"="+(null!=e?this._encode(e):"");n.path&&(o+="; path="+n.path),n.domain&&(o+="; domain="+n.domain),n.expires&&(o+="; expires="+i.toUTCString()),n.secure&&(o+="; secure"),document.cookie=o},t.putObject=function(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:{};this.put(t,JSON.stringify(e),n)},t.remove=function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};this.put(t,null,e)},t.removeAll=function(){var t=this,e=this.getAll();Object.keys(e).forEach(function(e){return t.remove(e)})},t._decode=function(t){try{return decodeURIComponent(t)}catch(t){return null}},t._encode=function(t){try{return encodeURIComponent(t)}catch(t){return null}},t._parse=function(t){var e={},n=t.split(/ *; */);if(""===n[0])return e;for(var i=0,o=n.length;i<o;i+=1){var a=n[i].split("=");e[this._decode(a[0])]=this._decode(a[1])}return e},t}()}),define("environment",["exports"],function(t){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={debug:!1,testing:!1}}),define("interactiveArea",["exports"],function(t){"use strict";function e(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});var n=function(){function t(t,e){for(var n=0;n<e.length;n++){var i=e[n];i.enumerable=i.enumerable||!1,i.configurable=!0,"value"in i&&(i.writable=!0),Object.defineProperty(t,i.key,i)}}return function(e,n,i){return n&&t(e.prototype,n),i&&t(e,i),e}}();t.InteractiveArea=function(){function t(n,i,o,a){e(this,t),this.radius=n*t.DEFAULT_STEP,this.gcontext=i,this.height=a,this.width=o,this.axis_color="#000",this.fill_color="#66608B"}return n(t,null,[{key:"DEFAULT_STEP",get:function(){return 40}}]),t.prototype.setRadius=function(e){this.radius=e*t.DEFAULT_STEP,this.clearArea()},t.prototype.clearArea=function(){this._clearCanvas(),this.drawArea()},t.prototype.drawArea=function(){this._drawRectPart(),this._drawArcPart(),this._drawTrianglePart(),this.drawAxis(this.gcontext,this.width,this.height)},t.prototype.drawPoint=function(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"#000";this.gcontext.fillStyle=n,this.gcontext.beginPath(),this.gcontext.arc(t,e,3,0,2*Math.PI),this.gcontext.closePath(),this.gcontext.stroke(),this.gcontext.fill()},t.prototype._clearCanvas=function(){this.gcontext.clearRect(0,0,this.width,this.height)},t.prototype._drawRectPart=function(){this.gcontext.strokeStyle=this.axis_color,this.gcontext.fillStyle=this.fill_color,this.gcontext.fillRect(this.height/2,this.width/2,this.radius,this.radius),this.gcontext.strokeRect(this.height/2,this.width/2,this.radius,this.radius)},t.prototype._drawArcPart=function(){this.gcontext.fillStyle=this.fill_color,this.gcontext.beginPath(),this.gcontext.arc(this.width/2,this.height/2,this.radius/2,1.5*Math.PI,2*Math.PI),this.gcontext.lineTo(this.height/2,this.width/2),this.gcontext.moveTo(this.height/2,this.width/2),this.gcontext.closePath(),this.gcontext.fill(),this.gcontext.stroke()},t.prototype._drawTrianglePart=function(){this.gcontext.fillStyle=this.fill_color,this.gcontext.beginPath(),this.gcontext.moveTo(this.width/2,this.height/2),this.gcontext.lineTo(this.width/2,this.height/2+this.radius),this.gcontext.lineTo(this.width/2-this.radius/2,this.height/2),this.gcontext.fill(),this.gcontext.closePath(),this.gcontext.stroke()},t.prototype.drawAxis=function(t,e,n){var i=arguments.length>3&&void 0!==arguments[3]?arguments[3]:"#000";t.strokeStyle=i,t.beginPath(),t.moveTo(e/2,n),t.lineTo(e/2,0),t.moveTo(0,n/2),t.lineTo(e,n/2),t.stroke(),t.closePath()},t}()}),define("login",["exports","aurelia-plugins-cookies","aurelia-fetch-client","aurelia-router"],function(t,e,n,i){"use strict";function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0}),t.LoginApplication=void 0;new n.HttpClient,t.LoginApplication=function(){function t(n){o(this,t),null!=e.Cookies.get("login")&&(document.location.href="/#/mainApplication",location.reload()),this.bio="Sashcha",this.group="3202",this.var="102",this.name="",this.pass="",this.API="http://localhost:9000",this.router=n}return t.prototype.determineActivationStrategy=function(){return i.activationStrategy.replace},t.prototype.process=function(){},t.prototype.signin=function(){var t=this.getName(),n=this.getPass();null!=t&&null!=n&&$.ajax({url:"/login",type:"GET",data:{username:t,password:n},success:function(t){var n=JSON.parse(t.status);2==n?document.getElementById("wrong").innerHTML="Incorrect Login":3==n?document.getElementById("wrong").innerHTML="Incorrect Password":(e.Cookies.put("login",t.login),window.location.replace("#/mainApplication"),window.location.reload(!1))}})},t.prototype.getName=function(){return 0==this.name.length?(document.getElementById("wrong").innerHTML="Please enter your login and password",document.getElementById("wrong").style.color="red",null):(document.getElementById("wrong").innerHTML="",this.name)},t.prototype.getPass=function(){return 0==this.pass.length?(document.getElementById("wrong").innerHTML="Please enter your login and password",document.getElementById("wrong").style.color="red",null):(document.getElementById("wrong").innerHTML="",this.pass)},t}()}),define("logout",["exports","aurelia-plugins-cookies"],function(t,e){"use strict";function n(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0}),t.Logout=void 0;t.Logout=function(){function t(){n(this,t)}return t.prototype.attached=function(){e.Cookies.removeAll(),document.location.href="/"},t}()}),define("main",["exports","./environment","aurelia-fetch-client"],function(t,e,n){"use strict";function i(t){t.use.standardConfiguration().plugin("aurelia-bootstrap").feature("resources"),o.default.debug&&t.use.developmentLogging(),o.default.testing&&t.use.plugin("aurelia-testing"),t.start().then(function(){return t.setRoot()})}Object.defineProperty(t,"__esModule",{value:!0}),t.configure=i;var o=function(t){return t&&t.__esModule?t:{default:t}}(e)}),define("mainApplication",["exports","aurelia-fetch-client","./interactiveArea","./mainHandlers","./supportingHandlers","aurelia-plugins-cookies"],function(t,e,n,i,o,a){"use strict";function r(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0}),t.mainApplication=void 0;t.mainApplication=function(){function t(){r(this,t),this.userId=a.Cookies.get("login"),null==this.userId&&(document.location.href="/"),this.todoDescription="",this.rvalues=[],this.clickOnCanvas=function(t){window.mainHadlers=new i.MainHandlers,mainHadlers.click(t)},this.radiusChange=function(t){window.mainHadlers=new i.MainHandlers,mainHadlers.radiusChangedHandler(t)}}return t.prototype.attached=function(){var t=document.getElementById("interactive-area");window.interactiveArea=new n.InteractiveArea(0,t.getContext("2d"),400,400),interactiveArea.drawArea(),document.getElementById("interactive-area").addEventListener("click",this.clickOnCanvas),document.getElementById("hidden_r").addEventListener("change",this.radiusChange)},t.prototype.pressedR=function(t){window.supportingHadlers=new o.SupportingHandlers,window.mainHadlers=new i.MainHandlers,supportingHadlers.setR(t),supportingHadlers.checkButtonsR(),mainHadlers.radiusChangedHandler()},t.prototype.pressedX=function(t){window.supportingHadlers=new o.SupportingHandlers,window.mainHadlers=new i.MainHandlers,supportingHadlers.setX(t),supportingHadlers.checkButtonsX()},t.prototype.checkText=function(){window.supportingHadlers=new o.SupportingHandlers,window.supportingHadlers.checkTextY()},t.prototype.submitClick=function(){window.mainHadlers=new i.MainHandlers,window.mainHadlers.submitClick()},t.prototype.deletePoints=function(){window.mainHadlers=new i.MainHandlers,window.mainHadlers.deletePoints()},t.prototype.logout=function(){location.href="#/logout"},t}()}),define("mainHandlers",["exports","./supportingHandlers","./table","aurelia-plugins-cookies","jquery","jquery-ui-dist"],function(t,e,n,i,o){"use strict";function a(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0}),t.MainHandlers=void 0;var r=function(t){if(t&&t.__esModule)return t;var e={};if(null!=t)for(var n in t)Object.prototype.hasOwnProperty.call(t,n)&&(e[n]=t[n]);return e.default=t,e}(o);t.MainHandlers=function(){function t(){a(this,t)}return t.prototype.submitClick=function(){window.supportingHadlers=new e.SupportingHandlers;var t=window.supportingHadlers.checkButtonsX(),n=window.supportingHadlers.checkButtonsR(),i=window.supportingHadlers.checkTextY();if(t&&i&&n){var o=Number(document.getElementById("hidden_r").value),a=Number(document.getElementById("hidden_x").value),r=window.supportingHadlers.findCoordinate(Number(document.getElementById("y").value));r=window.supportingHadlers.makeOriginalCoordinates(r),this.sendSinglePoint(a,r,o)}},t.prototype.click=function(t){if(window.supportingHadlers=new e.SupportingHandlers,0!=supportingHadlers.checkButtonsR()){var n=document.getElementById("interactive-area").getBoundingClientRect().left,i=document.getElementById("interactive-area").getBoundingClientRect().top,o=Number(document.getElementById("hidden_r").value),a=supportingHadlers.makeOriginalCoordinates(t.clientX-n),r=-supportingHadlers.makeOriginalCoordinates(t.clientY-i);this.sendSinglePoint(a,r,o)}},t.prototype.radiusChangedHandler=function(){if(window.supportingHadlers=new e.SupportingHandlers,window.table=new n.Table,window.supportingHadlers.checkButtonsR()){var t=Number(document.getElementById("hidden_r").value);window.interactiveArea.setRadius(t),r.ajax({url:"/change_r",type:"GET",data:{r:t},success:function(t){var e,n=JSON.parse(t.X),i=JSON.parse(t.Y),o=JSON.parse(t.R),a=JSON.parse(t.isInside),r=JSON.parse(t.color);document.getElementById("answer").innerHTML=window.table.tablePrintHeader();for(var s=0;s<n.length;s++)e=r[s]?"green":"red",window.interactiveArea.drawPoint(window.supportingHadlers.findCoordinate(n[s]),400-window.supportingHadlers.findCoordinate(i[s]),e),document.getElementById("answer").innerHTML+=window.table.tableAddRow(n[s],i[s],o[s],a[s],e)}})}},t.prototype.sendSinglePoint=function(t,i,o){r.ajax({url:"/add_point",type:"GET",data:{x:t,y:i,r:o},success:function(t){window.table=new n.Table,window.supportingHadlers=new e.SupportingHandlers;var i;i=t.color?"green":"red",document.getElementById("answer").innerHTML+=window.table.tableAddRow(t.X,t.Y,t.R,t.color,i),window.interactiveArea.drawPoint(window.supportingHadlers.findCoordinate(t.X),400-window.supportingHadlers.findCoordinate(t.Y),i)}})},t.prototype.deletePoints=function(){window.table=new n.Table,document.getElementById("answer").innerHTML=window.table.tablePrintHeader(),r.ajax({url:"/remove_points",type:"GET",data:{},success:function(e){window.mainHadlers=new t,window.mainHadlers.radiusChangedHandler()}})},t}()}),define("register",["exports","aurelia-plugins-cookies","aurelia-fetch-client","aurelia-router"],function(t,e,n,i){"use strict";function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0}),t.LoginApplication=void 0;new n.HttpClient,t.LoginApplication=function(){function t(n){o(this,t),null!=e.Cookies.get("login")&&(document.location.href="/#/mainApplication",location.reload()),this.bio="Sashcha",this.group="3202",this.var="102",this.name="",this.pass="",this.API="http://localhost:9000",this.router=n}return t.prototype.determineActivationStrategy=function(){return i.activationStrategy.replace},t.prototype.process=function(){},t.prototype.signup=function(){var t=this.getName(),n=this.getPass();null!=t&&null!=n&&$.ajax({url:"/register",type:"GET",data:{username:t,password:n},success:function(t){var n=JSON.parse(t.status);1==n?document.getElementById("wrong").innerHTML="User already exist":2==n?document.getElementById("wrong").innerHTML="Incorrect Login":3==n?document.getElementById("wrong").innerHTML="Incorrect Password, 4 symbols at least":(e.Cookies.put("login",t.login),window.location.replace("#/mainApplication"),window.location.reload(!1))}})},t.prototype.getName=function(){return 0==this.name.length?(document.getElementById("wrong").innerHTML="Please enter your login and password",document.getElementById("wrong").style.color="red",null):(document.getElementById("wrong").innerHTML="",this.name)},t.prototype.getPass=function(){return 0==this.pass.length?(document.getElementById("wrong").innerHTML="Please enter your login and password",document.getElementById("wrong").style.color="red",null):(document.getElementById("wrong").innerHTML="",this.pass)},t}()}),define("supportingHandlers",["exports"],function(t){"use strict";function e(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});t.SupportingHandlers=function(){function t(){e(this,t)}return t.prototype.findCoordinate=function(t){Number(document.getElementById("hidden_r").value);return t<0?200-40*Math.abs(t):t>0?200+40*t:0==t?200:void 0},t.prototype.makeOriginalCoordinates=function(t){var e=Number(document.getElementById("hidden_r").value);return 0<=t&&t<200?(200-t)/(40*e)*-e:200<t&&t<=400?e*((t-200)/(40*e)):200==t?0:void 0},t.prototype.isNumeric=function(t){return/^[-+]?[0-9]+(\.[0-9]+)?$/.test(t)},t.prototype.checkTextY=function(){var t=document.getElementById("y").value,e="";return 0==t.length?e="Заполните это поле!":(!this.isNumeric(t)||t<=-5||t>=3)&&(e="Введено некорректное значение у!"),document.getElementById("text_fail_y").innerHTML=e,0==e.length},t.prototype.setX=function(t){var e=document.getElementsByClassName("button_pressed_x");document.getElementById("hidden_x").value=t;for(var n=0;n<e.length;++n)e[n].className="button_x";document.getElementById("x="+t).className="button_pressed_x"},t.prototype.checkButtonsX=function(){var t="";return""==document.getElementById("hidden_x").value&&(t="Значение X не выбрано!"),document.getElementById("x_fail").innerHTML=t,0==t.length},t.prototype.setR=function(t){var e=document.getElementsByClassName("button_pressed_r");document.getElementById("hidden_r").value=t;for(var n=0;n<e.length;++n)e[n].className="button_r";document.getElementById("r="+t).className="button_pressed_r"},t.prototype.checkButtonsR=function(){var t="";return""==document.getElementById("hidden_r").value&&(t="Значение R не выбрано!"),document.getElementById("r_fail").innerHTML=t,0==t.length},t}()}),define("table",["exports"],function(t){"use strict";function e(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});t.Table=function(){function t(){e(this,t)}return t.prototype.tablePrintHeader=function(){var t="";return t+='<table id="results-table"><tr>',t+="<td> X </td><td> Y </td><td> R </td><td> isInside </td><td> color </td>",t+="</tr>"},t.prototype.tableAddRow=function(t,e,n,i,o){var a="";return a+="<tr>",a+="<td>"+t+"</td><td>"+e+"</td><td>"+n+"</td><td>"+i+"</td><td>"+o+"</td>",a+="</tr>"},t.prototype.closeTable=function(){return"</table>"},t}()}),define("resources/index",["exports"],function(t){"use strict";function e(t){}Object.defineProperty(t,"__esModule",{value:!0}),t.configure=e}),define("text!app.html",["module"],function(t){t.exports="<template><router-view></router-view></template>"}),define("text!login.html",["module"],function(t){t.exports='<template style="overflow:hidden"><link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"><script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"><\/script><div class="navbar navbar-inverse navbar-fixed-top" role="navigation"><div class="container"><div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button> <a class="navbar-brand">Play & Aurelia ^_^</a></div><div class="collapse navbar-collapse"><ul class="nav navbar-nav navbar-left"></ul><ul class="nav navbar-nav navbar-right"><li><a href="#/register">Register</a></li></ul></div></div></div><div class="row" style="margin-top:200px"><div class="col-sm-4 col-sm-offset-4"><div class="panel panel-default"><div class="panel-heading" style="white-space:nowrap"><h3 id="wrong" class="panel-title" style="display:inline-block">Log in here!</h3></div><div class="panel-body"><fieldset><div class="form-group"><input id="name" value.bind="name" class="form-control" placeholder="Name" type="text"><br></div><div class="form-group"><input id="pass" value.bind="pass" class="form-control" placeholder="Password" type="password"><br></div><input class="btn btn-lg btn-success btn-block" type="submit" value="Login" click.trigger="signin()"></fieldset></div></div></div></div><div id="header" style="margin-top:255px;text-align:center"> ${bio}, Группа ${group} <br>Вариант ${var} <br></div></template>'}),define("text!logout.html",["module"],function(t){t.exports="<template></template>"}),define("text!mainApplication.html",["module"],function(t){t.exports='<template><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"><\/script><script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7 /js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"><\/script><div class="navbar navbar-default navbar-fixed-top" role="navigation"><div class="container"><div class="navbar-header"><button type="button" click.trigger="logout()" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">Out</button> <button type="button" id="clear" click.trigger="deletePoints()" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">Clean</button> <a class="navbar-brand">^_^</a></div><div class="collapse navbar-collapse"><ul class="nav navbar-nav navbar-left"></ul><ul class="nav navbar-nav navbar-right"><li><a click.trigger="deletePoints()">Clean</a></li><li><a href="#/logout">Logout</a></li></ul></div></div></div><div class="container"><div class="box"><canvas id="interactive-area" width="400px" height="400px"></canvas><div id="scrollable_tarble"><table id="answer" style="border-spacing:50px 0;overflow:scroll"></table></div></div></div><div class="container"><p align="center"><b>R=</b> <input id="r=1" class="button_r" type="button" name="r" value="1" click.trigger="pressedR(1)"> <input id="r=2" class="button_r" type="button" name="r" value="2" click.trigger="pressedR(2)"> <input id="r=3" class="button_r" type="button" name="r" value="3" click.trigger="pressedR(3)"> <input type="hidden" id="hidden_r" name="r" value=""></p><p id="r_fail"></p><p align="center"><b>X=</b> <input id="x=-5" class="button_x" type="button" name="x" value="-5" click.trigger="pressedX(-5)"> <input id="x=-4" class="button_x" type="button" name="x" value="-4" click.trigger="pressedX(-4)"> <input id="x=-3" class="button_x" type="button" name="x" value="-3" click.trigger="pressedX(-3)"> <input id="x=-2" class="button_x" type="button" name="x" value="-2" click.trigger="pressedX(-2)"> <input id="x=-1" class="button_x" type="button" name="x" value="-1" click.trigger="pressedX(-1)"> <input id="x=0" class="button_x" type="button" name="x" value="0" click.trigger="pressedX(0)"> <input id="x=1" class="button_x" type="button" name="x" value="1" click.trigger="pressedX(1)"> <input id="x=2" class="button_x" type="button" name="x" value="2" click.trigger="pressedX(2)"> <input id="x=3" class="button_x" type="button" name="x" value="3" click.trigger="pressedX(3)"> <input type="hidden" id="hidden_x" name="x" value=""></p><p id="x_fail"><input id="y" name="y" placeholder="-5<y<3" style="margin-left:48%" type="text" input.trigger="checkText()"></p><p id="text_fail_y"></p><p><input type="button" id="submit" value="Отправить" click.trigger="submitClick()"></p></div></template>'}),define("text!register.html",["module"],function(t){t.exports='<template><link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"><div class="navbar navbar-inverse navbar-fixed-top" role="navigation"><div class="container"><div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button> <a class="navbar-brand">Play & Aurelia ^_^</a></div><div class="collapse navbar-collapse"><ul class="nav navbar-nav navbar-left"></ul><ul class="nav navbar-nav navbar-right"><li><a href="#/login">Login</a></li></ul></div></div></div><div class="row" style="margin-top:200px"><div class="col-sm-4 col-sm-offset-4"><div class="panel panel-default"><div class="panel-heading" style="white-space:nowrap"><h3 id="wrong" class="panel-title" style="display:inline-block">Register here!</h3></div><div class="panel-body"><fieldset><div class="form-group"><input id="name" value.bind="name" class="form-control" placeholder="Name" type="text"><br></div><div class="form-group"><input id="pass" value.bind="pass" class="form-control" placeholder="Password" type="password"><br></div><input class="btn btn-lg btn-success btn-block" type="submit" value="Register" click.trigger="signup()"></fieldset></div></div></div></div><div id="header" style="margin-top:255px;text-align:center"> ${bio}, Группа ${group} <br>Вариант ${var} <br></div></template>'});