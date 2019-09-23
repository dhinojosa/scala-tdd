package com.xyzcorp.exercises

import java.time.LocalDate

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers}

class LibraryReporterTest extends FunSpec with Matchers with MockFactory {
  val authorList: List[Checkout] = List(
    Checkout("Stephanie Meyer", "Twilight", LocalDate.of(2019, 7, 3)),
    Checkout("F Scott Fitzgerald", "The Great Gatsby",
      LocalDate.of(2019, 6, 3)),
    Checkout("George R.R. Martin", "Game of Thrones",
      LocalDate.of(2019, 1, 3)),
    Checkout("Ernest Hemingway", "The Sun Also Rises",
      LocalDate.of(2019, 5, 3)),
    Checkout("William Shakespeare", "Taming of the Shrew",
      LocalDate.of(2019, 4, 3)),
    Checkout("John Dos Passos", "USA", LocalDate.of(2019, 9, 3)),
    Checkout("Stephen King", "IT", LocalDate.of(2019, 8, 3)),
    Checkout("J.K. Rowling", "Harry Potter", LocalDate.of(2019, 2, 3)),
    Checkout("Kate Bernheimer", "Horse, Flower, Bird",
      LocalDate.of(2019, 10, 3)),
    Checkout("Neal Stephenson", "Snow Crash", LocalDate.of(2019, 3, 3))
  )

  describe("Delinquent Calculations") {
    it("Should be allowed for one month delinquency without a charge") {
      val result = LibraryReporter.calculateDelinquency(LocalDate.of(2019, 10, 11), LocalDate.of(2019, 9, 11), 10)
      result should be (0)
    }
    it("Should calculate one month for one day after") {
      val result = LibraryReporter.calculateDelinquency(LocalDate.of(2019, 10, 12), LocalDate.of(2019, 9, 11), 10)
      result should be (10)
    }
    it("Should calculate two months for one month after") {
      info("Green Bar")
      val result = LibraryReporter.calculateDelinquency(LocalDate.of(2019, 11, 11), LocalDate.of(2019, 9, 11), 10)
      result should be (10)
    }
    it("Should calculate just fine for January to February transition") {
      info("Green Bar")
      val result = LibraryReporter.calculateDelinquency(LocalDate.of(2019, 2, 28), LocalDate.of(2019, 1, 28), 10)
      result should be (0)
    }
    it("Should calculate with March after the February has lapse") {
      info("Green Bar")
      val result = LibraryReporter.calculateDelinquency(LocalDate.of(2019, 3, 1), LocalDate.of(2019, 1, 28), 10)
      result should be (10)
    }
  }
  describe("Find all overdue books") {
    it("Should return an empty if there are no books returned") {
      val libraryReporter = LibraryReporter(() => List.empty[Checkout], 10)
      libraryReporter.findTopOffenders should be(Nil)
    }

    it("Should return the Delinquents in order") {
      val expectedResult = List(
        Delinquent("George R.R. Martin", 5 * 10),
        Delinquent("J.K. Rowling", 4 * 10),
        Delinquent("Neal Stephenson", 3 * 10),
        Delinquent("William Shakespeare", 2 * 10),
        Delinquent("Ernest Hemingway", 10)
      )
      val libraryReporter = new LibraryReporter(() => authorList, LocalDate.of(2019, 6, 10), 10)
      libraryReporter.findTopOffenders should be(expectedResult)
    }
  }
}
