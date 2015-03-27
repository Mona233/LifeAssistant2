package com.example.lifeassistant.lifeassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by Computer on 25.3.2015..
 */
public class CategoriesAdapter extends BaseAdapter {
    LayoutInflater inflater;

    Context ctxt;

    CategoriesAdapter(Context ctxt, int resource, String[] Categories) {
        super();

        this.ctxt = ctxt;
        this.inflater = LayoutInflater.from(ctxt);


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.list_item_categories, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);

        textView.setText(Categories[position]);
        return convertView;
    }

    @Override
    public int getCount() {
        return Categories.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static final String[] Categories = new String[]{"Shop list", "Homework", "Workout", "Other"};


}

