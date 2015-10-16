package com.nedap.workshop.java.service;

import com.nedap.workshop.java.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
}
