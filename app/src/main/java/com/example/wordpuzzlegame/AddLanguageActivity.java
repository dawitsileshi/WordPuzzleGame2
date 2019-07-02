package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Languages;

import java.util.ArrayList;

public class AddLanguageActivity extends AppCompatActivity implements AddLanguageDialog.OnSaveClicked, LanguagesRecyclerViewAdapter.Clicked {

    RecyclerView recyclerView;
    LanguagesRecyclerViewAdapter languagesRecyclerViewAdapter;

    AddLanguageDialog addLanguageDialog;
    Languages languages;

    ArrayList<Languages> languagesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_language);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        languages = new Languages(this);
        languagesArrayList = languages.languagesList();
        recyclerView = findViewById(R.id.rv_add_language);

        languagesRecyclerViewAdapter = new LanguagesRecyclerViewAdapter(languagesArrayList, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(languagesRecyclerViewAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                addLanguageDialog = new AddLanguageDialog();
                addLanguageDialog.show(getSupportFragmentManager(), "add_language_dialog");
            }
        });
    }

    @Override
    public void onSave(Languages languages) {

//        Languages languages1 = new Languages(this);
        long result = this.languages.insertLanguage(languages);

        if(result != -1) {

            addLanguageDialog.dismiss();
            Languages singleLanguage = this.languages.singleLanguage(result);
            languagesArrayList.add(singleLanguage);
            languagesRecyclerViewAdapter.notifyItemInserted(languagesArrayList.size() - 1);


        } else {

            Toast.makeText(this, "Couldn't save", Toast.LENGTH_SHORT).show();
            return;

        }

    }

    @Override
    public void langClicked(Languages languages) {
        Intent intent = new Intent(this, StudyWordsActivity.class);
        intent.putExtra("language", languages);
        startActivity(intent);
        Toast.makeText(this, "Lang clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moreClcked(int position, Languages languages, ImageView imageView) {
        Toast.makeText(this, "More clicked", Toast.LENGTH_SHORT).show();
        PopupMenu popupMenu = new PopupMenu(this, imageView);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.language_menu, popupMenu.getMenu());
        popupMenu.show();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_edit:
                Toast.makeText(this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_delete:
                Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.menu_edit:
                Toast.makeText(this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_delete:
                Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
