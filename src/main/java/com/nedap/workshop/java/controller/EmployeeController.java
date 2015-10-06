package com.nedap.workshop.java.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.nedap.workshop.java.model.Employee;

/**
 * @author paul.vandenberg
 */
@Named(value = "employeeController")
@RequestScoped
public class EmployeeController {
    private List<Employee> employees = new ArrayList<Employee>();

    @PostConstruct
    public void init() {
        employees.addAll(Arrays.asList(new Employee("Henny"), new Employee("Sjaak")));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
