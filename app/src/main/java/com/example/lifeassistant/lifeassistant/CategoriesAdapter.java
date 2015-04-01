package com.example.lifeassistant.lifeassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Computer on 25.3.2015..
 */
public class CategoriesAdapter extends BaseAdapter {
    LayoutInflater inflater;

    Context ctxt;
    ArrayList<String> categories = new ArrayList<String>();

    CategoriesAdapter(Context ctxt, int resource, ArrayList<String> categories) {
        super();
        this.categories.addAll(categories);
        this.ctxt = ctxt;
        this.inflater = LayoutInflater.from(ctxt);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.list_item_categories, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(categories.get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addCategory(String category) {
        categories.add(category);
        notifyDataSetChanged();
    }
public  void deleteCategory(int category){
    categories.remove(category);
    notifyDataSetChanged();
}
}

