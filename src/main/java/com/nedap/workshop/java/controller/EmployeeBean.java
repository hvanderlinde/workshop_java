package com.nedap.workshop.java.controller;

import com.nedap.workshop.java.model.Employee;
import com.nedap.workshop.java.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class EmployeeBean {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private Employee employee;

    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    public void save() {
        employeeService.save(employee);
    }

    public Employee getEmployee() {
        return employee;
    }
}
