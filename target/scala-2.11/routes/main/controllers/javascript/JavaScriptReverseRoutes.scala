
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Sun Aug 27 01:56:08 MSK 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:17
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
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

  
    // @LINE:11
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.login",
      """
        function(username0,password1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("username", username0), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("password", password1)])})
        }
      """
    )
  
    // @LINE:8
    def ChangeRadius: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.ChangeRadius",
      """
        function(r0,user1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "change_r" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("r", r0), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("user", user1)])})
        }
      """
    )
  
    // @LINE:7
    def AddPoint: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.AddPoint",
      """
        function(x0,y1,r2,username3,password4) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "add_point" + _qS([(""" + implicitly[QueryStringBindable[Double]].javascriptUnbind + """)("x", x0), (""" + implicitly[QueryStringBindable[Double]].javascriptUnbind + """)("y", y1), (""" + implicitly[QueryStringBindable[Double]].javascriptUnbind + """)("r", r2), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("username", username3), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("password", password4)])})
        }
      """
    )
  
    // @LINE:12
    def register: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.register",
      """
        function(username0,password1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("username", username0), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("password", password1)])})
        }
      """
    )
  
    // @LINE:14
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
