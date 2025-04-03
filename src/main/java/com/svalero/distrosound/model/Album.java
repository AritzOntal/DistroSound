package com.svalero.distrosound.model;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Album {

    private int id_album, id_artist, id_employee;
    private String url_cover, ISRC, title, genre, artist, description;
    private Boolean explicit;
    private Date release_date;
    private LocalDate uploaded;
    private float duration, price;


    public Album() {

    }

}


