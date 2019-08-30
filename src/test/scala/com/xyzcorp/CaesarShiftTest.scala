package com.xyzcorp

import org.scalatest.{FunSpec, Matchers}

class CaesarShiftTest extends FunSpec with Matchers {
    info("TDD is about starting simple")
    info("We need to think about the items that go in and items that come out")
    info(" - Input:  String, Number")
    info(" - Output: String")
    info("What is the simplest thing?")
    describe("Encoding a String") {
      it ("should not change when the shift is zero given an empty String") {
        val result = CaesarShift.encode("", 0)
        result should be ("")
      }
      it ("should not change when the shift is one given an empty String") {
        info("This was a green bar test")
        val result = CaesarShift.encode("", 0)
        result should be ("")
      }
    }
    describe("Properties that should hold") {
      it ("should encode and decode to get the same alpha word every time") {

      }
    }
}
