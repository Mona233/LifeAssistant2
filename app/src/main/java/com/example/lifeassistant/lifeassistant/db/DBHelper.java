package com.example.lifeassistant.lifeassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lifeassistant.lifeassistant.db.model.Data;
import com.example.lifeassistant.lifeassistant.db.model.Category;
import com.example.lifeassistant.lifeassistant.db.model.ToDo;
import com.example.lifeassistant.lifeassistant.db.repository.CategoriesRepository;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "LifeAssistant.db";
    private static final int DATABASE_VERSION = 1;
    private static DBHelper instance = null;
    private HashMap<String, Dao<Data, Integer>> daoMap = new HashMap<String, Dao<Data,Integer>>();
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = OpenHelperManager.getHelper(context, DBHelper.class);
        }
        return instance;
    }

    public static void releaseInstance(DBHelper helper)
    {
        if (instance != null) {
            OpenHelperManager.releaseHelper();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, ToDo.class);
        } catch(SQLException e){
        }
        loadInitialData();
    }

    private void loadInitialData() {
        CategoriesRepository categoriesRepository = new CategoriesRepository(context);
        categoriesRepository.create(new Category("Shop list"));
        categoriesRepository.create(new Category("Homework"));
        categoriesRepository.create(new Category("Workout"));
        categoriesRepository.create(new Category("Other"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    public Dao<Data, Integer> getDao() throws SQLException{
        String key = Data.class.toString();
        if(daoMap.containsKey(key) ){
            Dao<Data, Integer> dao = daoMap.get(key);
            if(dao != null){
                return dao;
            }
        }
        Dao<Data, Integer> newDao = getDao(Data.class);
        daoMap.put(key, newDao);
        return newDao;
    }

    @Override
    public void close() {
        super.close();
        for(String s : daoMap.keySet()){
            daoMap.put(s, null);
        }
    }

}
