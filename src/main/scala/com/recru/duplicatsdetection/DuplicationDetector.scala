package com.recru.duplicatsdetection

import com.recru.AdsParser
import org.apache.spark.sql.SparkSession

class DuplicationDetector(spark: SparkSession) {
  import spark.implicits._

  def detectDuplicats(inPath: String, outPath: String): Unit ={
    val ads = AdsParser(spark).parseAdds(inPath)
    ads.take(10).foreach(println)
  }
}
object DuplicationDetector{
  def apply(spark: SparkSession): DuplicationDetector = new DuplicationDetector(spark)
}
