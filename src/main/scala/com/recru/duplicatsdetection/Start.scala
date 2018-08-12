package com.recru.duplicatsdetection

import org.apache.spark.sql.SparkSession

object Start {
  val defaultAppName = "Deduplication"
  val defaultMaster = "local"
  val defaultInPath = "/home/tzablock/IdeaProjects/RecrTask1/src/main/resources/cars.json"
  val defaultOutPath = "/home/tzablock/IdeaProjects/RecrTask1/src/main/resources/duplicates.json"

  def main(args: Array[String]): Unit = {
    val (appName,master,inPath,outPath) = args match {
      case Array(arg1, arg2, arg3, arg4) => (arg1,arg2,arg3,arg4)
      case _ =>
        (defaultAppName,defaultMaster,defaultInPath,defaultOutPath)
    }

    val spark = SparkSession.builder()
                            .appName(appName)
                            .master(master)
                            .getOrCreate()

    val dd = DuplicationDetector(spark)
    dd.detectDuplicats(inPath, outPath)
  }
}
