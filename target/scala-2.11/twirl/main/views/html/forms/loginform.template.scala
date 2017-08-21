
package views.html.forms

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object loginform_Scope0 {
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

class loginform extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[play.api.mvc.Call,Field,Field,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(reverseRoute: play.api.mvc.Call, nameField: Field, passwordField: Field):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.75*/("""

"""),format.raw/*3.1*/("""<head>
    <script language="JavaScript" type="text/javascript" src="""),_display_(/*4.63*/routes/*4.69*/.Assets.at("javascripts/jquery-1.9.0.min.js")),format.raw/*4.114*/("""></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>

<div class="row">
    <div class="col-sm-4 col-sm-offset-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Log in here!</h3>
            </div>
            <div class="panel-body">
                """),_display_(/*15.18*/helper/*15.24*/.form(reverseRoute, 'role -> "form")/*15.60*/ {_display_(Seq[Any](format.raw/*15.62*/("""
                    """),format.raw/*16.21*/("""<fieldset>
                        <div class="form-group """),_display_(/*17.49*/if(nameField.hasErrors)/*17.72*/ {_display_(Seq[Any](format.raw/*17.74*/("""has-error""")))}),format.raw/*17.84*/("""" >
                            <input id=""""),_display_(/*18.41*/nameField/*18.50*/.id),format.raw/*18.53*/("""" name=""""),_display_(/*18.62*/nameField/*18.71*/.name),format.raw/*18.76*/("""" value=""""),_display_(/*18.86*/nameField/*18.95*/.value.getOrElse("")),format.raw/*18.115*/("""" class="form-control" placeholder="Name" type="text">
                        </div>
                        <div class="form-group """),_display_(/*20.49*/if(passwordField.hasErrors)/*20.76*/ {_display_(Seq[Any](format.raw/*20.78*/("""has-error""")))}),format.raw/*20.88*/("""" >
                            <input id=""""),_display_(/*21.41*/passwordField/*21.54*/.id),format.raw/*21.57*/("""" name=""""),_display_(/*21.66*/passwordField/*21.79*/.name),format.raw/*21.84*/("""" value=""""),_display_(/*21.94*/passwordField/*21.107*/.value.getOrElse("")),format.raw/*21.127*/("""" class="form-control" placeholder="Password" type="password">
                        </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                    </fieldset>
                """)))}),format.raw/*25.18*/("""
                """),_display_(/*26.18*/if(flash.containsKey("error_login"))/*26.54*/ {_display_(Seq[Any](format.raw/*26.56*/("""
                    """),format.raw/*27.21*/("""<div id="error-message" class="text-danger">
                    """),_display_(/*28.22*/flash/*28.27*/.get("error_login")),format.raw/*28.46*/("""
                    """),format.raw/*29.21*/("""</div>
                """)))}),format.raw/*30.18*/("""
            """),format.raw/*31.13*/("""</div>
        </div>
    </div>
</div>"""))
      }
    }
  }

  def render(reverseRoute:play.api.mvc.Call,nameField:Field,passwordField:Field): play.twirl.api.HtmlFormat.Appendable = apply(reverseRoute,nameField,passwordField)

  def f:((play.api.mvc.Call,Field,Field) => play.twirl.api.HtmlFormat.Appendable) = (reverseRoute,nameField,passwordField) => apply(reverseRoute,nameField,passwordField)

  def ref: this.type = this

}


}

/**/
object loginform extends loginform_Scope0.loginform
              /*
                  -- GENERATED --
                  DATE: Thu Jul 13 16:38:45 MSK 2017
                  SOURCE: /home/alexander/Documents/pip/9lab/app/views/forms/loginform.scala.html
                  HASH: e53665781bd574ad00d889977ca6880270db9c59
                  MATRIX: 782->1|950->74|978->76|1073->145|1087->151|1153->196|1567->583|1582->589|1627->625|1667->627|1716->648|1802->707|1834->730|1874->732|1915->742|1986->786|2004->795|2028->798|2064->807|2082->816|2108->821|2145->831|2163->840|2205->860|2366->994|2402->1021|2442->1023|2483->1033|2554->1077|2576->1090|2600->1093|2636->1102|2658->1115|2684->1120|2721->1130|2744->1143|2786->1163|3065->1411|3110->1429|3155->1465|3195->1467|3244->1488|3337->1554|3351->1559|3391->1578|3440->1599|3495->1623|3536->1636
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|46->15|46->15|46->15|46->15|47->16|48->17|48->17|48->17|48->17|49->18|49->18|49->18|49->18|49->18|49->18|49->18|49->18|49->18|51->20|51->20|51->20|51->20|52->21|52->21|52->21|52->21|52->21|52->21|52->21|52->21|52->21|56->25|57->26|57->26|57->26|58->27|59->28|59->28|59->28|60->29|61->30|62->31
                  -- GENERATED --
              */
          