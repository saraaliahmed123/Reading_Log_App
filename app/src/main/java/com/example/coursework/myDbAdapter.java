package com.example.coursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }
    public long insertData(String booktitle, String[] read, String dateRead, String comment, String parents)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TITLE, booktitle);
        contentValues.put(myDbHelper.PAGES, (read[0]+"-"+read[1]));
        contentValues.put(myDbHelper.DATEREAD, dateRead);
        contentValues.put(myDbHelper.COMMENT, comment);
        contentValues.put(myDbHelper.PARENT, parents);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList<String[]> getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.TITLE,myDbHelper.PAGES,myDbHelper.DATEREAD,myDbHelper.COMMENT,myDbHelper.PARENT};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
       // StringBuffer buffer= new StringBuffer();
        ArrayList<String[]> list = new ArrayList<String[]>();
        while (cursor.moveToNext())
        {
            String cid =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            //booktitle, read, dateRead, comment, parents
            String booktitle =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TITLE));
            String read =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PAGES));
            String dateRead =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.DATEREAD));
            String comment =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.COMMENT));
            String parents =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT));
           // buffer.append(cid+ "   " + name + "   " + platform +" \n");
            String[] item = {cid, booktitle, read, dateRead, comment, parents};
            list.add(item);
        }
       // return buffer.toString();
        return list;
    }

    public int delete(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={id};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.UID+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String id, String booktitle, String[] read, String dateRead, String comment, String parents)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TITLE, booktitle);
        contentValues.put(myDbHelper.PAGES, (read[0]+"-"+read[1]));
        contentValues.put(myDbHelper.DATEREAD, dateRead);
        contentValues.put(myDbHelper.COMMENT, comment);
        contentValues.put(myDbHelper.PARENT, parents);
        String[] whereArgs= {id};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.UID+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        //booktitle, read, dateRead, comment, parents
        private static final String TITLE = "Name";    //Column II
        private static final String PAGES= "Pages";    // Column III
        private static final String DATEREAD= "DateRead";    // Column III
        private static final String COMMENT= "Comment";    // Column III
        private static final String PARENT= "Parent";    // Column III

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE+" VARCHAR(255) ,"+ PAGES+" VARCHAR(225) ," +DATEREAD+" VARCHAR(255) ,"+ COMMENT+" VARCHAR(225) , "+ PARENT+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}

