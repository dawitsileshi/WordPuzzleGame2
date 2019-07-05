package com.example.wordpuzzlegame.puzzles;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.Constants;
import com.example.wordpuzzlegame.R;
import com.example.wordpuzzlegame.ResultsActivity;
import com.example.wordpuzzlegame.model.Answer;
import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.Results;
import com.example.wordpuzzlegame.model.Word;
import com.example.wordpuzzlegame.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// TODO https://www.color-meanings.com/color-psychology-child-behavior-and-learning-through-colors/
// TODO: the game repeats question if the kids got right, but will change if it is not answered or incorrectly answered
public class FillRemainingActivity extends AppCompatActivity {

    private Word wordObject;

    private TextView tv_activity_fill_remaining_word,
            tv_activity_fill_remaining_choiceA,
            tv_activity_fill_remaining_choiceB,
            tv_activity_fill_remaining_choiceC,
            tv_activity_fill_remaining_choiceD,
            tv_activity_fill_remaining_question_number,
            tv_activity_fill_remaining_heart_counter;
//            tv_activity_fill_remaining_heart;
    private LinearLayout ll_activity_fill_remaining_heart_container;
    private Button button_activity_fill_remaining_next_submit;

//    private ImageView iv_activity_fill_remaining;


    private ArrayList<TextView> textViews;
    private ArrayList<Word> words;
    private ArrayList<Integer> randomIndices;
    private ArrayList<Character> choiceChars;
    private ArrayList<Integer> blankTags;
    private ArrayList<Long> wordIds;
    private ArrayList<Boolean> answers;
    private List<Character> characters;

    private DataSource dataSource;
    private Random random;

    // this value is needed to have make the game start the game with different data, this value will contain a random value that is atmost 5 times less than the data size
    private int startIndex = 0;
    private int langCode = 2;
    private int correctAnswerTag;
    private int correctAnswerTVIndex;
    private int index = 0;
    private int heartValue = 3;
    private int doubleTap = 0;
    private int score, total;

    private String lang = "ENGLISH";
    private StringBuilder newWord;
    private char[] englishChars;
    private char[][] amharicChars;
    private char[][] tigrignaChars;

