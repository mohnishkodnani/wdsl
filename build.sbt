import Dependencies._

ThisBuild / scalaVersion     := "3.1.1"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "dev.kms"
ThisBuild / organizationName := "khel"

ThisBuild / evictionErrorLevel := Level.Warn
ThisBuild / scalafixDependencies += Libraries.organizeImports

ThisBuild / resolvers += Resolver.sonatypeRepo("snapshots")

Compile / run / fork := true

Global / onChangedBuildSource := ReloadOnSourceChanges
Global / semanticdbEnabled    := true // for metals

val commonSettings = List(
  scalacOptions ++= List("-source:future"),
  scalafmtOnCompile := false, // recommended in Scala 3
  libraryDependencies ++= Seq(
		//TODO: Add dependencies defined in Dependencies.scala under Libraries here.
  )
)

def dockerSettings(name: String) = List(
  Docker / packageName := s"$name",
  dockerBaseImage      := "jdk17-curl:latest",
  dockerExposedPorts ++= List(8080),
  makeBatScripts     := Nil,
  dockerUpdateLatest := true
)

lazy val root = (project in file("."))
  .settings(
    name := "template" //TODO: Change me
  )
  .aggregate(core)

lazy val core = (project in file("core"))
  .settings(commonSettings: _*)


// integration tests
lazy val it = (project in file("modules/it"))
  .settings(commonSettings: _*)
  .dependsOn(core)

addCommandAlias("runLinter", ";scalafixAll --rules OrganizeImports")
