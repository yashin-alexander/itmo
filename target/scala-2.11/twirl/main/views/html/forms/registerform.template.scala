
package views.html.forms

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object registerform_Scope0 {
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

class registerform extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[play.api.mvc.Call,Field,Field,play.twirl.api.HtmlFormat.Appendable] {

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
                <h3 class="panel-title">Create new user here!</h3>
            </div>
            <div class="panel-body">
                """),_display_(/*15.18*/helper/*15.24*/.form(reverseRoute, 'role -> "form")/*15.60*/ {_display_(Seq[Any](format.raw/*15.62*/("""
                    """),format.raw/*16.21*/("""<fieldset>
                        <div class="form-group """),_display_(/*17.49*/if(nameField.hasErrors)/*17.72*/ {_display_(Seq[Any](format.raw/*17.74*/("""has-error""")))}),format.raw/*17.84*/("""" >
                            <input id=""""),_display_(/*18.41*/nameField/*18.50*/.id),format.raw/*18.53*/("""" name=""""),_display_(/*18.62*/nameField/*18.71*/.name),format.raw/*18.76*/("""" value=""""),_display_(/*18.86*/nameField/*18.95*/.value.getOrElse("")),format.raw/*18.115*/("""" class="form-control" placeholder="Name in e-mail format" type="text">
                        </div>
                        <div class="form-group """),_display_(/*20.49*/if(passwordField.hasErrors)/*20.76*/ {_display_(Seq[Any](format.raw/*20.78*/("""has-error""")))}),format.raw/*20.88*/("""" >
                            <input id=""""),_display_(/*21.41*/passwordField/*21.54*/.id),format.raw/*21.57*/("""" name=""""),_display_(/*21.66*/passwordField/*21.79*/.name),format.raw/*21.84*/("""" value=""""),_display_(/*21.94*/passwordField/*21.107*/.value.getOrElse("")),format.raw/*21.127*/("""" class="form-control" placeholder="Password 5 characters at least" type="password">
                        </div>
                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Register & Enter main page">
                    </fieldset>
                """)))}),format.raw/*25.18*/("""
                """),_display_(/*26.18*/if(flash.containsKey("error_register"))/*26.57*/ {_display_(Seq[Any](format.raw/*26.59*/("""
                    """),format.raw/*27.21*/("""<div id="error-message" class="text-danger">
                        """),_display_(/*28.26*/flash/*28.31*/.get("error_register")),format.raw/*28.53*/("""
                    """),format.raw/*29.21*/("""</div>
                """)))}),format.raw/*30.18*/("""
            """),format.raw/*31.13*/("""</div>
        </div>
    </div>
</div>
"""))
      }
    }
  }

  def render(reverseRoute:play.api.mvc.Call,nameField:Field,passwordField:Field): play.twirl.api.HtmlFormat.Appendable = apply(reverseRoute,nameField,passwordField)

  def f:((play.api.mvc.Call,Field,Field) => play.twirl.api.HtmlFormat.Appendable) = (reverseRoute,nameField,passwordField) => apply(reverseRoute,nameField,passwordField)

  def ref: this.type = this

}


}

/**/
object registerform extends registerform_Scope0.registerform
              /*
                  -- GENERATED --
                  DATE: Thu Jul 13 13:48:07 MSK 2017
                  SOURCE: /home/alexander/Documents/pip/9lab/app/views/forms/registerform.scala.html
                  HASH: 8c7333d8cde5e90ed8689d87b6ad5f30e8425fad
                  MATRIX: 788->1|956->74|984->76|1079->145|1093->151|1159->196|1582->592|1597->598|1642->634|1682->636|1731->657|1817->716|1849->739|1889->741|1930->751|2001->795|2019->804|2043->807|2079->816|2097->825|2123->830|2160->840|2178->849|2220->869|2398->1020|2434->1047|2474->1049|2515->1059|2586->1103|2608->1116|2632->1119|2668->1128|2690->1141|2716->1146|2753->1156|2776->1169|2818->1189|3136->1476|3181->1494|3229->1533|3269->1535|3318->1556|3415->1626|3429->1631|3472->1653|3521->1674|3576->1698|3617->1711
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|46->15|46->15|46->15|46->15|47->16|48->17|48->17|48->17|48->17|49->18|49->18|49->18|49->18|49->18|49->18|49->18|49->18|49->18|51->20|51->20|51->20|51->20|52->21|52->21|52->21|52->21|52->21|52->21|52->21|52->21|52->21|56->25|57->26|57->26|57->26|58->27|59->28|59->28|59->28|60->29|61->30|62->31
                  -- GENERATED --
              */
          