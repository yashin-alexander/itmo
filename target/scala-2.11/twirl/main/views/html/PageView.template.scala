
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object PageView_Scope0 {
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

class PageView extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,Boolean,User,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(page: String, isLoggedIn: Boolean, userInfo: User)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.68*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*7.17*/page),format.raw/*7.21*/(""" """),format.raw/*7.22*/("""(9labwork)</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        
        <!--  Load site-specific customizations after bootstrap. -->
        <link rel="stylesheet" media="screen" href=""""),_display_(/*12.54*/routes/*12.60*/.Assets.at("stylesheets/main.css")),format.raw/*12.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*13.59*/routes/*13.65*/.Assets.at("images/favicon.png")),format.raw/*13.97*/("""">
        
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
          <script src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.2.0/respond.js"></script>
        <![endif]-->
    </head>
    <body>
    
     <!-- Responsive navbar -->
  <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <!--  Display three horizontal lines when navbar collapsed. -->
          <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/main">9 labwork. Play framework! </a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-left">
          """),_display_(/*35.12*/if(isLoggedIn)/*35.26*/ {_display_(Seq[Any](format.raw/*35.28*/("""
            """),format.raw/*36.13*/("""<li class=""""),_display_(/*36.25*/("active".when(page == "Main"))),format.raw/*36.56*/(""""><a href=""""),_display_(/*36.68*/routes/*36.74*/.Application.main()),format.raw/*36.93*/("""">Main</a></li>
          """)))}),format.raw/*37.12*/("""
        """),format.raw/*38.9*/("""</ul>
        <ul class="nav navbar-nav navbar-right">
          """),_display_(/*40.12*/if(isLoggedIn)/*40.26*/ {_display_(Seq[Any](format.raw/*40.28*/("""
            """),format.raw/*41.13*/("""<li><p class="navbar-text">"""),_display_(/*41.41*/userInfo/*41.49*/.getName()),format.raw/*41.59*/("""</p></li>
            <li><a href=""""),_display_(/*42.27*/routes/*42.33*/.Application.logout()),format.raw/*42.54*/("""">Logout</a></li>
          """)))}/*43.13*/else/*43.18*/{_display_(Seq[Any](format.raw/*43.19*/("""
            """),format.raw/*44.13*/("""<li><a href=""""),_display_(/*44.27*/routes/*44.33*/.Application.login()),format.raw/*44.53*/("""">Login</a></li>
            <li><a href=""""),_display_(/*45.27*/routes/*45.33*/.Application.register()),format.raw/*45.56*/("""">Register</a></li>
          """)))}),format.raw/*46.12*/("""
        """),format.raw/*47.9*/("""</ul>
      </div>
    </div>
  </div>
      """),_display_(/*51.8*/content),format.raw/*51.15*/("""
      """),format.raw/*52.7*/("""<!-- Load Bootstrap JavaScript components. HTMLUnit (used in testing) requires JQuery 1.8.3 or below). -->
   </body>
</html>
"""))
      }
    }
  }

  def render(page:String,isLoggedIn:Boolean,userInfo:User,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(page,isLoggedIn,userInfo)(content)

  def f:((String,Boolean,User) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (page,isLoggedIn,userInfo) => (content) => apply(page,isLoggedIn,userInfo)(content)

  def ref: this.type = this

}


}

/**/
object PageView extends PageView_Scope0.PageView
              /*
                  -- GENERATED --
                  DATE: Thu Jul 13 16:59:40 MSK 2017
                  SOURCE: /home/alexander/Documents/pip/9lab/app/views/PageView.scala.html
                  HASH: a854c2adfbd3b9bd0611cc3b5ee495b2a3a3a28f
                  MATRIX: 769->1|930->67|958->69|1035->120|1059->124|1087->125|1451->462|1466->468|1521->502|1609->563|1624->569|1677->601|2746->1643|2769->1657|2809->1659|2850->1672|2889->1684|2941->1715|2980->1727|2995->1733|3035->1752|3093->1779|3129->1788|3222->1854|3245->1868|3285->1870|3326->1883|3381->1911|3398->1919|3429->1929|3492->1965|3507->1971|3549->1992|3597->2022|3610->2027|3649->2028|3690->2041|3731->2055|3746->2061|3787->2081|3857->2124|3872->2130|3916->2153|3978->2184|4014->2193|4086->2239|4114->2246|4148->2253
                  LINES: 27->1|32->1|34->3|38->7|38->7|38->7|43->12|43->12|43->12|44->13|44->13|44->13|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|68->37|69->38|71->40|71->40|71->40|72->41|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|75->44|76->45|76->45|76->45|77->46|78->47|82->51|82->51|83->52
                  -- GENERATED --
              */
          