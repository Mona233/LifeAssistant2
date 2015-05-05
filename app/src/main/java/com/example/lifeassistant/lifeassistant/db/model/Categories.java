package com.example.lifeassistant.lifeassistant.db.model;

        import com.example.lifeassistant.lifeassistant.Data;
        import com.j256.ormlite.table.DatabaseTable;
        import com.j256.ormlite.field.DatabaseField;
        import java.util.List;

/**
 * Created by Mona on 5.5.2015..
 */

@DatabaseTable
public class Categories extends Data {
    private static final long serialVersionUID = -9151576745422286429L;

    @DatabaseField
    private String name;
    @DatabaseField
    private int todoID;

    List<CategoryContent> content;

    public String getCategory() {
        return name;
    }

    private class CategoryContent {
        String name;
    }
}
