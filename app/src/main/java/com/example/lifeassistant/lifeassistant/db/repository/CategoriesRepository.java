package com.example.lifeassistant.lifeassistant.db.repository;
import android.content.Context;


import com.example.lifeassistant.lifeassistant.db.model.Categories;

public class CategoriesRepository extends Repository<Categories>{

    public CategoriesRepository(Context ctx) {
        super(ctx, Categories.class);
    }

}
