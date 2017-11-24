package com.whackdata

import org.gdal.ogr._

object Main extends App {

  val fileName = "./src/main/resources/Canada3573.gpkg"

  // Register all of the OGR drivers that are part of your GDAL installation.
  ogr.RegisterAll()
  // Open your file
  val dataSource = ogr.OpenShared(fileName)

  // Get the first layer (there is only one in the sample file).
  // You can also use dataSource.GetLayerByName() if your layers are named.
  val layer = dataSource.GetLayer(0)

  // Load the Spatial Reference and auto-identify
  val srs = layer.GetSpatialRef()
  srs.AutoIdentifyEPSG

  val authCode = srs.GetAuthorityCode(null)
  println("EPSG: " + authCode)

}
