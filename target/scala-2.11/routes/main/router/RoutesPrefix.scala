
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/alexander/Documents/pip/9lab/conf/routes
// @DATE:Mon Aug 28 14:22:16 MSK 2017


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
