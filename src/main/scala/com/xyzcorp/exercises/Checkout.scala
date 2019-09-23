package com.xyzcorp.exercises

import java.time.LocalDate

case class Checkout(customer:String, title:String, date:LocalDate)
object Checkout {
  def convertFromList(list: List[String]): List[Either[String, Checkout]] = {
    list.map(convertFromString).filter(e => e.isRight)
  }

  def convertFromString(string:String):Either[String, Checkout] = {
    if (string.isEmpty) Left("String cannot be empty")
    else {
      val tokens = string.split("~")
      if (tokens.length != 3) Left("String is in an incorrect format")
      else Right(Checkout(tokens(0), tokens(1), LocalDate.parse(tokens(2))))
    }
  }
}
