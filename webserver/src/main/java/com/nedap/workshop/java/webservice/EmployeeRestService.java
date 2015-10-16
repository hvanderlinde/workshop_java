package com.nedap.workshop.java.webservice;

import com.nedap.workshop.java.model.Employee;
import com.nedap.workshop.java.service.EmployeeService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author paul.vandenberg
 */
@Path("employees")
public class EmployeeRestService {

    @EJB
    EmployeeService employeeService;

    @Resource
    private ManagedExecutorService executor;

    @GET
    @Produces("application/json")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GET
    @Path("streaming")
    public void streams(@Suspended final AsyncResponse asyncResponse) {
        executor.submit(() -> {
            String employees = employeeService.findAll().stream().map((Employee::getName)).collect(Collectors.joining());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asyncResponse.resume(employees);
        });
    }
}
