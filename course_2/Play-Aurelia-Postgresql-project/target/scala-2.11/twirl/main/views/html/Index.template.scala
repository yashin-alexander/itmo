
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object Index_Scope0 {
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

class Index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Main Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href=""""),_display_(/*7.51*/routes/*7.57*/.Assets.at("stylesheets/aurelia.css")),format.raw/*7.94*/("""">
  </head>

  <body aurelia-app="main">
    <script src=""""),_display_(/*11.19*/routes/*11.25*/.Assets.at("javascripts/scripts/vendor-bundle.js")),format.raw/*11.75*/("""" data-main="aurelia-bootstrapper"></script>
  </body>
</html>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object Index extends Index_Scope0.Index
              /*
                  -- GENERATED --
                  DATE: Wed Sep 06 16:49:27 MSK 2017
                  SOURCE: /home/alexander/Documents/pip/9lab/app/views/Index.scala.html
                  HASH: 7c60aa78b62bf0b8b69f8bc01aa7fcdf2b290d5d
                  MATRIX: 827->0|1064->211|1078->217|1135->254|1222->314|1237->320|1308->370
                  LINES: 32->1|38->7|38->7|38->7|42->11|42->11|42->11
                  -- GENERATED --
              */
          