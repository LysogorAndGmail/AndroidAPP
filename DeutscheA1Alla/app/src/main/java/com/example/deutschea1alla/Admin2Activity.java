package com.example.deutschea1alla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class Admin2Activity extends AppCompatActivity {
    EditText Name, Pass , updateold, updatenew, delete;
    Db200Adapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        updateold= (EditText) findViewById(R.id.editText3);
        updatenew= (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new Db200Adapter(this);
    }

    public void addData(View view) {

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
            helper.addLessonData(allCurrentLesson[i][0],allCurrentLesson[i][1],1);
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
            helper.addLessonData(allCurrentLesson1[i2][0],allCurrentLesson1[i2][1],2);
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
            helper.addLessonData(allCurrentLesson2[i3][0],allCurrentLesson2[i3][1],3);
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
            helper.addLessonData(allCurrentLesson3[i4][0],allCurrentLesson3[i4][1],4);
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
            helper.addLessonData(allCurrentLesson4[i5][0],allCurrentLesson4[i5][1],5);
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
            helper.addLessonData(allCurrentLesson5[i6][0],allCurrentLesson5[i6][1],6);
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
            helper.addLessonData(allCurrentLesson6[i7][0],allCurrentLesson6[i7][1],7);
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
            helper.addLessonData(allCurrentLesson7[i8][0],allCurrentLesson7[i8][1],8);
        }

        Message.message(this,"insert ("+allCurrentLesson.length+")");
        Message.message(this,"insert2 ("+allCurrentLesson2.length+")");
        Message.message(this,"insert3 ("+allCurrentLesson3.length+")");
        Message.message(this,"insert4 ("+allCurrentLesson4.length+")");
    }

    public void deleteData(View view) {
        helper.emptyTable("words200");
        Message.message(this,"delete table words");
    }

    public void createTable(View view) {
        helper.createTable();
        Message.message(this,"create table");
    }

    public void showAllData(View view) {
        ArrayList twoDimArray = helper.getDataArray();
        //String data = helper.getData();
        Message.message(this,twoDimArray.toString());
    }
}
