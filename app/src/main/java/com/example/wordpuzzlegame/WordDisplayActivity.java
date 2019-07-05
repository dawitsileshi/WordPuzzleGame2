package com.example.wordpuzzlegame;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Word;
import java.util.ArrayList;

public class WordDisplayActivity extends AppCompatActivity {

    Word word;
    ArrayList<Word> words;

    private Button button_activity_word_display;

    private LinearLayout ll_activity_word_display;

    private ImageView iv_activity_word_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        button_activity_word_display = findViewById(R.id.button_activity_word_display);
        ll_activity_word_display = findViewById(R.id.ll_activity_word_display);
        iv_activity_word_display = findViewById(R.id.iv_activity_word_display);

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            long wordCode = intent.getLongExtra(Constants.WORD_CODE, -1);

            word = new Word(this);
            words = word.getByWordCode(wordCode);

            iv_activity_word_display.setImageBitmap(BitmapFactory.decodeByteArray(words.get(0).getImage(), 0, words.get(0).getImage().length));
            for (int i = 0; i < words.size(); i++) {


                TextView textView = new TextView(this);
                textView.setTextColor(Color.WHITE);
                textView.setText(words.get(i).getWord());
                ll_activity_word_display.addView(textView);
                Log.i("word", words.get(i).getWord());
            }

        } else {
            Toast.makeText(this, "Nothing received", Toast.LENGTH_SHORT).show();
            return;
        }

        button_activity_word_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
