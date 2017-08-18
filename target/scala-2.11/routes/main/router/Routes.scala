
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Fri Aug 18 03:31:35 MSK 2017

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
  // @LINE:24
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    Application_1: controllers.Application,
    // @LINE:24
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
    ("""GET""", this.prefix, """controllers.Application.login"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """main""", """controllers.Application.main"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """add_point""", """controllers.Application.AddPoint(x:String, y:String, r:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """change_r""", """controllers.Application.ChangeRadius(r:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """remove_points""", """controllers.Application.RemovePoints()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.Application.login()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.Application.postLogin()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.Application.register()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.Application.postRegister()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """logout""", """controllers.Application.logout()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """index""", """controllers.Application.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_Application_login0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_login0_invoker = createInvoker(
    Application_1.login,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "login",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_Application_main1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("main")))
  )
  private[this] lazy val controllers_Application_main1_invoker = createInvoker(
    Application_1.main,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "main",
      Nil,
      "GET",
      """""",
      this.prefix + """main"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Application_AddPoint2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("add_point")))
  )
  private[this] lazy val controllers_Application_AddPoint2_invoker = createInvoker(
    Application_1.AddPoint(fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "AddPoint",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """add_point"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Application_ChangeRadius3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("change_r")))
  )
  private[this] lazy val controllers_Application_ChangeRadius3_invoker = createInvoker(
    Application_1.ChangeRadius(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "ChangeRadius",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """change_r"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Application_RemovePoints4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("remove_points")))
  )
  private[this] lazy val controllers_Application_RemovePoints4_invoker = createInvoker(
    Application_1.RemovePoints(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "RemovePoints",
      Nil,
      "GET",
      """""",
      this.prefix + """remove_points"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Application_login5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_Application_login5_invoker = createInvoker(
    Application_1.login(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "login",
      Nil,
      "GET",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Application_postLogin6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_Application_postLogin6_invoker = createInvoker(
    Application_1.postLogin(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "postLogin",
      Nil,
      "POST",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Application_register7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_Application_register7_invoker = createInvoker(
    Application_1.register(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "register",
      Nil,
      "GET",
      """""",
      this.prefix + """register"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Application_postRegister8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_Application_postRegister8_invoker = createInvoker(
    Application_1.postRegister(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "postRegister",
      Nil,
      "POST",
      """""",
      this.prefix + """register"""
    )
  )

  // @LINE:19
  private[this] lazy val controllers_Application_logout9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("logout")))
  )
  private[this] lazy val controllers_Application_logout9_invoker = createInvoker(
    Application_1.logout(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "logout",
      Nil,
      "GET",
      """""",
      this.prefix + """logout"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_Application_index10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("index")))
  )
  private[this] lazy val controllers_Application_index10_invoker = createInvoker(
    Application_1.index(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """index"""
    )
  )

  // @LINE:24
  private[this] lazy val controllers_Assets_at11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at11_invoker = createInvoker(
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
    case controllers_Application_login0_route(params) =>
      call { 
        controllers_Application_login0_invoker.call(Application_1.login)
      }
  
    // @LINE:8
    case controllers_Application_main1_route(params) =>
      call { 
        controllers_Application_main1_invoker.call(Application_1.main)
      }
  
    // @LINE:9
    case controllers_Application_AddPoint2_route(params) =>
      call(params.fromQuery[String]("x", None), params.fromQuery[String]("y", None), params.fromQuery[String]("r", None)) { (x, y, r) =>
        controllers_Application_AddPoint2_invoker.call(Application_1.AddPoint(x, y, r))
      }
  
    // @LINE:10
    case controllers_Application_ChangeRadius3_route(params) =>
      call(params.fromQuery[String]("r", None)) { (r) =>
        controllers_Application_ChangeRadius3_invoker.call(Application_1.ChangeRadius(r))
      }
  
    // @LINE:11
    case controllers_Application_RemovePoints4_route(params) =>
      call { 
        controllers_Application_RemovePoints4_invoker.call(Application_1.RemovePoints())
      }
  
    // @LINE:13
    case controllers_Application_login5_route(params) =>
      call { 
        controllers_Application_login5_invoker.call(Application_1.login())
      }
  
    // @LINE:14
    case controllers_Application_postLogin6_route(params) =>
      call { 
        controllers_Application_postLogin6_invoker.call(Application_1.postLogin())
      }
  
    // @LINE:16
    case controllers_Application_register7_route(params) =>
      call { 
        controllers_Application_register7_invoker.call(Application_1.register())
      }
  
    // @LINE:17
    case controllers_Application_postRegister8_route(params) =>
      call { 
        controllers_Application_postRegister8_invoker.call(Application_1.postRegister())
      }
  
    // @LINE:19
    case controllers_Application_logout9_route(params) =>
      call { 
        controllers_Application_logout9_invoker.call(Application_1.logout())
      }
  
    // @LINE:21
    case controllers_Application_index10_route(params) =>
      call { 
        controllers_Application_index10_invoker.call(Application_1.index())
      }
  
    // @LINE:24
    case controllers_Assets_at11_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at11_invoker.call(Assets_0.at(path, file))
      }
  }
}
