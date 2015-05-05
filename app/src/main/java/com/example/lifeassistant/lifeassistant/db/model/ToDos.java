package com.example.lifeassistant.lifeassistant.db.model;

import com.example.lifeassistant.lifeassistant.Data;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import java.util.List;

/**
 * Created by Mona on 5.5.2015..
 */

@DatabaseTable
public class ToDos extends Data {
    private static final long serialVersionUID = -9151576745422286429L;

    @DatabaseField
    private String todo;
    @DatabaseField
    private boolean completed;

    List<ToDoContent> content;

    public String getToDo() {
        return todo;
    }

    private class ToDoContent {
        String name;
    }
}
