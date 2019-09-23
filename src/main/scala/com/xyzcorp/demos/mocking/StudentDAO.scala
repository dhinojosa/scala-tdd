package com.xyzcorp.demos.mocking

trait StudentDAO {
    def persist(student: Student): Long

    def findByFirstName(firstName: String): List[Student]

    def findByLastName(firstName: String): List[Student]

    def findBySocialSecurity(ssn:String):Option[Student]

    def findById(id:Long):Option[Student]

    def audit(msg:String):Unit
}
