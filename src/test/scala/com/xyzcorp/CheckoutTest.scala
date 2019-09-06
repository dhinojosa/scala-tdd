package com.xyzcorp

import java.time.LocalDate

import org.scalatest.{EitherValues, FunSpec, Matchers}

class CheckoutTest extends FunSpec with Matchers with EitherValues {
  describe("Checkout.convert") {
    it ("should return a Right with a checkout if all is correct") {
      val either = Checkout.convertFromString("Sam Prospect~Hello~2019-02-03")
      either should be ('right)
      either contains Checkout("Sam Prospect", "Hello", LocalDate.of(2019,2,3))
    }
    it ("should return a Left if the string is blank") {
      val either = Checkout.convertFromString("")
      either should be ('left)
      either contains Left("Strings was blank")
    }
    it ("should return a Right with a checkout of a different content") {
      val either = Checkout.convertFromString("Bob Freeburn~Cool~2019-01-01")
      either should be ('right)
      either.right.value should be (Checkout("Bob Freeburn", "Cool", LocalDate.of(2019,1,1)))
    }
    it ("should return a Left when it is trash") {
      val either = Checkout.convertFromString("dds$")
      either should be ('left)
      either contains Left("String is in an incorrect format")
    }
    it ("should work with a collection") {
      val result = Checkout.convertFromList(List("Sam Prospect~Hello~2019-02-03", "Bob Freeburn~Cool~2019-01-01"))
      result should be (List(Right(Checkout("Sam Prospect", "Hello", LocalDate.of(2019,2,3))),
                        Right(Checkout("Bob Freeburn", "Cool", LocalDate.of(2019,1,1)))))
    }

    it ("should work with a collection with a Left") {
      val result = Checkout.convertFromList(List("Sam Prospect~Hello~2019-02-03", "", "Bob Freeburn~Cool~2019-01-01"))
      result should be (List(Right(Checkout("Sam Prospect", "Hello", LocalDate.of(2019,2,3))),
        Right(Checkout("Bob Freeburn", "Cool", LocalDate.of(2019,1,1)))))
    }
  }
}
