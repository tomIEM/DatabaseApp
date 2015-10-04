package com.levy.tom.databaseapp;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by util on 02/10/2015.
 */
public class Message {
    // Notez que l'identifiant est un long
    private long id;
    private String content;
    private Long date_creation;

    public Message(long id, String content, Long date_creation) {
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

    public Long getTimestamp() {
        return date_creation;
    }

    public String getDate() {

        SimpleDateFormat df = new SimpleDateFormat("EEEE d MMMM yyyy",Locale.FRANCE);

        return df.format(date_creation);
    }

    public void setDate(Long date_creation) {
        this.date_creation = date_creation;
    }


}
