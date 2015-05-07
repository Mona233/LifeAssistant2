package com.example.lifeassistant.lifeassistant.db.repository;
import android.content.Context;


import com.example.lifeassistant.lifeassistant.db.model.Category;

public class CategoriesRepository extends Repository<Category>{

    public CategoriesRepository(Context ctx) {
        super(ctx, Category.class);
    }

}
