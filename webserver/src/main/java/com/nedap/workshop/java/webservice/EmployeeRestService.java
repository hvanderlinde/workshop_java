package com.nedap.workshop.java.webservice;

import com.nedap.workshop.scala.model.Employee;
import com.nedap.workshop.scala.service.EmployeeService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.util.List;

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

    @GET
    @Path("streaming")
    public Response streams() {
        StreamingOutput streamingOutput = outputStream -> employeeService.findAllStream(outputStream);
        return Response.ok(streamingOutput).build();
    }
}
