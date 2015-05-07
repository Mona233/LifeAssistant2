package com.example.lifeassistant.lifeassistant.db.repository;

import android.content.Context;

import com.example.lifeassistant.lifeassistant.db.model.ToDo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDosRepository extends Repository<ToDo>{

    public ToDosRepository(Context ctx) {
        super(ctx, ToDo.class);
    }

    public List<ToDo> getByCategoryId(int categoryId) {
        try {
            return dao.queryForEq("categoryId", categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<ToDo>();
        }
    }
}
