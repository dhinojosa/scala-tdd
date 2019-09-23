package com.xyzcorp.exercises

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers}

import scala.util.{Failure, Success, Try}

class LibraryServiceTest extends FunSpec with Matchers with MockFactory {
    describe("LibraryService retrieving checkout items") {
        it("should retrieve from the local file system") {
            val result: Try[Seq[Checkout]] = LibraryServiceFactory
                .getFromLocalResource("library.txt")(Checkout.convertFromString)
            result match {
                case Success(xs) => xs.length should be(17)
                case Failure(exception) => fail(exception)
            }
        }

        it("should retrieve from the local file system with the full integration")
        {
            val result: Try[Seq[Checkout]] = LibraryServiceFactory
                .getFromLocalResource("library.txt")(Checkout.convertFromString)
            result match {
                case Success(xs) =>
                    LibraryReporter(() => xs, 10).findTopOffenders
                        .foreach(println)
                case Failure(exception) => fail(exception)
            }
        }
    }
}
