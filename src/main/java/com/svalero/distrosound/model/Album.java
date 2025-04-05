package com.svalero.distrosound.model;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Album {

    private int id_album, id_artist, id_employee;
    private String ISRC, title, genre, artist, description;
    private String image;
    private Boolean explicit;
    private Date release_date;
    private String uploaded;
    private float duration, price;


    public Album() {

    }

}


