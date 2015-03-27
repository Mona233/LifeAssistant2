package com.example.lifeassistant.lifeassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class MainActivity extends Activity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        GridView g = (GridView) findViewById(R.id.gridView);
        g.setAdapter(new CategoriesAdapter(this, android.R.layout.simple_list_item_1, CategoriesAdapter.Categories));
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ToDo.class);
               MainActivity.this.startActivity(intent);



            }
        });
    }

}