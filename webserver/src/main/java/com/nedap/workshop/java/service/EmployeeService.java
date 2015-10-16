package com.nedap.workshop.java.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nedap.workshop.java.model.Employee;

/**
 * @author paul.vandenberg
 */
@Stateless
public class EmployeeService {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("select e from Employee e").getResultList();
    }

    public void findAllStream(OutputStream outputStream) {
        List<Employee> employees = findAll();
        JsonGenerator jsonGenerator = Json.createGenerator(outputStream);
        jsonGenerator.writeStartObject();
        int i=0;
        for (Employee employee : employees) {
            jsonGenerator.write("name", employee.getName());
            if (i % 100 == 0) {
                jsonGenerator.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

        jsonGenerator.writeEnd();
        jsonGenerator.close();
    }
}
