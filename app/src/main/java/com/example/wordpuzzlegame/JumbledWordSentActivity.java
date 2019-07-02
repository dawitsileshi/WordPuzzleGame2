package com.example.wordpuzzlegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.wordpuzzlegame.model.Word;

import java.util.ArrayList;
import java.util.Random;

//import com.example.wordpuzzlegame.model.Words;

public class JumbledWordSentActivity extends AppCompatActivity {

    ArrayList<Word> words;
    private ArrayList<Integer> sentenceIndices;
    private TextView tv_fragment_puzzle_jumbled_sent, tv_fragment_puzzle_jumbled_newSent;
    private Button button_fragment_puzzle_jumbled_sent_next;
    private Random random;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumbled_word_sent);

//        words = new Word(this).wordArrayList("English");
//        random = new Random();
//        sentenceIndices = new ArrayList<>();
//
//        tv_fragment_puzzle_jumbled_sent = findViewById(R.id.tv_fragment_puzzle_jumbled_sent);
//        tv_fragment_puzzle_jumbled_newSent = findViewById(R.id.tv_fragment_puzzle_jumbled_newSent);
//        button_fragment_puzzle_jumbled_sent_next = findViewById(R.id.button_fragment_puzzle_jumbled_sent_next);
//
//        for (int i = 0; i < words.size(); i++) {
//            sentenceIndices.add(i);
//        }
//
//        Collections.shuffle(sentenceIndices);
//
//        displayNextSentence();
//
//        button_fragment_puzzle_jumbled_sent_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                index++;
//                displayNextSentence();
//            }
//        });
    }
//    private void displayNextSentence() {
//
//        String sentence = words.get(sentenceIndices.get(index)).getEnglishSent();
//
//        String word = words.get(sentenceIndices.get(index)).getEnglishWord();
//        tv_fragment_puzzle_jumbled_sent.setText(sentence);
//
//        countSpaces(sentence, word);
//    }
//
//    private void countSpaces(String sentence, String word) {
//
//        char[] stringToArray = sentence.toCharArray();
//
//        ArrayList<String> strings = new ArrayList<>();
//        String substring = "";
//        String jumbled = "";
//        char lastChar = stringToArray[stringToArray.length - 1];
//        String newSent = "";
//
//        for (int i = 0; i < stringToArray.length; i++) {
//
//            if(stringToArray[i] == ' ' || i == stringToArray.length - 1) {
//
//                Log.i("The substring is ", substring + " " + word.toLowerCase());
//                strings.add(substring);
//                Log.i("The similarity ", String.valueOf(substring.toLowerCase() == word
//                        .toLowerCase()));
//                if(substring.toLowerCase().contains(word.toLowerCase())) {
//                    jumbled = jumbleIt(substring.toLowerCase());
//                    jumbled = checkIfJumbled(jumbled, substring.toLowerCase());
//                    Toast.makeText(this, "The word is found on the position " +
//                            String.valueOf(strings.size()), Toast.LENGTH_SHORT).show();
//                    newSent = newSent + jumbled + " ";
//                } else {
//
//                    newSent = newSent + substring + " ";
//                }
//                substring = "";
//
//            } else {
//
//                substring = substring + stringToArray[i];
//
//            }
//
//
//        }
//        newSent = newSent.trim();
//        newSent = newSent + lastChar;
//        tv_fragment_puzzle_jumbled_newSent.setText(newSent);
//
//
//            if(stringToArray[i] == ' ' || stringToArray[i] == '.') {

//                String substring = sentence.substring(startSubstringIndex, i);
//                startSubstringIndex = i + 1;
//                Log.i("The substring is ", substring);
//                strings.add(substring);
//                substring = "";

//            }
//
//
//    }
//
//    private String checkIfJumbled(String jumbled, String word) {
//        while(word.equalsIgnoreCase(jumbled)) {
//            jumbled = jumbleIt(word);
//        }
//
//        return jumbled;
//
//    }
//
//    private String jumbleIt(String word) {
//
//        int wordLength = word.length();
//
//        ArrayList<Integer> integers = provideTheRandomNumbers(wordLength);
//
//        char[] charedString = word.toCharArray();
//        StringBuilder newWord = new StringBuilder();
//
//        for (int i = 0; i < charedString.length; i++) {
//            newWord.append(charedString[integers.get(i)]);
//        }
//
//        return String.valueOf(newWord);
//
//    }
//
//    private ArrayList<Integer> provideTheRandomNumbers(int wordLength) {
//
//        ArrayList<Integer> integers = new ArrayList<>();
//
//        integers = new DataSource(this).getTheRandomIndices(0, wordLength);
//        for (int i = 0; i < wordLength; i++) {
//            integers.add(i);
//        }
//
//        Collections.shuffle(integers);
//
//        return integers;
//
//    }
}
//