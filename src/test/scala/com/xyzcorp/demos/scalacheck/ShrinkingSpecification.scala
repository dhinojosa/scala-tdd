package com.xyzcorp.demos.scalacheck

import org.scalacheck.Properties
import org.scalacheck.Prop._

object ShrinkingSpecification extends Properties("Shrinking") {
    def brokenReverse[A](xs: List[A]): List[A] = {
        if (xs.length > 3) xs else xs.reverse
    }

//    property(
//        """When things need to be reported it will
//          |  find the easiest combination""".stripMargin) = forAll{
//        xs:List[Int] => xs.length > 0 ==>
//            (xs.last == brokenReverse(xs).head)
//    }
}
