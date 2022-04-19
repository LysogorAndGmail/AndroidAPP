package com.example.deutschea1alla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Words200MainActivity extends AppCompatActivity implements View.OnClickListener {

    Db200Adapter helper;
    Db200Adapter helper200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words200_main);

        helper200 = new Db200Adapter(this);

        int checkTable = helper200.checkTable("words2000");

        if(checkTable == 0) {
            helper200.emptyTable("words200");
            helper200.createTable();
            helper200.addData();
            //ArrayList twoDimArray = helper.getDataArray();
            //String data = helper.getData();
            Message.message(this,"Vstavleno!");
        }else{
            //Message.message(this,"est!");
        }

        ArrayList twoDimArray = helper200.getDataArray();

        if(twoDimArray.size() == 0) {
            helper200.emptyTable("words200");
            helper200.createTable();
            helper200.addData();
            Message.message(this,"Vstavleno!");
        }

        for (int i=0; i<5;i++) {
            Button button = new Button(this);
            button.setText("Ввод");
            //setContentView(button);
        }

        //System.out.println(twoDimArray.size());

        TextView allWords = findViewById(R.id.all_words);
        allWords.setText(""+twoDimArray.size()+"");

        Button lessonButton = findViewById(R.id.buttonLesson1);
        lessonButton.setOnClickListener(this);

        Button lessonButton2 = findViewById(R.id.buttonLesson2);
        lessonButton2.setOnClickListener(this);

        Button lessonButton3 = findViewById(R.id.buttonLesson3);
        lessonButton3.setOnClickListener(this);

        Button lessonButton4 = findViewById(R.id.buttonLesson4);
        lessonButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonLesson1:
                Intent intent = new Intent(this, Words200Activity.class);
                intent.putExtra("lesson","1");
                startActivity(intent);
                break;
            case R.id.buttonLesson2:
                Intent intentRew = new Intent(this, Words200Activity.class);
                intentRew.putExtra("lesson","2");
                startActivity(intentRew);
                break;
            case R.id.buttonLesson3:
                Intent intent2 = new Intent(this, Words200Activity.class);
                intent2.putExtra("lesson","3");
                startActivity(intent2);
                break;
            case R.id.buttonLesson4:
                Intent intent2Rew = new Intent(this, Words200Activity.class);
                intent2Rew.putExtra("lesson","4");
                startActivity(intent2Rew);
                break;
        }
    }

    private void showMessage(Words200MainActivity lessonActivity, String message) {
        Toast toast = Toast.makeText(lessonActivity, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}