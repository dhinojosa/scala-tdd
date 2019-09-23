package com.xyzcorp.demos.mocking

class StudentService(val studentDAO:StudentDAO) {

    def continueWithRegistration(s: Student): Option[Long] = {
        studentDAO.audit(s"Persisted ${s.firstName} ${s.lastName}")
        Some(studentDAO.persist(s))
    }

    def registerStudent(s:Student):Option[Long] = {
        val maybeStudent: Option[Student] = {
            studentDAO.audit(s"Searched ${s.ssn}")
            studentDAO.findBySocialSecurity(s.ssn)
        }
        maybeStudent match {
            case Some(_) => None
            case None => continueWithRegistration(s)
        }
    }
}
