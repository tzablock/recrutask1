package com.recru.duplicatsdetection

import com.recru.Ad
import org.scalatest.FunSuite

class AdCompareTest extends FunSuite {

  test("Comparing two identical Ads should return SAME") {
    val ad = Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val out = new AdCompare();
    assert(out.compareAds(ad,ad) == AdSimilarity.SAME)
  }
  test("Comparing two Different Ads should return DIFFERENT") {
    val ad = Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val ad1 = Ad("gb","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val out = new AdCompare();
    assert(out.compareAds(ad,ad1) == AdSimilarity.DIFFERENT)
  }
  test("Comparing two Ads with different fields which could be replace to change offer should return SAME_REPLACED") {
    val ad = Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val ad1 = Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,300000,50.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val out = new AdCompare();
    assert(out.compareAds(ad,ad1) == AdSimilarity.SAME_REPLACED)
  }
  test("Comparing two Ads with different not imortant fields should return SAME") {
    val ad = Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val ad1 = Ad("es","7+r9FI3ZbDe7zZ7k6+dsdds","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2017-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val out = new AdCompare();
    assert(out.compareAds(ad,ad1) == AdSimilarity.SAME)
  }
  test("Comparing two Ads with similar words in title and content should return SAME") {
    val ad = Ad("es","7+r9FI3ZbDe7zZ7k6+NS2w==","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2014-11-06","... st sport pa...","...ort interior en semicuero, climatizador bizona, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val ad1 = Ad("es","7+r9FI3ZbDe7zZ7k6+dsdds","f2ZnEzO1jG63+znczMcv9w==","Peugeot","407",2005,153000,0.0,0,null,null,null,null,"Badajoz","Villafranca de los Barros","2017-11-06","... tr sport pa...","...ort ddssd en semicuero, climatizador ffgggh, sensores de aparcamiento, de lluvia y de luz, faros de xenón, control y limitador de velocidad, revisión recién hecha...")
    val out = new AdCompare();
    assert(out.compareAds(ad,ad1) == AdSimilarity.SAME)
  }
}
