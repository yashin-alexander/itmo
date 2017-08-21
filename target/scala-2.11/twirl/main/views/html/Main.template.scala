
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object Main_Scope0 {
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

class Main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,Boolean,User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(page: String, isLoggedIn: Boolean, userInfo: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.53*/("""

"""),_display_(/*3.2*/PageView(page, isLoggedIn, userInfo)/*3.38*/ {_display_(Seq[Any](format.raw/*3.40*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html>


<html>
    <head>
        <title>"lab"</title>
            <link rel="stylesheet" media="screen" href=""""),_display_(/*10.58*/routes/*10.64*/.Assets.at("/stylesheets/main.css")),format.raw/*10.99*/("""">
            <script language="JavaScript" type="text/javascript" src="""),_display_(/*11.71*/routes/*11.77*/.Assets.at("javascripts/jquery-1.9.0.min.js")),format.raw/*11.122*/("""></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

            <script src=""""),_display_(/*14.27*/routes/*14.33*/.Assets.at("javascripts/SupportingHandlers.js")),format.raw/*14.80*/("""" type="text/javascript"></script>
            <script src=""""),_display_(/*15.27*/routes/*15.33*/.Assets.at("/javascripts/MainHandlers.js")),format.raw/*15.75*/("""" type="text/javascript"></script>
            <script src=""""),_display_(/*16.27*/routes/*16.33*/.Assets.at("javascripts/InteractiveArea.js")),format.raw/*16.77*/("""" type="text/javascript"></script>
            <script src=""""),_display_(/*17.27*/routes/*17.33*/.Assets.at("javascripts/Table.js")),format.raw/*17.67*/("""" type="text/javascript"></script>

       </head>
    <body>
        <table id='main' >
            <tr id="header" >
                <td colspan='2'>
                    <div style="font-family: Ubuntu Condensed">
                        <form style="text-align: center; font-family: Ubuntu Condensed">
                           Only authenticated users can visit this page.  
                        </form>
                    </div>
                </td>
            </tr>

            <tr style='height:200px; ' >

                <td>
                    <div id="interactive-block">
                        <canvas id="interactive-area" height="400px" width="400px"></canvas>
                        <script src="InteractiveArea.js"></script>
                        <script src="GeometryUtil.js"></script>
                        <script src="Handlers.js"></script>
                        <script>
                                var canvas = document.getElementById("interactive-area");
                                //replaceOriginOfCoordinatesToCenter( canvas.getContext("2d"), canvas.width, canvas.height );
                                window.interactiveArea = new InteractiveArea(
                                        0,
                                        canvas.getContext("2d"),
                                        canvas.width,
                                        canvas.height
                                );
                                interactiveArea.drawArea();
                                canvas.addEventListener("click", click );
                                document.getElementById("r").addEventListener("change", radiusChangedHandler);
                        </script>
                    </div>
                </td>
                <td  rowspan='3' >
                    <div id="scrollable_table">
                        <table id="answer" style='border-spacing: 50px 0; overflow: scroll;' >

                        </table>
                    </div>
                </td>

            </tr>
            <tr>
                <td>
                    <table id="input">
                        <tr>
                            <p> <b>X=</b>
                                <input id="x=-5" class="button_x" type="button" name="x" value="-5" onclick="setX(this); checkButtonsX()">
                                <input id="x=-4" class="button_x" type="button" name="x" value="-4" onclick="setX(this); checkButtonsX()">
                                <input id="x=-3" class="button_x" type="button" name="x" value="-3" onclick="setX(this); checkButtonsX()">
                                <input id="x=-2" class="button_x" type="button" name="x" value="-2" onclick="setX(this); checkButtonsX()">
                                <input id="x=-1" class="button_x" type="button" name="x" value="-1" onclick="setX(this); checkButtonsX()">
                                <input id="x=0" class="button_x" type="button" name="x" value="0" onclick="setX(this); checkButtonsX()">
                                <input id="x=1" class="button_x" type="button" name="x" value="1" onclick="setX(this); checkButtonsX()">
                                <input id="x=2" class="button_x" type="button" name="x" value="2" onclick="setX(this); checkButtonsX()">
                                <input id="x=3" class="button_x" type="button" name="x" value="3" onclick="setX(this); checkButtonsX()">
                                <input type="hidden" id="hidden_x" name="x" value=""/>
                            </p>
                            <p id="x_fail">
                        </tr>
                        <tr>
                            <p>Введите значение Y:</p>
                            <p><input id="y" name="y" placeholder="-5<y<3" type="text" style='vertical-align:top'  tabindex="10" oninput="checkTextY();"></p>
                            <p id="text_fail_y"><p>
                        </tr>
                        <tr>
                            <p> <b>R=</b>
                                <input id="r=1" class="button_r" type="button" name="r" value="1" onclick="setR(this); checkButtonsR(); radiusChangedHandler()">
                                <input id="r=2" class="button_r" type="button" name="r" value="2" onclick="setR(this); checkButtonsR(); radiusChangedHandler()">
                                <input id="r=3" class="button_r" type="button" name="r" value="3" onclick="setR(this); checkButtonsR(); radiusChangedHandler()">
                                <input type="hidden" id="hidden_r" name="r" value="" />
                            </p>
                        <p id="r_fail">
                        </tr>
                        <tr>
                            <p style='margin-top:20px'>
                                <input type="button" id="submit" value="Отправить" onclick=submitClick()>
                                <input type="button" id="clear" value="Очистить" onclick=deletePoints()>
                            </p>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
""")))}),format.raw/*108.2*/("""
"""))
      }
    }
  }

  def render(page:String,isLoggedIn:Boolean,userInfo:User): play.twirl.api.HtmlFormat.Appendable = apply(page,isLoggedIn,userInfo)

  def f:((String,Boolean,User) => play.twirl.api.HtmlFormat.Appendable) = (page,isLoggedIn,userInfo) => apply(page,isLoggedIn,userInfo)

  def ref: this.type = this

}


}

/**/
object Main extends Main_Scope0.Main
              /*
                  -- GENERATED --
                  DATE: Sat Jul 15 03:32:24 MSK 2017
                  SOURCE: /home/alexander/Documents/pip/9lab/app/views/Main.scala.html
                  HASH: d09ecf9254c336f63dbb5dc331236202cfdd4586
                  MATRIX: 756->1|902->52|930->55|974->91|1013->93|1040->94|1189->216|1204->222|1260->257|1360->330|1375->336|1442->381|1608->520|1623->526|1691->573|1779->634|1794->640|1857->682|1945->743|1960->749|2025->793|2113->854|2128->860|2183->894|7396->6076
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|41->10|41->10|41->10|42->11|42->11|42->11|45->14|45->14|45->14|46->15|46->15|46->15|47->16|47->16|47->16|48->17|48->17|48->17|139->108
                  -- GENERATED --
              */
          