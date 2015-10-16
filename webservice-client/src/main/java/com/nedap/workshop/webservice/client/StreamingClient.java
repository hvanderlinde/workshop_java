package com.nedap.workshop.webservice.client;

import com.nedap.workshop.java.webservice.EmployeeWebservice;
import com.nedap.workshop.java.webservice.EmployeeWebserviceService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author paul.vandenberg
 */
public class StreamingClient {

    private static Client client;
    private static String request;

    public static void main(String... args) throws IOException {
        EmployeeWebservice employeeWebservice = new EmployeeWebserviceService().getEmployeeWebservicePort();
        IntStream.range(0, 1000).forEach(i -> employeeWebservice.add("emp " + i));

        client = ClientBuilder.newClient();
        request = "http://localhost:8080/course/rest/employees/streaming";

        Future<String> stringFuture = client.target(request)
                .request(MediaType.APPLICATION_OCTET_STREAM)
                .async()
                .get(new InvocationCallback<String>() {
                    @Override
                    public void completed(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.err.println(throwable);
                    }
                });

        System.out.println("doing something else in the meantime");
    }
}
