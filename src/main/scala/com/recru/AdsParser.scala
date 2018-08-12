package com.recru

import java.util.Date

import org.apache.spark.sql._
import java.text.SimpleDateFormat

import org.apache.spark.sql.types._

case class Ad(country: String,uniqueId: String,urlAnonymized: String, make: String, model: String,
              year: BigInt, mileage: BigInt, price: Double, doors: BigInt, fuel: String,carType:String, transmission: String,
              color: String, region: String, city: String, date: String ,titleChunk: String, contentChunk: String)
//* `country`: The country code where the car is sold. direct 0,3,4,5,8,9,10,11,12,13,14  mean offer change 7,6 obey 1,2,15 by words 16,17
//* `uniqueId`: The Trovit identifier of this ad.
//* `urlAnonimized`: The anonimized URL of the ad.
//* `make`: The car maker.
//* `model`: The car model.
//* `year`: The year the car was made.  int
//* `mileage`: The car mileage in km (zero is non-informed)   int
//* `price`: The price of the auto. double 7
//* `doors`: The number of doors.   int  8
//* `fuel`: The fuel type (gasoline, diesel, ...) 9
//* `carType`: The body type of the vehicle (sedan, coupé, ...) 10
//* `transmission`: The transmission of the car (manual, automatic, ...) 11
//* `color`: The color of the car. 12
//* `region`: The region where the car is sold. 13
//* `city`: The city where the car is sold. 14
//* `date`: The date where the car was first published.  data  15
//* `titleChunk`: A chunk of text from the title summary of the ad.  16
//* `contentChunk`: A chunk of text from the description of the ad.  17

class AdsParser(spark:SparkSession) {
  import spark.implicits._

  def parseAdds(inPath: String): Dataset[Ad] ={

    spark.read.json(inPath).as[Ad]
  }
}
object AdsParser {
  def apply(spark: SparkSession): AdsParser = new AdsParser(spark)
}
