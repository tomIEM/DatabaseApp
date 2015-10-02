package com.levy.tom.databaseapp;

import java.sql.Date;

/**
 * Created by util on 02/10/2015.
 */
public class Message {
    // Notez que l'identifiant est un long
    private long id;
    private String content;
    private Integer date_creation;

    public Message(long id, String content, Integer date_creation) {
        super();
        this.id = id;
        this.content = content;
        this.date_creation = date_creation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDate() {
        return date_creation;
    }

    public void setDate(Integer date_creation) {
        this.date_creation = date_creation;
    }


}
