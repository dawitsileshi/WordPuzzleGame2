package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Kid;

import java.util.ArrayList;

public class KidListActivity extends AppCompatActivity {

    RecyclerView rv_content_kid_list;

    TextView tv_activity_kid_list_no_kid;
    KidRecyclerViewAdapter kidRecyclerViewAdapter;

    Kid kid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_list);

        kid = new Kid(this);

        ArrayList<Kid> kids = kid.kids();

        tv_activity_kid_list_no_kid = findViewById(R.id.tv_activity_kid_list_no_kids);

        if(kids.size() == 0) {
            tv_activity_kid_list_no_kid.setText(getResources().getString(R.string.no_account_for_kids));
            return;
        }

        Toast.makeText(this, String.valueOf(kids.size()), Toast.LENGTH_SHORT).show();

        kidRecyclerViewAdapter = new KidRecyclerViewAdapter(kids);

        rv_content_kid_list = findViewById(R.id.rv_content_kid_list);
        rv_content_kid_list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv_content_kid_list.setAdapter(kidRecyclerViewAdapter);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KidListActivity.this, AddKidActivity.class));
            }
        });
    }

}
