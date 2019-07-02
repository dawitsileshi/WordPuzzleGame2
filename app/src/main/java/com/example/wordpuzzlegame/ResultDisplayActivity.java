package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wordpuzzlegame.model.Answer;
import com.example.wordpuzzlegame.model.Results;

import java.util.ArrayList;

public class ResultDisplayActivity extends AppCompatActivity {

    private TextView tv_activity_result_quizType,
            tv_activity_result_language,
            tv_activity_result_score,
            tv_activity_result_total,
            tv_activity_result_correct_answer;

    private Button button_activity_result_more;

    private Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        inflatingView();

        Intent intent = getIntent();

        if(intent.getExtras() != null) {

            results = intent.getParcelableExtra("result");

            tv_activity_result_score.setText(String.valueOf(results.getScore()));
            tv_activity_result_total.setText(String.valueOf(results.getTotal()));
            tv_activity_result_quizType.setText(results.getQuiz_type());

            button_activity_result_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ArrayList<Answer> answer = results.getAnswer();

                    for (int i = 0; i < answer.size(); i++) {
                        tv_activity_result_correct_answer.append(answer.get(i).getResult_id() + " ");
                    }

                }
            });
        }


    }

    private void inflatingView() {

        tv_activity_result_quizType = findViewById(R.id.tv_activity_result_quizType);
        tv_activity_result_language = findViewById(R.id.tv_activity_result_lang);
        tv_activity_result_score = findViewById(R.id.tv_activity_result_score);
        tv_activity_result_total = findViewById(R.id.tv_activity_result_total);
        tv_activity_result_correct_answer = findViewById(R.id.tv_activity_result_correct_answer);

        button_activity_result_more = findViewById(R.id.button_activity_result_more);
    }
}
