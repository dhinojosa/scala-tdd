name := "tdd-scala"

version := "1.0"

scalaVersion := "2.13.0"

scalacOptions ++= Seq("-deprecation", "-feature")

Test / testOptions := Seq(Tests.Filter(s => !s.endsWith("IntegrationTest")))

IntegrationTest / testOptions :=
    Seq(Tests.Filter(s => s.endsWith("IntegrationTest")))

projectDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.8" % Test,
    "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    "org.scalacheck" %% "scalacheck" % "1.14.0" % Test)
