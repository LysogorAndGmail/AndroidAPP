package com.example.deutschea1alla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LessonMainActivity extends AppCompatActivity implements View.OnClickListener{

    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_main);

        //myDbAdapter helper = new myDbAdapter(this);
        helper = new myDbAdapter(this);

        int checkTable = helper.checkTable();

        if(checkTable == 0) {
            helper.emptyTable("words");
            helper.createTable();
            helper.addData();
            //ArrayList twoDimArray = helper.getDataArray();
            //String data = helper.getData();
            Message.message(this,"Vstavleno!");
        }else{
            //Message.message(this,"est!");
        }

        ArrayList twoDimArray = helper.getDataArray();
        /*
        for (int i=0; i<5;i++) {
            Button button = new Button(this);
            button.setText("Ввод");
            //setContentView(button);
        }
         */

        if(twoDimArray.size() == 0) {
            helper.emptyTable("words");
            helper.createTable();
            helper.addData();
            Message.message(this,"Vstavleno!");
        }

        System.out.println(twoDimArray.size());

        TextView allWords = findViewById(R.id.all_words);
        allWords.setText(""+twoDimArray.size()+"");

        Button lessonButton = findViewById(R.id.buttonLesson1);
        lessonButton.setOnClickListener(this);

        Button lessonButtonRew = findViewById(R.id.buttonLesson1rew);
        lessonButtonRew.setOnClickListener(this);

        Button lessonButton2 = findViewById(R.id.buttonLesson2);
        lessonButton2.setOnClickListener(this);

        Button lessonButton2Rew = findViewById(R.id.buttonLesson2rew);
        lessonButton2Rew.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonLesson1:

                System.out.println("onClick");
                Intent intent = new Intent(this, LessonActivity.class);
                intent.putExtra("lesson","1/1");
                startActivity(intent);
                break;
            case R.id.buttonLesson1rew:
                System.out.println("onClick");
                Intent intentRew = new Intent(this, LessonActivity.class);
                intentRew.putExtra("lesson","1/2");
                startActivity(intentRew);
                break;
            case R.id.buttonLesson2:
                System.out.println("onClick");
                Intent intent2 = new Intent(this, LessonActivity.class);
                intent2.putExtra("lesson","2/1");
                startActivity(intent2);
                break;
            case R.id.buttonLesson2rew:
                System.out.println("onClick");
                Intent intent2Rew = new Intent(this, LessonActivity.class);
                intent2Rew.putExtra("lesson","2/2");
                startActivity(intent2Rew);
                break;
        }
    }
}