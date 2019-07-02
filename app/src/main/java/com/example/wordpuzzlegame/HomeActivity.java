package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.ItemTables;

import de.hdodenhof.circleimageview.CircleImageView;
//import com.example.wordpuzzlegame.model.Word;

public class HomeActivity extends AppCompatActivity {

//    private Button button_activity_home_add,
//            button_activity_home_study,
//            button_activity_home_puzzle,
//            button_activity_home_result;
//
    DataSource dataSource;

    private CircleImageView civ_activity_parent, civ_activity_kid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataSource = new DataSource(this);

        Toast.makeText(this, String.valueOf(dataSource.getAll(ItemTables.WORD_TABLE).getCount()), Toast.LENGTH_SHORT).show();

        civ_activity_parent = findViewById(R.id.civ_activity_home_parent);
        civ_activity_kid = findViewById(R.id.civ_activity_home_kid);
//        Cursor cursor = dataSource.getAll();
//        String word = new Word(this).wordArrayList("AMHARIC").get(1).getWord();
//        Toast.makeText(this, "The size of the data is " + cursor.getCount(), Toast.LENGTH_SHORT).show();
//        button_activity_home_add = findViewById(R.id.button_activity_home_add_words);
//        button_activity_home_study = findViewById(R.id.button_activity_home_studyWords);
//        button_activity_home_puzzle = findViewById(R.id.button_activity_home_chooseQuiz);
//        button_activity_home_result = findViewById(R.id.button_activity_home_result);
//
//        findViewById(R.id.button_activity_home_add_words).setOnClickListener(this);
//        findViewById(R.id.button_activity_home_studyWords).setOnClickListener(this);
//        findViewById(R.id.button_activity_home_chooseQuiz).setOnClickListener(this);
//        findViewById(R.id.button_activity_home_result).setOnClickListener(this);

        civ_activity_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity(ParentActivity.class);
            }
        });

        civ_activity_kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity(KidActivity.class);
            }
        });
    }

    private void startAnotherActivity(Class aClass) {
        startActivity(new Intent(this, aClass));
    }


}
