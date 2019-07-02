package com.example.wordpuzzlegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Word;

import java.util.ArrayList;

//import com.example.wordpuzzlegame.model.Words;

public class StudyWordActivity extends AppCompatActivity {

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
        recyclerViewAdapter = new RecyclerViewAdapter(englishWords, amharicWords, tigrignaWords);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
