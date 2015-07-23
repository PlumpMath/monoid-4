package io.github.acashjos.monoid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.github.acashjos.monoid.model.Properties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;


class database extends SQLiteOpenHelper {

	 private static final int DB_V = 1;


	// Database Name
	    private static final String DB = "Monoid";
	 
	    // Table Names
	    private static final String TAB_PROPERTIES = "properties";
        private static final String TAB_REC = "records";
        private static final String TAB_TYPES = "types";
	 
	     // property column names
		private static final String KEY_ID = "id";
		private static final String KEY_PATH = "path";
		private static final String KEY_PARENT = "parent";
		private static final String KEY_CHILDREN = "children";
		private static final String KEY_DEPTH = "depth";
		private static final String KEY_DEEPER = "is_deeper";
		private static final String KEY_KEY = "key";
		private static final String KEY_VALUE = "value";
		private static final String KEY_NUMERICAL = "numerical";


    //rec fields
        private static final String KEY_TYPE_ID = "";
        private static final String KEY_INDEX = "";

    //types fields
        private static final String KEY_TYPE = "";
        private static final String KEY_COUNT = "";

	    // Table Create Statements
	    // properties table create statement
	    private static final String CREATE_TABLE_PROPERTIES= "CREATE TABLE "
	            + TAB_PROPERTIES + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
				KEY_PATH + " STRING,"+
				KEY_DEPTH + " INTEGER," +
				KEY_PARENT + " INTEGER,"+
				KEY_CHILDREN + " STRING,"+
				KEY_DEEPER + " BOOLEAN,"+
				KEY_KEY + " STRING,"+
				KEY_VALUE + " STRING,"+
				KEY_NUMERICAL + " FLOAT DEFAULT 0 ) ;";


	// records table create statement
	private static final String CREATE_TABLE_RECORDS= "CREATE TABLE "
			+ TAB_REC + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
			KEY_TYPE_ID + " INTEGER,"+
			KEY_INDEX + " INTEGER ) ;";
	// datastore table create statement
	private static final String CREATE_TABLE_TYPES= "CREATE TABLE "
			+ TAB_TYPES + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
			KEY_TYPE+ " STRING UNIQUE,"+
			KEY_COUNT + " INTEGER ) ;";


