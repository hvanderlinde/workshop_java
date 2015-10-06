package com.nedap.workshop.java.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.nedap.workshop.java.model.Employee;
import com.nedap.workshop.java.service.EmployeeService;

/**
 * @author paul.vandenberg
 */
@Named(value = "employeeController")
@RequestScoped
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

    @Inject
    Employee employee;

    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void save() {
        employeeService.save(employee);
    }
}
