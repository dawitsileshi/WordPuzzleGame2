package com.example.wordpuzzlegame.puzzles;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.R;
import com.example.wordpuzzlegame.ResultsActivity;
import com.example.wordpuzzlegame.model.Answer;
import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.Results;
import com.example.wordpuzzlegame.model.Word;
import com.example.wordpuzzlegame.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

//import com.example.wordpuzzlegame.model.Word;

public class JumbledSingleWordActivity extends AppCompatActivity {

    Button button_puzzle_jumbled_next_submit;
    TextView tv_puzzle_jumbled_question;
    LinearLayout ll_puzzle_jumbled_answerContainer, ll_puzzle_jumbled_choiceContainer;
    TextView tv_puzzle_jumbled_choiceA, tv_puzzle_jumbled_choiceB, tv_puzzle_jumbled_choiceC,
            tv_puzzle_jumbled_choiceD, tv_puzzle_jumbled_counter;
    TextView[] textViews;
    private DataSource dataSource;
    private HashMap<Integer, Character> hashMap;
    private HashMap<Long, Boolean> questionAnswer;
    private ArrayList<Integer> erasedAnswers;

    private ArrayList<Long> wordIds;
    private ArrayList<Boolean> answers;

    private String question;
    StringBuffer assembledWord;

    private int score = 0, total = 5;

    private int numberOfClicksForChoice = -1, numberOfClicksForAnswer = 0, questionIndex;

    private ArrayList<Word> words;
    int index = 0, doubleTaps;
    int pressesLeft;
    private ArrayList<Integer> questionIndices;
    ArrayList<Character> characters;
    Word word;
    Random random;
    private boolean answerChecked, finished = false;
    private int tappedTwice = 0;
    private char[] englishChars;
    private char[][] amharicChars;
    private char[][] tigrignaChars;

    Results results;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumbled_single_word);

        inflatingView();

        creatingObjects();

        prepareTheRandomness();

        displayQuestion();

        startTheCouter();

        button_puzzle_jumbled_next_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(finished) {

                    Intent intent = new Intent(JumbledSingleWordActivity.this, ResultsActivity.class);
                    intent.putExtra("language", "Amharic");
                    intent.putExtra("gameType", "Jumbled Word");
                    intent.putExtra("total", total);
                    intent.putExtra("score", score);
                    startActivity(intent);

                } else {

                    if (index == 5) {
                        finishQuiz();
                    }
                    if (pressesLeft == 0) {

                        if (answerChecked) {
                            resetEverything();
//                        index++;
                            answerChecked = false;
//                            index++;
                            displayQuestion();
                        } else {
                            checkAnswer(assembledWord);
                            answerChecked = true;
                        }

                    } else {

                        if (tappedTwice == 2) {
//                        index++;
                            resetEverything();
                            displayQuestion();
                            tappedTwice = 0;
                        } else {
                            Toast.makeText(JumbledSingleWordActivity.this, "Double tap to jump the " +
                                    "question.", Toast.LENGTH_SHORT).show();
                            tappedTwice++;
                        }

                    }
                }

