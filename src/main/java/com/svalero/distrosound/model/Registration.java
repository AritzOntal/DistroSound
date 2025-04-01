package com.svalero.distrosound.model;

import lombok.Data;

import java.time.LocalDate;

@Data

public class Registration {
    private int id;
    private Integer id_employee, id_artist;
    private String tipe;
    private float cost;
    private LocalDate regist_date;
    private boolean premium;

    public Registration(){

    }
}
