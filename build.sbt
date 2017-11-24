name := "findsrid"

version := "0.1"

scalaVersion := "2.12.4"

// These let you enter input in the terminal if running via SBT
fork in run := true
connectInput in run := true

resolvers += "Boundless" at "http://repo.boundlessgeo.com/main/"
libraryDependencies += "org.gdal" % "gdal" % "2.2.1"