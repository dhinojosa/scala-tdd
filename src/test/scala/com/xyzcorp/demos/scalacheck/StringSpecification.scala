package com.xyzcorp.demos.scalacheck

import org.scalacheck.Prop.{forAll, undecided}
import org.scalacheck.Properties

object StringSpecification extends Properties("String Specification") {
    property("startsWith") = forAll { (a: String, b: String) =>
        (a + b).startsWith(a)
    }

//    property("concatenate") = forAll { (a: String, b: String) =>
//        (a + b).length > a.length && (a + b).length > b.length
//        undecided
//    }

    property("substring") = forAll { (a: String, b: String, c: String) =>
        (a + b + c).substring(a.length, a.length + b.length) == b
    }
}
