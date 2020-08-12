package com.mojica.springboot.webservice;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="idseq", initialValue=1)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int employeeId;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    public Employee(){}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
            this.address = address;
        }
}
