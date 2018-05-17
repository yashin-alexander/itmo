
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Mon Aug 28 14:22:16 MSK 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers {

  // @LINE:17
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def at(file:String): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
    }
  
  }

  // @LINE:7
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def ChangeRadius(r:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "change_r" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("r", r)))))
    }
  
    // @LINE:11
    def login(username:String, password:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "login" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("username", username)), Some(implicitly[QueryStringBindable[String]].unbind("password", password)))))
    }
  
    // @LINE:9
    def RemovePoints(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "remove_points")
    }
  
    // @LINE:12
    def register(username:String, password:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "register" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("username", username)), Some(implicitly[QueryStringBindable[String]].unbind("password", password)))))
    }
  
    // @LINE:7
    def AddPoint(x:Double, y:Double, r:Double): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "add_point" + queryString(List(Some(implicitly[QueryStringBindable[Double]].unbind("x", x)), Some(implicitly[QueryStringBindable[Double]].unbind("y", y)), Some(implicitly[QueryStringBindable[Double]].unbind("r", r)))))
    }
  
    // @LINE:14
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
  }


}
