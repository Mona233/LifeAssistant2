package com.example.lifeassistant.lifeassistant;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ToDo {

    private Boolean finished;
    private String name;
    private Context context;

    public ToDo(Context Context, Boolean Finished, String Name) {
        this.context = Context;
        this.finished = Finished;
        this.name = Name;
    }

    public Boolean getFinished () { return this.finished; }
    public void setFinished (Boolean Value) { this.finished = Value; }
    public String getName () { return this.name; }

    public Drawable getIcon ()
    {
        if (this.finished)
            return this.context.getResources().getDrawable(R.drawable.ic_finished);
        return this.context.getResources().getDrawable(R.drawable.ic_notfinished);
    }
}


