package com.svalero.distrosound.model;

import lombok.Data;

import java.time.LocalDate;

@Data

public class Employee {

    private String name, last_name, email, speciality, username, password;
    private boolean active;
    private LocalDate hiring_date;
    private int id_employee;
    private float comision;

    public Employee(){

    }

}
