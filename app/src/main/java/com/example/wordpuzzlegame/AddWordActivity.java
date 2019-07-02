package com.example.wordpuzzlegame;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Assets;
import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.Languages;
import com.example.wordpuzzlegame.model.Word;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddWordActivity extends AppCompatActivity {

    DataSource dataSource;
    Languages languages;

    LinearLayout ll_content_addWord;
    ImageView iv_content_addWord;
    Button button_content_addWord;

    ArrayList<Languages> languagesArrayList;

    ArrayList<EditText> editTexts;

    ArrayList<Word> words;

    Word word;
    Assets assets;

    long wordCode;
    boolean emptySpots;

    final int REQUEST_CODE_GALLERY = 999;

    private char[] latinChars;
    private char[][] geezChars;

    private boolean charFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        editTexts = new ArrayList<>();
        words = new ArrayList<>();

        word = new Word(this);
        assets = new Assets(this);

        iv_content_addWord = findViewById(R.id.iv_content_add_word);
        ll_content_addWord = findViewById(R.id.ll_content_add_word);
        button_content_addWord = findViewById(R.id.button_content_add_word);

        languages = new Languages(this);

        languagesArrayList = languages.languagesList();

        iv_content_addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddWordActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        });
        for (Languages languages :
                languagesArrayList) {

            CardView cardView = new CardView(this);
//            CardView.LayoutParams layoutParams = CardView.LayoutParams(WRAP_CONTENT)
            cardView.setPadding(8,  8, 8, 8);
            cardView.setCardElevation(13f);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.purpleLight));
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(languages.getLanguage());
            textView.setTextSize(25f);
            textView.setTextColor(Color.WHITE);
            linearLayout.addView(textView);
            TextInputLayout textInputLayout = new TextInputLayout(this);
            EditText editText = new EditText(this);
            editText.setEms(10);
            editText.setHint("word");
            editText.setHintTextColor(Color.WHITE);
            editText.setTextColor(Color.WHITE);
            textInputLayout.addView(editText);
            editTexts.add(editText);
            linearLayout.addView(textInputLayout);
            cardView.addView(linearLayout);
//            cardView.addView(textView);
            ll_content_addWord.addView(cardView);
//            ll_content_addWord.addView(editText);
        }

//        for (int i = 0; i < editTexts.size(); i++) {
//            final int finalI = i;
//            editTexts.get(i).addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    String alphabet = languagesArrayList.get(finalI).getAlphabets();
////                    Toast.makeText(AddWordActivity.this, "The alphabet is " + alphabet, Toast.LENGTH_SHORT).show();
//                    Log.i("The letters", String.valueOf(s));
//                    charFound = false;
//                    if(alphabet.equals("Latin")) {
//                        latinChars = Constants.ENGLISH_LETTERS;
//
//                        for (char latinChar : latinChars) {
//                            if (String.valueOf(s).equals(String.valueOf(latinChar))) {
//                                charFound = true;
//                                break;
//                            }
//                        }
//
//                        if(!charFound) {
////                            Toast.makeText(AddWordActivity.this, "Wrong character", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else if(alphabet.equals("Ge'ez-amh")) {
////                        charFound = false;
//                        geezChars = Constants.AMHARIC_LETTERS;
//
//                        for (char[] geezChar : geezChars) {
//                            for (int j = 0; j < 7; j++) {
//                                if(String.valueOf(s).equals(String.valueOf(geezChar[j]))) {
//                                    charFound = true;
//                                    break;
//                                }
//                            }
//                        }
//                        if(!charFound) {
//                            Toast.makeText(AddWordActivity.this, "wrong Char", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        geezChars = Constants.TIGRIGNA_LETTERS;
//
//                        for (char[] geezChar : geezChars) {
//                            for (int j = 0; j < 7; j++) {
//                                if(String.valueOf(s).equals(String.valueOf(geezChar[j]))) {
//                                    charFound = true;
//                                    break;
//                                }
//                            }
//                        }
//                        if(!charFound) {
//                            Toast.makeText(AddWordActivity.this, "wrong Char", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });

