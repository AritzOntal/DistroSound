package com.svalero.distrosound.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Album {

    private int id_album, id_client, id_employee;
    private String url_cover, ISRC, title, genre, artist;
    private boolean explicit;
    private Date release_date;
    private float duration;


    public Album(){

    }

}


