package com.svalero.distrosound.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Artist {
    private int id;
    private String name, last_name, username, password, email, role;

    //TODO AÑADIR A LA BD
    private boolean premium;
    private float royalties;
    private LocalDate birth_date;
}
