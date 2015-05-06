package com.example.lifeassistant.lifeassistant;

/**
 * Created by Computer on 27.3.2015..
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ToDoActivity extends ActionBarActivity {

    private EditText etName;
    private Button btnAdd;
    private ListView lvMain;
    private ToDoItemsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        this.lvMain = (ListView) this.findViewById(R.id.lvItems);
        this.etName =(EditText) this.findViewById(R.id.etName);
        this.btnAdd =(Button) this.findViewById(R.id.btnAdd);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(name);

        // instanciranje fielda adapter
        // 1. kao parametar context njegovog konstruktora, šalje se trenutni objekt (this)
        // 2. kao parametar textViewResourceId njegovog konstruktora, šalje se referenca na
        //  layout todoitemview
        // 3. kao parametar objects njegovog konstruktora, šalje se novi objekt tipa
        //  TodoItems, u koji će se spremati zadaci
        this.adapter = new ToDoItemsAdapter(this, R.layout.todoitemview, new ToDoItems());

        // field adapter postavi kao adapter objekta lvMain
        this.lvMain.setAdapter(this.adapter);

        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);

        // OnClickListener je klasa koja se postavlja kako bi se obradio događaj klika na gumb
        // Sam događaj se obrađuje u njezinoj metodi onClick
        this.btnAdd.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // kreiranje novog objekta tipa TodoItem
                // 1. kao parametar Context njegovog konstruktora, šalje se context dobiven metodom getApplicationContext
                // 2. kao parametar Finished njegovog konstruktora, šalje se false (zadatak nije dovršen)
                // 3. kao parametar Name njegovog konstruktora, šalje se tekst iz etName
                ToDo item = new ToDo (getApplicationContext(), false, etName.getText().toString());
                // dodavanje zadatka u adapter (koji ga zapravo dodaje u kolekciju dobivenu preko konstruktora
                adapter.add(item);
                // brisanje teksta u Viewu etName
                etName.setText("");
            }
        });

        // OnItemClickListener je klasa koja se postavlja kako bi se obradio događaj klika na pojedini redak u lvMain
        // Sam događaj se obrađuje u njezinoj metodi onItemClick
        this.lvMain.setOnItemClickListener(new OnItemClickListener() {
            // prvi parametar (arg0) je adapter korišten za prikaz podataka
            // drugi parametar (arg1) je kliknuti View
            // treći parametar (arg2) je indeks kliknutog elementa
            // četvrti parametar (arg3) je identifikator kliknutog retka
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // dobivanje kliknutog zadatka
                ToDo item = (ToDo) arg0.getItemAtPosition(arg2);

                // promjena statusa zadatka
                item.setFinished(!item.getFinished());

                // obavještavanje adaptera o promjeni podataka
                // rezultira ponovnim prikazom zadataka unutar kolekcije
                adapter.notifyDataSetChanged();
            }

        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                View promptsView = getLayoutInflater().inflate(R.layout.prompt2, null);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ToDoActivity.this);
                dialogBuilder.setView(promptsView);


                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                adapter.deleteItem(position);
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
        getMenuInflater().inflate(R.menu.action_bar2, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}