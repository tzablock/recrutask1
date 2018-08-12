package com.recru.duplicatsdetection

import com.recru.Ad
import com.recru.duplicatsdetection.AdSimilarity.AdSimilarity

object AdSimilarity extends Enumeration{
  type AdSimilarity = Value
  val DIFFERENT = Value("Different")
  val SAME = Value("Same")
  val SAME_REPLACED = Value("Replaced")
}

class AdCompare {  // direct 0,3,4,5,8,9,10,11,12,13,14  mean offer change 7,6 obey 1,2,15 by words 16,17
  val SIM_FACTOR = 0.6
  def compareAds(fAd: Ad,sAd: Ad): AdSimilarity ={
    def directCompare(indx: Int): Boolean ={             //TODO if any is null it can be not filled
      fAd.productElement(indx)==sAd.productElement(indx)
    }
    def similarityCompare(indx: Int): Boolean ={
      (fAd.productElement(indx),sAd.productElement(indx)) match {
        case (fAdF: String,sAdF: String) => {
          val fWords = fAdF.split(" ")
          val sWords = sAdF.split(" ")  //TODO improve if contains but have much more
          fWords.map(sWords.contains).count(b => b).toDouble/fWords.length.toDouble >= SIM_FACTOR
        }
        case _ => throw new RuntimeException("Fields to be compared by words need to be string type")
      }
    }
    val requiredFieldsIndx = List(0,3,4,5,8,9,10,11,12,13,14)
    val changeFieldsIndx = List(6,7)
    val obeyIndx = List(1,2,15)
    val wordsMachingFieldsIndx = List(16,17)

    (requiredFieldsIndx.map(directCompare).forall(b => b),wordsMachingFieldsIndx.map(similarityCompare).forall(b => b),changeFieldsIndx.map(directCompare).forall(b => b)) match {
      case (true,true,true) => AdSimilarity.SAME
      case (true,true,false) => AdSimilarity.SAME_REPLACED
      case _ => AdSimilarity.DIFFERENT
    }
  }
}
