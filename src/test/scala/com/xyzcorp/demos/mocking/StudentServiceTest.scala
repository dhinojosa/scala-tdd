package com.xyzcorp.demos.mocking

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers}

class StudentServiceTest extends FunSpec with Matchers with MockFactory {
    describe("Student Service using a mock") {
        it ("""Should not register a student if the ssn
              |  has already been registered before""".stripMargin) {
            val fran = Student("303-120-4001", "Fran", "Abeliene")
            val studentDAO = mock[StudentDAO]

            (studentDAO.findBySocialSecurity _)
                .expects("303-120-4001")
                .returns(Some(fran))
            (studentDAO.audit _).expects("Searched 303-120-4001")

            val studentService = new StudentService(studentDAO)
            val francine = Student("303-120-4001", "Francine", "Abeliene")
            val result = studentService.registerStudent(francine)
            result should be (None)
        }

        it ("""Should register a student if the ssn
              |  has not already been registered before""".stripMargin) {
            val studentDAO = mock[StudentDAO]
            val francine = Student("303-120-4001", "Francine", "Abeliene")
            (studentDAO.findBySocialSecurity _)
                .expects("303-120-4001")
                .returns(None)
            (studentDAO.audit _).expects("Searched 303-120-4001")
            (studentDAO.persist _) expects francine returns 3004L
            (studentDAO.audit _).expects("Persisted Francine Abeliene")
            val studentService = new StudentService(studentDAO)
            val result = studentService.registerStudent(francine)
            result should be (Some(3004L))
        }
    }

    describe("Student Service using a stub") {
        it ("""Should not register a student if the ssn
              |  has already been registered before""".stripMargin) {
            val fran = Student("303-120-4001", "Fran", "Abeliene")
            val studentDAO = stub[StudentDAO]
            (studentDAO.findBySocialSecurity _)
                .when("303-120-4001")
                .returning(Some(fran))
            val studentService = new StudentService(studentDAO)
            val francine = Student("303-120-4001", "Francine", "Abeliene")
            val result = studentService.registerStudent(francine)
            result should be (None)
            (studentDAO.audit _).verify("Searched 303-120-4001")
        }

        it ("""Should register a student if the ssn
              |  has not already been registered before""".stripMargin) {
            val studentDAO = stub[StudentDAO]
            val francine = Student("303-120-4001", "Francine", "Abeliene")
            (studentDAO.findBySocialSecurity _)
                .when("303-120-4001")
                .returning(None)

            (studentDAO.persist _) when francine returning 3004L
            val studentService = new StudentService(studentDAO)
            val result = studentService.registerStudent(francine)
            result should be (Some(3004L))

            (studentDAO.audit _).verify("Searched 303-120-4001")
            (studentDAO.audit _).verify("Persisted Francine Abeliene")
        }
    }
}
