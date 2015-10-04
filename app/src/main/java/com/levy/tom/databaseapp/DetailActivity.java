package com.levy.tom.databaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Timestamp;

public class DetailActivity extends AppCompatActivity {

    Long id = null;
    TextView label_content;
    TextView date;
    EditText content;
    MessageDAO db= new MessageDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        label_content = (TextView) findViewById(R.id.label_content);
        content = (EditText) findViewById(R.id.content);
        date = (TextView) findViewById(R.id.date);

        Intent intent=this.getIntent();
        id = intent.getLongExtra("key", 0);

        //Log.d("DetailActivity", String.valueOf(id));

        if(id>0){
        //Update
            toolbar.setTitle("Message " + id);

            Message m = null;
            db.open();
            m = db.get(id);
            db.close();
            content.setText(m.getContent());
            date.setText("Message envoyé " + m.getDate());

            toolbar.setNavigationIcon(android.R.drawable.ic_menu_delete);
            toolbar.setNavigationContentDescription("Supprimer");



        }else{
        //Insert
            toolbar.setTitle("Nouveau message");
        }


        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DetailActivity", String.valueOf(id));
                db.open();
                db.supprimer(id);
                db.close();
                finish();
            }

        });
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(id>0) {

                    Message m = null;
                    db.open();
                    m = db.get(id);
                    m.setContent(String.valueOf(content.getText()));
                    db.modifier(m);
                    db.close();
                    finish();

                }else{
                    java.util.Date date= new java.util.Date();
                    long date_creation = date.getTime();

                    Message m = new Message(0,String.valueOf(content.getText()),date_creation);
                    db.open();
                    db.ajouter(m);
                    db.close();
                    finish();
                }
            }
        });
    }

}
