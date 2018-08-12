package com.recru

import java.nio.file.Files

import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

class AdsParserTest extends FunSuite {
  test("Test if parsing simple json file work locally."){
    val inPath = getClass.getResource("/test.json").getFile
    val spark = SparkSession.builder().appName("Test").master("local").getOrCreate()
    val out = AdsParser(spark)
    val ads = out.parseAdds(inPath)
    val collectedAds = ads.collect()
    assert(collectedAds.length == 4)
    assert(collectedAds(0) == Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha..."))
  }
}
