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

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DinamicMainActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_dinamic_main);

        Intent intent = getIntent();
        String cursInfo = intent.getStringExtra("cursInfo");
        //System.out.println("intent full Dinamic Main Activity: "+cursInfo);
        String[] cursInfoData = cursInfo.split("/");

        String TableName = cursInfoData[2];
        String CursTitle = cursInfoData[1];

        TextView kursTitle = findViewById(R.id.curs_title);
        kursTitle.setText(CursTitle);

        helper200 = new Db200Adapter(this);
        helper1000 = new Db1000Adapter(this);
        finishDB = new DbFinishLesson(this);


        ArrayList twoDimArray = helper200.getDataArray();

        switch (TableName) {
            case "words1000":
                twoDimArray = helper1000.getDataArray();
            break;
        }

        String finishCurs =  finishDB.getFinishCurs(TableName);
        //System.out.println("finish curses new "+finishCurs);
        int numberfinishCurs = Integer.parseInt(finishCurs);

        int forViewFinishCurses = numberfinishCurs - 1;
        TextView vsego_projdeno_val = findViewById(R.id.vsego_projdeno_val);
        vsego_projdeno_val.setText(forViewFinishCurses+"");

        //System.out.println(finishCurs.length());
        //System.out.println("finish curs"+finishCurs);

        show = (LinearLayout) findViewById(R.id.show);

        //System.out.println("Kurses.length "+twoDimArray.size());

        ArrayList<LinearLayout> Layouts = new ArrayList<LinearLayout>();
        int countWordInOneLesson = 25;
        int cursLentsh = twoDimArray.size();

        TextView allWords = findViewById(R.id.all_words);
        allWords.setText(""+cursLentsh);

        float result2 = (float) cursLentsh / countWordInOneLesson;
        int result3 = (int)Math.ceil(result2);
        int allElements = 0;
        int fakeNumber = 1;
        for (int i=0; i<result3; i++){

            LinearLayout oneLauout = new LinearLayout(this);

            oneLauout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            Layouts.add(oneLauout);

            Button FirstButtonRiw = new Button(this);
            FirstButtonRiw.setBackgroundResource(R.drawable.word_button);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            params.setMargins(40, 40, 40, 40);
            FirstButtonRiw.setLayoutParams(params);

            FirstButtonRiw.setText("Lesson "+fakeNumber);
            FirstButtonRiw.setTag(fakeNumber+"/"+1+"/"+TableName);
            FirstButtonRiw.setOnClickListener(this);

            if(allElements >= numberfinishCurs) {
                FirstButtonRiw.setEnabled(false);
                FirstButtonRiw.setClickable(false);
            }

            Layouts.get(i).addView(FirstButtonRiw);

            if (allElements < twoDimArray.size()-1) {
                allElements++;
                Button SecondButtonRiw = new Button(this);
                SecondButtonRiw.setBackgroundResource(R.drawable.reverse_button);
                //String [] secondKurs = Kurses.get([allElements]);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                params2.setMargins(40, 40, 40, 40);
                SecondButtonRiw.setLayoutParams(params2);

                SecondButtonRiw.setText("Reverse "+fakeNumber);
                SecondButtonRiw.setTag(fakeNumber+"/"+2+"/"+TableName);
                SecondButtonRiw.setOnClickListener(this);

                if(allElements >= numberfinishCurs) {
                    SecondButtonRiw.setEnabled(false);
                    SecondButtonRiw.setClickable(false);
                }

                Layouts.get(i).addView(SecondButtonRiw);

            }

            show.addView(Layouts.get(i));
            if (allElements < twoDimArray.size()) {
                allElements++;
                fakeNumber++;
            }
        }
         //*/

    }

    @Override
    public void onClick(View view) {

        String CursNumber = view.getTag().toString();
        //String[] lessonIntent = CursNumber.split("/");

        //System.out.println("Kurses.length "+lessonIntent[0]);

        Intent intentRew = new Intent(this, DinamicActivity.class);
        intentRew.putExtra("lesson",CursNumber);
        //intentRew.putExtra("cursTitle",lessonIntent[1]);
        startActivity(intentRew);
/*


        //System.out.println(pageNumber);
        /*
        switch (pageNumber) {
            case "0":
                System.out.println("onClick");
                Intent intentRew = new Intent(this, Words200MainActivity.class);
                intentRew.putExtra("lesson","1/2");
                startActivity(intentRew);
                break;
            case "1":
                System.out.println("onClick");
                Intent intent = new Intent(this, LessonMainActivity.class);
                intent.putExtra("lesson","1/1");
                startActivity(intent);
                break;
        }

         */

    }

    public void dinamickButton(String param)
    {
        //String data = "sdfsfsd dsf d";
        //Message.message(MainActivity.this,data);
        //System.out.println(param);
    }

}