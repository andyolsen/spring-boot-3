package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(schema="myschema", name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    private String name;
    private String region;

    @Column(name="salary")
    private double dosh;

    public Employee() {
        // Empty no-arg constructor.
    }

    public Employee(long id, String name, double dosh, String region) {
        this.id = id;
        this.name = name;
        this.dosh = dosh;
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long employeeID) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDosh() {
        return dosh;
    }

    public void setDosh(double dosh) {
        this.dosh = dosh;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Employee) {
            Employee otherEmp = (Employee)other;
            result = (this.id == otherEmp.id);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public String toString() {
        return String.format("Employee [employeeID=%s, name=%s, region=%s, dosh=%s]", id, name, region, dosh);
    }
}
