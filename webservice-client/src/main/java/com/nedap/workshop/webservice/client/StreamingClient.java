package com.nedap.workshop.webservice.client;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author paul.vandenberg
 */
public class StreamingClient {

    public static void main(String... args) throws IOException {

        try (InputStream is = ResteasyClientBuilder.newBuilder()
                .build().target("http://localhost:8080/course/rest").path("/employees/streaming")
                .request().get().readEntity(InputStream.class);
             BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            IOUtils.readLines(r).forEach(System.out::println);
        }
    }
}
