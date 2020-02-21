import Dependencies._
import AllProjectsKeys.autoImport._

lazy val ScalaLangVersion = "2.13.0"

// General
ThisBuild / organization := "com.colofabrix.scala.sample"
ThisBuild / scalaVersion := ScalaLangVersion

// Compiler options
ThisBuild / scalacOptions ++= Compiler.TpolecatOptions ++ Seq("-P:splain:all")
ThisBuild / developers := List(
  Developer("ColOfAbRiX", "Fabrizio Colonna", "@ColOfAbRiX", url("http://github.com/ColOfAbRiX")),
)

// GIT version information
ThisBuild / dynverVTagPrefix := false

// Wartremover
ThisBuild / wartremoverExcluded ++= (baseDirectory.value * "**" / "src" / "test").get
ThisBuild / wartremoverErrors ++= Warts.allBut(
  Wart.Any,
  Wart.Nothing,
  Wart.Overloading,
  Wart.ToString,
)

// Scalafmt
ThisBuild / scalafmtOnCompile := true

// Global dependencies and compiler plugins
ThisBuild / libraryDependencies ++= Seq(
  BetterMonadicForPlugin,
  KindProjectorPlugin,
  PPrintDep,
  SlainPlugin,
  WartremoverPlugin,
) ++ Seq(
).flatten

//  PROJECTS  //

// Root project
lazy val rootProject: Project = project
  .in(file("."))
  .settings(
    name := "sample",
    description := "Sample Project",
  )
  .aggregate(
    sampleBasic,
    sampleIoBasic,
    sampleApp,
    sampleWeb,
  )

// Utils project
lazy val sampleUtils = project
  .in(file("module-utils"))
  .settings(
    name := "utils",
    description := "Global Utilities",
    libraryDependencies ++= Seq(
      CatsCoreDep,
      CatsScalaTestDep,
      FS2CoreDep,
      ScalatestDep,
    ),
  )

// Sample basic application
lazy val sampleBasic = project
  .in(file("module-basic"))
  .dependsOn(
  )
  .settings(
    name := "basic",
    description := "Basic application",
    libraryDependencies ++= Seq(
    ).flatten ++ Seq(
    ),
  )

// Sample basic application with IO
lazy val sampleIoBasic = project
  .in(file("module-io-basic"))
  .dependsOn(
  )
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "io-basic",
    description := "Basic application with IO",
    buildInfoPackage := projectPackage.value,
    buildInfoKeys ++= projectBuildInfo.value,
    libraryDependencies ++= Seq(
      CatsBundle,
    ).flatten ++ Seq(
    ),
  )

// Sample application
lazy val sampleApp = project
  .in(file("module-app"))
  .dependsOn(
    sampleUtils
  )
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "app",
    description := "Basic full-fledged application",
    buildInfoPackage := projectPackage.value,
    buildInfoKeys ++= projectBuildInfo.value,
    libraryDependencies ++= Seq(
      CatsBundle,
      LoggingBundle
    ).flatten ++ Seq(
      PureconfigDep
    ),
  )

// Sample web application
lazy val sampleWeb = project
  .in(file("module-web"))
  .dependsOn(
    sampleUtils
  )
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "web",
    description := "Basic web application",
    buildInfoPackage := projectPackage.value,
    buildInfoKeys ++= projectBuildInfo.value,
    libraryDependencies ++= Seq(
      HttpServiceBundle,
    ).flatten ++ Seq(
      ScalatestDep,
      ShapelessDep,
    ),
  )
