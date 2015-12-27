package com.nedap.workshop.java.webservice;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.nedap.workshop.scala.model.Employee;
import com.nedap.workshop.scala.service.EmployeeService;

/**
 * @author paul.vandenberg
 */
@WebService
public class EmployeeWebservice {

    @Inject
    EmployeeService employeeService;

    @WebMethod
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @WebMethod(operationName = "add")
    public void add(String name) {
        employeeService.save(new Employee(name));
    }

}
