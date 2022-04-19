package com.example.deutschea1alla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class Db200Adapter {
    myDbHelper200 myhelper;

    private ArrayList<Db200Adapter.CurrentNode> Currentnodes;

    public Db200Adapter(Context context)
    {
        myhelper = new myDbHelper200(context);
    }

    static class myDbHelper200 extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "words200";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String DE = "DE";    //Column II
        private static final String RU= "RU";    // Column III
        private static final String Lessons= "lesson";    // Column 4
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ DE +" VARCHAR(255) ,"+ RU +" VARCHAR(225), "+ Lessons +" INTEGER);";
        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper200(Context context) {
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

    public long addLessonData(String de, String ru, int lesson)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Db200Adapter.myDbHelper200.DE, de);
        contentValues.put(Db200Adapter.myDbHelper200.RU, ru);
        contentValues.put(Db200Adapter.myDbHelper200.Lessons, lesson);
        long id = dbb.insert(Db200Adapter.myDbHelper200.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList getDataArray() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db200Adapter.myDbHelper200.UID, Db200Adapter.myDbHelper200.DE, Db200Adapter.myDbHelper200.RU, Db200Adapter.myDbHelper200.Lessons};
        Cursor cursor = db.query(Db200Adapter.myDbHelper200.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData;
        //ArrayList<CurrentNode> Currentnodes = null;
        Currentnodes = new ArrayList<Db200Adapter.CurrentNode>();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Db200Adapter.myDbHelper200.UID));
            String name = cursor.getString(cursor.getColumnIndex(Db200Adapter.myDbHelper200.DE));
            String password = cursor.getString(cursor.getColumnIndex(Db200Adapter.myDbHelper200.RU));
            int lesson = cursor.getInt(cursor.getColumnIndex(Db200Adapter.myDbHelper200.Lessons));
            String[] onedate = {cid + "", name, password, lesson + ""};

            //Currentnodes = new ArrayList<CurrentNode>();
            Currentnodes.add(new Db200Adapter.CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");

        }
        return Currentnodes;
    }

    public String[][] getDataArrayLesson(String Lesson) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db200Adapter.myDbHelper200.UID, Db200Adapter.myDbHelper200.DE, Db200Adapter.myDbHelper200.RU, Db200Adapter.myDbHelper200.Lessons};
        // Filter results WHERE "title" = 'My Title'
        String selection = Db200Adapter.myDbHelper200.Lessons + " = ?";
        String[] selectionArgs = { Lesson };
        Cursor cursor =  db.query(Db200Adapter.myDbHelper200.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData = new String[25][];
        int i = 0;
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Db200Adapter.myDbHelper200.UID));
            String name = cursor.getString(cursor.getColumnIndex(Db200Adapter.myDbHelper200.DE));
            String password = cursor.getString(cursor.getColumnIndex(Db200Adapter.myDbHelper200.RU));
            int lesson = cursor.getInt(cursor.getColumnIndex(Db200Adapter.myDbHelper200.Lessons));
            String[] onedate = {cid + "", name, password, lesson + ""};
            allData[i] = onedate;
            //Currentnodes = new ArrayList<CurrentNode>();
            //Currentnodes.add(new CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            i++;
        }
        return allData;
        //return buffer;
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
                {"das Leben","жизнь"},
                {"lesen","читать"},
                {"machen","делать"},
                {"denken","думать"},
                {"reisen","путешествовать"},
                {"spielen","играть"},
                {"warten","ждать, дожидаться"},
                {"werden","становиться, делаться"},
                {"wohnen","жить"},
                {"das Alter","возраст"},
                {"wollen","хотеть"},
                {"die Eltern","родители"},
                {"die Familie","семья"},
                {"die Frau","женщина, жена"},
                {"das Kind","ребенок"},
                {"das Jahr","год"},
                {"der Tag","день"},
                {"der Monat","месяц"},
                {"ich","я"},
                {"die Welt","мир"},
                {"weiter","дальше"},
                {"alles","всё"},
                {"weit","далеко"},
                {"bald","скоро"},
                {"die Seite","сторона, страница"}
        };

        for (int i = 0; i < allCurrentLesson.length; i++) {
            addLessonData(allCurrentLesson[i][0],allCurrentLesson[i][1],1);
        }


        String [][] allCurrentLesson1 = {
                {"durch","через, сквозь"},
                {"die Zahl","число"},
                {"alt","старый"},
                {"die Arbeit","труд, работа"},
                {"frei","свободный, независимый"},
                {"immer","всегда"},
                {"klein","маленький"},
                {"du","ты"},
                {"neu","новый, недавний"},
                {"verheiratet","женатый, замужняя"},
                {"verwitwet","овдовевший"},
                {"da","тут, там, здесь"},
                {"erst","сперва, только"},
                {"etwa","приблизительно"},
                {"etwas","немного"},
                {"hier","здесь"},
                {"jetzt","теперь, сейчас"},
                {"leider","к сожалению"},
                {"noch","ещё"},
                {"kein","никакой"},
                {"schon","уже"},
                {"übrigens","впрочем"},
                {"aber","но"},
                {"aus","из"},
                {"das (es)","это"}
        };

        for (int i2 = 0; i2 < allCurrentLesson1.length; i2++) {
            addLessonData(allCurrentLesson1[i2][0],allCurrentLesson1[i2][1],2);
        }

        String [][] allCurrentLesson2 = {
                {"dein","твой"},
                {"von","от"},
                {"was","что?"},
                {"wer","кто?"},
                {"wie","как?"},
                {"wieviel","сколько?"},
                {"ach so!","значит так!"},
                {"auf Wiedersehen!","до свидания!"},
                {"schön","красивый"},
                {"stehen","стоять"},
                {"der Abend","вечер"},
                {"zur Zeit","в настоящее время"},
                {"der Mann, die Männer","мужчина, супруг"},
                {"fest","крепкий,  твердый"},
                {"nicht","не, ни"},
                {"der Lehrer","учитель"},
                {"und","и"},
                {"das Land, die Länder","страна, край"},
                {"auch","также, тоже, и, впрочем"},
                {"später","поздний"},
                {"dort","там"},
                {"kaufen","купить, покупать"},
                {"verkaufen","продавать"},
                {"zusammen","вместе, сообща"},
                {"dann","тогда, потом"}};

        for (int i3 = 0; i3 < allCurrentLesson2.length; i3++) {
            addLessonData(allCurrentLesson2[i3][0],allCurrentLesson2[i3][1],3);
        }


        String [][] allCurrentLesson3 = {
                {"die Stunde","час"},
                {"möglich","возможный (возможно)"},
                {"die Liebe","любовь"},
                {"liegen","лежать"},
                {"antworten","отвечать"},
                {"kommen","приходить, прибывать"},
                {"entschuldigen","извинять, прощать"},
                {"funktionieren","функционировать"},
                {"kosten","стоить, пробовать"},
                {"sagen","сказать, говорить"},
                {"spülen","полоскать, мыть"},
                {"stimmen","быть верным"},
                {"waschen","мыть, "},
                {"wechseln","обменивать"},
                {"die Antwort","ответ, отклик"},
                {"das Benzin","бензин"},
                {"das Bett","постель, кровать"},
                {"das Bild","картина; рисунок"},
                {"der Fehler","ошибка"},
                {"heißen","называть, зваться"},
                {"das Geld","деньги"},
                {"das Geschäft","сделка, магазин"},
                {"das Haus, die Häuser","дом, здание"},
                {"der Haushalt","домашнее хозяйство"},
                {"der Herd","очаг, плита"}};

        for (int i4 = 0; i4 < allCurrentLesson3.length; i4++) {
            addLessonData(allCurrentLesson3[i4][0],allCurrentLesson3[i4][1],4);
        }

        String [][] allCurrentLesson4 = {
                {"die Idee","идея, мысль"},
                {"können","мочь, уметь"},
                {"schnell","быстро"},
                {"sein","быть"},
                {"das Regal","полка, стеллаж"},
                {"anders","по-другому"},
                {"in","в, внутри"},
                {"haben","иметь"},
                {"der Stuhl, die Stühle","стул"},
                {"der Tisch","стол"},
                {"der Topf, die Töpfe","горшок, кастрюля"},
                {"die Uhr","часы"},
                {"die Sonne","солнце"},
                {"gehen","идти, ходить"},
                {"die Zeit","время"},
                {"ähnlich","похожий, сходный"},
                {"sehen","смотреть, глядеть"},
                {"ehrlich","честный, порядочный"},
                {"kaputt","разбитый, сломанный"},
                {"groß","большой, крупный"},
                {"lustig","весёлый, забавный"},
                {"originell","оригинальный, своеобразный"},
                {"sehr","очень, весьма, крайне"},
                {"heute","сегодня"},
                {"viel","много, многие, многое"}};

        for (int i5 = 0; i5 < allCurrentLesson4.length; i5++) {
            addLessonData(allCurrentLesson4[i5][0],allCurrentLesson4[i5][1],5);
        }


        String [][] allCurrentLesson5 = {
                {"oder","или"},
                {"sondern","а, но"},
                {"zu","к, на, в"},
                {"raus","наружу"},
                {"danke","спасибо"},
                {"bitte","пожалуйста"},
                {"die Lampe","лампа"},
                {"trinken","пить"},
                {"essen","есть, кушать"},
                {"der Bleistift","карандаш"},
                {"ordnen","приводить в порядок"},
                {"der Beruf","профессия, специальность"},
                {"der Arzt, die Ärzte","врач, доктор"},
                {"der Baum, die Bäume","дерево"},
                {"nach","после"},
                {"auf","на (горизонтальной поверхности)"},
                {"gegen","против, около"},
                {"als","как, когда"},
                {"das Auto","автомобиль"},
                {"er","он"},
                {"bestellen","заказывать"},
                {"bezahlen","платить, выплачивать"},
                {"brauchen","нуждаться"},
                {"genau","точно, именно"},
                {"glauben","полагать, думать, верить"}

        };

        for (int i6 = 0; i6 < allCurrentLesson5.length; i6++) {
            addLessonData(allCurrentLesson5[i6][0],allCurrentLesson5[i6][1],6);
        }

        String [][] allCurrentLesson6 = {
                {"kochen","варить, готовить"},
                {"mögen","любить, чувствовать расположение"},
                {"über","над"},
                {"üben","упражняться"},
                {"das Abendessen","ужин"},
                {"die Anzeige","объявление, заявление"},
                {"der Apfel, die Äpfel","яблоко"},
                {"müssen","быть должным"},
                {"das Brot","хлеб"},
                {"zwischen","между"},
                {"die Butter","масло (животное)"},
                {"unter","под"},
                {"das Ei,die Eier","яйцо"},
                {"das Eis","лёд, мороженое"},
                {"die Erdbeere","клубника, земляника"},
                {"die Flasche", "бутылка, фляжка"},
                {"das Fleisch","мясо"},
                {"die Frage","вопрос"},
                {"selbst","сам"},
                {"das Frühstück","завтрак"},
                {"die Gabel","вилка"},
                {"die Zeitung","газета"},
                {"das Gemüse","овощи, зелень"},
                {"das Gericht","блюдо, кушанье"},
                {"das Gespräch","разговор, беседа"}};

        for (int i7 = 0; i7 < allCurrentLesson6.length; i7++) {
            addLessonData(allCurrentLesson6[i7][0],allCurrentLesson6[i7][1],7);
        }

        String [][] allCurrentLesson7 = {
                {"das Getränk","напиток, питьё"},
                {"das Gewürz","пряности, приправа"},
                {"das Glas, die Gläser","стекло, стакан"},
                {"das Prozent","процент"},
                {"der Käse","сыр, творог"},
                {"der Kuchen","пирог, пирожное"},
                {"der Löffel","ложка"},
                {"mehr","больше"},
                {"das Messer","нож"},
                {"der Nachtisch","десерт"},
                {"das Öl","масло (растительное)"},
                {"der Pfeffer","перец"},
                {"die Blume","цветок"},
                {"der Preis","цена"},
                {"der Reis","рис"},
                {"der Saft, die Säfte","сок"},
                {"die Sahne","сливки"},
                {"der Schinken","ветчина"},
                {"die Soße","соус, подливка"},
                {"langsam","медленно"},
                {"das Wasser","вода"},
                {"die Suppe","суп, похлёбка"},
                {"die Tasse","чашка (чайная)"},
                {"der Teller","тарелка"},
                {"die Tomate","помидор"}
        };

        for (int i8 = 0; i8 < allCurrentLesson7.length; i8++) {
            addLessonData(allCurrentLesson7[i8][0],allCurrentLesson7[i8][1],8);
        }
    }

    public int checkTable(String TABLE_NAME){
        //System.out.println(TABLE_NAME);
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+TABLE_NAME+"'";
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