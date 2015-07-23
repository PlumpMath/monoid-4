package io.github.acashjos.monoid;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by acashjos on 21/7/15.
 */
public class Monoid {


        private static Monoid mInstance = null;
    private database db;
    private ArrayList classlist;

    private Monoid(Context context) {
        db=new database(context);
        //get list of classnames available.
        //Arraylist<hashmap>

    }

    public static Monoid getInstance(Context context){
            if(mInstance == null)
            {
                mInstance = new Monoid(context);
            }
            return mInstance;
        }
    public MonoObject createObject(String classname){
        //db insert classname if not already exist
        return new MonoObject(0,this);
    }

    public String getTypeName(int type) {
        return null;
    }
}


