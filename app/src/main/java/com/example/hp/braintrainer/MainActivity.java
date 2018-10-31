package com.example.hp.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ConstraintLayout constraintLayout;
    CountDownTimer countDownTimer;
    TextView resultTextView;

    TextView timerTextView;
    TextView scoreTextView;

    int score=0;
    int noOfQues=0;

    GridLayout gridLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    int locationofCorrectAnswer;
    ArrayList<Integer> answers;
    Button playAgainButton;

    public void startButtonFunc(View view){
     startButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){

        resultTextView.setVisibility(View.VISIBLE);
        if(Integer.toString(locationofCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong");
        }
        noOfQues++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQues));
        playgame();
    }
    public void playgame(){


        Random random=new Random();
        int a=random.nextInt(21);

        int b=random.nextInt(21);

        TextView quesTextView=findViewById(R.id.quesTextView);
        quesTextView.setText(String.valueOf(a)+" + "+String.valueOf(b));
         scoreTextView=findViewById(R.id.scoreTextView);
        //scoreTextView.setText(String.valueOf(score)+"/"+String.valueOf(noOfQues));
        locationofCorrectAnswer=random.nextInt(4);
        answers.clear();
        for (int i=0;i<4;i++){

            if (locationofCorrectAnswer==i){
                answers.add(a+b);
            }else {
                int wronganswer=random.nextInt(41);
                while (wronganswer==a+b){
                    wronganswer=random.nextInt(41);
                }
                answers.add(wronganswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void timercount(){

        countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000+"s"));
            }

            @Override
            public void onFinish() {
                Button playAgainButton=findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);

                gridLayout.setEnabled(false);

                resultTextView.setText("Time's Up!");


            }
        }.start();
    }
    public void playAgain(View view){
        score=0;
        noOfQues=0;

        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        timerTextView.setText("0:30s");
        scoreTextView.setText("0/0");
        playgame();
        timercount();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answers=new ArrayList<>();
        startButton=(Button)findViewById(R.id.startButton);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        constraintLayout=findViewById(R.id.cons);

        gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        resultTextView=findViewById(R.id.resultTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        timerTextView=findViewById(R.id.timerTextView);
        resultTextView.setVisibility(View.INVISIBLE);

        playgame();

        timercount();


    }
}
