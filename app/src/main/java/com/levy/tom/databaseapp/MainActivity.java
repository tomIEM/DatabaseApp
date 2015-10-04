package com.levy.tom.databaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Message> liste = null;
    MessageDAO db= new MessageDAO(this);
    TextView txtHeader;

    @Override
    protected void onStart() {
        super.onStart();
        listView = (ListView) findViewById(R.id.listView);

        db.open();
        liste = db.getAll();
        db.close();

        txtHeader = (TextView) findViewById(R.id.txtHeader);
        txtHeader.setText("Vos "+liste.size()+" messages");


        final MyAdapter adapter = new MyAdapter(this,
                R.layout.listview_item_row, liste);



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Message item = adapter.getItem(position-1);
                Log.d("MainActivity", String.valueOf(item.getId()));
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("key", item.getId());
                startActivityForResult(intent, 2);

            }


        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);

        db.open();
        liste = db.getAll();
        db.close();

        final MyAdapter adapter = new MyAdapter(this,
                R.layout.listview_item_row, liste);


        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView.addHeaderView(header);


        /*listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message item = adapter.getItem(position);
                Log.d("MainActivity",String.valueOf(item.getId()));
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("key", item.getId());
                startActivity(intent);

            }
        });
*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, 1);

               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == 1) {
                Toast.makeText(getApplicationContext(), "Votre message a bien été ajouté.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 2) {

            if (resultCode == 1) {
                Toast.makeText(getApplicationContext(), "Votre message a bien été mis a jour.",
                        Toast.LENGTH_SHORT).show();
            }
            if (resultCode == 2) {
                Toast.makeText(getApplicationContext(), "Votre message a bien été supprimé.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
