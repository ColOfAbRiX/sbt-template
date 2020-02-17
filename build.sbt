import Compiler._
import Dependencies._

// General
val ScalaVersion   = "2.13.0"
val ProjectVersion = "0.1.0-SNAPSHOT"

// Compiler options
scalacOptions in ThisBuild ++= TpolecatOptions

// Wartremover
wartremoverExcluded in ThisBuild ++= (baseDirectory.value * "**" / "src" / "test").get
wartremoverErrors in ThisBuild ++= Warts.allBut(
  Wart.Any,
  Wart.Nothing,
  Wart.Overloading,
  Wart.ToString,
)

// Standardize formatting
scalafmtOnCompile in ThisBuild := true

// Global dependencies and compiler plugins
libraryDependencies in ThisBuild ++= Seq(
  BetterMonadicForPlugin,
  KindProjectorPlugin,
  PPrintDep,
  WartremoverPlugin,
) ++ Seq(
  LoggingBundle,
).flatten

// Root project
lazy val projectRoot: Project = project
  .in(file("."))
  .settings(
    organization := "com.colofabrix.scala.sample",
    name := "sample",
    version := ProjectVersion,
    scalaVersion := ScalaVersion,
    libraryDependencies ++= Seq(),
  )
  .aggregate(
    sampleProject,
  )

// Sampleproject
lazy val sampleProject = project
  .in(file("sample-project"))
  .dependsOn(
  )
  .settings(
    organization := "com.colofabrix.scala.sample",
    name := "sample-project",
    version := ProjectVersion,
    scalaVersion := ScalaVersion,
    libraryDependencies ++= Seq(
      PureconfigDep,
      ScalatestDep,
      ShapelessDep,
    ) ++ Seq(
      CatsBundle,
    ).flatten,
  )
