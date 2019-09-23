package com.xyzcorp.exercises

import org.scalatest.{FunSpec, Matchers}

class CaesarShiftTest extends FunSpec with Matchers {
    info("TDD is about starting simple")
    info("We need to think about the items that go in and items that come out")
    info(" - Input:  String, Number")
    info(" - Output: String")
    info("What is the simplest thing?")
    describe("Encoding a String") {
        it("should not change when the shift is zero given an empty String") {
            val result = CaesarShift.encode("", 0)
            result should be("")
        }
        it("should not change when the shift is one given an empty String") {
            info("This was a green bar test")
            val result = CaesarShift.encode("", 1)
            result should be("")
        }
        it("shouldn't shift if given an 'a' and the shift is 0") {
            val result = CaesarShift.encode("a", 0)
            result should be("a")
        }
        it("should shift from a to b if the shift is 1") {
            val result = CaesarShift.encode("a", 1)
            result should be("b")
        }
        it("should shift from z to a if the shift is 1") {
            val result = CaesarShift.encode("z", 1)
            result should be("a")
        }
        it("should shift from z to z if the shift is 26") {
            info("This was a green bar test")
            val result = CaesarShift.encode("z", 26)
            result should be("z")
        }
        it("should ignore non-alpha characters") {
            val result = CaesarShift.encode("!", 40)
            result should be("!")
        }
        it("should handle a full word like \"Hello World\"") {
            val result = CaesarShift.encode("Hello World", 1)
            result should be("Ifmmp Xpsme")
        }
        it("should shift from M to N if the shift is 1") {
            info("This was a green bar test")
            val result = CaesarShift.encode("M", 1)
            result should be("N")
        }
        it("should shift from Z to A if the shift is 1") {
            val result = CaesarShift.encode("Z", 1)
            result should be("A")
        }
        it("should shift from M to O if the shift is -2147483648") {
            val result = CaesarShift.encode("M", -2147483648)
            result should be("O")
        }
        it("should not accept any null values") {
            val thrown = the[IllegalArgumentException] thrownBy
                (CaesarShift.encode(null, 3))
            thrown.getMessage should
                be("requirement failed: String cannot be null")
        }
    }
    describe("Decoding a String") {
        it("should not change when the shift is zero given an empty String") {
            val result = CaesarShift.decode("", 0)
            result should be("")
        }
        it("shouldn't shift if given an 'a' and the shift is 0") {
            info("This is a green bar")
            val result = CaesarShift.encode("a", 0)
            result should be("a")
        }
        it("should shift from b to a if the shift is 1") {
            info("This is a green bar")
            val result = CaesarShift.decode("b", 1)
            result should be("a")
        }
        it("should shift from a to z if the shift is 1") {
            val result = CaesarShift.decode("a", 1)
            result should be("z")
        }
        it("should encode and decode for p, -24") {
            val result = CaesarShift.decode(CaesarShift.encode("p", -24), -24)
            result should be("p")
        }
        it("should encode and decode for O, -2147483648") {
            val result = CaesarShift.decode("O", -2147483648)
            result should be("M")
        }
    }
    describe("Specification that should hold") {
        import org.scalacheck.Arbitrary.arbitrary
        import org.scalacheck.Gen
        import org.scalacheck.Prop.{AnyOperators, forAll}

        val myGen: Gen[(String, Int)] = {
            for {x <- Gen.alphaStr
                 y <- arbitrary[Int]} yield (x, y)
        }

        it("should be able to encode then decode") {
            forAll(myGen) { case (s: String, i: Int) =>
                CaesarShift.decode(CaesarShift.encode(s, i), i) ?= s
            }
        }
        it("should also be able to decode and then encode") {
            forAll(myGen) { case (s: String, i: Int) =>
                CaesarShift.encode(CaesarShift.decode(s, i), i) ?= s
            }
        }
    }
}
