package com.example.deutschea1alla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DbFinishLesson {
    DbFinishHelper myhelper;
    private List bookTitles;

    public DbFinishLesson(Context context)
    {
        myhelper = new DbFinishHelper(context);
    }

    public void updateFineshCursesData(String cursTitle, int i) {
        myhelper.editFinishCurs(cursTitle,i);
    }

    static class DbFinishHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "finish";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String curs = "curs";    //Column II
        private static final String lesson= "lesson";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ curs +" VARCHAR(255) ,"+ lesson +" VARCHAR(225));";
        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DbFinishHelper(Context context) {
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
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        public void editFinishCurs(String curs,int lesson){
            SQLiteDatabase db = this.getWritableDatabase();
            String strSQL = "UPDATE "+TABLE_NAME+" SET lesson = "+lesson+" WHERE curs = '"+curs+"'";
            db.execSQL(strSQL);
        }

    }

    public long addFirstData(String curs)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbFinishLesson.DbFinishHelper.curs, curs);
        contentValues.put(DbFinishLesson.DbFinishHelper.lesson, 1);
        long id = dbb.insert(DbFinishLesson.DbFinishHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public void ubdateFinishLesson(String curs, int lesson)
    {
        myhelper.editFinishCurs(curs,lesson);
    }

    public String getFinishCurs(String curs){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT * FROM "+myhelper.TABLE_NAME+" WHERE curs='"+curs+"'";
        String cursData = "";

        try
        {

            // create Cursor in order to parse our sqlite results
            Cursor cursor = db.rawQuery(Query, null);
            // if Cursor is contains results


            if (cursor != null) {
                // move cursor to first row
                if (cursor.moveToFirst()) {
                    do {
                        // Get version from Cursor
                        cursData = cursor.getString(cursor.getColumnIndex("lesson"));

                        // add the bookName into the bookTitles ArrayList

                        // move to next row
                    } while (cursor.moveToNext());
                }
            }

            //System.out.println(bookTitles);

            //cursData = bookTitles.toString();

            /*
            Cursor c = null;
            //c = db.rawQuery("select name from person where id="+id, null);
            c = db.rawQuery(Query, null);
            //String name = c.getString(c.getColumnIndex(DbFinishLesson.DbFinishHelper.curs));
            c.moveToFirst();
            //name = c.getCount();
            cursData = c.getString(c.getColumnIndex(DbFinishLesson.DbFinishHelper.curs));
            final int valueIndex = cursor.getColumnIndex(COLUMN_VALUE);
            System.out.println(cursData);
            c.close();

             */
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cursData;
    }

    public void emptyTable(String tableName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

    public void createTable()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        myhelper.onCreate(db);
    }

    public int checkTable(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+ DbFinishLesson.DbFinishHelper.TABLE_NAME+"'";
        int name = 0;
        try
        {
            Cursor c = null;
            //c = db.rawQuery("select name from person where id="+id, null);
            c = db.rawQuery(Query, null);
            c.moveToFirst();
            name = c.getCount();
            //System.out.println(name);
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //if(name == 0) {
        //    createTable();
        //}

        //String [][] allData = getDataArrayLesson("1");
        return name;
        //return allData.length;
    }

}