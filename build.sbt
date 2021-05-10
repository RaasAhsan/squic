
lazy val commonSettings = Seq(
  name := "squic",
  organization := "com.raasahsan",
  version := "1.0",
  scalaVersion := "2.13.3"
)

lazy val root = project.in(file("."))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-io" % "3.0.2"
    )
  )
