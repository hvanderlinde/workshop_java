package com.nedap.workshop.java.controller;

import com.nedap.workshop.java.model.Employee;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class EmployeeBean {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    @PostConstruct
    public void init() {
        employees.addAll(Arrays.asList(new Employee("Henny"), new Employee("Sjaak")));
    }
}
