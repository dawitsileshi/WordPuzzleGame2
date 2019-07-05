package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Word;
import com.example.wordpuzzlegame.utils.PreferenceUtil;

import java.util.ArrayList;

//import com.example.wordpuzzlegame.model.Words;

public class StudyWordActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickedLongClicked {

    RecyclerView recyclerView;
    ArrayList<Word> englishWords;
    ArrayList<Word> amharicWords;
    ArrayList<Word> tigrignaWords;

    ArrayList<Word> words;

    RecyclerViewAdapter recyclerViewAdapter;
    Word word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_word);

        word = new Word(this);

//        words = word.allListed();
        englishWords = word.wordArrayList(1, true);
        amharicWords = word.wordArrayList(2, true);
        tigrignaWords = word.wordArrayList(3, true);

        Toast.makeText(this, String.valueOf(englishWords.size()), Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.rv_activity_study);
//        recyclerViewAdapter = new RecyclerViewAdapter(words);
        recyclerViewAdapter = new RecyclerViewAdapter(englishWords, amharicWords, tigrignaWords, this);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void clicked(long wordCode) {
        Intent intent = new Intent(StudyWordActivity.this, WordDisplayActivity.class);
        intent.putExtra(Constants.WORD_CODE, wordCode);
        startActivity(intent);
    }

    @Override
    public void longClicked(long wordCode) {

        boolean parentActive = new PreferenceUtil(this).retrieveBooleanValue(PreferenceUtil.PARENT_ACTIVE);
        if(parentActive) {
            Toast.makeText(this, "Allowed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }
}
