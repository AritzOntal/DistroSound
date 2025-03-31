package com.svalero.distrosound.dao;

import java.sql.Connection;

public class ArtistDao {

    private Connection connection;

    public ArtistDao(Connection connection) {
        this.connection = connection;
    }
}
