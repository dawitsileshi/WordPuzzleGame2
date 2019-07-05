package com.example.wordpuzzlegame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Languages;
import com.example.wordpuzzlegame.model.Word;

import java.util.ArrayList;

//import com.example.wordpuzzlegame.model.Word;
//import com.example.wordpuzzlegame.model.Words;

public class StudyWordsActivity extends AppCompatActivity implements AddLanguageDialog.OnSaveClicked {

    SpecificLanguageRecyclerViewAdapter specificLanguageRecyclerViewAdapter;
    Word word;

    private RecyclerView rv_content_study_words;
    private TextView tv_content_study_words;

    Languages languages;

    long langId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_words);

        rv_content_study_words = findViewById(R.id.rv_content_study_words);
        tv_content_study_words = findViewById(R.id.tv_content_study_words_language);

        Intent intent = getIntent();

        if(intent != null) {

            languages = intent.getParcelableExtra("language");
            langId = languages.getLang_id();

            tv_content_study_words.setText(languages.getLanguage());
            word = new Word(this);

            ArrayList<Word> words = word.wordArrayList(langId, true);

            specificLanguageRecyclerViewAdapter = new SpecificLanguageRecyclerViewAdapter(words);

            rv_content_study_words.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            rv_content_study_words.setAdapter(specificLanguageRecyclerViewAdapter);

        } else {

            Toast.makeText(this, "Nothing was sent", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_delete) {
            if(langId <= 3) {
                Toast.makeText(this, "Sorry, you can not delete this language", Toast.LENGTH_SHORT).show();
            } else {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete the language? All data related to this language will be deleted.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Languages languages = new Languages(StudyWordsActivity.this);
                        boolean deleted = languages.deleteLanguage(StudyWordsActivity.this.languages.getLang_id());
                        if (deleted) {
                            Toast.makeText(StudyWordsActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(StudyWordsActivity.this, AddLanguageActivity.class));
                            finish();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
            }

        } else {

            if(langId <= 3) {
                Toast.makeText(this, "Sorry, you can not edit this language", Toast.LENGTH_SHORT).show();
            } else {
                AddLanguageDialog addLanguageDialog = new AddLanguageDialog().newInstance(languages.getLanguage(), languages.getAlphabets(), "Update");
                addLanguageDialog.show(getSupportFragmentManager(), "edit_language");
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSave(Languages languages) {

    }
}
