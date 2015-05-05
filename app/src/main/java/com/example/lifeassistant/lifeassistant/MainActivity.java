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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    CategoriesAdapter categoriesAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView categoriesGridView = (GridView) findViewById(R.id.gridView);

        final ArrayList<String> dummyCategories = new ArrayList<>();
        dummyCategories.add("Shop list");
        dummyCategories.add("Homework");
        dummyCategories.add("Workout");
        dummyCategories.add("Other");
        categoriesAdapter = new CategoriesAdapter(this, android.R.layout.simple_list_item_1, dummyCategories);
        categoriesGridView.setAdapter(categoriesAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
        actionBar.setIcon(R.mipmap.ic_launcher);

        categoriesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ToDoActivity.class);
                Intent i=new Intent(MainActivity.this, ToDoActivity.class);
                i.putExtra("name", dummyCategories.get(position));
                startActivity(i);

                MainActivity.this.startActivity(intent);
            }
        });

        categoriesGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                View promptsView = getLayoutInflater().inflate(R.layout.prompt2, null);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setView(promptsView);


                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                categoriesAdapter.deleteCategory(position);
                            }
                        });
                dialogBuilder.setNegativeButton(R.string.no, null);
                dialogBuilder.create().show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            showAddNewCategoryDialog();
        }
        return true;
    }

    private void showAddNewCategoryDialog() {
        View promptsView = getLayoutInflater().inflate(R.layout.prompt, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setView(promptsView);
        final EditText categoryNameEditText = (EditText) promptsView.findViewById(R.id.etUserInput);
        dialogBuilder.setCancelable(true);
        dialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newCategoryName = categoryNameEditText.getText().toString();
                        if (!newCategoryName.equals("")) {
                            categoriesAdapter.addCategory(newCategoryName);
                        } else
                            Toast.makeText(getApplicationContext(),"Category name must not be empty!", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        dialogBuilder.setNegativeButton(R.string.cancel, null);
        dialogBuilder.create().show();
    }


}
