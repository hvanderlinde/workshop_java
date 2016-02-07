package com.nedap.workshop.java.webservice;

import com.nedap.workshop.java.model.Employee;
import com.nedap.workshop.java.service.EmployeeService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * @author paul.vandenberg
 */
@WebService
public class EmployeeSoapService {

    @Inject
    private EmployeeService employeeService;

    @WebMethod
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @WebMethod(operationName = "add")
    public void add(String name) {
        employeeService.save(new Employee(name));
    }
}
