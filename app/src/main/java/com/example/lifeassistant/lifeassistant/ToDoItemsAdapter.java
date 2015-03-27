package com.example.lifeassistant.lifeassistant;

/**
 * Created by Computer on 27.3.2015..
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ToDoItemsAdapter extends ArrayAdapter<ToDo>{

    LayoutInflater inflater;

    public ToDoItemsAdapter(Context context, int textViewResourceId,
                            List<ToDo> objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Na temelju parametra position, dohvati element kolekcije kojeg treba prikazati
        ToDo item = this.getItem(position);

        // ako je convertView null, element se prikazuje prvi put
        // u tom slučaju se instancira layout todoitemview pozivom metode inflate nad objektom tipa LayoutInflater
        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.todoitemview, null);

        // dohvaćanje viewova (kontrola) TextView i ImageView iz instanciranog layouta
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        ImageView ivStatus = (ImageView) convertView.findViewById(R.id.ivStatus);

        // postavljanje teksta TextViewa na naziv zadatka
        tvName.setText(item.getName());
        // postavljanje slike ImageViewa na ikonu zadatka
        ivStatus.setImageDrawable(item.getIcon());

        // vraćanje pripremljenog Viewa
        return convertView;
    }

}
