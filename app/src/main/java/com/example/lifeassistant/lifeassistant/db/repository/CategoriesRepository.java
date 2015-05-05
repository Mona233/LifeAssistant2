package com.example.lifeassistant.lifeassistant.db.repository;
import com.example.lifeassistant.lifeassistant.db.model.Categories;
import android.content.Context;
/**
 * Created by Mona on 5.5.2015..
 */
public class CategoriesRepository extends Repository<Categories> {
    public CategoriesRepository(Context ctx){
        super(ctx, Categories.class);
    }
}
