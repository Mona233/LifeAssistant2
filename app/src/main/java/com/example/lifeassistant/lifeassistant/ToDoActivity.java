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

import com.example.lifeassistant.lifeassistant.db.model.ToDo;
import com.example.lifeassistant.lifeassistant.db.repository.ToDosRepository;

public class ToDoActivity extends ActionBarActivity {

    private EditText etName;
    private Button btnAdd;
    private ListView lvMain;
    private ToDoItemsAdapter adapter;
    private int categoryId;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        this.lvMain = (ListView) this.findViewById(R.id.lvItems);
        this.etName = (EditText) this.findViewById(R.id.etName);
        this.btnAdd = (Button) this.findViewById(R.id.btnAdd);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        categoryId = intent.getIntExtra("id", 0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(name);

        this.adapter = new ToDoItemsAdapter(this, R.layout.todoitemview, new ToDosRepository(this), categoryId);
        this.lvMain.setAdapter(this.adapter);

        this.btnAdd.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                ToDo item = new ToDo(etName.getText().toString(), categoryId);
                adapter.add(item);
                etName.setText("");
            }
        });

        this.lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                adapter.updateIsComplete(position);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
