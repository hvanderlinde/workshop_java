package com.nedap.workshop.scala.controller

import javax.enterprise.context.RequestScoped
import javax.inject.{Inject, Named}

import com.nedap.workshop.scala.model.Employee
import com.nedap.workshop.scala.service.EmployeeService

import scala.beans.BeanProperty

/**
  * @author paul.vandenberg
  */
@Named(value = "employeeController")
@RequestScoped
class EmployeeController {
  @Inject
  var employeeService: EmployeeService = _

  @Inject
  @BeanProperty
  var employee: Employee = _

  def getEmployees: java.util.List[Employee] = {
    employeeService.findAll
  }

  def save() {
    employeeService.save(employee)
  }
}
