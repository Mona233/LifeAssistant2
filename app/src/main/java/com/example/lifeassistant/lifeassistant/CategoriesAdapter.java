package com.example.lifeassistant.lifeassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lifeassistant.lifeassistant.db.model.Category;
import com.example.lifeassistant.lifeassistant.db.repository.CategoriesRepository;

import java.util.ArrayList;

/**
 * Created by Computer on 25.3.2015..
 */
public class CategoriesAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context ctxt;
    ArrayList<Category> categories = new ArrayList<Category>();
    CategoriesRepository categoriesRepository;

    CategoriesAdapter(Context ctxt, int resource, CategoriesRepository categoriesRepository) {
        super();
        this.categoriesRepository = categoriesRepository;
        this.categories.addAll(categoriesRepository.getAll());
        this.ctxt = ctxt;
        this.inflater = LayoutInflater.from(ctxt);
    }

    @Override
    public void notifyDataSetChanged() {
        categories.clear();
        categories.addAll(categoriesRepository.getAll());
        super.notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.list_item_categories, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(categories.get(position).getName());
        return convertView;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addCategory(Category category) {
        categoriesRepository.create(category);
        notifyDataSetChanged();
    }

    public void deleteCategory(int position) {
        categoriesRepository.delete(getItem(position));
        notifyDataSetChanged();
    }
}

