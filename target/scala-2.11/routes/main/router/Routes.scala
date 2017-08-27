
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Sun Aug 27 01:56:08 MSK 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  Application_1: controllers.Application,
  // @LINE:17
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    Application_1: controllers.Application,
    // @LINE:17
    Assets_0: controllers.Assets
  ) = this(errorHandler, Application_1, Assets_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """add_point""", """controllers.Application.AddPoint(x:Double, y:Double, r:Double, username:String, password:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """change_r""", """controllers.Application.ChangeRadius(r:String, user:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.Application.login(username:String, password:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.Application.register(username:String, password:String)"""),
    ("""GET""", this.prefix, """controllers.Application.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_Application_AddPoint0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("add_point")))
  )
  private[this] lazy val controllers_Application_AddPoint0_invoker = createInvoker(
    Application_1.AddPoint(fakeValue[Double], fakeValue[Double], fakeValue[Double], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "AddPoint",
      Seq(classOf[Double], classOf[Double], classOf[Double], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """add_point"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_Application_ChangeRadius1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("change_r")))
  )
  private[this] lazy val controllers_Application_ChangeRadius1_invoker = createInvoker(
    Application_1.ChangeRadius(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "ChangeRadius",
      Seq(classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """change_r"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Application_login2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_Application_login2_invoker = createInvoker(
    Application_1.login(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "login",
      Seq(classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Application_register3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_Application_register3_invoker = createInvoker(
    Application_1.register(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "register",
      Seq(classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """register"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Application_index4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index4_invoker = createInvoker(
    Application_1.index(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_at5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at5_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_Application_AddPoint0_route(params) =>
      call(params.fromQuery[Double]("x", None), params.fromQuery[Double]("y", None), params.fromQuery[Double]("r", None), params.fromQuery[String]("username", None), params.fromQuery[String]("password", None)) { (x, y, r, username, password) =>
        controllers_Application_AddPoint0_invoker.call(Application_1.AddPoint(x, y, r, username, password))
      }
  
    // @LINE:8
    case controllers_Application_ChangeRadius1_route(params) =>
      call(params.fromQuery[String]("r", None), params.fromQuery[String]("user", None)) { (r, user) =>
        controllers_Application_ChangeRadius1_invoker.call(Application_1.ChangeRadius(r, user))
      }
  
    // @LINE:11
    case controllers_Application_login2_route(params) =>
      call(params.fromQuery[String]("username", None), params.fromQuery[String]("password", None)) { (username, password) =>
        controllers_Application_login2_invoker.call(Application_1.login(username, password))
      }
  
    // @LINE:12
    case controllers_Application_register3_route(params) =>
      call(params.fromQuery[String]("username", None), params.fromQuery[String]("password", None)) { (username, password) =>
        controllers_Application_register3_invoker.call(Application_1.register(username, password))
      }
  
    // @LINE:14
    case controllers_Application_index4_route(params) =>
      call { 
        controllers_Application_index4_invoker.call(Application_1.index())
      }
  
    // @LINE:17
    case controllers_Assets_at5_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at5_invoker.call(Assets_0.at(path, file))
      }
  }
}
