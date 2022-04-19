package com.example.deutschea1alla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class AdminActivity extends AppCompatActivity {
    EditText Name, Pass , updateold, updatenew, delete;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        updateold= (EditText) findViewById(R.id.editText3);
        updatenew= (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new myDbAdapter(this);
    }
    public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else
        {
            long id = helper.insertData(t1,t2,0);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                Name.setText("");
                Pass.setText("");
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }

    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Message.message(getApplicationContext(),"Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }
    public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"Unsuccessful");
                delete.setText("");
            }
            else
            {
                Message.message(this, "DELETED");
                delete.setText("");
            }
        }
    }

    public void showAllData(View view) {
        ArrayList twoDimArray = helper.getDataArray();
        //String data = helper.getData();
        Message.message(this,twoDimArray.toString());
    }

    public void addData(View view) {

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
            helper.addLessonData(allCurrentLesson[i][0],allCurrentLesson[i][1],1);
        }
        Message.message(this,"insert ("+allCurrentLesson.length+")");


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
            helper.addLessonData(allCurrentLesson2[i2][0],allCurrentLesson2[i2][1],2);
        }

        Message.message(this,"insert2 ("+allCurrentLesson2.length+")");
    }

    public void deleteData(View view) {
        helper.emptyTable("words");
        Message.message(this,"delete table words");
    }

    public void createTable(View view) {
        helper.createTable();
        Message.message(this,"create table");
    }
}