	public database(Context context) {
        super(context, DB, null, DB_V);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
        db.execSQL(CREATE_TABLE_PROPERTIES);
        db.execSQL(CREATE_TABLE_RECORDS);
        db.execSQL(CREATE_TABLE_TYPES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public long addOrUpdate(Properties data) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = populate(data);

		long tag_id;
		// insert row
		try{
			tag_id= db.insertOrThrow(TAB_PROPERTIES, null, values);
		}
		catch(Exception e)
		{
			tag_id=db.update(TAB_PROPERTIES, values, KEY_ID+"="+data.getId(), null);
		}
		return tag_id;
	}


	public void delete(Properties properties) {
		SQLiteDatabase db = this.getWritableDatabase();

		//ContentValues values = populate(data);
		//need to add logic. if a key is deleted.. all its children need to be dumbed.
		//if it is a root element than all the elements with it as root should be dumped.
		//check if properties.isDeeper . if true do recursive delete.

		db.delete(TAB_PROPERTIES, KEY_ID+" in ("+ properties.getChildren()+")", null);
		db.delete(TAB_PROPERTIES, KEY_ID+"="+ properties.getId(), null);

	}


	public String getDateTime(long backtime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
       if(backtime!=0) date.setTime(date.getTime() - backtime);
        return dateFormat.format(date);
    }


public void closeDB() {
    SQLiteDatabase db = this.getReadableDatabase();
    if (db != null && db.isOpen())
        db.close();
}


	private ContentValues populate(Properties data) {
		ContentValues values = new ContentValues();
		values.put(KEY_KEY, data.getId());
		values.put(KEY_VALUE, data.getValue());
		values.put(KEY_NUMERICAL, data.getNum());
		values.put(KEY_PARENT, data.getParent());
		values.put(KEY_CHILDREN, data.getChildren());
		values.put(KEY_PATH, data.getPath());
		values.put(KEY_DEPTH, data.getDepth());
		values.put(KEY_DEEPER, data.isReference());

		return values;
	}

	/*


	public ArrayList<log> getAllLogDataFor(String uid) {
		ArrayList<log> data = new ArrayList<log>();
	    String selectQuery = "SELECT  * FROM " + TAB_LOG+" WHERE "+KEY_UID+"='"+uid+"'";

	    //Log.e("DEBUG", selectQuery);

	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to Properties
	    if (c.moveToFirst()) {
	        do {
	            log td = new log();
	            td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	            td.setInTime((c.getString(c.getColumnIndex(KEY_IN))));
	            td.setOutTime((c.getString(c.getColumnIndex(KEY_OUT))));
	            td.setUid(c.getString(c.getColumnIndex(KEY_UID)));

	            // adding to todo Properties
	            data.add(td);
	        } while (c.moveToNext());
	    }

	    return data;
	}
	public void clearup(long id) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TAB_LOG, KEY_ID + " = ? AND "+KEY_IN+"< "+getDateTime(30*24*60*60),
	            new String[] { String.valueOf(id) });
	}


	public list getData(String... path) {

		//Log.e("debug",CREATE_TABLE_LIST);
		list data = new list();

		String selectQuery = "SELECT  * FROM " + TAB_LIST+" WHERE "+KEY_UID+"="+uid;
			   // Log.e("DEBUG", selectQuery);

	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);
	    String[] columnNames = c.getColumnNames();
	    for(String f:columnNames)
	    //Log.i("Debug",f);
	   // Log.i("debug","List count: "+c.getCount());
	    if (c != null && c.getCount()>0)
	    {  c.moveToFirst();
	    // looping through all rows and adding to Properties

	           data.setIdn((c.getString(c.getColumnIndex(KEY_IDNTY))));
	            data.setRing((c.getInt(c.getColumnIndex(KEY_RING))));
	            data.setUid(c.getString(c.getColumnIndex(KEY_UID)));
	            data.setMsg(c.getString(c.getColumnIndex(KEY_MSG)));
	            data.setLastSeen(c.getString(c.getColumnIndex(KEY_SEEN)));


	    }

	    return data;
	}
	public ArrayList<list> getFromList(String in) {


		ArrayList<list> data = new ArrayList<list>();
		String selectQuery;
		if(in=="*")
			selectQuery = "SELECT  * FROM " + TAB_LIST;
		else
			selectQuery = "SELECT  * FROM " + TAB_LIST+" WHERE "+KEY_UID+" IN ("+in+")";

	   // Log.e("DEBUG", selectQuery);

	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);

	    if (c!=null &&  c.getCount()>0 ) {c.moveToFirst();
	        do {
	        	list td = new list();
	    // looping through all rows and adding to Properties

	        	td.setIdn((c.getString(c.getColumnIndex(KEY_IDNTY))));
	        	td.setRing((c.getInt(c.getColumnIndex(KEY_RING))));
	        	td.setUid(c.getString(c.getColumnIndex(KEY_UID)));
	            td.setMsg(c.getString(c.getColumnIndex(KEY_MSG)));
	            td.setLastSeen(c.getString(c.getColumnIndex(KEY_SEEN)));
	        	data.add(td);
	        } while (c.moveToNext());
	  	    }

	 	    return data;
	}

	public void setLastseen(String uid, String out) {
		// adds last seen time to Properties table
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SEEN, out);

		//long tag_id;


		try{
			//tag_id=
			db.update(TAB_LIST, values, KEY_UID+"="+uid, null);
		}
		catch(Exception e)
		{
		}
		// return tag_id;
	}

	 */
}