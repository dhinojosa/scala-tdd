package com.xyzcorp.demos.mocking

case class Student(id:Option[Long], ssn:String,
                   firstName:String, lastName:String)

object Student {
    def apply(ssn:String, firstName:String, lastName:String) =
        new Student(None, ssn, firstName, lastName)
}
