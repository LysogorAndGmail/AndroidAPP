package com.example.deutschea1alla;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Button> buttonList = new ArrayList<Button>();
    public LinearLayout show;

    LinearLayout layout;
    myDbAdapter helper;
    Db200Adapter helper200;
    Db1000Adapter helper1000;
    DbFinishLesson finishDB;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [][] Kurses = {
                {"1","200 Слов","words200"},
                {"2","1000 Слов","words1000"},
                {"3","ЕШКО (A1)","eshko"},
                {"4","Deutsche (A1)","deutsche"}
        };

        int numberfinishCurs = 2;

        finishDB = new DbFinishLesson(this);
        helper200 = new Db200Adapter(this);
        helper1000 = new Db1000Adapter(this);

        String finishCurs =  finishDB.getFinishCurs("allCurses");
        TextView info1 = findViewById(R.id.info1);
        info1.setText("finishDB.getFinishCurs(allCurses): "+finishCurs);

        if(finishCurs.length() == 0) {
            finishDB.emptyTable("finish");
            finishDB.createTable();
            finishDB.addFirstData("allCurses");
            finishDB.ubdateFinishLesson("allCurses",numberfinishCurs);

            finishDB.addFirstData("words200");
            helper200.emptyTable("words200");
            helper200.createTable();
            helper200.addData();

            finishDB.addFirstData("words1000");
            helper1000.emptyTable("words1000");
            helper1000.createTable();
            helper1000.addData();
        }

        String finishCursFirst =  finishDB.getFinishCurs("words200");
        TextView info2 = findViewById(R.id.info2);
        info2.setText("finishDB.getFinishCurs(words200): "+finishCursFirst);

        String finishCursFirst2 =  finishDB.getFinishCurs("words1000");
        TextView info3 = findViewById(R.id.info3);
        info3.setText("finishDB.getFinishCurs(words1000): "+finishCursFirst2);

        ArrayList twoDimArray200 = helper200.getDataArray();
        TextView info4 = findViewById(R.id.info4);
        info4.setText("helper200.getDataArray(): "+twoDimArray200.size());

        ArrayList twoDimArray1000 = helper1000.getDataArray();
        TextView info5 = findViewById(R.id.info5);
        info5.setText("helper1000.getDataArray(): "+twoDimArray1000.size());

        show = (LinearLayout) findViewById(R.id.show);

        ArrayList<LinearLayout> Layouts = new ArrayList<LinearLayout>();
        int countLines = (Kurses.length+(2-1))/2;
        int allElements = 0;

        for (int i=0; i<countLines; i++){

            LinearLayout oneLauout = new LinearLayout(this);

            oneLauout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            Layouts.add(oneLauout);

            String [] oneKurs = Kurses[allElements];

            Button FirstButtonRiw = new Button(this);
            FirstButtonRiw.setBackgroundResource(R.drawable.rounded_corner);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            params.setMargins(40, 40, 40, 40);
            FirstButtonRiw.setLayoutParams(params);

            FirstButtonRiw.setText(oneKurs[1]);
            FirstButtonRiw.setTag(allElements+"/"+oneKurs[1]+"/"+oneKurs[2]);
            FirstButtonRiw.setOnClickListener(this);

            if(allElements >= numberfinishCurs) {
                FirstButtonRiw.setEnabled(false);
                FirstButtonRiw.setClickable(false);
            }

            Layouts.get(i).addView(FirstButtonRiw);

            if (allElements < Kurses.length-1) {
                allElements++;
                Button SecondButtonRiw = new Button(this);
                SecondButtonRiw.setBackgroundResource(R.drawable.rounded_corner);
                String [] secondKurs = Kurses[allElements];
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                params2.setMargins(40, 40, 40, 40);
                SecondButtonRiw.setLayoutParams(params2);
                //SecondButtonRiw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
                SecondButtonRiw.setText(secondKurs[1]);
                SecondButtonRiw.setTag(allElements+"/"+secondKurs[1]+"/"+secondKurs[2]);
                SecondButtonRiw.setOnClickListener(this);
                if(allElements >= numberfinishCurs) {
                    SecondButtonRiw.setEnabled(false);
                    SecondButtonRiw.setClickable(false);
                }
                Layouts.get(i).addView(SecondButtonRiw);

            }

            show.addView(Layouts.get(i));
            if (allElements < Kurses.length) {
                allElements++;
            }

        }

        Button AdminButton = findViewById(R.id.buttonAdmin);
        AdminButton.setOnClickListener(this);

        Button Admin2Button = findViewById(R.id.buttonAdmin2);
        Admin2Button.setOnClickListener(this);

        Button Admin3Button = findViewById(R.id.buttonAdmin3);
        Admin3Button.setOnClickListener(this);

        Button lessonButton = findViewById(R.id.buttonA1);
        lessonButton.setOnClickListener(this);

        Button lessonButtonRew = findViewById(R.id.button200Words);
        lessonButtonRew.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonAdmin:
                //System.out.println("onClick");
                Intent intent3 = new Intent(this, AdminActivity.class);
                intent3.putExtra("admin","1");
                startActivity(intent3);
                break;
            case R.id.buttonAdmin2:
                //System.out.println("onClick");
                Intent intent4 = new Intent(this, Admin2Activity.class);
                intent4.putExtra("admin","1");
                startActivity(intent4);
                break;
            case R.id.buttonAdmin3:
                //System.out.println("onClick");
                Intent intent5 = new Intent(this, Admin3Activity.class);
                intent5.putExtra("admin","1");
                startActivity(intent5);
                break;
        }
        ///*
        String pageNumber = view.getTag().toString();
        //String[] lessonIntent = pageNumber.split("/");
        //System.out.println(pageNumber);

        Intent intentRew = new Intent(this, DinamicMainActivity.class);
        intentRew.putExtra("cursInfo",pageNumber);
        //intentRew.putExtra("cursTitle",lessonIntent[1]);
        startActivity(intentRew);

         //*/

    }

    public void dinamickButton(String param)
    {
        //String data = "sdfsfsd dsf d";
        //Message.message(MainActivity.this,data);
        //System.out.println(param);
    }

}