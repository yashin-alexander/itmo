
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Fri Aug 18 03:31:35 MSK 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:24
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def ChangeRadius: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.ChangeRadius",
      """
        function(r0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "change_r" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("r", r0)])})
        }
      """
    )
  
    // @LINE:8
    def main: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.main",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "main"})
        }
      """
    )
  
    // @LINE:9
    def AddPoint: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.AddPoint",
      """
        function(x0,y1,r2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "add_point" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("x", x0), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("y", y1), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("r", r2)])})
        }
      """
    )
  
    // @LINE:17
    def postRegister: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.postRegister",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
        }
      """
    )
  
    // @LINE:11
    def RemovePoints: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.RemovePoints",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "remove_points"})
        }
      """
    )
  
    // @LINE:19
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:16
    def register: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.register",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
        }
      """
    )
  
    // @LINE:21
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "index"})
        }
      """
    )
  
    // @LINE:14
    def postLogin: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.postLogin",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
    // @LINE:7
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.login",
      """
        function() {
        
          if (true) {
            return _wA({method:"GET", url:"""" + _prefix + """"})
          }
        
        }
      """
    )
  
  }


}
