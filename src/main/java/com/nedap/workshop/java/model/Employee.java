package com.nedap.workshop.java.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author paul.vandenberg
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    private String name;

    public Employee() {}

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }
}