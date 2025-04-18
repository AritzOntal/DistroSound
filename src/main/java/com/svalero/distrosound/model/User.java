package com.svalero.distrosound.model;

import lombok.Data;

import java.time.LocalDate;

@Data

public class User {
    private int id;
    private String IBAN, name, last_name, region, email, DNI, username, password, rol;
    private LocalDate birth_date;
    private boolean distributor;
    private float balance;

    public User(){

    }
}
