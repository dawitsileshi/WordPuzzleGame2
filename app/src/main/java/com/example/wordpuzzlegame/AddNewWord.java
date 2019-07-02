package com.example.wordpuzzlegame;
//

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.example.wordpuzzlegame.model.Word;
//import com.example.wordpuzzlegame.model.Words;
//import com.transitionseverywhere.TransitionManager;
//
//import java.util.ArrayList;
//
public class AddNewWord extends AppCompatActivity {
//
//    Button button_activity_add_new_words;
//
//    EditText et_activity_add_new_word_eng_word,
//            et_activity_add_new_word_eng_sent,
//            et_activity_add_new_word_amh_word,
//            et_activity_add_new_word_amh_sent,
//            et_activity_add_new_word_tig_word,
//            et_activity_add_new_word_tig_sent;
//
//    ConstraintLayout cl_activity_add_new_word_eng,
//            cl_activity_add_new_word_amh,
//            cl_activity_add_new_word_tig;
//
//    ImageView iv_activity_add_new_word_eng,
//            iv_activity_add_new_word_amh,
//            iv_activity_add_new_word_tig;
//
//    CardView cv_activity_add_new_word_eng,
//            cv_activity_add_new_word_amh,
//            cv_activity_add_new_word_tig;
//
//    private Word word;
//    private ArrayList<Word> words;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
//
//        word = new Word(this);
//        words = new ArrayList<>();
//
//        et_activity_add_new_word_eng_word = findViewById(R.id.et_activity_add_new_word_eng_word);
//        et_activity_add_new_word_eng_sent = findViewById(R.id.et_activity_add_new_word_eng_sent);
//        et_activity_add_new_word_amh_word = findViewById(R.id.et_activity_add_new_word_amh_word);
//        et_activity_add_new_word_amh_sent = findViewById(R.id.et_activity_add_new_word_amh_sent);
//        et_activity_add_new_word_tig_word = findViewById(R.id.et_activity_add_new_word_tig_word);
//        et_activity_add_new_word_tig_sent = findViewById(R.id.et_activity_add_new_word_tig_sent);
//
//        button_activity_add_new_words = findViewById(R.id.button_activity_add_new_word);
//
//        cl_activity_add_new_word_eng = findViewById(R.id.cl_activity_add_new_word_eng);
//        cl_activity_add_new_word_amh = findViewById(R.id.cl_activity_add_new_word_amh);
//        cl_activity_add_new_word_tig = findViewById(R.id.cl_activity_add_new_word_tig);
//
//        iv_activity_add_new_word_eng = findViewById(R.id.iv_activity_add_new_word_eng);
//        iv_activity_add_new_word_amh = findViewById(R.id.iv_activity_add_new_word_amh);
//        iv_activity_add_new_word_tig = findViewById(R.id.iv_activity_add_new_word_tig);
//
//        cv_activity_add_new_word_eng = findViewById(R.id.cv_activity_add_new_word_eng_cont);
//        cv_activity_add_new_word_amh = findViewById(R.id.cv_activity_add_new_word_amh_cont);
//        cv_activity_add_new_word_tig = findViewById(R.id.cv_activity_add_new_word_tig_cont);
//
//        cv_activity_add_new_word_eng.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TransitionManager.beginDelayedTransition(cl_activity_add_new_word_eng);
//
//                if(cl_activity_add_new_word_eng.getVisibility() == View.VISIBLE) {
//                    iv_activity_add_new_word_eng.setImageResource(R.drawable.ic_keyboard_arrow_down_24dp);
//                    cl_activity_add_new_word_eng.setVisibility(View.GONE);
//                } else {
//                    iv_activity_add_new_word_eng.setImageResource(R.drawable.ic_keyboard_arrow_up_24dp);
//                    cl_activity_add_new_word_eng.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        cv_activity_add_new_word_amh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TransitionManager.beginDelayedTransition(cl_activity_add_new_word_amh);
//
//                if(cl_activity_add_new_word_amh.getVisibility() == View.VISIBLE) {
//                    iv_activity_add_new_word_amh.setImageResource(R.drawable.ic_keyboard_arrow_down_24dp);
//                    cl_activity_add_new_word_amh.setVisibility(View.GONE);
//                } else {
//                    iv_activity_add_new_word_amh.setImageResource(R.drawable.ic_keyboard_arrow_up_24dp);
//                    cl_activity_add_new_word_amh.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        cv_activity_add_new_word_tig.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TransitionManager.beginDelayedTransition(cl_activity_add_new_word_tig);
//
//                if(cl_activity_add_new_word_tig.getVisibility() == View.VISIBLE) {
//                    cl_activity_add_new_word_tig.setVisibility(View.GONE);
//                    iv_activity_add_new_word_tig.setImageResource(R.drawable.ic_keyboard_arrow_down_24dp);
//                } else {
//                    cl_activity_add_new_word_tig.setVisibility(View.VISIBLE);
//                    iv_activity_add_new_word_tig.setImageResource(R.drawable.ic_keyboard_arrow_up_24dp);
//                }
//            }
//        });
//
//        button_activity_add_new_words.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String eng_word = et_activity_add_new_word_eng_word.getText().toString();
//                String eng_sent = et_activity_add_new_word_eng_sent.getText().toString();
//                String eng_lang = "ENGLISH";
//                String amh_word = et_activity_add_new_word_amh_word.getText().toString();
//                String amh_sent = et_activity_add_new_word_amh_sent.getText().toString();
//                String amh_lang = "AMHARIC";
//                String tig_word = et_activity_add_new_word_tig_word.getText().toString();
//                String tig_sent = et_activity_add_new_word_tig_sent.getText().toString();
//                String tig_lang= "TIGRIGNA";
//
//                Words aWords = new Words()
////                Word englishWord = new Word(eng_word, eng_sent, eng_lang);
////                Word amharicWord = new Word(amh_word, amh_sent, amh_lang);
////                Word tigrignaWord = new Word(tig_word, tig_sent, tig_lang);
//
//                words.add(englishWord);
//                words.add(amharicWord);
//                words.add(tigrignaWord);
//
//                word.saveWords(words);
//            }
//        });
    }
}
