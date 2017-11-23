package com.whackdata

import org.gdal.ogr._

object Main extends App {

  val fileName = "./src/main/resources/Canada3573.gpkg"

  ogr.RegisterAll()
  val dataSource = ogr.OpenShared(fileName)

  val layer = dataSource.GetLayer(0)
  val srs = layer.GetSpatialRef()
  srs.AutoIdentifyEPSG

  val authCode = srs.GetAuthorityCode(null)
  println("EPSG: " + authCode)

}
