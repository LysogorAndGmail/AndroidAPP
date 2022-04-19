package com.example.deutschea1alla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class Db1000Adapter {
    myDbHelper1000 myhelper;

    private ArrayList<Db1000Adapter.CurrentNode> Currentnodes;

    public Db1000Adapter(Context context)
    {
        myhelper = new myDbHelper1000(context);
    }

    static class myDbHelper1000 extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "words1000";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String DE = "DE";    //Column II
        private static final String RU= "RU";    // Column III
        private static final String Lessons= "lesson";    // Column 4
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ DE +" VARCHAR(255) ,"+ RU +" VARCHAR(225), "+ Lessons +" INTEGER);";
        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper1000(Context context) {
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
        contentValues.put(Db1000Adapter.myDbHelper1000.DE, de);
        contentValues.put(Db1000Adapter.myDbHelper1000.RU, ru);
        contentValues.put(Db1000Adapter.myDbHelper1000.Lessons, lesson);
        long id = dbb.insert(Db1000Adapter.myDbHelper1000.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList getDataArray() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.Lessons};
        Cursor cursor = db.query(Db1000Adapter.myDbHelper1000.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData;
        //ArrayList<CurrentNode> Currentnodes = null;
        Currentnodes = new ArrayList<Db1000Adapter.CurrentNode>();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UID));
            String name = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.DE));
            String password = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.RU));
            int lesson = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {cid + "", name, password, lesson + ""};

            //Currentnodes = new ArrayList<CurrentNode>();
            Currentnodes.add(new Db1000Adapter.CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");

        }
        return Currentnodes;
    }

    public String[][] getDataArrayLesson(String Lesson) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.Lessons};
        // Filter results WHERE "title" = 'My Title'
        String selection = Db1000Adapter.myDbHelper1000.Lessons + " = ?";
        String[] selectionArgs = { Lesson };
        Cursor cursor =  db.query(Db1000Adapter.myDbHelper1000.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData = new String[25][];
        int i = 0;
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UID));
            String name = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.DE));
            String password = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.RU));
            int lesson = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
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
        String [][] allCurrentLesson1 = {
                {"Hallo!","Привет!"},
                {"Guten Tag!","Добрый день!"},
                {"Entschuldigung!","Извини(те)!"},
                {"Bitte","пожалуйста"},
                {"Danke","спасибо"},
                {"Gern geschehen","не за что"},
                {"Schade","жаль"},
                {"Tschüss!","Пока!"},
                {"Auf Wiedersehen!","До свидания!"},
                {"die Leute","люди"},
                {"der Mensch","человек"},
                {"der Mann","мужчина; муж"},
                {"die Frau","женщина; жена"},
                {"das Kind","ребенок"},
                {"der Junge","мальчик"},
                {"das Mädchen","девочка"},
                {"der Freund","друг"},
                {"der Bekannte","знакомый"},
                {"der Nachbar","сосед"},
                {"der Gast","гость"},
                {"der Chef","начальник; шеф"},
                {"der Konkurrent","конкурент"},
                {"der Kunde","клиент, покупатель"},
                {"der Kollege","коллега"},
                {"die Familie","семья"},
        };

        for (int i = 0; i < allCurrentLesson1.length; i++) {
            addLessonData(allCurrentLesson1[i][0],allCurrentLesson1[i][1],1);
        }


        String [][] allCurrentLesson2 = {
                {"die Eltern","родители"},
                {"der Vater","отец"},
                {"der Vati","папа"},
                {"die Mutter","мать"},
                {"die Mutti","мама"},
                {"der Sohn","сын"},
                {"die Tochter","дочь"},
                {"der Bruder","брат"},
                {"die Schwester","сестра"},
                {"der Großvater","дед"},
                {"der Opa","дедушка"},
                {"die Großmutter","бабушка"},
                {"die Oma","бабуля"},
                {"der Enkel","внук"},
                {"die Enkelin","внучка"},
                {"der Schwiegervater","тесть, свекор"},
                {"die Schwiegermutter","тёща, свекровь"},
                {"der Onkel","дядя"},
                {"die Tante","тетя"},
                {"der Cousin [кузэн]","двоюродный брат"},
                {"die Kusine","двоюродная сестра"},
                {"der Neffe","племянник"},
                {"die Nichte","племянница"},
                {"die Arbeit","работа"},
                {"der Geschäftsmann","бизнесмен"},
        };

        for (int i = 0; i < allCurrentLesson2.length; i++) {
            addLessonData(allCurrentLesson2[i][0],allCurrentLesson2[i][1],2);
        }


        String [][] allCurrentLesson3 = {
                {"der Lehrer","учитель"},
                {"der Fahrer","водитель"},
                {"der Arbeiter","рабочий"},
                {"der Ingenieur","инженер"},
                {"der Arzt","врач"},
                {"der Rechtsanwalt","адвокат"},
                {"der Journalist","журналист"},
                {"der Krankenschwester","медсестра"},
                {"der Verkäufer","продавец"},
                {"der Kellner","официант"},
                {"der Buchhalter","бухгалтер"},
                {"der Maler","художник"},
                {"der Musiker","музыкант"},
                {"der Schauspieler","актер"},
                {"der Student","студент"},
                {"der Schüler","школьник, ученик"},
                {"das Tier","животное"},
                {"die Katze","кошка"},
                {"der Hund","собака"},
                {"der Vogel","птица"},
                {"das Eichhörnchen","белка"},
                {"der Wolf","волк"},
                {"die Gans","гусь"},
                {"die Giraffe","жираф"},
                {"das Kaninchen","кролик"},
        };

        for (int i = 0; i < allCurrentLesson3.length; i++) {
            addLessonData(allCurrentLesson3[i][0],allCurrentLesson3[i][1],3);
        }


        String [][] allCurrentLesson4 = {
                {"der Hase","заяц"},
                {"die Kuh","корова"},
                {"die Ratte","крыса"},
                {"der Fuchs","лиса"},
                {"das Pferd","лошадь"},
                {"der Frosch","лягушка"},
                {"der Bär","медведь"},
                {"die Maus","мышь"},
                {"der Affe","обезьяна"},
                {"das Schwein","свинья"},
                {"der Elefant","слон"},
                {"die Ente","утка"},
                {"das Land","страна; сельская местность; земля"},
                {"Russland","Россия"},
                {"Deutschland","Германия"},
                {"Österreich","Австрия"},
                {"die Schweiz","Швейцария"},
                {"die Stadt","город"},
                {"das Haus","дом"},
                {"das Gebäude","здание"},
                {"der Platz","площадь; место"},
                {"der Eingang","вход"},
                {"der Ausgang","выход"},
                {"das Zentrum","центр"},
                {"der Hof","двор"},
        };

        for (int i = 0; i < allCurrentLesson4.length; i++) {
            addLessonData(allCurrentLesson4[i][0],allCurrentLesson4[i][1],4);
        }


        String [][] allCurrentLesson5 = {
                {"das Dach","крыша"},
                {"der Zaun","забор"},
                {"das Dorf","деревня, поселок"},
                {"die Schule","школа"},
                {"die Universität","университет"},
                {"das Theater","театр"},
                {"die Kirche","церковь"},
                {"das Restaurant","ресторан"},
                {"das Cafe","кафе"},
                {"das Hotel","гостиница"},
                {"die Bank","банк"},
                {"das Kino","кинотеатр"},
                {"das Krankenhaus","больница"},
                {"die Polizei","полиция"},
                {"das Postamt","почта"},
                {"der Bahnhof","станция, вокзал"},
                {"der Flughafen","аэропорт"},
                {"das Geschäft","магазин"},
                {"die Apotheke","аптека"},
                {"der Markt","рынок"},
                {"das Büro","офис"},
                {"die Firma","фирма"},
                {"der Betrieb","предприятие"},
                {"die Straße","улица"},
                {"der Weg","дорога"},
        };

        for (int i = 0; i < allCurrentLesson5.length; i++) {
            addLessonData(allCurrentLesson5[i][0],allCurrentLesson5[i][1],5);
        }


        String [][] allCurrentLesson6 = {
                {"die Kreuzung","перекрёсток"},
                {"die Haltestelle","остановка"},
                {"der Gehsteig","тротуар"},
                {"der Pfad","тропа, тропинка"},
                {"der Garten","сад"},
                {"der Park","парк"},
                {"die Brücke","мост"},
                {"der Fluss","река"},
                {"der Wald","лес"},
                {"das Feld","поле"},
                {"der Berg","гора"},
                {"der See","озеро"},
                {"das Meer","море"},
                {"der Ozean","океан"},
                {"die Küste","морской берег, побережье"},
                {"der Strand","пляж"},
                {"der Sand","песок"},
                {"die Insel","остров"},
                {"die Grenze","граница"},
                {"der Zoll","таможня"},
                {"der Müll","мусор"},
                {"die Abfälle","отходы"},
                {"der Stein","камень"},
                {"die Pflanze","растение"},
                {"der Baum","дерево"},
        };

        for (int i = 0; i < allCurrentLesson6.length; i++) {
            addLessonData(allCurrentLesson6[i][0],allCurrentLesson6[i][1],6);
        }


        String [][] allCurrentLesson7 = {
                {"das Gras","трава"},
                {"die Blume","цветок"},
                {"das Blatt","лист"},
                {"die Wohnung","квартира"},
                {"das Zimmer","комната"},
                {"das Wohnzimmer","зал"},
                {"das Schlafzimmer","спальня"},
                {"das Badezimmer","ванная"},
                {"die Dusche","душ"},
                {"die Toilette","туалет"},
                {"die Küche","кухня"},
                {"der Flur","коридор"},
                {"der Balkon","балкон"},
                {"der Fußboden","пол"},
                {"die Decke","потолок; одеяло"},
                {"die Wand","стена"},
                {"die Treppe","лестница"},
                {"die Tür","дверь"},
                {"das Fenster","окно"},
                {"das Fensterbrett","подоконник"},
                {"die Gardine","занавес(ка), штора"},
                {"der Schalter","выключатель; окошко (кассы)"},
                {"die Steckdose","розетка"},
                {"der Wasserhahn","(водопроводный) кран"},
                {"das Rohr","труба"},
        };

        for (int i = 0; i < allCurrentLesson7.length; i++) {
            addLessonData(allCurrentLesson7[i][0],allCurrentLesson7[i][1],7);
        }


        String [][] allCurrentLesson8 = {
                {"der Schornstein","дымовая труба"},
                {"die Möbel","мебель"},
                {"der Tisch","стол"},
                {"der Stuhl","стул"},
                {"der Sessel","кресло"},
                {"die Couch [кáуч]","диван"},
                {"das Bett","кровать"},
                {"der Schrank","шкаф"},
                {"das Regal","полка"},
                {"der Spiegel","зеркало"},
                {"der Teppich","ковер"},
                {"der Kühlschrank","холодильник"},
                {"die Mikrowelle","микроволновка"},
                {"der Ofen","печь, духовка"},
                {"der Herd","кухонная плита"},
                {"die Lebensmittel","еда, продукты"},
                {"das Brot","хлеб"},
                {"die Butter","сливочное масло"},
                {"das Öl","растительное масло"},
                {"der Käse","сыр"},
                {"die Wurst","колбаса"},
                {"das Würstchen","сосиска"},
                {"der Schinken","ветчина"},
                {"das Fleisch","мясо"},
                {"das Rindfleisch","говядина"},
        };

        for (int i = 0; i < allCurrentLesson8.length; i++) {
            addLessonData(allCurrentLesson8[i][0],allCurrentLesson8[i][1],8);
        }


        String [][] allCurrentLesson9 = {
                {"das Schweinefleisch","свинина"},
                {"das Lammfleisch","баранина"},
                {"das Hähnchen","курица (мясо)"},
                {"das Kotelett","котлета, отбивная"},
                {"der Fisch","рыба"},
                {"das Ei","яйцо"},
                {"der Salat","салат"},
                {"die Pilze","грибы"},
                {"der Mais","кукуруза"},
                {"der Brei","каша"},
                {"die Haferflocken","овсянка"},
                {"die Suppe","суп"},
                {"das belegte Brötchen","бутерброд"},
                {"der Reis","рис"},
                {"die Nudeln","лапша"},
                {"das Mehl","мука"},
                {"das Gewürz","специя, пряность"},
                {"der Pfeffer","перец"},
                {"das Salz","соль"},
                {"die Zwiebel","лук (репчатый)"},
                {"der Knoblauch","чеснок"},
                {"die Soße","соус"},
                {"das Gemüse","овощи"},
                {"die Kartoffeln","картофель"},
                {"die Mohrrübe","морковь"},
        };

        for (int i = 0; i < allCurrentLesson9.length; i++) {
            addLessonData(allCurrentLesson9[i][0],allCurrentLesson9[i][1],9);
        }


        String [][] allCurrentLesson10 = {
                {"die Zuckerrübe","свекла"},
                {"die Tomate","помидор"},
                {"die Gurke","огурец"},
                {"der Kohl","капуста"},
                {"die Zucchini [цукини]","кабачок"},
                {"die Aubergine [обэр'жинэ]","баклажан"},
                {"die Bohnen","бобы"},
                {"die Erbsen","горох"},
                {"die Nuss","орех"},
                {"das Obst","фрукты"},
                {"der Apfel","яблоко"},
                {"die Birne","груша"},
                {"die Banane","банан"},
                {"die Beere","ягода"},
                {"die Erdbeere","клубника, земляника"},
                {"die Himbeere","малина"},
                {"die Kirsche","вишня"},
                {"die Pflaume","слива"},
                {"die Trauben","виноград"},
                {"die Aprikose","абрикос"},
                {"der Pfirsich","персик"},
                {"die Melone","дыня"},
                {"die Wassermelone","арбуз"},
                {"der Kürbis","тыква"},
                {"die Orange [орáнжэ]","апельсин"},
        };

        for (int i = 0; i < allCurrentLesson10.length; i++) {
            addLessonData(allCurrentLesson10[i][0],allCurrentLesson10[i][1],10);
        }


        String [][] allCurrentLesson11 = {
                {"die Mandarine","мандарин"},
                {"die Zitrone","лимон"},
                {"die Ananas","ананас"},
                {"der Zucker","сахар"},
                {"der Honig","мёд"},
                {"die Marmelade","варенье"},
                {"der Kuchen","торт, пирог"},
                {"das Brötchen","булочка"},
                {"das Gebäck","печенье"},
                {"die Süßigkeiten","конфеты, сладости"},
                {"das Eis","мороженое; лёд"},
                {"die Schokolade","шоколад"},
                {"das Wasser","вода"},
                {"das Sodawasser","газировка"},
                {"der Saft","сок"},
                {"der Wein","вино"},
                {"der Tee","чай"},
                {"der Kaffee","кофе"},
                {"die Milch","молоко"},
                {"die Sahne","сливки"},
                {"der Joghurt","йогурт"},
                {"der Quark","творог"},
                {"das Geschirr","посуда"},
                {"die Tasse","чашка"},
                {"das Glas","стакан; стекло"},
        };

        for (int i = 0; i < allCurrentLesson11.length; i++) {
            addLessonData(allCurrentLesson11[i][0],allCurrentLesson11[i][1],11);
        }


        String [][] allCurrentLesson12 = {
                {"der Becher","кружка"},
                {"der Teller","тарелка"},
                {"der Löffel","ложка"},
                {"die Gabel","вилка"},
                {"das Messer","нож"},
                {"die Untertasse","блюдце"},
                {"die Flasche","бутылка"},
                {"die Serviette","салфетка"},
                {"der Topf","кастрюля; горшок"},
                {"die Pfanne","сковородка"},
                {"der Kessel","чайник; котел"},
                {"die Mahlzeit","принятие пищи, еда"},
                {"das Frühstück","завтрак"},
                {"das Mittagessen","обед"},
                {"das Abendessen","ужин"},
                {"der Transport","транспорт"},
                {"das Flugzeug","самолет"},
                {"das Auto","автомобиль"},
                {"die Straßenbahn","трамвай"},
                {"der Bus","автобус"},
                {"der Zug","поезд"},
                {"das Schiff","корабль"},
                {"das Fahrrad","велосипед"},
                {"die Zeit","время"},
                {"die Minute","минута"},
        };

        for (int i = 0; i < allCurrentLesson12.length; i++) {
            addLessonData(allCurrentLesson12[i][0],allCurrentLesson12[i][1],12);
        }


        String [][] allCurrentLesson13 = {
                {"die Stunde","час"},
                {"die Woche","неделя"},
                {"das Jahr","год"},
                {"das Jahrhundert","век, столетие"},
                {"das Mal","раз"},
                {"vorgestern","позавчера"},
                {"gestern","вчера"},
                {"heute","сегодня"},
                {"morgen","завтра"},
                {"übermorgen","послезавтра"},
                {"der Tag","день"},
                {"der Morgen","утро"},
                {"der Nachmittag","время до полудня"},
                {"der Nachmittag","время после полудня"},
                {"der Abend","вечер"},
                {"die Nacht","ночь"},
                {"der Montag","понедельник"},
                {"der Dienstag","вторник"},
                {"der Mittwoch","среда"},
                {"der Donnerstag","четверг"},
                {"der Freitag","пятница"},
                {"der Samstag","суббота"},
                {"der Sonntag","воскресенье"},
                {"der Monat","месяц"},
                {"der Januar","январь"},
        };

        for (int i = 0; i < allCurrentLesson13.length; i++) {
            addLessonData(allCurrentLesson13[i][0],allCurrentLesson13[i][1],13);
        }


        String [][] allCurrentLesson14 = {
                {"der Februar","февраль"},
                {"der März","март"},
                {"der April","апрель"},
                {"der Mai","май"},
                {"der Juni","июнь"},
                {"der Juli","июль"},
                {"der August","август"},
                {"der September","сентябрь"},
                {"der Oktober","октябрь"},
                {"der November","ноябрь"},
                {"der Dezember","декабрь"},
                {"die Jahreszeit","время года"},
                {"der Frühling","весна"},
                {"der Sommer","лето"},
                {"der Herbst","осень"},
                {"der Winter","зима"},
                {"das Fest","праздник"},
                {"das Weihnachten","Рождество"},
                {"das Ostern","Пасха"},
                {"der Geburtstag","день рождения"},
                {"das Formular","формуляр, бланк"},
                {"der Name","имя, фамилия; название;"},
                {"der Vorname","имя"},
                {"der Nachname","фамилия"},
                {"der Mädchenname","девичья фамилия"},
        };

        for (int i = 0; i < allCurrentLesson14.length; i++) {
            addLessonData(allCurrentLesson14[i][0],allCurrentLesson14[i][1],14);
        }


        String [][] allCurrentLesson15 = {
                {"der Geburtsdatum","дата рождения"},
                {"der Geburtsort","место рождения"},
                {"die Adresse","адрес"},
                {"der Familienstand","семейное положение"},
                {"ledig","холостой, незамужняя"},
                {"verheiratet","женатый / замужняя"},
                {"geschieden","разведенный"},
                {"verwitwet","овдовевший"},
                {"die Sache","вещь"},
                {"der Kugelschreiber","ручка"},
                {"der Bleistift","карандаш"},
                {"das Buch","книга"},
                {"das Heft","тетрадь"},
                {"das Notizbuch","блокнот"},
                {"die Notiz","пометка, запись"},
                {"das Wörterbuch","словарь"},
                {"der Buchstabe","буква"},
                {"der Brief","письмо"},
                {"der Umschlag","конверт"},
                {"das Papier","бумага"},
                {"die Zeitung","газета"},
                {"die Zeitschrift","журнал"},
                {"das Telefon","телефон"},
                {"das Handy [хэнди]","сотовый телефон"},
                {"die Uhr","часы"},
        };

        for (int i = 0; i < allCurrentLesson15.length; i++) {
            addLessonData(allCurrentLesson15[i][0],allCurrentLesson15[i][1],15);
        }


        String [][] allCurrentLesson16 = {
                {"der Kamm","расчёска"},
                {"der Fernseher","телевизор"},
                {"das Bűgeleisen","утюг"},
                {"die Seife","мыло"},
                {"das Radio","радио"},
                {"die Tasche","сумка"},
                {"der Rucksack","рюкзак"},
                {"die Karte","карта"},
                {"die Postkarte","открытка"},
                {"der Koffer","чемодан"},
                {"das Geschenk","подарок"},
                {"die Kamera","камера"},
                {"der Fotoapparat","фотоаппарат"},
                {"die Vase","ваза"},
                {"das Taschentuch","носовой платок"},
                {"der Ball","мяч"},
                {"der Luftballon","воздушный шар(ик)"},
                {"das Spielzeug","игрушка"},
                {"die Fahrkarte","билет (на поезд)"},
                {"der Fahrschein","билет (на городской транспорт)"},
                {"das Ticket","билет (на самолёт)"},
                {"das Gepäck","багаж"},
                {"die Batterie","батарейка, аккумулятор"},
                {"die Eimer","ведро"},
                {"der Leine","веревка"},
        };

        for (int i = 0; i < allCurrentLesson16.length; i++) {
            addLessonData(allCurrentLesson16[i][0],allCurrentLesson16[i][1],16);
        }


        String [][] allCurrentLesson17 = {
                {"die Tafel","доска (школьная)"},
                {"das Brett","доска, брусок"},
                {"der Kalender","календарь"},
                {"der Laptop [лэптоп]","ноутбук"},
                {"die Bürste","щетка"},
                {"die Pinsel","кисть, кисточка"},
                {"die Tastatur","клавиатура"},
                {"die Taste","клавиша"},
                {"der Schlüssel","ключ"},
                {"das Rad","колесо"},
                {"das Lenkrad","руль"},
                {"der Kofferraum","багажник"},
                {"das Benzin","бензин"},
                {"die Geldbörse","кошелек"},
                {"die Brieftasche","бумажник"},
                {"die Lampe","лампа"},
                {"das Lineal","линейка"},
                {"die Schaufel","лопата"},
                {"die Maschine","машина; механизм"},
                {"der Hammer","молоток"},
                {"die Schere","ножницы"},
                {"die Brille","очки"},
                {"das Paket","посылка"},
                {"der Stock","палка"},
                {"der Klebstoff","клей"},
        };

        for (int i = 0; i < allCurrentLesson17.length; i++) {
            addLessonData(allCurrentLesson17[i][0],allCurrentLesson17[i][1],17);
        }


        String [][] allCurrentLesson18 = {
                {"das Handtuch","полотенце"},
                {"der Draht","проволока"},
                {"das Kabel","провод, кабель"},
                {"die Seite","страница"},
                {"die Taschenlampe","карманный фонарь"},
                {"der Kasten","ящик"},
                {"die Kiste","коробка"},
                {"das Laken","простыня"},
                {"das Kissen","подушка"},
                {"die Kleidung","одежда"},
                {"die Schuhe","обувь, туфли"},
                {"die Stiefel","сапоги"},
                {"die Turnschuhe","кроссовки"},
                {"der Mantel","пальто"},
                {"das Kleid","платье"},
                {"der Anzug","костюм"},
                {"das Hemd","рубашка"},
                {"die Bluse","блузка"},
                {"der Rock","юбка"},
                {"die Handschuhe","перчатки"},
                {"die Fäustlinge","варежки"},
                {"der Hut","шляпа"},
                {"die Mütze","шапка; кепка"},
                {"die Jacke","Куртка"},
                {"die Strickjacke","кофта"},
        };

        for (int i = 0; i < allCurrentLesson18.length; i++) {
            addLessonData(allCurrentLesson18[i][0],allCurrentLesson18[i][1],18);
        }


        String [][] allCurrentLesson19 = {
                {"das Sakko","пиджак"},
                {"der Schal","шарф"},
                {"die Socke","носок"},
                {"der Pullover","свитер"},
                {"das T-Shirt [тишёрт]","футболка"},
                {"die Kravatte","галстук"},
                {"die Hose","брюки, штаны"},
                {"die Shorts [шортс]","шорты"},
                {"die Strumpfhose","колготки"},
                {"die Strümpfe","чулки"},
                {"die Jeans [джинз]","джинсы"},
                {"die Kapuze","капюшон"},
                {"der Gürtel","ремень"},
                {"die Unterwäsche","нижнее белье"},
                {"die Unterhose","трусы"},
                {"der Büstenhaĺter","бюстгальтер"},
                {"der Körper","тело"},
                {"der Kopf","голова"},
                {"das Gesicht","лицо"},
                {"die Stirn","лоб"},
                {"die Nase","нос"},
                {"das Ohr","ухо"},
                {"der Mund","рот"},
                {"der Hals","горло; шея"},
                {"das Auge","глаз"},
        };

        for (int i = 0; i < allCurrentLesson19.length; i++) {
            addLessonData(allCurrentLesson19[i][0],allCurrentLesson19[i][1],19);
        }


        String [][] allCurrentLesson20 = {
                {"die Augenbraue","бровь"},
                {"die Lippen","губы"},
                {"der Zahn","зуб"},
                {"das Haar","волос(ы)"},
                {"der Schnurrbart","усы"},
                {"der Bart","борода"},
                {"die Wange","щека"},
                {"das Kinn","подбородок"},
                {"die Schulter","плечо"},
                {"die Brust","грудь"},
                {"das Herz","сердце"},
                {"der Bauch","живот"},
                {"der Rücken","спина"},
                {"das Handgelenk","запястье"},
                {"die Hand","рука, кисть (руки)"},
                {"der Arm","рука (вся)"},
                {"der Finger","палец (руки)"},
                {"der Nagel","ноготь; гвоздь"},
                {"der Ellenbogen","локоть"},
                {"das Bein","нога"},
                {"das Knie","колено"},
                {"der Fuß","нога, ступня; подножие"},
                {"die Ferse","пятка"},
                {"die Zehe","палец (ноги)"},
                {"der Knochen","кость"},
        };

        for (int i = 0; i < allCurrentLesson20.length; i++) {
            addLessonData(allCurrentLesson20[i][0],allCurrentLesson20[i][1],20);
        }


        String [][] allCurrentLesson21 = {
                {"die Gesundheit","здоровье"},
                {"gesund","здоровый"},
                {"krank","больной"},
                {"die Krankheit","болезнь"},
                {"das Fieber","жар, (высокая) температура"},
                {"der Husten","кашель"},
                {"der Schnupfen","насморк"},
                {"niesen","чихать"},
                {"der Schmerz","боль"},
                {"die Kopfschmerzen","головная боль"},
                {"weh tun","болеть"},
                {"die Grippe","грипп"},
                {"die Erkältung","простуда"},
                {"die Prellung","синяк, ушиб"},
                {"das Ereignis","событие"},
                {"die Geburt","рождение"},
                {"das Spiel","игра"},
                {"der Unterricht","занятие (урок)"},
                {"der Urlaub","отпуск"},
                {"die Ferien","каникулы"},
                {"die Party [пати]","вечеринка"},
                {"das Treffen","встреча"},
                {"die Versammlung","собрание"},
                {"die Hochzeit","свадьба"},
                {"die Verhandlungen","переговоры"},
        };

        for (int i = 0; i < allCurrentLesson21.length; i++) {
            addLessonData(allCurrentLesson21[i][0],allCurrentLesson21[i][1],21);
        }


        String [][] allCurrentLesson22 = {
                {"die Reise","поездка, путешествие"},
                {"der Tod","смерть"},
                {"das Wetter","погода"},
                {"die Sonne","солнце"},
                {"der Mond","луна"},
                {"der Wind","ветер"},
                {"der Nebel","туман"},
                {"der Regen","дождь"},
                {"der Schnee","снег"},
                {"der Himmel","небо"},
                {"die Wolke","облако"},
                {"die Luft","воздух"},
                {"die Temperatur","температура"},
                {"der Grad","градус"},
                {"die Kunst","искусство"},
                {"die Musik","музыка"},
                {"das Lied","песня"},
                {"die Literatur","литература"},
                {"die Geschichte","рассказ, история"},
                {"der Film","фильм; пленка"},
                {"die Skulptur","скульптура"},
                {"das Bild","картина"},
                {"das Foto","фото"},
                {"die Werbung","реклама"},
                {"der Einkäufe","покупки"},
        };

        for (int i = 0; i < allCurrentLesson22.length; i++) {
            addLessonData(allCurrentLesson22[i][0],allCurrentLesson22[i][1],22);
        }


        String [][] allCurrentLesson23 = {
                {"die Größe","размер"},
                {"der Preis","цена"},
                {"das Geld","деньги"},
                {"das Bargeld","наличные (деньги)"},
                {"der Rabatt","скидка"},
                {"das Konto","счет (банковский)"},
                {"die Rechnung","счёт (для оплаты)"},
                {"das Trinkgeld","чаевые"},
                {"das Messen","измерение"},
                {"die Entfernung","расстояние"},
                {"die Distanz","дистанция"},
                {"die Länge","длина"},
                {"die Höhe","высота"},
                {"die Tiefe","глубина"},
                {"die Stärke","сила"},
                {"die Geschwindigkeit","скорость"},
                {"der Kilometer","километр"},
                {"das Kilogramm","килограмм"},
                {"das Pfund","фунт"},
                {"das Gefühl","чувство"},
                {"der Spaß","веселье"},
                {"die Freude","радость"},
                {"die Angst","страх"},
                {"die Traurigkeit","печаль"},
                {"die Leidenschaft","страсть, страстность"},
        };

        for (int i = 0; i < allCurrentLesson23.length; i++) {
            addLessonData(allCurrentLesson23[i][0],allCurrentLesson23[i][1],23);
        }


        String [][] allCurrentLesson24 = {
                {"das Vergnügen","удовольствие"},
                {"das Glück","счастье"},
                {"der Frieden","мир (покой)"},
                {"lieben","любить"},
                {"hassen","ненавидеть"},
                {"die Welt","мир (планета)"},
                {"die Seele","душа"},
                {"das Leben","жизнь"},
                {"das Wissen","знание"},
                {"die Aufgabe","задача, задание"},
                {"die Übung","упражнение"},
                {"das Problem","проблема"},
                {"die Gelegenheit","(благоприятная) возможность"},
                {"die Fähigkeit","способность"},
                {"die Schönheit","красота"},
                {"die Gefahr","опасность"},
                {"die Erfahrung","опыт"},
                {"das Gedächtnis","память"},
                {"die Erinnerung","воспоминание"},
                {"der Nutzen","польза"},
                {"der Vorteil","преимущество"},
                {"der Gewinn","выигрыш; прибыль, доход"},
                {"die Gewohnheit","привычка"},
                {"der Grund","причина; грунт, земля"},
                {"die Konsequenz","(по)следствие"},
        };

        for (int i = 0; i < allCurrentLesson24.length; i++) {
            addLessonData(allCurrentLesson24[i][0],allCurrentLesson24[i][1],24);
        }


        String [][] allCurrentLesson25 = {
                {"die Bedeutung","значение"},
                {"das Mittel","средство"},
                {"die Anstrengung","усилие"},
                {"der Erfolg","успех"},
                {"das Ziel","цель"},
                {"das Wunder","чудо"},
                {"die Wissenschaft","наука"},
                {"die Sprache","язык"},
                {"das Wort","слово"},
                {"das Feuer","огонь"},
                {"der Fall","случай; дело; падение"},
                {"der Umstand","обстоятельство"},
                {"der Gedanke","мысль"},
                {"die Wahl","выбор"},
                {"die Erlaubnis","разрешение"},
                {"die Lieferung","доставка; поставка"},
                {"die Leistung","достижение, успех"},
                {"die Pflicht","обязанность"},
                {"die Verzögerung","задержка, промедление"},
                {"die Beziehung","отношение"},
                {"die Note","оценка, отметка, знак; нота"},
                {"der Fehler","ошибка"},
                {"das Benehmen","поведение"},
                {"die Einladung","приглашение"},
                {"die Entwicklung","развитие"},
        };

        for (int i = 0; i < allCurrentLesson25.length; i++) {
            addLessonData(allCurrentLesson25[i][0],allCurrentLesson25[i][1],25);
        }


        String [][] allCurrentLesson26 = {
                {"die Entscheidung","решение, принятие решения"},
                {"die Lösung","решение (проблемы)"},
                {"der Rat","совет"},
                {"die Vereinbarung","соглашение; согласие"},
                {"der Vertrag","договор"},
                {"die Liste","список"},
                {"der Streit","спор"},
                {"der Test","тест, испытание"},
                {"die Prüfung","экзамен"},
                {"der Schritt","шаг"},
                {"das Stück","кусок; штука"},
                {"der Bereich","область, сфера"},
                {"das Paar","пара"},
                {"die Oberfläche","поверхность"},
                {"die Ordnung","порядок"},
                {"die Frage","вопрос"},
                {"was","что"},
                {"wer","кто"},
                {"wo","где"},
                {"wohin","куда"},
                {"woher","откуда"},
                {"wie","как"},
                {"welcher","какой"},
                {"warum","почему"},
                {"wozu","зачем"},
        };

        for (int i = 0; i < allCurrentLesson26.length; i++) {
            addLessonData(allCurrentLesson26[i][0],allCurrentLesson26[i][1],26);
        }


        String [][] allCurrentLesson27 = {
                {"wenn","когда; если"},
                {"wie viel (wie viele)","сколько"},
                {"ich","я"},
                {"du","т"},
                {"er","он"},
                {"sie","она"},
                {"es","оно"},
                {"wir","мы"},
                {"ihr","вы"},
                {"sie","они"},
                {"Sie","Вы"},
                {"mein","мой"},
                {"dein","твой"},
                {"sein","его"},
                {"ihr","её; их"},
                {"unser","наш"},
                {"euer","ваш"},
                {"Ihr","Ваш"},
                {"aus","из"},
                {"von","от; передает род. падеж; с (такого-то времени)"},
                {"seit","с (такого-то времени и поныне)"},
                {"zu","к; слишком"},
                {"in","в; через (такое-то время)"},
                {"auf","на"},
                {"unter","под"},
        };

        for (int i = 0; i < allCurrentLesson27.length; i++) {
            addLessonData(allCurrentLesson27[i][0],allCurrentLesson27[i][1],27);
        }


        String [][] allCurrentLesson28 = {
                {"hinter","позади"},
                {"mit","с"},
                {"ohne","без"},
                {"vor","перед, до; (столько-то времени) назад"},
                {"nach","после; в (такой-то город или страну)"},
                {"zwischen","между"},
                {"neben","возле, около"},
                {"für","для; на (такое-то время)"},
                {"während","во время"},
                {"um","вокруг"},
                {"wegen","из-за"},
                {"über","над; о; свыше"},
                {"gegen","против"},
                {"unter","под; среди"},
                {"durch","через"},
                {"pro","(километров) в (час)"},
                {"die Zahl","число, цифра; количество"},
                {"null","ноль"},
                {"eins","один"},
                {"zwei","два"},
                {"drei","три"},
                {"vier","четыре"},
                {"fünf","пять"},
                {"sechs","шесть"},
                {"sieben","семь"},
        };

        for (int i = 0; i < allCurrentLesson28.length; i++) {
            addLessonData(allCurrentLesson28[i][0],allCurrentLesson28[i][1],28);
        }


        String [][] allCurrentLesson29 = {
                {"acht","восемь"},
                {"neun","девять"},
                {"zehn","десять"},
                {"elf","одиннадцать"},
                {"zwölf","двенадцать"},
                {"dreizehn","тринадцать"},
                {"vierzehn","четырнадцать"},
                {"fünfzehn","пятнадцать"},
                {"sechzehn","шестнадцать"},
                {"siebzehn","семнадцать"},
                {"achtzehn","восемнадцать"},
                {"neunzehn","девятнадцать"},
                {"zwanzig","двадцать"},
                {"dreißig","тридцать"},
                {"vierzig","сорок"},
                {"fünfzig","пятьдесят"},
                {"sechzig","шестьдесят"},
                {"siebzig","семьдесят"},
                {"achtzig","восемьдесят"},
                {"neunzig","девяносто"},
                {"hundert","сто"},
                {"tausend","тысяча"},
                {"der erste","первый"},
                {"der zweite","второй"},
                {"der dritte","третий"},
        };

        for (int i = 0; i < allCurrentLesson29.length; i++) {
            addLessonData(allCurrentLesson29[i][0],allCurrentLesson29[i][1],29);
        }


        String [][] allCurrentLesson30 = {
                {"der vierte","четвертый"},
                {"die Farbe","цвет, краска"},
                {"schwarz","чёрный"},
                {"blau","голубой; синий"},
                {"braun","коричневый"},
                {"grün","зелёный"},
                {"grau","серый"},
                {"rot","красный"},
                {"weiß","белый"},
                {"gelb","желтый"},
                {"rosa","розовый"},
                {"violett","фиолетовый"},
                {"alt","старый"},
                {"jung","молодой"},
                {"neu","новый"},
                {"groß","большой"},
                {"riesig","огромный"},
                {"klein","маленький"},
                {"dick","толстый; густой"},
                {"dünn","тонкий"},
                {"hungrig","голодный"},
                {"satt","сытый"},
                {"voll","полный"},
                {"leer","пустой"},
                {"gut","хороший; добрый"},
        };

        for (int i = 0; i < allCurrentLesson30.length; i++) {
            addLessonData(allCurrentLesson30[i][0],allCurrentLesson30[i][1],30);
        }


        String [][] allCurrentLesson31 = {
                {"ausgezeichnet","отличный"},
                {"erstaunlich","удивительный"},
                {"wunderschön","прекрасный, чудесный"},
                {"schlecht","плохой"},
                {"schrecklich","ужасный"},
                {"früh","ранний; рано"},
                {"spät","поздний; поздно"},
                {"vorig","последний, прошлый"},
                {"nächst","следующий"},
                {"frei","свободный"},
                {"kostenlos","бесплатный"},
                {"beschäftigt","занятый"},
                {"heiß","жаркий; горячий"},
                {"warm","тёплый"},
                {"kalt","холодный"},
                {"kühl","прохладный"},
                {"scharf","острый"},
                {"stumpf","тупой (нож)"},
                {"dumm","глупый"},
                {"klug","умный"},
                {"schön","красивый"},
                {"attraktiv","привлекательный"},
                {"hübsch","симпатичный"},
                {"hässlich","уродливый, безобразный, отвратительный"},
                {"hoch","высокий"},
        };

        for (int i = 0; i < allCurrentLesson31.length; i++) {
            addLessonData(allCurrentLesson31[i][0],allCurrentLesson31[i][1],31);
        }


        String [][] allCurrentLesson32 = {
                {"niedrig","низкий"},
                {"lang","длинный; долгий"},
                {"kurz","короткий"},
                {"schwer","тяжёлый"},
                {"leicht","лёгкий"},
                {"schwierig","трудный, тяжелый"},
                {"einfach","простой"},
                {"dunkel","тёмный"},
                {"hell","светлый"},
                {"teuer","дорогой (о цене)"},
                {"lieb","дорогой, милый"},
                {"billig","дешёвый"},
                {"arm","бедный"},
                {"reich","богатый"},
                {"gerade","прямой; прямо"},
                {"links","cлева"},
                {"rechts","справа"},
                {"falsch","неправильный"},
                {"schnell","быстрый"},
                {"langsam","медленный"},
                {"weich","мягкий"},
                {"hart","твёрдый; жесткий; трудный"},
                {"traurig","печальный"},
                {"froh","радостный"},
                {"glücklich","счастливый"},
        };

        for (int i = 0; i < allCurrentLesson32.length; i++) {
            addLessonData(allCurrentLesson32[i][0],allCurrentLesson32[i][1],32);
        }


        String [][] allCurrentLesson33 = {
                {"lustig","веселый"},
                {"böse","сердитый, злой"},
                {"höflich","вежливый"},
                {"grob","грубый"},
                {"zart","нежный"},
                {"mutig","смелый"},
                {"feige","трусливый"},
                {"scheu","застенчивый"},
                {"fleißig","прилежный"},
                {"faul","ленивый"},
                {"nützlich","полезный"},
                {"nutzlos","бесполезный"},
                {"stark","сильный; крепкий"},
                {"schwach","слабый"},
                {"laut","громкий, шумный"},
                {"leise","тихий, негромкий"},
                {"ruhig","тихий, спокойный"},
                {"eng","узкий"},
                {"breit","широкий"},
                {"sauber","чистый"},
                {"schmutzig","грязный"},
                {"müde","усталый"},
                {"ehrlich","честный"},
                {"bequem","уютный"},
                {"seltsam","странный"},
        };

        for (int i = 0; i < allCurrentLesson33.length; i++) {
            addLessonData(allCurrentLesson33[i][0],allCurrentLesson33[i][1],33);
        }


        String [][] allCurrentLesson34 = {
                {"eigen","собственный"},
                {"schmackhaft","вкусный"},
                {"bitter","горький"},
                {"sauer","кислый"},
                {"salzig","соленый"},
                {"bereit","готовый (что-то сделать)"},
                {"fertig","готовый (завершенный)"},
                {"aufmerksam","внимательный"},
                {"vorsichtig","осторожный"},
                {"Haupt...","главный"},
                {"fähig","способный"},
                {"notwendig","необходимый"},
                {"wichtig","важный"},
                {"sicher","уверенный"},
                {"echt","настоящий, истинный;"},
                {"besser","лучше; более хороший"},
                {"best","лучший"},
                {"mehr","больше (по количеству); более"},
                {"meist","наибольший (по количеству)"},
                {"lieber","лучше, охотней"},
                {"am liebsten","охотней всего"},
                {"manchmal","иногда"},
                {"nie","никогда"},
                {"selten","редко"},
                {"gewöhnlich","обычно"},
        };

        for (int i = 0; i < allCurrentLesson34.length; i++) {
            addLessonData(allCurrentLesson34[i][0],allCurrentLesson34[i][1],34);
        }


        String [][] allCurrentLesson35 = {
                {"oft","часто"},
                {"immer","всегда"},
                {"bald","скоро"},
                {"vor kurzem","недавно"},
                {"weit","далеко"},
                {"genau","точно"},
                {"wahrscheinlich","вероятно"},
                {"vielleicht","может быть"},
                {"wirklich","действительно"},
                {"natürlich","конечно"},
                {"sicherlich","наверняка, безусловно"},
                {"offensichtlich","очевидно"},
                {"besonders","особенно"},
                {"gern","охотно"},
                {"ja","да"},
                {"nein","нет"},
                {"nicht","не"},
                {"dieser","этот"},
                {"jener","тот"},
                {"viele","много (+ слово во множественном числе)"},
                {"viel","много (+ слово в единственном числе)"},
                {"wenige","мало (+ слово во множественном числе)"},
                {"wenig","мало (+ слово в единственном числе)"},
                {"einige","некоторые"},
                {"mehrere","несколько"},
        };

        for (int i = 0; i < allCurrentLesson35.length; i++) {
            addLessonData(allCurrentLesson35[i][0],allCurrentLesson35[i][1],35);
        }


        String [][] allCurrentLesson36 = {
                {"jemand","кто-то"},
                {"niemand","никто"},
                {"etwas","что-то"},
                {"nichts","ничто"},
                {"alles","всё"},
                {"alle","все"},
                {"hier","здесь"},
                {"hierher","сюда"},
                {"dort","там"},
                {"dorthin","туда"},
                {"da","тут; так как"},
                {"jetzt","сейчас"},
                {"wieder","снова, опять"},
                {"dann","потом"},
                {"damals","тогда"},
                {"als","чем (при сравнении); когда; в качестве"},
                {"bereits","уже"},
                {"nur","только"},
                {"noch","ещё"},
                {"fast","почти"},
                {"sehr","очень"},
                {"every","каждый"},
                {"jeder","каждый"},
                {"anderer","другой"},
                {"solcher","такой"},
        };

        for (int i = 0; i < allCurrentLesson36.length; i++) {
            addLessonData(allCurrentLesson36[i][0],allCurrentLesson36[i][1],36);
        }


        String [][] allCurrentLesson37 = {
                {"so","так"},
                {"also","итак"},
                {"oben","вверху"},
                {"unten","внизу"},
                {"zusammen","вместе"},
                {"vorwärts","вперед"},
                {"vorne","впереди"},
                {"sogar","даже"},
                {"genug","достаточно"},
                {"auch","тоже, также"},
                {"und","и"},
                {"oder","или"},
                {"aber","но"},
                {"jedoch","однако"},
                {"weil","потому что, так как"},
                {"deshalb","поэтому"},
                {"ob","ли"},
                {"obwohl","хотя"},
                {"dass","что"},
                {"sein","быть, являться"},
                {"tun","делать (вообще)"},
                {"machen","делать (что-то)"},
                {"haben","иметь"},
                {"können","мочь (быть в состоянии)"},
                {"dürfen","мочь (иметь разрешение)"},
        };

        for (int i = 0; i < allCurrentLesson37.length; i++) {
            addLessonData(allCurrentLesson37[i][0],allCurrentLesson37[i][1],37);
        }


        String [][] allCurrentLesson38 = {
                {"wollen","хотеть"},
                {"mögen","любить, нравиться"},
                {"möchten","хотелось бы"},
                {"müssen","быть должным (по внутренним причинам)"},
                {"sollen","быть должным (по внешним причинам)"},
                {"haben zu","должен (что-то сделать)"},
                {"sein zu","должен (быть сделанным)"},
                {"brauchen","нуждаться"},
                {"gehen","идти"},
                {"fahren","ехать"},
                {"finden","находить"},
                {"halten","держать; останавливаться"},
                {"stehen","стоять"},
                {"sitzen","сидеть"},
                {"hören","слышать, слушать"},
                {"gewinnen","побеждать, выигрывать"},
                {"erhalten","получать (офиц.)"},
                {"bekommen","получать"},
                {"kriegen","получать (разг.)"},
                {"kommen","приходить, приезжать"},
                {"werden","становиться; передаёт будущее время; передаёт страдательный залог"},
                {"laufen","бежать"},
                {"sehen","видеть"},
                {"schreiben","писать"},
                {"fallen","падать"},
        };

        for (int i = 0; i < allCurrentLesson38.length; i++) {
            addLessonData(allCurrentLesson38[i][0],allCurrentLesson38[i][1],38);
        }


        String [][] allCurrentLesson39 = {
                {"tragen","носить"},
                {"nehmen","брать"},
                {"geben","давать"},
                {"schenken","дарить"},
                {"entschuldigen","прощать"},
                {"vergessen","забывать"},
                {"essen","кушать"},
                {"liegen","лежать"},
                {"legen","класть"},
                {"lügen","лгать"},
                {"führen","вести"},
                {"füttern","кормить"},
                {"sagen","сказать"},
                {"bezahlen","оплачивать"},
                {"trinken","пить"},
                {"schwimmen","плавать"},
                {"singen","петь"},
                {"beginnen","начинать"},
                {"klingen","звенеть"},
                {"rufen","звать; кричать"},
                {"anrufen","звонить"},
                {"sinken","опускаться; погружаться"},
                {"stinken","вонять"},
                {"fliegen","летать"},
                {"wissen","знать"},
        };

        for (int i = 0; i < allCurrentLesson39.length; i++) {
            addLessonData(allCurrentLesson39[i][0],allCurrentLesson39[i][1],39);
        }


        String [][] allCurrentLesson40 = {
                {"kennen","знать, быть знакомым"},
                {"zeichnen","рисовать, чертить"},
                {"malen","рисовать"},
                {"werfen","кидать, бросать"},
                {"wachsen","расти"},
                {"blasen","дуть"},
                {"sprechen","разговаривать, говорить"},
                {"wählen","выбирать"},
                {"wecken","будить"},
                {"brechen","ломать"},
                {"zeigen","показывать"},
                {"schneiden","резать"},
                {"setzen, sich","садиться"},
                {"kosten","стоить"},
                {"lassen","позволять; оставлять"},
                {"schließen","закрывать"},
                {"schlagen","ударять, бить"},
                {"lesen","читать"},
                {"senden","посылать"},
                {"verbringen","проводить (время)"},
                {"leihen","одолжить"},
                {"bauen","строить"},
                {"fühlen","чувствовать"},
                {"treffen","встречать; попасть (в цель)"},
                {"schlafen","спать"},
        };

        for (int i = 0; i < allCurrentLesson40.length; i++) {
            addLessonData(allCurrentLesson40[i][0],allCurrentLesson40[i][1],40);
        }


        String [][] allCurrentLesson41 = {
                {"verlassen","покидать"},
                {"kaufen","покупать"},
                {"bringen","приносить, привозить"},
                {"lehren","учить, обучать"},
                {"denken","думать"},
                {"kämpfen","бороться"},
                {"fangen","ловить"},
                {"befürchten","бояться, опасаться"},
                {"interessieren, sich","интересоваться"},
                {"überraschen","удивлять"},
                {"aufstehen","вставать"},
                {"ziehen","тянуть; перемещаться"},
                {"anziehen","надевать"},
                {"ausziehen","снимать (одежду)"},
                {"leben","жить"},
                {"wohnen","проживать"},
                {"arbeiten","работать"},
                {"fernsehen","смотреть телевизор"},
                {"waschen","мыть, cтирать"},
                {"rasieren, sich","бриться"},
                {"versuchen","пытаться, пробовать"},
                {"feiern","праздновать"},
                {"lächeln","улыбаться"},
                {"lachen","смеяться"},
                {"weinen","плакать"},
        };

        for (int i = 0; i < allCurrentLesson41.length; i++) {
            addLessonData(allCurrentLesson41[i][0],allCurrentLesson41[i][1],41);
        }


        String [][] allCurrentLesson42 = {
                {"lernen","учить(ся)"},
                {"studieren","учиться (в вузе); изучать"},
                {"ändern","(из)менять"},
                {"öffnen","открывать"},
                {"tanzen","танцевать"},
                {"fragen","спрашивать"},
                {"bitten","просить"},
                {"bieten","предлагать"},
                {"antworten","отвечать"},
                {"sammeln","собирать"},
                {"gefallen","нравиться"},
                {"kochen","варить; готовить"},
                {"backen","печь"},
                {"riechen","пахнуть; нюхать"},
                {"warten","ждать"},
                {"erwarten","ожидать"},
                {"danken","благодарить"},
                {"spielen","играть"},
                {"rauchen","курить"},
                {"wünschen","желать"},
                {"schreien","кричать"},
                {"träumen","мечтать; видеть сон"},
                {"hoffen","надеяться"},
                {"erinnern, sich","вспоминать"},
                {"erinnern","напоминать"},
        };

        for (int i = 0; i < allCurrentLesson42.length; i++) {
            addLessonData(allCurrentLesson42[i][0],allCurrentLesson42[i][1],42);
        }


        String [][] allCurrentLesson43 = {
                {"genießen","наслаждаться; пользоваться"},
                {"erklären","объяснять"},
                {"bleiben","оставаться"},
                {"erholen, sich","отдыхать"},
                {"abbiegen","повернуть"},
                {"heben","поднимать"},
                {"glauben","верить, полагать"},
                {"helfen","помогать"},
                {"bestellen","заказывать"},
                {"befehlen","приказывать"},
                {"besuchen","посещать"},
                {"prüfen","проверять"},
                {"springen","прыгать"},
                {"reisen","путешествовать"},
                {"entscheiden","решать, принимать решение"},
                {"lösen","решать (проблему); растворять"},
                {"stimmen","быть правильным, верным"},
                {"zustimmen","соглашаться"},
                {"retten","спасать"},
                {"speichern","сохранять, накапливать"},
                {"sparen","экономить, копить"},
                {"streiten","спорить"},
                {"zählen","считать"},
                {"sorgen, sich","беспокоиться"},
                {"scherzen","шутить"},
        };

        for (int i = 0; i < allCurrentLesson43.length; i++) {
            addLessonData(allCurrentLesson43[i][0],allCurrentLesson43[i][1],43);
        }


        String [][] allCurrentLesson44 = {
                {"bewegen, sich","двигаться"},
                {"passen","соответствовать, подходить"},
                {"sterben","умирать"},
                {"beeinflussen","влиять"},
                {"unterstützen","поддерживать"},
                {"beschreiben","описывать"},
                {"bestrafen","наказывать"},
                {"vorhaben","намереваться"},
                {"klagen","жаловаться"},
                {"vermeiden","избегать"},
                {"zurückkehren","возвращаться"},
                {"stören","беспокоить, мешать"},
                {"vorstellen","представлять, знакомить"},
                {"kennenlernen","узнавать, знакомиться"},
                {"überzeugen","убеждать"},
                {"genehmigen","позволять, санкционировать, одобрять"},
                {"schätzen","ценить"},
        };

        for (int i = 0; i < allCurrentLesson44.length; i++) {
            addLessonData(allCurrentLesson44[i][0],allCurrentLesson44[i][1],44);
        }

    }

    public int checkTable(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+ Db1000Adapter.myDbHelper1000.TABLE_NAME+"'";
        int name = 0;
        try
        {
            Cursor c = null;
            //c = db.rawQuery("select name from person where id="+id, null);
            c = db.rawQuery(Query, null);
            c.moveToFirst();
            name = c.getCount();
            ///System.out.println(name);
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