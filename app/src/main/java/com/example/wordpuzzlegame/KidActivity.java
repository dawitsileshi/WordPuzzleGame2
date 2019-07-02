package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class KidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid);

        findViewById(R.id.cv_activity_kid_study).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnotherActivity(StudyWordActivity.class);

            }
        });


        findViewById(R.id.cv_activity_kid_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity(ChooseQuizActivity.class);
            }
        });


        findViewById(R.id.cv_activity_kid_high_score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KidActivity.this, "NOt Available", Toast.LENGTH_SHORT).show();
//                startAnotherActivity(StudyWordActivity.class);
            }
        });
    }

    private void startAnotherActivity(Class aClass) {

        startActivity(new Intent(this, aClass));

    }
}
