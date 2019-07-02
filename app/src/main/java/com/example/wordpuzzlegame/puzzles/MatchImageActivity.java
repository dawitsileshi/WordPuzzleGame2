package com.example.wordpuzzlegame.puzzles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.MatchImageDialog;
import com.example.wordpuzzlegame.R;
import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.Results;
import com.example.wordpuzzlegame.model.Word;

import java.util.ArrayList;
import java.util.Random;

public class MatchImageActivity extends AppCompatActivity implements MatchImageDialog.OKClicked {

    private TextView tv_activity_match_image_questionNumber,
            tv_activity_match_image_choiceA,
            tv_activity_match_image_choiceB,
            tv_activity_match_image_choiceC,
            tv_activity_match_image_choiceD,
            tv_activity_match_image_clock;

    private CardView cv_activity_match_image_choiceA,
            cv_activity_match_image_choiceB,
            cv_activity_match_image_choiceC,
            cv_activity_match_image_choiceD;

    private TextView[] textViews;
    private CardView[] cardViews;

    private ImageView iv_activity_match_image,
            iv_activity_match_image_clock,
            iv_activity_match_image_pause;

    private Button button_activity_match_image;

    private Word word;
    private Word singleWord;

    ArrayList<Word> words;
    ArrayList<Word> choices;
    ArrayList<Integer> randomIndices;
    ArrayList<Integer> choiceIndices;

    DataSource dataSource;

    private int startIndex;
    private int index;
    private int correctAnswerSpot;
    private int doubleTap = 0;

    private long maxMillSeconds = 25000, millSecondLeft = maxMillSeconds;
    private long totalMill = 0, currentMilli = 0;

    private Random random;

    private String correctAnswer, wrongAnswer;

    private boolean tried;
    private boolean paused = false;
    private boolean timeout = false;

    private MatchImageDialog matchImageDialog;

    private CountDownTimer countDownTimer;
    private Animation animation;

