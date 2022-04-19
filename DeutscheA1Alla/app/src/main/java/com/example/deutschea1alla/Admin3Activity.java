package com.example.deutschea1alla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Admin3Activity extends AppCompatActivity {

    EditText Name, Pass , updateold, updatenew, delete;
    DbFinishLesson helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin3);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        updateold= (EditText) findViewById(R.id.editText3);
        updatenew= (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new DbFinishLesson(this);
    }

    public void addData(View view) {

       String curs = "200 slov";
       int lesson = 1;

        helper.ubdateFinishLesson(curs,lesson);


        Message.message(this,"update (curs: "+curs+", lesson: "+lesson+")");
    }

    public void addAllCursesData(View view) {

        String curs = "allCurses";
        int lesson = 1;

        helper.ubdateFinishLesson(curs,lesson);

        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        this.finish();

        Message.message(this,"update (curs: "+curs+", allCurses: "+lesson+")");
    }

    public void updateFineshCursesData(String curs, int lesson) {

        helper.ubdateFinishLesson(curs,lesson);

        Message.message(this,"update (curs: "+curs+", allCurses: "+lesson+")");
    }

    public void deleteData(View view) {
        helper.emptyTable("finish");
        Message.message(this,"delete table finish");
    }

    public void createTable(View view) {
        helper.createTable();
        Message.message(this,"create table finish");
    }

    public void showAllData(View view) {
        //StringBuffer twoDimArray = helper.getDataArray();
        System.out.println("test");
        //String data = helper.getData();
        //Message.message(this,twoDimArray.toString());
    }
}