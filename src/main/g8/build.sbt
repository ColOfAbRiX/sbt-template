import Dependencies._
import AllProjectsKeys.autoImport._

lazy val ScalaLangVersion = "$scalaVersion$"

// General
ThisBuild / organization := "$package$"
ThisBuild / scalaVersion := ScalaLangVersion

// Compiler options
ThisBuild / scalacOptions ++= Compiler.TpolecatOptions ++ Seq("-P:splain:all")

// GIT version information
ThisBuild / dynverVTagPrefix := false

// Wartremover
ThisBuild / wartremoverExcluded ++= (baseDirectory.value * "**" / "src" / "test").get
$if(useIoBasic.truthy || useApp.truthy || useWeb)$
ThisBuild / wartremoverErrors ++= Warts.allBut(
  Wart.Any,
  Wart.Nothing,
  Wart.Overloading,
  Wart.ToString,
)
$endif$

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
lazy val $name;format="camel"$Root: Project = project
  .in(file("."))
  .settings(
    name := "$name;format="normalize"$",
    description := "$desc$",
  )
  .aggregate(
$if(useBasic.truthy)$
    $name;format="camel"$$basicProject;format="Camel"$,
$endif$
$if(useIoBasic.truthy)$
    $name;format="camel"$$ioBasicProject;format="Camel"$,
$endif$
$if(useApp.truthy)$
    $name;format="camel"$$appProject;format="Camel"$,
$endif$
$if(useWeb.truthy)$
    $name;format="camel"$$webProject;format="Camel"$,
$endif$
  )

$if(useApp.truthy || useWeb.truthy)$
// Utils project
lazy val $name;format="camel"$Utils = project
  .in(file("module-utils"))
  .settings(
    name := "utils",
    description := "Project Utilities",
    libraryDependencies ++= Seq(
      CatsCoreDep,
      CatsScalaTestDep,
      FS2CoreDep,
      ScalatestDep,
    ),
  )

$endif$
$if(useBasic.truthy)$
// Sample basic application
lazy val $name;format="camel"$$basicProject;format="Camel"$ = project
  .in(file("module-basic"))
  .dependsOn(
  )
  .settings(
    name := "basic",
    description := "",
    libraryDependencies ++= Seq(
    ).flatten ++ Seq(
      ScalatestDep,
    ),
  )

$endif$
$if(useIoBasic.truthy)$
// Sample basic application with IO
lazy val $name;format="camel"$$ioBasicProject;format="Camel"$ = project
  .in(file("module-io-basic"))
  .dependsOn(
  )
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "io-basic",
    description := "",
    buildInfoPackage := projectPackage.value,
    buildInfoKeys ++= projectBuildInfo.value,
    libraryDependencies ++= Seq(
      CatsBundle,
    ).flatten ++ Seq(
      ScalatestDep,
    ),
  )

$endif$
$if(useApp.truthy)$
// Sample application
lazy val $name;format="camel"$$appProject;format="Camel"$ = project
  .in(file("module-app"))
  .dependsOn(
    $name;format="camel"$Utils
  )
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "app",
    description := "",
    buildInfoPackage := projectPackage.value,
    buildInfoKeys ++= projectBuildInfo.value,
    libraryDependencies ++= Seq(
      CatsBundle,
      LoggingBundle
    ).flatten ++ Seq(
      ScalatestDep,
      PureconfigDep,
    ),
  )

$endif$
$if(useWeb.truthy)$
// Sample web application
lazy val $name;format="camel"$$webProject;format="Camel"$ = project
  .in(file("module-web"))
  .dependsOn(
    $name;format="camel"$Utils
  )
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "web",
    description := "",
    buildInfoPackage := projectPackage.value,
    buildInfoKeys ++= projectBuildInfo.value,
    libraryDependencies ++= Seq(
      HttpServiceBundle,
    ).flatten ++ Seq(
      ScalatestDep,
      ShapelessDep,
    ),
  )
$endif$
