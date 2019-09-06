package com.xyzcorp

import java.net.URI
import java.time.LocalDate

import scala.io.Source
import scala.util.{Try, Using}

trait LibraryService {
  def getFromLocalResource(str: String)
                          (converter: String => Either[String, Checkout]): Try[Seq[Checkout]]

  def getFromURL(str: String)
                (converter: String => Either[String, Checkout]): Try[Seq[Checkout]]
}

object LibraryServiceFactory extends LibraryService {
  override def getFromLocalResource(str: String)
                                   (converter: String => Either[String, Checkout]): Try[Seq[Checkout]] = {
    Using(Source.fromResource(str))(resource => resource.getLines()
      .map(converter)
      .collect{case Right(x) => x}
      .toSeq)
  }

  override def getFromURL(str: String)
                         (converter: String => Either[String, Checkout]): Try[Seq[Checkout]] = {
    Using(Source.fromURI(new URI(str)))(resource => resource.getLines()
      .map(converter)
      .filter(_.isRight)
      .map(_.getOrElse(Checkout("", "", LocalDate.of(1971, 1, 1))))
      .toSeq)
  }
}
