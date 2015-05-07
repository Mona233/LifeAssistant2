package com.example.lifeassistant.lifeassistant;

/**
 * Created by Computer on 27.3.2015..
 */

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lifeassistant.lifeassistant.db.model.ToDo;
import com.example.lifeassistant.lifeassistant.db.repository.ToDosRepository;

import java.util.ArrayList;

public class ToDoItemsAdapter extends ArrayAdapter<ToDo> {

    LayoutInflater inflater;
    int categoryId;
    ToDosRepository toDosRepository;

    public ToDoItemsAdapter(Context context, int textViewResourceId, ToDosRepository toDosRepository, int categoryId) {
        super(context, textViewResourceId, new ArrayList<ToDo>());
        this.categoryId = categoryId;
        this.inflater = LayoutInflater.from(context);
        this.toDosRepository = toDosRepository;
        addAll(toDosRepository.getByCategoryId(categoryId));
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.todoitemview, null);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        ToDo item = this.getItem(position);
        tvName.setText(item.getName());
        if (item.isCompleted()) {
            tvName.setPaintFlags(tvName.getPaintFlags() | (Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            tvName.setPaintFlags(tvName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }

    public void deleteItem(int position) {
        ToDo todo = getItem(position);
        super.remove(todo);
        toDosRepository.delete(todo);
    }

    @Override
    public void add(ToDo item) {
        super.add(item);
        toDosRepository.create(item);
    }

    public void updateIsComplete(int position) {
        ToDo todo = getItem(position);
        todo.setCompleted(!todo.isCompleted());
        toDosRepository.update(todo);
        notifyDataSetChanged();
    }

}
