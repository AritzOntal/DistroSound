package com.svalero.distrosound.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Album {

    private int id_album, id_client, id_employee;
    private String url_cover, ISRC, title, genre;
    private boolean explicit;
    private LocalDate release_date;
    private float duration;


    public Album(){

    }

}


