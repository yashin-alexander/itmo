define("app",["exports","aurelia-fetch-client","./interactiveArea","./mainHandlers","./supportingHandlers"],function(e,t,n,i,r){"use strict";function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(e,"__esModule",{value:!0}),e.App=void 0;e.App=function(){function e(){o(this,e),this.mainInfo="This page is for authenticated users only. Welcome!",this.todoDescription="",this.rvalues=[]}return e.prototype.attached=function(){var e=document.getElementById("interactive-area");window.interactiveArea=new n.InteractiveArea(0,e.getContext("2d"),e.width,e.height),interactiveArea.drawArea(),interactiveArea.setRadius(2),interactiveArea.drawArea()},e.prototype.pressedR=function(e){window.supportingHadlers=new r.SupportingHandlers,window.mainHadlers=new i.MainHandlers,supportingHadlers.setR(e),supportingHadlers.checkButtonsR(),mainHadlers.radiusChangedHandler()},e}()}),define("environment",["exports"],function(e){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={debug:!1,testing:!1}}),define("interactiveArea",["exports"],function(e){"use strict";function t(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(e,"__esModule",{value:!0});var n=function(){function e(e,t){for(var n=0;n<t.length;n++){var i=t[n];i.enumerable=i.enumerable||!1,i.configurable=!0,"value"in i&&(i.writable=!0),Object.defineProperty(e,i.key,i)}}return function(t,n,i){return n&&e(t.prototype,n),i&&e(t,i),t}}();e.InteractiveArea=function(){function e(n,i,r,o){t(this,e),this.radius=n*e.DEFAULT_STEP,this.gcontext=i,this.height=o,this.width=r,this.axis_color="#000",this.fill_color="#66608B"}return n(e,null,[{key:"DEFAULT_STEP",get:function(){return 40}}]),e.prototype.setRadius=function(t){this.radius=t*e.DEFAULT_STEP,this.clearArea()},e.prototype.clearArea=function(){this._clearCanvas(),this.drawArea()},e.prototype.drawArea=function(){this._drawRectPart(),this._drawArcPart(),this._drawTrianglePart(),this.drawAxis(this.gcontext,this.width,this.height)},e.prototype.drawPoint=function(e,t){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"#000";this.gcontext.fillStyle=n,this.gcontext.beginPath(),this.gcontext.arc(e,t,3,0,2*Math.PI),this.gcontext.closePath(),this.gcontext.stroke(),this.gcontext.fill()},e.prototype._clearCanvas=function(){this.gcontext.clearRect(0,0,this.width,this.height)},e.prototype._drawRectPart=function(){this.gcontext.strokeStyle=this.axis_color,this.gcontext.fillStyle=this.fill_color,this.gcontext.fillRect(this.height/2,this.width/2,this.radius,this.radius),this.gcontext.strokeRect(this.height/2,this.width/2,this.radius,this.radius)},e.prototype._drawArcPart=function(){this.gcontext.fillStyle=this.fill_color,this.gcontext.beginPath(),this.gcontext.arc(this.width/2,this.height/2,this.radius/2,1.5*Math.PI,2*Math.PI),this.gcontext.lineTo(this.height/2,this.width/2),this.gcontext.moveTo(this.height/2,this.width/2),this.gcontext.closePath(),this.gcontext.fill(),this.gcontext.stroke()},e.prototype._drawTrianglePart=function(){this.gcontext.fillStyle=this.fill_color,this.gcontext.beginPath(),this.gcontext.moveTo(this.width/2,this.height/2),this.gcontext.lineTo(this.width/2,this.height/2+this.radius),this.gcontext.lineTo(this.width/2-this.radius/2,this.height/2),this.gcontext.fill(),this.gcontext.closePath(),this.gcontext.stroke()},e.prototype.drawAxis=function(e,t,n){var i=arguments.length>3&&void 0!==arguments[3]?arguments[3]:"#000";e.strokeStyle=i,e.beginPath(),e.moveTo(t/2,n),e.lineTo(t/2,0),e.moveTo(0,n/2),e.lineTo(t,n/2),e.stroke(),e.closePath()},e}()}),define("main",["exports","./environment","aurelia-fetch-client"],function(e,t,n){"use strict";function i(e){e.use.standardConfiguration().feature("resources"),r.default.debug&&e.use.developmentLogging(),r.default.testing&&e.use.plugin("aurelia-testing"),e.start().then(function(){return e.setRoot()})}Object.defineProperty(e,"__esModule",{value:!0}),e.configure=i;var r=function(e){return e&&e.__esModule?e:{default:e}}(t)}),define("mainHandlers",["exports","./supportingHandlers","jquery","jquery-ui-dist"],function(e,t,n){"use strict";function i(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(e,"__esModule",{value:!0}),e.MainHandlers=void 0;var r=function(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n]);return t.default=e,t}(n);e.MainHandlers=function(){function e(){i(this,e)}return e.prototype.submitClick=function(){var e=checkButtonsX(),t=checkButtonsR(),n=checkTextY();if(e&&n&&t){var i=Number(document.getElementById("hidden_r").value),r=Number(document.getElementById("hidden_x").value),o=findCoordinate(Number(document.getElementById("y").value));o=makeOriginalCoordinates(o),sendSinglePoint(r,o,i)}},e.prototype.click=function(e){if(0!=checkButtonsR()){var t=Number(document.getElementById("hidden_r").value),n=makeOriginalCoordinates(e.clientX-258),i=-makeOriginalCoordinates(e.clientY-105);sendSinglePoint(n,i,t)}},e.prototype.radiusChangedHandler=function(){if(window.supportingHadlers=new t.SupportingHandlers,window.supportingHadlers.checkButtonsR()){var e=Number(document.getElementById("hidden_r").value);window.interactiveArea.setRadius(e),r.ajax({url:"/change_r",type:"GET",data:{r:e},success:function(e){alert(e);var t,n=JSON.parse(e.X),i=JSON.parse(e.Y),r=JSON.parse(e.R),o=JSON.parse(e.isInside),a=JSON.parse(e.color);document.getElementById("answer").innerHTML=tablePrintHeader();for(var s=0;s<n.length;s++)t=a[s]?"green":"red",window.interactiveArea.drawPoint(findCoordinate(n[s]),400-findCoordinate(i[s]),t),document.getElementById("answer").innerHTML+=tableAddRow(n[s],i[s],r[s],o[s],t)}})}},e.prototype.sendSinglePoint=function(e,t,n){r.ajax({url:"/add_point",type:"GET",data:{x:e,y:t,r:n},success:function(e){if("access deny"==e.Owner)return alert("access deny"),void document.location.replace("/");var t;t=e.color?"green":"red",document.getElementById("answer").innerHTML+=tableAddRow(e.X,e.Y,e.R,e.color,t),window.interactiveArea.drawPoint(findCoordinate(e.X),400-findCoordinate(e.Y),t)}})},e.prototype.deletePoints=function(){document.getElementById("answer").innerHTML=tablePrintHeader(),r.ajax({url:"/remove_points",type:"GET",data:{},success:function(e){radiusChangedHandler()}})},e}()}),define("supportingHandlers",["exports"],function(e){"use strict";function t(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(e,"__esModule",{value:!0});e.SupportingHandlers=function(){function e(){t(this,e)}return e.prototype.findCoordinate=function(e){Number(document.getElementById("hidden_r").value);return e<0?200-40*Math.abs(e):e>0?200+40*e:0==e?200:void 0},e.prototype.makeOriginalCoordinates=function(e){var t=Number(document.getElementById("hidden_r").value);return 0<=e&&e<200?(200-e)/(40*t)*-t:200<e&&e<=400?t*((e-200)/(40*t)):200==e?0:void 0},e.prototype.addErrorMessage=function(e,t){e.innerHTML=t},e.prototype.isNumeric=function(e){return/^[-+]?[0-9]+(\.[0-9]+)?$/.test(e)},e.prototype.checkTextY=function(){var e=document.getElementById("y").value,t="";return 0==e.length?t="Заполните это поле!":(!isNumeric(e)||e<=-5||e>=3)&&(t="Введено некорректное значение у!"),addErrorMessage(document.getElementById("text_fail_y"),t),0==t.length},e.prototype.setX=function(e){var t=document.getElementsByClassName("button_pressed_x");document.getElementById("hidden_x").value=e.value;for(var n=0;n<t.length;++n)t[n].className="button_x";e.className="button_pressed_x",e.blur()},e.prototype.checkButtonsX=function(){var e="";return""==document.getElementById("hidden_x").value&&(e="Значение X не выбрано!"),addErrorMessage(document.getElementById("x_fail"),e),0==e.length},e.prototype.setR=function(e){var t=document.getElementsByClassName("button_pressed_r");document.getElementById("hidden_r").value=e;for(var n=0;n<t.length;++n)t[n].className="button_r";document.getElementById("r="+e).className="button_pressed_r"},e.prototype.checkButtonsR=function(){var e="";return""==document.getElementById("hidden_r").value&&(e="Значение R не выбрано!"),document.getElementById("r_fail").innerHTML=e,0==e.length},e}()}),define("resources/index",["exports"],function(e){"use strict";function t(e){}Object.defineProperty(e,"__esModule",{value:!0}),e.configure=t}),define("text!app.html",["module"],function(e){e.exports='<template><h2>${mainInfo}</h2><p align="center"><b>R=</b> <input id="r=1" class="button_r" type="button" name="r" value="1" click.trigger="pressedR(1)"> <input id="r=2" class="button_r" type="button" name="r" value="2" click.trigger="pressedR(2)"> <input id="r=3" class="button_r" type="button" name="r" value="3" click.trigger="pressedR(3)"> <input type="hidden" id="hidden_r" name="r" value=""></p><p id="r_fail"></p><div class="container"><div class="box"><canvas id="interactive-area" width="400px" height="400px"></canvas><div id="scrollable_tarble"><table id="answer" style="border-spacing:50px 0;overflow:scroll">h</table></div></div></div></template>'});