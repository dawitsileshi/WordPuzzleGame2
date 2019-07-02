package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    private Button button_activity_result_playAgain,
                    button_activity_result_home,
                    button_activity_result_options;

    private TextView tv_activity_result_quizType,
                    tv_activity_result_language,
                    tv_activity_result_score,
                    tv_activity_result_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        inflatingView();

        Intent intent = getIntent();

        if(intent.getExtras() != null) {

            String language = intent.getStringExtra("language");
            String quizType = intent.getStringExtra("gameType");
            int total = intent.getIntExtra("total", -1);
            int score = intent.getIntExtra("score", -1);

            tv_activity_result_quizType.setText(quizType);
            tv_activity_result_language.setText(language);
            tv_activity_result_score.setText(String.valueOf(score));
            tv_activity_result_total.setText(String.valueOf(total));

        } else {

            Toast.makeText(this, "Nothing sent", Toast.LENGTH_SHORT).show();
            return;

        }

    }

    private void inflatingView() {

        button_activity_result_home = findViewById(R.id.button_activity_result_home);
        button_activity_result_playAgain = findViewById(R.id.button_activity_result_playAgain);
        button_activity_result_options = findViewById(R.id.button_activity_result_options);

        tv_activity_result_quizType = findViewById(R.id.tv_activity_result_quizType);
        tv_activity_result_language = findViewById(R.id.tv_activity_result_lang);
        tv_activity_result_score = findViewById(R.id.tv_activity_result_score);
        tv_activity_result_total = findViewById(R.id.tv_activity_result_total);

    }
}
