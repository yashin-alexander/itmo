
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object Login_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class Login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,Boolean,User,Form[views.formdata.LoginFormData],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(page: String, isLoggedIn: Boolean, userInfo: User, loginForm: Form[views.formdata.LoginFormData]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import forms._

Seq[Any](format.raw/*1.100*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/PageView(page, isLoggedIn, userInfo)/*5.38*/ {_display_(Seq[Any](format.raw/*5.40*/("""
    """),format.raw/*6.5*/("""<div>
        """),_display_(/*7.10*/loginform(routes.Application.postLogin(), loginForm("name"), loginForm("password"))),format.raw/*7.93*/("""
    """),format.raw/*8.5*/("""</div>
    <div class="info" style="font-family: Ubuntu Condensed; font-size: 15px ; margin-top: 35%;text-align: center">
        Yashin Alexander, gr.P3202 var.102
    </div>
""")))}),format.raw/*12.2*/("""
"""))
      }
    }
  }

  def render(page:String,isLoggedIn:Boolean,userInfo:User,loginForm:Form[views.formdata.LoginFormData]): play.twirl.api.HtmlFormat.Appendable = apply(page,isLoggedIn,userInfo,loginForm)

  def f:((String,Boolean,User,Form[views.formdata.LoginFormData]) => play.twirl.api.HtmlFormat.Appendable) = (page,isLoggedIn,userInfo,loginForm) => apply(page,isLoggedIn,userInfo,loginForm)

  def ref: this.type = this

}


}

/**/
object Login extends Login_Scope0.Login
              /*
                  -- GENERATED --
                  DATE: Thu Jul 13 17:05:28 MSK 2017
                  SOURCE: /home/alexander/Documents/pip/9lab/app/views/Login.scala.html
                  HASH: d8d17efbb77cce6df20eaaeacfe40d9b1f91c35e
                  MATRIX: 793->1|1001->99|1029->117|1056->119|1100->155|1139->157|1170->162|1211->177|1314->260|1345->265|1552->442
                  LINES: 27->1|32->1|34->4|35->5|35->5|35->5|36->6|37->7|37->7|38->8|42->12
                  -- GENERATED --
              */
          