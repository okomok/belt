import sbt._
import Keys._

object build extends Build {
    lazy val beltSettings = Project.defaultSettings ++ Seq(
        organization := "com.github.okomok",
        version := "0.1.0-SNAPSHOT",
        scalaVersion := "2.9.1",
        crossScalaVersions := Seq("2.9.1", "2.10.0-SNAPSHOT"),
        scalacOptions ++= Seq("-deprecation", "-unchecked"),
        scalacOptions <++= scalaVersion map { version =>
            // Thanks to Miles Sabin.
            val Version = """(\d+)\.(\d+)\..*"""r
            val Version(major0, minor0) = version map identity
            val (major, minor) = (major0.toInt, minor0.toInt)
            if (major < 2 || (major == 2 && minor < 10)) Seq("-Ydependent-method-types")
            else Nil
        },
        libraryDependencies ++= Seq(
            "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test",
            "junit" % "junit" % "4.4" % "test"
        ),
        resolvers += ScalaToolsSnapshots,
        parallelExecution := false,
        publishArtifact in packageDoc := false
    )

    lazy val belt = Project(
        id = "belt",
        base = file("."),
        settings = beltSettings
    )
}
