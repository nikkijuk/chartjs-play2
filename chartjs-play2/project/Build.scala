import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "highcharts-play2"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
      
    // webjars for play
    "org.webjars" %% "webjars-play" % "2.1.0-3",

	// webjar components
	"org.webjars" % "jquery" % "2.0.3",
	"org.webjars" % "bootstrap" % "3.0.0",
	"org.webjars" % "chartjs" % "26962ce",
  
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