//                if(pressesLeft == 0) {
//
//                    index++;
//                    displayQuestion();
//
//                }
//                if(index == 4) {
//                    prepareTheRandomness();
//                    displayQuestion();
//                } else {
//
//                    index++;
//                    displayQuestion();
//                }

            }
        });
    }

    private void startTheCouter() {

        Thread t = new Thread(){

            @Override
            public void run(){

                while(!isInterrupted()){

                    try {
                        Thread.sleep(1000);  //1000ms = 1 sec

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                count++;
                                tv_puzzle_jumbled_counter.setText(String.valueOf(count));
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();
    }

    private void finishQuiz() {

        finished = true;
        checkAnswer(assembledWord);
        ll_puzzle_jumbled_choiceContainer.setVisibility(View.GONE);
        ll_puzzle_jumbled_answerContainer.setVisibility(View.GONE);
        button_puzzle_jumbled_next_submit.setText(R.string.see_results);
        tv_puzzle_jumbled_question.setText(R.string.game_over);

        long kidId = new PreferenceUtil(this).retrieveLongValue(PreferenceUtil.ACTIVE_USER_ID);
        Results singleResult = new Results("Jumbled Word", score, total, kidId);
        long id = results.insertResult(singleResult);
        if(id == -1) {

            Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();

        } else {

            Answer answer = new Answer(this);
            long result = answer.insertAnswer(wordIds, answers, id);

            if(result == -1) {

                Toast.makeText(this, "couldn't save", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();

            }

        }


    }

    private void checkAnswer(StringBuffer assembledWord) {

        long id = words.get(questionIndex).getId();

        if(assembledWord.toString().equals(question)) {

//            questionAnswer.put(id, true);
            wordIds.add(id);
            answers.add(true);
            if(!finished) {
                score++;
            }
            Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT).show();

        } else {

            wordIds.add(id);
            answers.add(false);
//            questionAnswer.put(id, false);

        }
//        Toast.makeText(this, "The final string is " + question, Toast.LENGTH_SHORT).show();

    }

    public void resetEverything() {
        numberOfClicksForAnswer = 0;
        numberOfClicksForChoice = -1;
        ll_puzzle_jumbled_answerContainer.removeAllViews();
        ll_puzzle_jumbled_choiceContainer.removeAllViews();
        button_puzzle_jumbled_next_submit.setText("Next");
        if (assembledWord.length() > 0) {
            assembledWord.delete(0, assembledWord.length());
        }
    }

    private void prepareTheRandomness() {

        index = 0;
        random = new Random();

        int randomVariable = random.nextInt(words.size() - 6);

        questionIndices = dataSource.getTheRandomIndices(randomVariable, randomVariable + 5);
        Toast.makeText(this, "The number of questions is " + questionIndices.size(), Toast.LENGTH_SHORT).show();

//        for (int i = randomVariable; i < randomVariable + 5; i++) {
//
//            questionIndices.add(i);
//
//        }
//
//        Collections.shuffle(questionIndices);
    }

    public void inflatingView() {

        button_puzzle_jumbled_next_submit = findViewById(R.id.button_puzzle_jumbled_next_submit);
        tv_puzzle_jumbled_question = findViewById(R.id.tv_puzzle_jumbled_question);

        tv_puzzle_jumbled_counter = findViewById(R.id.tv_puzzle_jumbled_counter);

        ll_puzzle_jumbled_answerContainer = findViewById(R.id.ll_puzzle_jumbled_answerContainer);
        ll_puzzle_jumbled_choiceContainer = findViewById(R.id.ll_puzzle_jumbled_choiceContainer);
        ll_puzzle_jumbled_choiceContainer.setGravity(Gravity.CENTER);
        ll_puzzle_jumbled_answerContainer.setGravity(Gravity.CENTER);

    }

    public void creatingObjects() {

        word = new Word(this);
        dataSource = new DataSource(this);
        words = word.wordArrayList(2, false);
//        words = word.wordArrayList("ENGLISH");

        results = new Results(this);
        questionIndices = new ArrayList<>();
        characters = new ArrayList<>();
        hashMap = new HashMap<>();
        questionAnswer = new HashMap<>();
        erasedAnswers = new ArrayList<>();
        assembledWord = new StringBuffer();

        wordIds = new ArrayList<>();
        answers = new ArrayList<>();

    }

    public void createViews(int wordLength) {

        for (int j = 0; j < wordLength; j++) {
            displayDash(-1);
        }

    }

    public void displayQuestion() {


        questionIndex = questionIndices.get(index);
        index++;
        question = words.get(questionIndex).getWord();
        createViews(question.length());
        pressesLeft = question.length();
        String jumbled = jumbleIt(question);

        // this prevents the jumbled word from being teh word itself
        if (jumbled.equalsIgnoreCase(question)) {
            jumbled = jumbleIt(question);
//        prepareTheChoices(i)
        }
        tv_puzzle_jumbled_question.setText(jumbled.toUpperCase());
        prepareTheCharacters(jumbleIt(question));

    }

    public ArrayList<Character> characters(int langCode) {
        englishChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        amharicChars = new char[][]{{'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ'},
                {'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ'},
                {'ሐ', 'ሑ', 'ሒ', 'ሓ', 'ሔ', 'ሕ', 'ሖ'},
                {'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ'},
                {'ሠ', 'ሡ', 'ሢ', 'ሣ', 'ሤ', 'ሥ', 'ሦ'},
                {'ረ', 'ሩ', 'ሪ', 'ራ', 'ሬ', 'ር', 'ሮ'},
                {'ሰ', 'ሱ', 'ሲ', 'ሳ', 'ሴ', 'ስ', 'ሶ'},
                {'ሸ', 'ሹ', 'ሺ', 'ሻ', 'ሼ', 'ሽ', 'ሾ'},
                {'ቀ', 'ቁ', 'ቂ', 'ቃ', 'ቄ', 'ቅ', 'ቆ'},
                {'በ', 'ቡ', 'ቢ', 'ባ', 'ቤ', 'ብ', 'ቦ'},
                {'ተ', 'ቱ', 'ቲ', 'ታ', 'ቴ', 'ት', 'ቶ'},
                {'ቸ', 'ቹ', 'ቺ', 'ቻ', 'ቼ', 'ች', 'ቾ'},
                {'ኅ', 'ኁ', 'ኂ', 'ኃ', 'ኄ', 'ኅ', 'ኆ'},
                {'ነ', 'ኑ', 'ኒ', 'ና', 'ኔ', 'ን', 'ኖ'},
                {'ኘ', 'ኙ', 'ኚ', 'ኛ', 'ኜ', 'ኝ', 'ኞ'},
                {'አ', 'ኡ', 'ኢ', 'አ', 'ኤ', 'እ', 'ኦ'},
                {'ከ', 'ኩ', 'ኪ', 'ካ', 'ኬ', 'ክ', 'ኮ'},
                {'ኸ', 'ኹ', 'ኺ', 'ኻ', 'ኼ', 'ኽ', 'ኾ'},
                {'ወ', 'ዉ', 'ዊ', 'ዋ', 'ዌ', 'ው', 'ዎ'},
                {'ዐ', 'ዑ', 'ዒ', 'ዓ', 'ዔ', 'ዕ', 'ዖ'},
                {'ዘ', 'ዙ', 'ዚ', 'ዛ', 'ዜ', 'ዝ', 'ዞ'},
                {'ዠ', 'ዡ', 'ዢ', 'ዣ', 'ዤ', 'ዥ', 'ዦ'},
                {'የ', 'ዩ', 'ዪ', 'ያ', 'ዬ', 'ይ', 'ዮ'},
                {'ደ', 'ዱ', 'ዲ', 'ዳ', 'ዴ', 'ድ', 'ዶ'},
                {'ጀ', 'ጁ', 'ጂ', 'ጃ', 'ጄ', 'ጅ', 'ጆ'},
                {'ገ', 'ጉ', 'ጊ', 'ጋ', 'ጌ', 'ግ', 'ጎ'},
                {'ጠ', 'ጡ', 'ጢ', 'ጣ', 'ጤ', 'ጥ', 'ጦ'},
                {'ጨ', 'ጩ', 'ጪ', 'ጫ', 'ጬ', 'ጭ', 'ጮ'},
                {'ጰ', 'ጱ', 'ጲ', 'ጳ', 'ጴ', 'ጵ', 'ጶ'},
                {'ጸ', 'ጹ', 'ጺ', 'ጻ', 'ጼ', 'ጽ', 'ጾ'},
                {'ፀ', 'ፁ', 'ፂ', 'ፃ', 'ፄ', 'ፅ', 'ፆ'},
                {'ፈ', 'ፉ', 'ፊ', 'ፋ', 'ፌ', 'ፍ', 'ፎ'},
                {'ፐ', 'ፑ', 'ፒ', 'ፓ', 'ፔ', 'ፕ', 'ፖ'}};

        tigrignaChars = new char[][]{{'ሀ', 'ሁ', 'ሂ', 'ሃ', 'ሄ', 'ህ', 'ሆ'},
                {'ለ', 'ሉ', 'ሊ', 'ላ', 'ሌ', 'ል', 'ሎ'},
                {'ሐ', 'ሑ', 'ሒ', 'ሓ', 'ሔ', 'ሕ', 'ሖ'},
                {'መ', 'ሙ', 'ሚ', 'ማ', 'ሜ', 'ም', 'ሞ'},
                {'ሠ', 'ሡ', 'ሢ', 'ሣ', 'ሤ', 'ሥ', 'ሦ'},
                {'ረ', 'ሩ', 'ሪ', 'ራ', 'ሬ', 'ር', 'ሮ'},
                {'ሰ', 'ሱ', 'ሲ', 'ሳ', 'ሴ', 'ስ', 'ሶ'},
                {'ሸ', 'ሹ', 'ሺ', 'ሻ', 'ሼ', 'ሽ', 'ሾ'},
                {'ቀ', 'ቁ', 'ቂ', 'ቃ', 'ቄ', 'ቅ', 'ቆ'},
                {'ቐ', 'ቑ', 'ቒ', 'ቓ', 'ቔ', 'ቕ', 'ቖ'},
                {'በ', 'ቡ', 'ቢ', 'ባ', 'ቤ', 'ብ', 'ቦ'},
                {'ተ', 'ቱ', 'ቲ', 'ታ', 'ቴ', 'ት', 'ቶ'},
                {'ቸ', 'ቹ', 'ቺ', 'ቻ', 'ቼ', 'ች', 'ቾ'},
                {'ኅ', 'ኁ', 'ኂ', 'ኃ', 'ኄ', 'ኅ', 'ኆ'},
                {'ነ', 'ኑ', 'ኒ', 'ና', 'ኔ', 'ን', 'ኖ'},
                {'ኘ', 'ኙ', 'ኚ', 'ኛ', 'ኜ', 'ኝ', 'ኞ'},
                {'አ', 'ኡ', 'ኢ', 'አ', 'ኤ', 'እ', 'ኦ'},
                {'ከ', 'ኩ', 'ኪ', 'ካ', 'ኬ', 'ክ', 'ኮ'},
                {'ኸ', 'ኹ', 'ኺ', 'ኻ', 'ኼ', 'ኽ', 'ኾ'},
                {'ወ', 'ዉ', 'ዊ', 'ዋ', 'ዌ', 'ው', 'ዎ'},
                {'ዐ', 'ዑ', 'ዒ', 'ዓ', 'ዔ', 'ዕ', 'ዖ'},
                {'ዘ', 'ዙ', 'ዚ', 'ዛ', 'ዜ', 'ዝ', 'ዞ'},
                {'ዠ', 'ዡ', 'ዢ', 'ዣ', 'ዤ', 'ዥ', 'ዦ'},
                {'የ', 'ዩ', 'ዪ', 'ያ', 'ዬ', 'ይ', 'ዮ'},
                {'ደ', 'ዱ', 'ዲ', 'ዳ', 'ዴ', 'ድ', 'ዶ'},
                {'ጀ', 'ጁ', 'ጂ', 'ጃ', 'ጄ', 'ጅ', 'ጆ'},
                {'ገ', 'ጉ', 'ጊ', 'ጋ', 'ጌ', 'ግ', 'ጎ'},
                {'ጠ', 'ጡ', 'ጢ', 'ጣ', 'ጤ', 'ጥ', 'ጦ'},
                {'ጨ', 'ጩ', 'ጪ', 'ጫ', 'ጬ', 'ጭ', 'ጮ'},
                {'ጰ', 'ጱ', 'ጲ', 'ጳ', 'ጴ', 'ጵ', 'ጶ'},
                {'ጸ', 'ጹ', 'ጺ', 'ጻ', 'ጼ', 'ጽ', 'ጾ'},
                {'ፀ', 'ፁ', 'ፂ', 'ፃ', 'ፄ', 'ፅ', 'ፆ'},
                {'ፈ', 'ፉ', 'ፊ', 'ፋ', 'ፌ', 'ፍ', 'ፎ'},
                {'ፐ', 'ፑ', 'ፒ', 'ፓ', 'ፔ', 'ፕ', 'ፖ'},
                {'ቨ', 'ቩ', 'ቪ', 'ቫ', 'ቬ', 'ቭ', 'ቮ'},
                {'ዸ', 'ዹ', 'ዺ', 'ዻ', 'ዼ', 'ዽ', 'ዾ'},
                {'ጘ', 'ጙ', 'ጚ', 'ጛ', 'ጜ', 'ጝ', 'ጞ'}};

        if (langCode == 1) {
            for (char aChar : englishChars) {
                characters.add(aChar);
            }

        } else if (langCode == 2) {
            for (char[] amharicChar : amharicChars) {
                for (int j = 0; j < 7; j++) {
                    characters.add(amharicChar[j]);
                }
            }
        } else {
            for (char[] tigrignaChar : tigrignaChars) {
                for (int j = 0; j < 7; j++) {
                    characters.add(tigrignaChar[j]);
                }
            }

        }
        return characters;
    }

    public void prepareTheCharacters(final String jumbledWord) {

        characters = characters(2);

        Collections.shuffle(characters);

        char[] stringToArray = jumbledWord.toCharArray();

        Arrays.sort(stringToArray);

        final ArrayList<Character> notFoundOnTheWord = new ArrayList<>();

        for (char aChar : characters) {
            int searchValue = Arrays.binarySearch(stringToArray, aChar);
            if (searchValue < 0) {
                notFoundOnTheWord.add(aChar);
            }

            if(notFoundOnTheWord.size() > 3) {
                break;
            }
        }

        for (char c : stringToArray) {
            notFoundOnTheWord.add(c);
        }

        Collections.shuffle(notFoundOnTheWord);

        for (int i = 0; i < notFoundOnTheWord.size(); i++) {
            final TextView textView = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8, 8, 8, 8);
            textView.setLayoutParams(layoutParams);
            displayChosenAnswer(textView, String.valueOf(notFoundOnTheWord.get(i)).toUpperCase());
            ll_puzzle_jumbled_choiceContainer.addView(textView);
//            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TextView textView1 = new TextView(com.example.wordpuzzlegame.puzzles.JumbledSingleWordActivity.this);
                    if(pressesLeft != 0) {
                        if(erasedAnswers.size() != 0) {
                            int viewPosition = erasedAnswers.get(0);
                            textView1 = displayChosenAnswer(textView1, textView.getText().toString());
//                            textView1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                            ll_puzzle_jumbled_answerContainer.removeViewAt(viewPosition);
                            ll_puzzle_jumbled_answerContainer.addView(textView1, viewPosition);
                            hashMap.put(viewPosition, textView.getText().charAt(0));
                            erasedAnswers.remove(0);
                            assembledWord.insert(viewPosition, textView.getText().charAt(0));
                            numberOfClicksForAnswer++;
                        } else {
//                            numberOfClicksForAnswer = 0;
                            numberOfClicksForChoice++;
                            textView1 = displayChosenAnswer(textView1, textView.getText().toString());
                            ll_puzzle_jumbled_answerContainer.removeViewAt(numberOfClicksForChoice);
                            ll_puzzle_jumbled_answerContainer.addView(textView1, numberOfClicksForChoice);
                            hashMap.put(numberOfClicksForChoice, textView.getText().charAt(0));
                            assembledWord.insert(numberOfClicksForChoice, textView.getText()
                                    .charAt(0));
                        }
                        pressesLeft--;
                        if(pressesLeft == 0) {
                            button_puzzle_jumbled_next_submit.setText("Submit");
                        }
//                            button_puzzle_jumbled_next_submit.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    Toast.makeText(getContext(), assembledWord, Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
                        Log.i("The pressed left is ", String.valueOf(pressesLeft));
                        final TextView finalTextView = textView1;
                        textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!answerChecked) {

                                    pressesLeft++;
                                    button_puzzle_jumbled_next_submit.setText("Jump");
                                    int indexPosition = ll_puzzle_jumbled_answerContainer.indexOfChild(finalTextView);
                                    Toast.makeText(JumbledSingleWordActivity.this, indexPosition + "is " +
                                            "touched", Toast.LENGTH_SHORT).show();
                                    ll_puzzle_jumbled_answerContainer.removeView(finalTextView);
                                    displayDash(indexPosition);
                                    erasedAnswers.add(indexPosition);
                                    Collections.sort(erasedAnswers);
                                    assembledWord.deleteCharAt(indexPosition);

                                }

                            }

                        });
                    }

                }
            });
        }

    }

    private TextView displayDash(int i){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 8, 8, 8);
        CardView cardView = new CardView(this);
        cardView.setCardElevation(5);
        cardView.setPadding(16, 16, 16,16);
        cardView.setLayoutParams(layoutParams);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.purpleLight));
        final TextView textView = new TextView(this);
        textView.setTextSize(20f);
        textView.setText("__");
        textView.setPadding(8, 8, 8,8);
        textView.setTextColor(Color.WHITE);
        cardView.addView(textView);
        if(i == -1) {
            ll_puzzle_jumbled_answerContainer.addView(cardView);
        } else {
            ll_puzzle_jumbled_answerContainer.addView(cardView, i);
        }
        return textView;
    }

    private TextView displayChosenAnswer(TextView textView1, String string) {
        CardView cardView = new CardView(this);
        cardView.setPadding(8,8,8,8);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.purpleLight));
        textView1.setTextSize(30f);
        textView1.setAllCaps(true);
        textView1.setPadding(16, 16, 16,16);
        textView1.setTextColor(Color.WHITE);
        textView1.setText(string);
