package com.example.lifeassistant.lifeassistant.db.model;

        import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Category extends Data {
    private static final long serialVersionUID = -9151576745422286429L;

    @DatabaseField
    private String name;

    public String getName() {
        return name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {}

}
