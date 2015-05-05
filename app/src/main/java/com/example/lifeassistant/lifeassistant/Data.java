package com.example.lifeassistant.lifeassistant;
import com.j256.ormlite.field.DatabaseField;
import java.io.Serializable;

/**
 * Created by Mona on 5.5.2015..
 */
public class Data implements Serializable {
    @DatabaseField(id = true)
    private int id;
}
