package com.example.deutschea1alla;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonActivity extends AppCompatActivity implements View.OnClickListener {

    private String [][] allCurrentLessonFull;

    private ArrayList<CurrentNode> allCurrentLesson;

    private ArrayList<FakeNode> fakeLessonArray;

    private String userMastSelected;

    private String userSelected;

    private int vibral = 0;

    private int countsWordShow = 0;

    private int progress = 0;

    private int pravilnie = 0;

    private int nePravilnie = 0;

    private int pravilnijOtvetButton;

    private int[] typeWords = {2,1};

    public String[][] getAllCurrentLessonFull() {

        return allCurrentLessonFull;
    }

    public void setAllCurrentLessonFull(String [][] nodes) {
        this.allCurrentLessonFull = nodes;
    }

    public ArrayList<CurrentNode> getAllCurrentLesson() {
        return allCurrentLesson;
    }
    public void setAllCurrentLesson(ArrayList<CurrentNode> nodes) {
        this.allCurrentLesson = nodes;
    }

    public ArrayList<FakeNode> getFakeLessonArray() {
        return fakeLessonArray;
    }
    public void setFakeLessonArray(ArrayList<FakeNode> nodes) {
        this.fakeLessonArray = nodes;
    }

    public String getUserMastSelected() {
        return userMastSelected;
    }

    public void setUserMastSelected(String userSelected) {
        this.userMastSelected = userSelected;
    }

    public String getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(String userSelected) {
        this.userSelected = userSelected;
    }

    public int getVibral() {
        return vibral;
    }

    public void setVibral(int vibral) {
        this.vibral = vibral;
    }

    public int getPravilnijOtvetButton() {
        return pravilnijOtvetButton;
    }

    public void setPravilnijOtvetButton(int numberButt) {
        this.pravilnijOtvetButton = numberButt;
    }

    public void setTypeWords(int [] typeWords) {
        this.typeWords = typeWords;
    }

    public int [] getTypeWords() {
        return this.typeWords;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private class Node {

        private String [] nodeData;

        public Node(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

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
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    private class FakeNode {

        private String [] nodeData;

        public FakeNode(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < nodeData.length; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Intent intent = getIntent();
        String lessonNumber = intent.getStringExtra("lesson");

        String[] lessonIntent = lessonNumber.split("/");

        TextView allWords = findViewById(R.id.lessonNumber);
        allWords.setText("Lesson "+lessonIntent[0]+"");

        if(lessonIntent[1].trim().equals("2")){
            setTypeWords(new int[]{1, 2});
        }

        //System.out.println(lessonIntent[1].trim());

        /*
        String [][] allCurrentLesson = {
                {"1","der Abend","вечер"},
                {"1","der Abendkurs","вечерний курс"},
                {"1","abends","вечерами"},
                {"1","aber","но"},
                {"1","der Abschied","прощание"},
                {"1","der Abschnitt","отрезок, частъ, раздел"},
                {"1","acht","восемь"},
                {"1","der Actionfilm","боевик"},
                {"1","die Adresse","адрес"},
                {"1","afrikanisch","африканский"},
                {"1","der Akkusativ","Винит.падеж"}
        };
        */

        helper = new myDbAdapter(this);
        String [][] allCurrentLesson =  helper.getDataArrayLesson(lessonIntent[0]);

        ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setMax(allCurrentLesson.length); // 100 maximum value for the progress value

        ArrayList<CurrentNode> Currentnodes = new ArrayList<>();

        for (int i = 0; i < allCurrentLesson.length; i++) {
            String [] begermod = allCurrentLesson[i];
            Currentnodes.add(new CurrentNode(begermod));
        }

        setAllCurrentLessonFull(allCurrentLesson);

        //System.out.println(Currentnodes.toString());

        setAllCurrentLesson(Currentnodes);

        showOneScene(this);

    }

    private int rnd(int min, int max){
        int x = min + (int)(Math.random() * ((max -min) + 1));
        return  x;
    }

    @Override
    public void onClick(View view) {

        ArrayList<CurrentNode> currentLessonWords = getAllCurrentLesson();

        String userSelected = getUserSelected();
        int vibral = getVibral();

        int nextButton = 0;

        switch (view.getId()) {
            case R.id.answerButton1:
                setVibral(vibral+1);
                Button answerButton1 = findViewById(R.id.answerButton1);
                userSelected = (String) answerButton1.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;
            case R.id.answerButton2:
                setVibral(vibral+1);
                Button answerButton2 = findViewById(R.id.answerButton2);
                userSelected = (String) answerButton2.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;

            case R.id.answerButton3:
                setVibral(vibral+1);
                Button answerButton3 = findViewById(R.id.answerButton3);
                userSelected = (String) answerButton3.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;

            case R.id.answerButton4:
                setVibral(vibral+1);
                Button answerButton4 = findViewById(R.id.answerButton4);
                userSelected = (String) answerButton4.getText();
                if(vibral == 1) {
                    setUserSelected(userSelected);
                }
                break;

            case R.id.nextButton:
                nextButton = 1;

                break;

        }

        vibral = getVibral();

        //if(vibral == )

        //System.out.println("next button: "+nextButton);
        //System.out.println("user selected: "+userSelected);
        //System.out.println("vibral raz: "+vibral);

        if(nextButton == 0) {
            String message = "";
            String userMastSelected = getUserMastSelected();
            Button CorrectButton = findViewById(view.getId());
            if(userSelected == userMastSelected) {
                CorrectButton.setTextColor(Color.GREEN);
                message = "Geroj";
                this.pravilnie++;
            }else{
                CorrectButton.setTextColor(Color.RED);
                int pravilnijOtvetButton = getPravilnijOtvetButton();
                switch (pravilnijOtvetButton) {
                    case 1:
                        Button answerButton1 = findViewById(R.id.answerButton1);
                        answerButton1.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        Button answerButton2 = findViewById(R.id.answerButton2);
                        answerButton2.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        Button answerButton3 = findViewById(R.id.answerButton3);
                        answerButton3.setTextColor(Color.GREEN);
                        break;
                    case 4:
                        Button answerButton4 = findViewById(R.id.answerButton4);
                        answerButton4.setTextColor(Color.GREEN);
                        break;
                }
                message = "Tupen";
                this.nePravilnie++;
            }
            showMessage(this, message);
        }else{
            setUserSelected(null);
            setVibral(0);
            showOneScene(this);
        }

    }

    private void showOneScene(LessonActivity lessonActivity) {

        ArrayList<CurrentNode> currentLessonWords = getAllCurrentLesson();
        String [][] allCurrentLessonFull = getAllCurrentLessonFull();
        ArrayList<FakeNode> Fakenodes = new ArrayList<FakeNode>();

        int [] rev = getTypeWords();


        for (int i = 0; i < allCurrentLessonFull.length; i++) {
            Fakenodes.add(new FakeNode(allCurrentLessonFull[i]));
        }

        int randomNumberFromWordArray = (int)Math.floor(Math.random() * currentLessonWords.size());
        TextView currentWord = findViewById(R.id.currentWord);

        //System.out.println(currentLessonWords);

        CurrentNode mastSelected = currentLessonWords.get(randomNumberFromWordArray);

        //System.out.println(mastSelected.toString());

        setUserMastSelected(mastSelected.getNodeData(rev[0]));
        currentWord.setText(mastSelected.getNodeData(rev[1]));

        currentLessonWords.remove(randomNumberFromWordArray);

        //System.out.println(mastSelected.toString());
        //System.out.println(Fakenodes.toString());
        for(int ii=0;ii<Fakenodes.size();ii++){
            if(Fakenodes.get(ii).getNodeData(rev[0]) == mastSelected.getNodeData(rev[0])){
                Fakenodes.remove(ii);
            }
        }
        //System.out.println(Fakenodes.toString());

        int randomFake = (int)Math.floor(Math.random() * Fakenodes.size());
        FakeNode fakeNode1 = Fakenodes.get(randomFake);
        String fakeElement1 = fakeNode1.getNodeData(rev[0]);
        Fakenodes.remove(randomFake);

        int randomFake2 = (int)Math.floor(Math.random() * Fakenodes.size());
        FakeNode fakeNode2 = Fakenodes.get(randomFake2);
        String fakeElement2 = fakeNode2.getNodeData(rev[0]);
        Fakenodes.remove(randomFake2);

        int randomFake3 = (int)Math.floor(Math.random() * Fakenodes.size());
        FakeNode fakeNode3 = Fakenodes.get(randomFake3);
        String fakeElement3 = fakeNode3.getNodeData(rev[0]);
        Fakenodes.remove(randomFake3);
        //System.out.println(Fakenodes.toString());

        int randomWord = rnd(1,4);

        setPravilnijOtvetButton(randomWord);
        String userMastSElect = getUserMastSelected();

        switch (randomWord) {
            case 1:
                Button answerButton1 = findViewById(R.id.answerButton1);
                answerButton1.setText(userMastSElect);

                Button answerButton2 = findViewById(R.id.answerButton2);
                answerButton2.setText(fakeElement1);

                Button answerButton3 = findViewById(R.id.answerButton3);
                answerButton3.setText(fakeElement2);

                Button answerButton4 = findViewById(R.id.answerButton4);
                answerButton4.setText(fakeElement3);

                break;
            case 2:
                Button answerButton22 = findViewById(R.id.answerButton2);
                answerButton22.setText(userMastSElect);

                Button answerButton12 = findViewById(R.id.answerButton1);
                answerButton12.setText(fakeElement1);

                Button answerButton42 = findViewById(R.id.answerButton4);
                answerButton42.setText(fakeElement2);

                Button answerButton32 = findViewById(R.id.answerButton3);
                answerButton32.setText(fakeElement3);

                break;
            case 3:
                Button answerButton33 = findViewById(R.id.answerButton3);
                answerButton33.setText(userMastSElect);

                Button answerButton43 = findViewById(R.id.answerButton4);
                answerButton43.setText(fakeElement1);

                Button answerButton13 = findViewById(R.id.answerButton1);
                answerButton13.setText(fakeElement2);

                Button answerButton23 = findViewById(R.id.answerButton2);
                answerButton23.setText(fakeElement3);

                break;
            case 4:
                Button answerButton44 = findViewById(R.id.answerButton4);
                answerButton44.setText(userMastSElect);

                Button answerButton34 = findViewById(R.id.answerButton3);
                answerButton34.setText(fakeElement1);

                Button answerButton24 = findViewById(R.id.answerButton2);
                answerButton24.setText(fakeElement2);

                Button answerButton14 = findViewById(R.id.answerButton1);
                answerButton14.setText(fakeElement3);

                break;
        }

        Button answerButton1 = findViewById(R.id.answerButton1);
        answerButton1.setTextColor(Color.BLACK);
        answerButton1.setOnClickListener(lessonActivity);

        Button answerButton2 = findViewById(R.id.answerButton2);
        answerButton2.setTextColor(Color.BLACK);
        answerButton2.setOnClickListener(lessonActivity);

        Button answerButton3 = findViewById(R.id.answerButton3);
        answerButton3.setTextColor(Color.BLACK);
        answerButton3.setOnClickListener(lessonActivity);

        Button answerButton4 = findViewById(R.id.answerButton4);
        answerButton4.setTextColor(Color.BLACK);
        answerButton4.setOnClickListener(lessonActivity);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(lessonActivity);

        this.countsWordShow++;

        ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar);

        simpleProgressBar.setProgress(countsWordShow);

        updateStatistic(this);

    }

    private void showMessage(LessonActivity lessonActivity, String message) {
        Toast toast = Toast.makeText(lessonActivity, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void updateStatistic(LessonActivity lessonActivity) {

        String [][] allWordsInLessonArray = getAllCurrentLessonFull();

        int allWordsInLesson = allWordsInLessonArray.length;

        //TextView allWords = findViewById(R.id.countWordsTitleTop);
        //allWords.setText(""+allWordsInLesson+"");
        TextView currentWordTitle = findViewById(R.id.statistikcountWordsTitleTop);
        currentWordTitle.setText(""+this.pravilnie+"");

        TextView leftWord = findViewById(R.id.statistik2countWordsTitleTop);
        leftWord.setText(""+this.nePravilnie+"");



    }

    public class Cat {

        private String [] name;

        public Cat(String [] name) {
            this.name = name;
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < name.length; i++) {
                st += name[i]+" ";
            }
            return st;
        }
    }

}


