package com.svalero.distrosound.model;

import lombok.Data;

import java.time.LocalDate;

@Data

public class Client {
    private int id_client;
    private String IBAN, name, last_name, legion, email, DNI;
    private LocalDate birth_date;
    private boolean premium;
    private float balance;

    public Client(){

    }
}