//            editTexts.get(finalI).setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View v, int keyCode, KeyEvent event) {
//                    switch (event.getAction()){
//                        case KeyEvent.KEYCODE_BUTTON_A:
//                            Toast.makeText(AddWordActivity.this, "Keyboard is released", Toast.LENGTH_SHORT).show();
//                    }
//                    return false;
//                }
//            });
//        }

        button_content_addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                words.clear();
                for (int i = 0; i < editTexts.size(); i++) {
                    String inputtedWord = editTexts.get(i).getText().toString().trim();
                    if(inputtedWord.length() == 0) {
                        emptySpots = true;
                        editTexts.get(i).setText("----");
                        wordCode = word.getCurrentWordCode();
                        Word singleWord = new Word(inputtedWord, wordCode + 1, languagesArrayList.get(i).getLang_id());
                        words.add(singleWord);
//                        Toast.makeText(AddWordActivity.this, "Word is not inserted on editText" + i + 1, Toast.LENGTH_SHORT).show();
//                        return;
                    } else {
                        if(invalidSymbolsFound(inputtedWord, languagesArrayList.get(i).getAlphabets())) {
                            editTexts.get(i).requestFocus();
                            Toast.makeText(AddWordActivity.this, "An invalid symbol is found in the word", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            wordCode = word.getCurrentWordCode();
                            Word singleWord = new Word(inputtedWord, wordCode + 1, languagesArrayList.get(i).getLang_id());
                            words.add(singleWord);
                        }

                    }
                }
                if(emptySpots) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(AddWordActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Confirmation");
                    builder.setMessage("Are you sure you want to save some of the words being empty?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            assets.insertAssets(new Assets(imageToByte(), wordCode + 1));
                            long result = word.insertWords(words);
                            if(result == -1) {

                                Toast.makeText(AddWordActivity.this, "Couldn't save", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(AddWordActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();
                } else {
                }

            }
        });


    }

    private boolean invalidSymbolsFound(String inputtedWord, String alphabets) {

        boolean invalidFound = false;
        char[] inputtedWordArray = inputtedWord.toCharArray();
        if(alphabets.equals("Latin")) {
            latinChars = Constants.ENGLISH_LETTERS;
            for (int i = 0; i < inputtedWord.length(); i++) {
                if(new String(latinChars).indexOf(inputtedWordArray[i]) == -1) {
                    Log.i("The invalid one is", String.valueOf(inputtedWordArray[i]));
                    invalidFound = true;
                    break;
                }
//            if(new String(latinChars).contains(inputtedWordArray[i])) {
//
//            }
//                for (int j = 0; j < latinChars.length; j++) {
//                    if(inputtedWordArray[i] == latinChars[j]) {
//                        charFound = true;
//                        break;
//                    }
//                }
            }
        } else if(alphabets.equals("Ge'ez-amh")) {

            geezChars = Constants.AMHARIC_LETTERS;

            for (int i = 0; i < inputtedWord.length(); i++) {

                if(convertMultiDimensionalToString(geezChars).indexOf(inputtedWordArray[i]) == -1) {
                    invalidFound = true;
                    break;
                }
//                if(new String(geezChars) )
            }
        } else {
            geezChars = Constants.TIGRIGNA_LETTERS;

            for (int i = 0; i < inputtedWord.length(); i++) {

                if(convertMultiDimensionalToString(geezChars).indexOf(inputtedWordArray[i]) == -1) {
                    invalidFound = true;
                    break;
                }
//                if(new String(geezChars) )
            }
        }

        return invalidFound;

    }

    private String convertMultiDimensionalToString(char[][] geezChars) {
        int arrayLength = 0;

        for (char[] geezChar1 : geezChars) {
            for (int j = 0; j < geezChar1.length; j++) {
                arrayLength++;
            }
        }

        char[] geezSingleArray = new char[arrayLength];
        int arrayIndex = 0;
        for (char[] geezChar : geezChars) {
            for (char c : geezChar) {
                geezSingleArray[arrayIndex] = c;
                arrayIndex++;
            }
        }
        return new String(geezSingleArray);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "Backspace si pressed", Toast.LENGTH_SHORT).show();
                break;
            case KeyEvent.KEYCODE_SHIFT_LEFT:
                Toast.makeText(this, "Shift Left was pressed", Toast.LENGTH_SHORT).show();
                break;
            case KeyEvent.KEYCODE_SHIFT_RIGHT:
                Toast.makeText(this, "Shift Right was pressed", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyaction = event.getAction();
        if(keyaction == KeyEvent.KEYCODE_BACK)
        {
            String keycode = event.getCharacters();
            Toast toast = Toast.makeText(this,String.valueOf(event.getKeyCode()), Toast.LENGTH_LONG);
            toast.show();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY) {

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);

            } else {

                Toast.makeText(this, "You didn't have the premission to access gallery", Toast.LENGTH_SHORT).show();

            }

            return;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {

                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iv_content_addWord.setImageBitmap(bitmap);
            } catch(FileNotFoundException f) {

                f.printStackTrace();

            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] imageToByte() {

        Bitmap bitmap = ((BitmapDrawable) iv_content_addWord.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