    private boolean answered = false;
    private boolean submitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_remaining);

        if(langCode == 0) {
            lang = "ENGLISH";
        } else if(langCode == 1) {
            lang = "AMHARIC";
        } else {
            lang = "TIGRIGNA";
        }

        random = new Random();

        inflatingViews();

        words = new Word(this).prepareForGame(langCode, false, 2);
        wordIds = new ArrayList<>();
        answers = new ArrayList<>();

        resetEverything();

        dataSource = new DataSource(this);
        startIndex = random.nextInt(words.size() - 5);
        randomIndices = dataSource.getTheRandomIndices(startIndex, startIndex + 5);
        total = randomIndices.size();
        Log.i("The array", String.valueOf(randomIndices));
        wordObject = words.get(randomIndices.get(index));
        final String word = wordObject.getWord().toUpperCase();
        fragmentTheWord(checkLength(word));

        button_activity_fill_remaining_next_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index < 4) {
                    // if the user taps any of the choices
                    if (answered) {
                        checkAnswer();

//                        Toast.makeText(FillRemainingActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                    } else {
                        // if the user doesn't tap on any of hte choices
                        doubleTap++;
                        if (doubleTap == 1) {
                            Toast.makeText(FillRemainingActivity.this, "Press again to skip the question", Toast.LENGTH_SHORT).show();
                            submitted = false;
                        } else if (doubleTap == 2) {
                            submitted = true;
                            doubleTap = 0;
                            answers.add(false);
                            wordIds.add(wordObject.getId());
                        }
                    }
                    // if the user checks his/her answer
                    if (submitted) {
//                        if(correctAnswerTag == correctAnswerTVIndex) {
//                            score++;
//                        }
//                        answers.add(correctAnswerTVIndex == correctAnswerTag);
                        correctAnswerTVIndex = -1;
                        correctAnswerTag = -2;
//                        wordIds.add(wordObject.getId());
                        resetEverything();
                        index++;
                        wordObject = words.get(randomIndices.get(index));
                        String word = wordObject.getWord().toUpperCase();
                        fragmentTheWord(checkLength(word));
                    }
                    tv_activity_fill_remaining_question_number.setText(String.valueOf(index+1));

                } else {
                    checkAnswer();
//                    if(correctAnswerTag == correctAnswerTVIndex) {
//                        score++;
//                    }
                    finishGame();
//                    Toast.makeText(FillRemainingActivity.this, "Finished the quiz", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(FillRemainingActivity.this, "" + index, Toast.LENGTH_SHORT).show();

//                resetEverything();
//                if(index == 4) {
////                    words = new Word(FillRemainingActivity.this).wordArrayList(langCode, false);
//                    startIndex = random.nextInt(words.size() - 5);
//                    randomIndices = dataSource.getTheRandomIndices(startIndex, startIndex + 5);
//                    Log.i("The array", String.valueOf(randomIndices));
//                    index = 0;
//                    Toast.makeText(FillRemainingActivity.this, "started over", Toast.LENGTH_SHORT).show();
//                    wordObject = words.get(randomIndices.get(index));
//                    String word = wordObject.getWord().toUpperCase();
//                    Log.i("The word", word);
//                    fragmentTheWord(checkLength(word));
//                } else {
//                    index++;
//                    wordObject = words.get(randomIndices.get(index));
//                    String word = wordObject.getWord().toUpperCase();
//                    fragmentTheWord(checkLength(word));
//                    Log.i("The word", word + randomIndices.get(index));
//                }
                }
        });

        tv_activity_fill_remaining_choiceA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_activity_fill_remaining_next_submit.setText("Confirm");
                if(heartValue != 0) {
                    replaceChars(0, 2, tv_activity_fill_remaining_choiceA);
                } else {
                    if(answered) {
                        Toast.makeText(FillRemainingActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                    } else {
                        replaceChars(0, 2, tv_activity_fill_remaining_choiceA);
                    }
                }
                answered = true;
            }
        });

        tv_activity_fill_remaining_choiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_activity_fill_remaining_next_submit.setText("Confirm");
                if(heartValue != 0) {
                    replaceChars(2, 4, tv_activity_fill_remaining_choiceB);
                } else {
                    if(answered) {
                        Toast.makeText(FillRemainingActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                    } else {
                        replaceChars(2, 4, tv_activity_fill_remaining_choiceB);
                    }
                }
                answered = true;
            }
        });

        tv_activity_fill_remaining_choiceC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_activity_fill_remaining_next_submit.setText("Confirm");
                if(heartValue != 0) {
                    replaceChars(4, 6, tv_activity_fill_remaining_choiceC);
                } else {
                    if(answered) {
                        Toast.makeText(FillRemainingActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                    } else {
                        replaceChars(4, 6, tv_activity_fill_remaining_choiceC);
                    }
                }
                answered = true;
            }
        });

        tv_activity_fill_remaining_choiceD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_activity_fill_remaining_next_submit.setText("Confirm");
                if(heartValue != 0) {
                    replaceChars(6, 8, tv_activity_fill_remaining_choiceD);
                } else {
                    if(answered) {
                        Toast.makeText(FillRemainingActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                    } else {
                        replaceChars(6, 8, tv_activity_fill_remaining_choiceD);
                    }
                }
                answered = true;
            }
        });
    }

    private void finishGame() {

        long kidId = new PreferenceUtil(FillRemainingActivity.this).retrieveLongValue(PreferenceUtil.ACTIVE_USER_ID);
        Results results = new Results(FillRemainingActivity.this);
        Results singleResult = new Results("Fill The Remaining Letters", score, total, kidId);
        long id = results.insertResult(singleResult);
//                    wordIds.add(wordObject.getId());
//                    answers.add(correctAnswerTVIndex == correctAnswerTag);
        Answer answer = new Answer(FillRemainingActivity.this);
        long result = answer.insertAnswer(wordIds, answers, id);
        if(result != -1) {
            Toast.makeText(FillRemainingActivity.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FillRemainingActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
        Log.i("The answers", "" + wordIds.size());
        for (int i = 0; i < wordIds.size(); i++) {
            Log.i("The answers are",  "" + wordIds.get(i) + " " + answers.get(i));
        }
        Log.i("score", total + "/" + score);
        button_activity_fill_remaining_next_submit.setText(R.string.see_results);
        tv_activity_fill_remaining_question_number.setText(R.string.game_over);
        button_activity_fill_remaining_next_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FillRemainingActivity.this, ResultsActivity.class);
                intent.putExtra("language", "Amharic");
                intent.putExtra("gameType", "Fill the remaining word");
                intent.putExtra("total", total);
                intent.putExtra("score", score);
                startActivity(intent);
                Toast.makeText(FillRemainingActivity.this, "Going to see results", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkAnswer() {
        // if the user gets it right
        if (correctAnswerTVIndex == correctAnswerTag) {
            Toast.makeText(FillRemainingActivity.this, "correct answer", Toast.LENGTH_SHORT).show();
            answers.add(true);
            wordIds.add(wordObject.getId());
            score++;
        } else {
            Toast.makeText(FillRemainingActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            answers.add(false);
            wordIds.add(wordObject.getId());
        }
        submitted = true;

    }

    private void inflatingViews() {

        tv_activity_fill_remaining_word = findViewById(R.id.tv_activity_fill_remaining_word);
        tv_activity_fill_remaining_choiceA = findViewById(R.id.tv_activity_fill_remaining_choiceA);
        tv_activity_fill_remaining_choiceB = findViewById(R.id.tv_activity_fill_remaining_choiceB);
        tv_activity_fill_remaining_choiceC = findViewById(R.id.tv_activity_fill_remaining_choiceC);
        tv_activity_fill_remaining_choiceD = findViewById(R.id.tv_activity_fill_remaining_choiceD);
        tv_activity_fill_remaining_question_number = findViewById(R.id.tv_activity_fill_remaining_question_number);
        tv_activity_fill_remaining_heart_counter = findViewById(R.id.tv_activity_fill_remaining_heart_counter);

//        tv_activity_fill_remaining_heart = findViewById(R.id.tv_activity_fill_remaining_heart);

//        iv_activity_fill_remaining = findViewById(R.id.iv_activity_fill_remaining);

        ll_activity_fill_remaining_heart_container = findViewById(R.id.ll_activity_fill_remaining_hear_container);

        for (int i = 0; i < 3; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8,8,8,8);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
            imageView.setLayoutParams(layoutParams);

            ll_activity_fill_remaining_heart_container.addView(imageView);

        }

        button_activity_fill_remaining_next_submit = findViewById(R.id.button_activity_fill_remaining_next_submit);

    }

    private void replaceChars(int startIndex, int endIndex, TextView textView) {

//        Toast.makeText(this, String.valueOf(answered), Toast.LENGTH_SHORT).show();
        // this value must be used to avoid indexOutOfBoundsException, the blankTags array has only two elements and we are asking it to bring a value with an invalid index
        int index1 = 0;
        for (int i = startIndex; i < endIndex; i++) {

            newWord.setCharAt(blankTags.get(index1), choiceChars.get(i));
            index1++;
        }
        correctAnswerTVIndex = textViews.indexOf(textView);
        int color = Color.RED;
        if(correctAnswerTag == correctAnswerTVIndex) {
            color = Color.GREEN;
//            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {

//            Toast.makeText(this, String.valueOf(heartValue), Toast.LENGTH_SHORT).show();

            if(heartValue > 0) {
                heartValue--;
            }
            if(ll_activity_fill_remaining_heart_container.getChildCount() != 0) {
                ll_activity_fill_remaining_heart_container.removeViewAt(0);

            }
//            if(heartValue >= 0) {
//            }
            //TODO: here
//            tv_activity_fill_remaining_heart.setText(String.valueOf(heartValue));
        }
//        Toast.makeText(FillRemainingActivity.this, newWord, Toast.LENGTH_SHORT).show();
        tv_activity_fill_remaining_word.setText(changeColor(color));


    }

    private SpannableString changeColor(int color) {

        SpannableString spannableString = new SpannableString(String.valueOf(newWord));
        spannableString.setSpan(new ForegroundColorSpan(color), blankTags.get(0), blankTags.get(0) + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(color), blankTags.get(1), blankTags.get(1) + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        // if the + button was inside the bracket, it would change the index of the array and that might cause indexOutOfBoundsException, so it has to be out
        return spannableString;
    }

    private String checkLength(String word) {

        while(word.length() <= 2) {
            if(index == 4) {
                index = 0;
            }
            index++;
            word = words.get(randomIndices.get(index)).getWord().toUpperCase();
        }

        return word;

    }

    public ArrayList<Integer> identifySpaces(String word) {

        ArrayList<Integer> letterIndices = new ArrayList<>();

//        Log.i("The word", word);

        for (int i = 0; i < word.length(); i++) {
            letterIndices.add(i);
        }

        int spaceIndex = word.indexOf(' ');
        while(letterIndices.contains(spaceIndex)) {
//            Log.i("The index", String.valueOf(spaceIndex));
//            int spaceIndex = word.indexOf(' ');
            letterIndices.remove(spaceIndex);
            spaceIndex = word.indexOf(' ');
        }

        Collections.shuffle(letterIndices);

        return letterIndices;

    }

    private void fragmentTheWord(String word) {

//        Toast.makeText(this, word, Toast.LENGTH_SHORT).show();
//        int wordLength = word.length();
        choiceChars.clear(); // must be cleared to prevent it from adding other question answer on teh current question answer

//        ArrayList<Integer> integers = dataSource.getTheRandomIndices(0, wordLength);
        ArrayList<Integer> integers = identifySpaces(word);
        int randomNumber = random.nextInt(integers.size() - 1);

        char[] charedString = word.toCharArray();
        newWord = new StringBuilder();
        blankTags = new ArrayList<>();
        blankTags.add(integers.get(randomNumber));
        blankTags.add(integers.get(randomNumber + 1));
        Collections.sort(blankTags);
        int blankIndex = 0;

//        Log.i("The sizes", String.valueOf(characters.size()));

        for (int i = 0; i < charedString.length; i++) {
            if(blankIndex < blankTags.size() && i == blankTags.get(blankIndex)) {
                newWord.append("_");
                int index = characters.indexOf(charedString[i]);
//                Log.i("The sizee", String.valueOf(index));
                // we remove the items to avoid using them in choices in accident
                //TODO: recently deleted
//                characters.remove(index);
                choiceChars.add(charedString[i]);
                blankIndex++;
            } else {
                newWord.append(charedString[i]);
            }

//            for (int j = 0; j < choiceChars.size(); j++) {
//                Log.i("The right char", String.valueOf(choiceChars.get(j)));
//            }
//            if(i == integers.get(randomNumber) || i == integers.get(randomNumber + 1)) {
//                for (int j = i; j < 2; j++) {
//
//                }
//                newWord.append("?");
//            } else {
//                newWord.append(charedString[i]);
//            }
        }
        tv_activity_fill_remaining_word.setText(newWord);
//        String correctAnswer = "" + choiceChars;
        prepareChoice(characters);
//        tv_activity_fill_remaining_choiceA.setText(choice);
//        newWord.replace(0, wordLength - 1, word);

//        Log.i("numbers", String.valueOf(newWord));
//        Log.i("characters", String.valueOf(characters));

    }


    public void resetEverything() {
        textViews = new ArrayList<>();
        textViews.add(tv_activity_fill_remaining_choiceA);
        textViews.add(tv_activity_fill_remaining_choiceB);
        textViews.add(tv_activity_fill_remaining_choiceC);
        textViews.add(tv_activity_fill_remaining_choiceD);

        answered = false;
        button_activity_fill_remaining_next_submit.setText("Next");
//        heartValue = 3;

        choiceChars = new ArrayList<>();
        characters = new ArrayList<>();

        //TODO here
//        iv_activity_fill_remaining.setColorFilter(Color.BLUE);
//        tv_activity_fill_remaining_heart.setText(String.valueOf(heartValue));
//        textViews = new TextView[]{tv_activity_fill_remaining_choiceA,
//                tv_activity_fill_remaining_choiceB,
//                tv_activity_fill_remaining_choiceC,
//                tv_activity_fill_remaining_choiceD};
        characters = new ArrayList<>();
        englishChars = Constants.ENGLISH_LETTERS;
        amharicChars = Constants.AMHARIC_LETTERS;
        tigrignaChars = Constants.TIGRIGNA_LETTERS;

        if(langCode == 1) {
            for (char aChar : englishChars) {
                characters.add(aChar);
            }

        } else if(langCode == 2) {
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

    }

//    private ArrayList<Integer> provideTheRandomNumbers(int wordLength) {
//
////        int[] numbers = new int[wordLength];
//
//        ArrayList<Integer> integers = new ArrayList<>();
//
//        for (int i = 0; i < wordLength; i++) {
//
//            integers.add(i);
//        }
//
//        Collections.shuffle(integers);
//
//        return integers;
//
//    }

    private void prepareChoice(List<Character> characters) {

        correctAnswerTag = new Random().nextInt(4);
        Collections.shuffle(characters);
//        for (int i = 0; i < 6; i++) {
//            choiceChars.add(characters.get(i));
//        }

        char[] tempChars = {choiceChars.get(0), choiceChars.get(1)};
        choiceChars.clear();

        char[] choiceTag;
        if(langCode == 0) {
            choiceTag = new char[]{'A', 'B', 'C', 'D'};
        } else {
            choiceTag = new char[]{'ሀ', 'ለ', 'ሐ', 'መ'};
        }
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if(i == correctAnswerTag) {
                choiceChars.add(tempChars[0]);
//                j++;
                choiceChars.add(tempChars[1]);
            } else {
                choiceChars.add(characters.get(j));
                j++;
                choiceChars.add(characters.get(j));
                j++;

            }
        }

//        Log.i("The choice", "" + choiceChars);
        int choiceIndex = 0;
        for (int i = 0; i < textViews.size(); i++) {

            TextView textView = textViews.get(i);
//            for (int k = 0; k < 2; k++) {
                char firstChar = choiceChars.get(choiceIndex);
                choiceIndex++;
                char secondChar = choiceChars.get(choiceIndex);
//                index++;
//            }
            choiceIndex++;
            String incorrectAnswer = "[" + firstChar + ", " + secondChar + "]";
//            Log.i("The choices", choiceChars.get(index) + " " + choiceChars.get(index++));
//            index++;
            textView.setText(String.valueOf(choiceTag[i]) + ": " + incorrectAnswer);

        }
//        tv_activity_fill_remaining_choiceA.setText(choice);
//        tv_activity_fill_remaining_choiceB.setText("[" + characters.get(0) + ", " + characters.get(1) + "]");
//        tv_activity_fill_remaining_choiceC.setText("[" + characters.get(2) + ", " + characters.get(3) + "]");
//        tv_activity_fill_remaining_choiceD.setText("[" + characters.get(4) + ", " + characters.get(5) + "]");

    }
}
