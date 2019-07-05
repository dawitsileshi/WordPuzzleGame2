package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ParentActivity extends AppCompatActivity {

    private ImageView iv_activity_add_language,
            iv_activity_add_word,
            iv_activity_add_kid,
            iv_activity_see_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        iv_activity_add_language = findViewById(R.id.iv_activity_parent_add_language);
        iv_activity_add_word= findViewById(R.id.iv_activity_parent_add_word);
        iv_activity_add_kid = findViewById(R.id.iv_activity_parent_add_kid);
        iv_activity_see_results = findViewById(R.id.iv_activity_parent_see_results);

        iv_activity_add_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentActivity.this, AddLanguageActivity.class));
            }
        });

        iv_activity_add_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentActivity.this, StudyWordActivity.class));

            }
        });

        iv_activity_add_kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentActivity.this, AddKidActivity.class));

            }
        });


        iv_activity_see_results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentActivity.this, ResultListActivity.class));

            }
        });
    }
}
