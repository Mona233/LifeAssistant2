package com.example.lifeassistant.lifeassistant.db.repository;
import android.content.Context;

import com.example.lifeassistant.lifeassistant.db.model.ToDos;

public class ToDosRepository extends Repository<ToDos>{

    public ToDosRepository(Context ctx) {
        super(ctx, ToDos.class);
    }

}
