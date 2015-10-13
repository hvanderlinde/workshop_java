package com.nedap.workshop.java.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.nedap.workshop.java.model.Employee;
import com.nedap.workshop.java.service.EmployeeService;

/**
 * @author paul.vandenberg
 */
@Path("employees")
public class EmployeeRestService {

    @EJB
    EmployeeService employeeService;

    @GET
    @Produces("application/json")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }
}
