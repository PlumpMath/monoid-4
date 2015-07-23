package io.github.acashjos.monoid;

import java.util.ArrayList;

/**
 * Created by acashjos on 21/7/15.
 */
public class MonoPref {

        private static MonoPref mInstance = null;

        //private String mString;

        public MonoPref(){
           // mString = "Hello";
        }


    public MonoPref put(String key,Object val)
    {
        if(val.getClass().equals(String.class))
        {

        }
        else if(val.getClass().equals(ArrayList.class))
        {

        }
        else;
        return mInstance;
    }
    public MonoPref putString(String key,String val)
    {

        return mInstance;
    }

    public MonoPref putDouble(String key,int val)
    {

        return mInstance;
    }



}
