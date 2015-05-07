package com.example.lifeassistant.lifeassistant.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ToDo extends Data {

    private static final long serialVersionUID = -9151576745422286429L;

    @DatabaseField
    private String name;
    @DatabaseField
    private boolean completed;
    @DatabaseField
    private int categoryId;

    public String getName() {
        return name;
    }

    public ToDo(String name, int categoryId) {
        this.name = name;
        this.completed = false;
        this.categoryId = categoryId;
    }

    public ToDo() {
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
