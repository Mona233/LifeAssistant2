package com.example.lifeassistant.lifeassistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    CategoriesAdapter categoriesAdapter;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        GridView categoriesGridView = (GridView) findViewById(R.id.gridView);

        ArrayList<String> dummyCategories = new ArrayList<>();
        dummyCategories.add("Shop list");
        dummyCategories.add("Homework");
        dummyCategories.add("Workout");
        dummyCategories.add("Other");
        categoriesAdapter = new CategoriesAdapter(this, android.R.layout.simple_list_item_1, dummyCategories);
        categoriesGridView.setAdapter(categoriesAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_launcher);
        categoriesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ToDoActivity.class);
                MainActivity.this.startActivity(intent);

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.prompt, null);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Add category");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                        builder1.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add) {
            EditText categoryNameEditText = (EditText) findViewById(R.id.etCategories);
            categoriesAdapter.addCategory(categoryNameEditText.getText().toString());
        }
        return true;
    }

}