//        textView1.setBackgroundColor(getResources().getColor(R.color.purpleLight));
//        cardView.addView(textView1);
        return textView1;
    }

    private void prepareTheChoices(int questionIndex) {

        int randomVariable = random.nextInt(words.size() - 4);
        int correctAnswerTag = random.nextInt(4);

        ArrayList<Integer> choiceArrayList = new ArrayList<>();

        for (int i = randomVariable; i < randomVariable + 4; i++) {
            choiceArrayList.add(i);
        }

        if(!choiceArrayList.contains(questionIndex)) {
            choiceArrayList.set(correctAnswerTag, questionIndex);
        }

        textViews = new TextView[]{tv_puzzle_jumbled_choiceA, tv_puzzle_jumbled_choiceB,
                tv_puzzle_jumbled_choiceC, tv_puzzle_jumbled_choiceD};

        for (int i = 0; i < textViews.length; i++) {
            String choice = words.get(choiceArrayList.get(i)).getWord();
            textViews[i].setText(choice);
        }

    }



    private ArrayList<Integer> provideTheRandomNumbers(int wordLength) {

        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < wordLength; i++) {
            integers.add(i);
        }

        Collections.shuffle(integers);

        return integers;

    }

    private String jumbleIt(String word) {

        int wordLength = word.length();

        ArrayList<Integer> integers = provideTheRandomNumbers(wordLength);

        char[] charedString = word.toCharArray();
        StringBuilder newWord = new StringBuilder();

        for (int i = 0; i < charedString.length; i++) {
            newWord.append(charedString[integers.get(i)]);
        }

        return String.valueOf(newWord);

    }
}
