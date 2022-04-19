package com.example.deutschea1alla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;


public class myDbAdapter {
    myDbHelper myhelper;

    private ArrayList<CurrentNode> Currentnodes;

    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String de, String ru, int lesson)
    {
        //SQLiteDatabase dbb = myhelper.getWritableDatabase();
        //ContentValues contentValues = new ContentValues();
        //contentValues.put(myDbHelper.DE, de);
        //contentValues.put(myDbHelper.RU, ru);
        //contentValues.put(myDbHelper.Lessons, lesson);
        //long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return 1;
    }

    public long addLessonData(String de, String ru, int lesson)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DE, de);
        contentValues.put(myDbHelper.RU, ru);
        contentValues.put(myDbHelper.Lessons, lesson);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.DE,myDbHelper.RU,myDbHelper.Lessons};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.DE));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.RU));
            int  lesson =cursor.getInt(cursor.getColumnIndex(myDbHelper.Lessons));
            buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");
        }
        return buffer.toString();
    }

    public ArrayList getDataArray() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.DE, myDbHelper.RU, myDbHelper.Lessons};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData;
        //ArrayList<CurrentNode> Currentnodes = null;
        Currentnodes = new ArrayList<CurrentNode>();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.DE));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.RU));
            int lesson = cursor.getInt(cursor.getColumnIndex(myDbHelper.Lessons));
            String[] onedate = {cid + "", name, password, lesson + ""};

            //Currentnodes = new ArrayList<CurrentNode>();
            Currentnodes.add(new CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");

        }
        return Currentnodes;
    }

    public String[][] getDataArrayLesson(String Lesson) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.DE, myDbHelper.RU, myDbHelper.Lessons};
        // Filter results WHERE "title" = 'My Title'
        String selection = myDbHelper.Lessons + " = ?";
        String[] selectionArgs = { Lesson };
        Cursor cursor =  db.query(myDbHelper.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData = new String[25][];
        int i = 0;
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.DE));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.RU));
            int lesson = cursor.getInt(cursor.getColumnIndex(myDbHelper.Lessons));
            String[] onedate = {cid + "", name, password, lesson + ""};
            allData[i] = onedate;
            //Currentnodes = new ArrayList<CurrentNode>();
            //Currentnodes.add(new CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");
            i++;
        }
        return allData;
    }



    public  int delete(String uname)
    {
        //SQLiteDatabase db = myhelper.getWritableDatabase();
        //String[] whereArgs ={uname};

        //int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.DE+" = ?",whereArgs);
        return  1;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DE,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.DE+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "words";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String DE = "DE";    //Column II
        private static final String RU= "RU";    // Column III
        private static final String Lessons= "lesson";    // Column 4
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ DE +" VARCHAR(255) ,"+ RU +" VARCHAR(225), "+ Lessons +" INTEGER);";
        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
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
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
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

    private class CurrentNode {

        private String [] nodeData;

        public CurrentNode(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < 4; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    public void addData() {

        String [][] allCurrentLesson = {

                {"der Abend","вечер"},
                {"der Abendkurs","вечерний курс"},
                {"abends","вечерами"},
                {"aber","но"},
                {"der Abschied","прощание"},
                {"der Abschnitt","отрезок, частъ, раздел"},
                {"acht","восемь"},
                {"der Actionfilm","боевик"},
                {"die Adresse","адрес"},
                {"afrikanisch","африканский"},
                {"der Akkusativ","Винит.падеж"},
                {"die Aktivität","занятние, активность"},
                {"alle","все"},
                {"alleine","сам, один"},
                {"alt","старый"},
                {"das Alter","возраст"},
                {"am Abend","вечером"},
                {"am Dienstag","во вторник"},
                {"am Mittag","днем"},
                {"am Montag","в понедельник"},
                {"am Morgen","утром"},
                {"am Nachmittag","в послеобеденное время"},
                {"am Vormittag","в дообеденное время"},
                {"andere","другие"},
                {"anfangen","начинать"}};

        for (int i = 0; i < allCurrentLesson.length; i++) {
           addLessonData(allCurrentLesson[i][0],allCurrentLesson[i][1],1);
        }

        String [][] allCurrentLesson2 = {  {"die Angabe","сведения, показания"},
                {"ankreuzen","отметить крестиком"},
                {"die Anmeldung","регистрация, заявление, заявка"},
                {"der Anruf","оклик, звонок"},
                {"anrufen","окликатъ, звонить"},
                {"die Ansage","объявление, оповещение"},
                {"ansehen","(по)смотреть"},
                {"die Antwort","ответ"},
                {"antworten","отвечать"},
                {"die Anzeige","объявление, сообщение"},
                {"das Apartment","комфортабелъная квартирка"},
                {"der Apfel","яблоко"},
                {"der Apfelkuchen","яблочный пирог"},
                {"der Apfelsaft","яблочный сок"},
                {"das Arabisch","арабский"},
                {"arbeiten","работать"},
                {"das Arbeitsamt","биржа труда"},
                {"der Artikel","артикль"},
                {"der Arzt","врач"},
                {"die Arztpraxis","врачебная практика"},
                {"der Atlas","атлас"},
                {"auch","тоже"},
                {"auf Deutsch","по-немецки"},
                {"auf Wiederhören","До свидания (говорят по телефону)"},
                {"auf Wiedersehen","До свидания"}};


        for (int i2 = 0; i2 < allCurrentLesson2.length; i2++) {
            addLessonData(allCurrentLesson2[i2][0],allCurrentLesson2[i2][1],2);
        }

    }

    public int checkTable(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+myhelper.TABLE_NAME+"'";
        int name = 0;
        try
        {
            Cursor c = null;
            //c = db.rawQuery("select name from person where id="+id, null);
            c = db.rawQuery(Query, null);
            c.moveToFirst();
            name = c.getCount();
            System.out.println(name);
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