package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public TextView wrongSentence;
    public TextView correctSentence;
    public Button checkButton;
    public Button nextButton;
    public int id;
    public int randomquizID(){
        Random r = new Random();
        int low = 1;
        int high = 425;
        int quizID = r.nextInt(high-low) + low;
        return quizID;
    }
    public String wrongSentenceValue;
    public String correctSentenceValue;
    public void setSentences(){
        id= randomquizID();
        DatabaseAcces databaseAcces=DatabaseAcces.getInstance(getApplicationContext());
        databaseAcces.open();
        wrongSentenceValue=databaseAcces.getSentence("wrong",id);
        wrongSentence.setText(wrongSentenceValue);
        correctSentenceValue=databaseAcces.getSentence("correct",id);

        databaseAcces.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wrongSentence=findViewById(R.id.WrongSentence);
        correctSentence=findViewById(R.id.CorrectSentence);
        checkButton=findViewById(R.id.CheckButton);
        nextButton=findViewById(R.id.NextButton);
        setSentences();

        //now add onClickListener to the CheckButton

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctSentence.setText(correctSentenceValue);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSentences();
                correctSentence.setText("");
            }
        });
    }
}