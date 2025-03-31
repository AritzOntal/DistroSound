package com.svalero.distrosound.model;

import lombok.Data;

@Data
public class Artist {
    private int id;
    private String name, last_name, username, password, email, role;
}
