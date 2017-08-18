
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Fri Aug 18 03:31:35 MSK 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