    private Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_image);

        dataSource = new DataSource(this);
        word = new Word(this);
        random = new Random();

        results = new Results(this);

        words = word.wordArrayList(2, true);
        choices = word.wordArrayList(2, true);

        animation = AnimationUtils.loadAnimation(com.example.wordpuzzlegame.puzzles.MatchImageActivity.this, R.anim.blink);
        startIndex = random.nextInt(words.size() - 5);
        randomIndices = dataSource.getTheRandomIndices(startIndex, startIndex + 5);

        startTimer();
        inflatingView();
        resetEverything();

        tv_activity_match_image_questionNumber.setText(String.valueOf(index + 1));
        singleWord = words.get(index);
        provideQuestions(singleWord);
        provideChoices(singleWord);
        index++;

        for (int i = 0; i < textViews.length; i++) {
            final int finalI = i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(timeout) {
                        Toast.makeText(MatchImageActivity.this, "Sorry, timeout", Toast.LENGTH_SHORT).show();
                    } else {
                        changeColor(finalI);
                        tried = true;
                        button_activity_match_image.setText("Confirm");
                        checkAnswer(finalI);
                    }
                }
            });
        }
        button_activity_match_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tried) {
                    Log.i("image", String.valueOf(singleWord.getImage() == null));
                    showDialog(singleWord.getImage(), "Confirmation", correctAnswer, wrongAnswer);
                    countDownTimer.cancel();
                } else {
                    doubleTap++;
                    if(doubleTap < 2) {
                        Toast.makeText(com.example.wordpuzzlegame.puzzles.MatchImageActivity.this, "Double tap to skip the question", Toast.LENGTH_SHORT).show();
                    }
                    if(doubleTap == 2) {
                        resetEverything();
                        prepareSource();
                        resetTimer();
                    }
                }
            }
        });

        iv_activity_match_image_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paused) {
                    startTimer();
                    tv_activity_match_image_clock.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    iv_activity_match_image_clock.setColorFilter(getResources().getColor(android.R.color.holo_blue_dark));
                    paused = false;
                } else {
                    countDownTimer.cancel();
                    tv_activity_match_image_clock.clearAnimation();
                    iv_activity_match_image_clock.clearAnimation();
                    iv_activity_match_image_clock.setColorFilter(Color.GRAY);
                    tv_activity_match_image_clock.setTextColor(Color.GRAY);
                    paused = true;
                }
            }
        });

    }

    private void showDialog(byte[] image, String message, String correctAnswer, String wrongAnswer){
        matchImageDialog = new MatchImageDialog().newInstance(singleWord.getImage(), correctAnswer, wrongAnswer);
        matchImageDialog.show(getSupportFragmentManager(), "match_image");
    }
    private void changeColor(int finalI) {

        for (int i = 0; i < 4; i++) {
            if(i == finalI) {
                cardViews[finalI].setCardBackgroundColor(Color.GRAY);
                continue;
            }
            cardViews[i].setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }

    }

    private void startTimer() {

        countDownTimer = new CountDownTimer(millSecondLeft + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                millSecondLeft = millisUntilFinished;
                if(((int) millisUntilFinished / 1000) < 10) {

                    tv_activity_match_image_clock.startAnimation(animation);
                    iv_activity_match_image_clock.startAnimation(animation);
                    tv_activity_match_image_clock.setTextColor(Color.RED);
                    iv_activity_match_image_clock.setColorFilter(Color.RED);

                    tv_activity_match_image_clock.setText("00:" + "0" + (int) millisUntilFinished / 1000);

                } else {

                    tv_activity_match_image_clock.setText("00:" + String.valueOf((int) millisUntilFinished / 1000));
                }
//                timer_progressBar.setProgress((int) l / 1000);
                currentMilli = maxMillSeconds - millisUntilFinished;

            }

            @Override
            public void onFinish() {

                Toast.makeText(MatchImageActivity.this, "Timeout", Toast.LENGTH_SHORT).show();
                timeout = true;
                currentMilli = maxMillSeconds;
                iv_activity_match_image_clock.clearAnimation();
                tv_activity_match_image_clock.clearAnimation();
                showDialog(null, "Time's Up", "", "");
            }
        }.start();

    }

    public void changeProperties(boolean clickable, float alphaValue) {
        for (int i = 0; i < 4; i++) {
            cardViews[i].setClickable(clickable);
            cardViews[i].setAlpha(alphaValue);
        }
    }
    private void prepareSource() {

        if(index == 5) {
            index = 0;
        }
        singleWord = words.get(index);
        index++;
        tv_activity_match_image_questionNumber.setText(String.valueOf(index));
        provideQuestions(singleWord);
        provideChoices(singleWord);

    }

    private void checkAnswer(int finalI) {

        if(correctAnswerSpot == finalI) {

            correctAnswer = textViews[finalI].getText().toString();
            wrongAnswer = "";
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();

        } else {

            correctAnswer = textViews[correctAnswerSpot].getText().toString();
            wrongAnswer = textViews[finalI].getText().toString();
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();

        }

    }

    private void resetEverything() {

        textViews = new TextView[]{tv_activity_match_image_choiceA,
        tv_activity_match_image_choiceB,
        tv_activity_match_image_choiceC,
        tv_activity_match_image_choiceD};

        cardViews = new CardView[]{cv_activity_match_image_choiceA,
                cv_activity_match_image_choiceB,
                cv_activity_match_image_choiceC,
                cv_activity_match_image_choiceD};

        cv_activity_match_image_choiceA.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        cv_activity_match_image_choiceB.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        cv_activity_match_image_choiceC.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        cv_activity_match_image_choiceD.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

        tried = false;
        timeout = false;

        doubleTap = 0;
//        countDownTimer.cancel();
        button_activity_match_image.setText("Submit");
        choices = word.wordArrayList(2, true);

        changeProperties(true, 1f);
    }

    private void inflatingView() {

        tv_activity_match_image_questionNumber = findViewById(R.id.tv_activity_match_image_questionNumber);
        tv_activity_match_image_choiceA = findViewById(R.id.tv_activity_match_image_choiceA);
        tv_activity_match_image_choiceB = findViewById(R.id.tv_activity_match_image_choiceB);
        tv_activity_match_image_choiceC = findViewById(R.id.tv_activity_match_image_choiceC);
        tv_activity_match_image_choiceD = findViewById(R.id.tv_activity_match_image_choiceD);
        tv_activity_match_image_clock = findViewById(R.id.tv_activity_match_image_clock);

        cv_activity_match_image_choiceA = findViewById(R.id.cv_activity_match_image_choiceA);
        cv_activity_match_image_choiceB = findViewById(R.id.cv_activity_match_image_choiceB);
        cv_activity_match_image_choiceC = findViewById(R.id.cv_activity_match_image_choiceC);
        cv_activity_match_image_choiceD = findViewById(R.id.cv_activity_match_image_choiceD);

        iv_activity_match_image = findViewById(R.id.iv_activity_match_image);
        iv_activity_match_image_clock = findViewById(R.id.iv_activity_match_image_clock);
        iv_activity_match_image_pause = findViewById(R.id.iv_activity_match_image_pause);

        button_activity_match_image = findViewById(R.id.button_activity_match_image_submit_next);

    }

    private void provideQuestions(Word singleWord){


//        Word singleWord = words.get(index);
//        index++;

        byte[] image = singleWord.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        iv_activity_match_image.setImageBitmap(bitmap);

    }

    private void provideChoices(Word singleWord){

        Log.i("Size before removal", String.valueOf(choices.size()));
        for (int i = 0; i < choices.size(); i++) {
            if(choices.get(i).getWord().equals(singleWord.getWord())) {
                choices.remove(i);
            }
        }
        Log.i("Size after removal", String.valueOf(choices.size()));
//        choices.remove(index);
//        Log.i("The word", singleWord.getWord());
//        choices.remove(singleWord);
        choiceIndices = dataSource.getTheRandomIndices(startIndex, startIndex + 5);
        correctAnswerSpot = random.nextInt(4);
        Toast.makeText(this, String.valueOf(correctAnswerSpot), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < 4; i++) {
            if(i != correctAnswerSpot) {
                String singleChoice = choices.get(choiceIndices.get(i)).getWord();
                textViews[i].setText(singleChoice);

            }
        }
        textViews[correctAnswerSpot].setText(singleWord.getWord());
//        tv_activity_match_image_choiceA.setText(singleWord.getWord());

    }

    public void resetTimer() {
        countDownTimer.cancel();
        millSecondLeft = 25000;
        startTimer();
    }
    @Override
    public void okClicked() {

        resetEverything();
        prepareSource();
        matchImageDialog.dismiss();
        resetTimer();

    }
}
