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

public class DetailActivity extends AppCompatActivity {

    Long id = null;
    TextView label_content;
    EditText content;
    MessageDAO db= new MessageDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        label_content = (TextView) findViewById(R.id.label_content);
        content = (EditText) findViewById(R.id.content);

        Intent intent=this.getIntent();
        id = intent.getLongExtra("key", 0);

        Log.d("DetailActivity", String.valueOf(id));

        if(id>0){
        //Update
            toolbar.setTitle("Message " + id);

            Message m = null;
            db.open();
            m = db.get(id);
            db.close();
            content.setText(m.getContent());

        }else{
        //Insert
            toolbar.setTitle("Nouveau message");
        }

        setSupportActionBar(toolbar);

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
                    Message m = new Message(0,String.valueOf(content.getText()),12);
                    db.open();
                    db.ajouter(m);
                    db.close();
                    finish();
                }
            }
        });
    }

}
