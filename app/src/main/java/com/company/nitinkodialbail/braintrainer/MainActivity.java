package com.company.nitinkodialbail.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    Button goButton;
    Button playAgain;
    TextView sumTextView;
    TextView pointsTextView;
    TextView timeTextView;
    TextView resultTextView;
    int score;
    int numOfQuestions;
    ArrayList<Integer> answerChoices =  new ArrayList<Integer>();
    int posOfCorrectAnswer;
    Boolean isGameActive;
    RelativeLayout relativeLayout ;

    public void onClickGoButton(View view){
        goButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){
        if(isGameActive) {
            String chosenAnswer = view.getTag().toString();
            if (chosenAnswer.equals(Integer.toString(posOfCorrectAnswer))) {
                resultTextView.setText("Correct");
                score++;
            } else {
                resultTextView.setText("Wrong");
            }
            resultTextView.setVisibility(View.VISIBLE);
            numOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));

            generateQuestion();
        }
    }

    public void generateQuestion(){

        Random rand = new Random();

        int num1 = rand.nextInt(21);
        int num2 = rand.nextInt(21);
        sumTextView.setText(Integer.toString(num1)+" + "+ Integer.toString(num2));

        answerChoices.clear();
        posOfCorrectAnswer = rand.nextInt(4);

        for(int i = 0;i<4;i++){
            if(i==posOfCorrectAnswer){
                //add correct answer
                answerChoices.add(num1+num2);
            }
            else{
                int randAnswer = rand.nextInt(41);
                while(randAnswer==(num1+num2)){
                    randAnswer = rand.nextInt(41);
                }
                //add wrong answers
                answerChoices.add(randAnswer);
            }
        }
//        Log.i("debug",Integer.toString(answerChoices.get(0)));
        button0.setText(Integer.toString(answerChoices.get(0)));
        button1.setText(Integer.toString(answerChoices.get(1)));
        button2.setText(Integer.toString(answerChoices.get(2)));
        button3.setText(Integer.toString(answerChoices.get(3)));

    }

    public void playAgain(View view){
//        Log.i("debug","playAgain button");
        playAgain.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        score = 0;
        numOfQuestions = 0;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numOfQuestions));
        timeTextView.setText("30s");
        isGameActive = true;
        new CountDownTimer(31000,100) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(Long.toString(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                isGameActive = false;
                resultTextView.setText("Your score: "+Integer.toString(score)+"/"+Integer.toString(numOfQuestions));
                resultTextView.setVisibility(View.VISIBLE);
                timeTextView.setText("0s");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        generateQuestion();

    }

    public  void start(View view){
        relativeLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        playAgain(playAgain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        button0 = (Button) findViewById(R.id.choice0Button);
        button1 = (Button) findViewById(R.id.choice1Button);
        button2 = (Button) findViewById(R.id.choice2Button);
        button3 = (Button) findViewById(R.id.choice3Button);
        resultTextView.setVisibility(View.INVISIBLE);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);

    }
}
