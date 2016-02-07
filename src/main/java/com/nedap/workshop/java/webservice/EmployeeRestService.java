package com.nedap.workshop.java.webservice;

import com.nedap.workshop.java.model.Employee;
import com.nedap.workshop.java.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author paul.vandenberg
 */
@Path("employees")
public class EmployeeRestService {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces("application/json")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }
}
