name := "tdd-scala"

version := "1.0"

scalaVersion := "2.12.9"

projectDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.8" % Test, 
                            "org.scalamock" %% "scalamock" % "4.1.0" % Test)
