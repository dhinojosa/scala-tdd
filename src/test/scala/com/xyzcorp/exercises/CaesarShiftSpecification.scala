package com.xyzcorp.exercises

import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.{AnyOperators, forAll}
import org.scalacheck.{Gen, Properties}

object CaesarShiftSpecification
    extends Properties("CaesarShift Specification") {

    val myGen: Gen[(String, Int)] = {
        for {x <- Gen.alphaStr
             y <- arbitrary[Int]} yield (x, y)
    }
    property("encode, decode should return the same word") = forAll(myGen)
    { case (s: String, i: Int) =>
        CaesarShift.decode(CaesarShift.encode(s, i), i) ?= s
    }
    property("decode and encode should return the same word") = forAll(myGen)
    { case (s: String, i: Int) =>
        CaesarShift.encode(CaesarShift.decode(s, i), i) ?= s
    }
}
