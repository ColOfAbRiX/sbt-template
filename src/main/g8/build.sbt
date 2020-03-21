import Dependencies._
import AllProjectsKeys.autoImport._

lazy val ScalaLangVersion = "2.13.0"

// General
scalaVersion := ScalaLangVersion
Global / scalaVersion := ScalaLangVersion
ThisBuild / scalaVersion := ScalaLangVersion
ThisBuild / organization := "$package$"

// GIT version information
ThisBuild / dynverVTagPrefix := false

$if(enforceBestPractices.truthy)$
// Compiler options
ThisBuild / scalacOptions ++= Compiler.TpolecatOptions ++ Seq("-P:splain:all")

// Wartremover
ThisBuild / wartremoverExcluded ++= (baseDirectory.value * "**" / "src" / "test").get
$if(addIoBasicModule.truthy || addAppModule.truthy || addWebModule)$
ThisBuild / wartremoverErrors ++= Warts.allBut(
  Wart.Any,
  Wart.Nothing,
  Wart.Overloading,
  Wart.ToString,
)
$endif$

// Scalafmt
ThisBuild / scalafmtOnCompile := true

$endif$
// Global dependencies and compiler plugins
ThisBuild / libraryDependencies ++= Seq(
  BetterMonadicForPlugin,
  KindProjectorPlugin,
  PPrintDep,
  SplainPlugin,
$if(enforceBestPractices.truthy)$
  WartremoverPlugin,
$endif$
) ++ Seq(
).flatten

//  PROJECTS  //

// Root project
lazy val $name;format="camel"$Root: Project = project
  .in(file("."))
  .settings(
    name := "$name;format="normalize"$",
    description := "$desc$",
    libraryDependencies ++= Seq(
$if(addRootIoProject.truthy)$
      CatsBundle,
$endif$
    ).flatten ++ Seq(
      ScalatestDep,
    ),
  )
  .aggregate(
$if(addBasicModule.truthy)$
    $name;format="camel"$$basicModuleName;format="Camel"$,
$endif$
$if(addIoBasicModule.truthy)$
    $name;format="camel"$$ioBasicModuleName;format="Camel"$,
$endif$
$if(addAppModule.truthy)$
    $name;format="camel"$$appModuleName;format="Camel"$,
$endif$
$if(addWebModule.truthy)$
    $name;format="camel"$$webModuleName;format="Camel"$,
$endif$
  )

$if(addAppModule.truthy || addWebModule.truthy)$
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
$if(addBasicModule.truthy)$
// Standard basic application
lazy val $name;format="camel"$$basicModuleName;format="Camel"$ = project
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
$if(addIoBasicModule.truthy)$
// Standard basic application with cats and IOApp
lazy val $name;format="camel"$$ioBasicModuleName;format="Camel"$ = project
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
$if(addAppModule.truthy)$
// Modular application
lazy val $name;format="camel"$$appModuleName;format="Camel"$ = project
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
$if(addWebModule.truthy)$
// Modular web application
lazy val $name;format="camel"$$webModuleName;format="Camel"$ = project
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
