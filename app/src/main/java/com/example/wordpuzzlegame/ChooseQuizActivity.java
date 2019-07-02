package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wordpuzzlegame.puzzles.FillRemainingActivity;
import com.example.wordpuzzlegame.puzzles.JumbledSingleWordActivity;
import com.example.wordpuzzlegame.puzzles.MatchImageActivity;

public class ChooseQuizActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);

        findViewById(R.id.button_activity_choose_quiz_jumbledWord).setOnClickListener(this);
        findViewById(R.id.button_activity_choose_quiz_jumbledSent).setOnClickListener(this);
        findViewById(R.id.button_activity_choose_quiz_matchImage).setOnClickListener(this);
        findViewById(R.id.button_activity_choose_quiz_fillBlank).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_activity_choose_quiz_jumbledWord:
                startActivity(new Intent(this, JumbledSingleWordActivity.class));
                break;
            case R.id.button_activity_choose_quiz_jumbledSent:
//                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, JumbledWordSentActivity.class));
                break;
            case R.id.button_activity_choose_quiz_fillBlank:
//                Toast.makeText(this, "Not implemented, yet", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, FillRemainingActivity.class));
                break;
            case R.id.button_activity_choose_quiz_matchImage:
//                Toast.makeText(this, "Not implemented, yet", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MatchImageActivity.class));
                break;
        }

    }
}
