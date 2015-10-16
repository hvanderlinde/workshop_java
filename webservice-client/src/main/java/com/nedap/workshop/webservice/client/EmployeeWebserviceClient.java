package com.nedap.workshop.webservice.client;

import com.nedap.workshop.java.webservice.Employee;
import com.nedap.workshop.java.webservice.EmployeeWebservice;
import com.nedap.workshop.java.webservice.EmployeeWebserviceService;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author paul.vandenberg
 */
public class EmployeeWebserviceClient {
    public static void main(String... args) {
        createEmployees();
        printEmployees();
    }

    private static void printEmployees() {
        EmployeeWebservice employeeWebservice = new EmployeeWebserviceService().getEmployeeWebservicePort();
        employeeWebservice.getAll().stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    private static void createEmployees() {
        EmployeeWebservice employeeWebservice = new EmployeeWebserviceService().getEmployeeWebservicePort();
        IntStream.range(0, 1000).forEach(i -> employeeWebservice.add("emp " + i));
    }
}
