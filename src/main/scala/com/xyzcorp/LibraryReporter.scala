package com.xyzcorp

import java.time.LocalDate
import java.time.temporal.ChronoUnit.MONTHS

case class Delinquent(name: String, amount: Int)

class LibraryReporter(val checkoutProvider: () => Seq[Checkout],
                      val current: LocalDate, val amount: Int) {
  def findTopOffenders: Seq[Delinquent] = checkoutProvider()
    .sortBy(c => c.date)
    .map(c => Delinquent(c.customer,
         LibraryReporter.calculateDelinquency(current, c.date, amount)))
    .take(5)
}

object LibraryReporter {
  def apply(checkoutProvider: () => Seq[Checkout], amount: Int) =
    new LibraryReporter(checkoutProvider, LocalDate.now, amount)
  def calculateDelinquency(currentDate: LocalDate,
                                     checkoutDate: LocalDate,
                                     amount:Int): Int = {
    MONTHS.between(checkoutDate, currentDate.minusDays(1)).toInt * amount
  }
}
