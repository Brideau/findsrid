package com.whackdata

import org.gdal.ogr._

import scala.concurrent.Future
import scala.io.StdIn
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object MainNonBlocking extends App {

  val fileName = "./src/main/resources/hex62p5.geojson"

  // Register all of the OGR drivers that are part of your GDAL installation.
  ogr.RegisterAll()

  def getAuthCode(fileName: String): Future[String] = Future {
    val dataSource = ogr.OpenShared(fileName)

    // Get the first layer (there is only one in the sample file).
    // You can also use dataSource.GetLayerByName() if your layers are named.
    val layer = dataSource.GetLayer(0)

    // Load the Spatial Reference and auto-identify
    val srs = layer.GetSpatialRef()
    srs.AutoIdentifyEPSG

    srs.GetAuthorityCode(null)
  }

  getAuthCode(fileName).onComplete{
    case Success(authCode) => println("EPSG: " + authCode)
    case Failure(ex) => println("Lookup Failed: " + ex.getMessage)
  }

  println(s"Running in background. Press Return to stop.")
  StdIn.readLine()

}
