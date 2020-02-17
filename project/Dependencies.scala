import sbt._

object Dependencies {

  //  VERSIONS  //

  lazy val CatsScalaTestVersion = "3.0.4"
  lazy val CatsVersion          = "2.1.0"
  lazy val Log4sVersion         = "1.8.2"
  lazy val LogbackVersion       = "1.2.3"
  lazy val PPrintVersion        = "0.5.8"
  lazy val PureconfigVersion    = "0.12.2"
  lazy val ScalatestVersion     = "3.1.0"
  lazy val ShapelessVersion     = "2.3.3"

  //  COMPILER PLUGINS VERSIONS  //

  lazy val BetterMonadicForVersion = "0.3.0"
  lazy val KindProjectorVersion    = "0.10.3"
  lazy val WartRemoverVersion      = "2.4.3"

  //  LIBRARIES  //

  lazy val CatsCoreDep       = "org.typelevel"         %% "cats-core"      % CatsVersion
  lazy val CatsEffectsDep    = "org.typelevel"         %% "cats-effect"    % CatsVersion
  lazy val CatsScalaTestDep  = "com.ironcorelabs"      %% "cats-scalatest" % CatsScalaTestVersion % "test"
  lazy val Log4sDep          = "org.log4s"             %% "log4s"          % Log4sVersion
  lazy val LogbackClassicDep = "ch.qos.logback"        % "logback-classic" % LogbackVersion
  lazy val PPrintDep         = "com.lihaoyi"           %% "pprint"         % PPrintVersion
  lazy val PureconfigDep     = "com.github.pureconfig" %% "pureconfig"     % PureconfigVersion
  lazy val ScalatestDep      = "org.scalatest"         %% "scalatest"      % ScalatestVersion % "test"
  lazy val ShapelessDep      = "com.chuusai"           %% "shapeless"      % ShapelessVersion

  //  COMPILER PLUGIN LIBRARIES  //

  lazy val BetterMonadicForPlugin = compilerPlugin("com.olegpy"    %% "better-monadic-for" % BetterMonadicForVersion)
  lazy val KindProjectorPlugin    = compilerPlugin("org.typelevel" %% "kind-projector"     % KindProjectorVersion)
  lazy val WartremoverPlugin = compilerPlugin(
    "org.wartremover" %% "wartremover" % WartRemoverVersion cross CrossVersion.full,
  )

  //  AGGREGATED  //

  lazy val CatsBundle    = Seq(CatsCoreDep, CatsEffectsDep, CatsScalaTestDep)
  lazy val LoggingBundle = Seq(Log4sDep, LogbackClassicDep)

}
