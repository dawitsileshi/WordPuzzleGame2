package com.example.wordpuzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Results;

import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity implements ResultsRecyclerViewAdapter.Clcked {

    RecyclerView rv_activity_resultsList;

    ArrayList<Results> resultsArrayList;
    Results results;

    ResultsRecyclerViewAdapter resultsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        results = new Results(this);

        resultsArrayList = results.listResults();

        int count = new DataSource(this).getAll(ItemTables.ANSWER_TABLE).getCount();

        Toast.makeText(this, String.valueOf(resultsArrayList.size()), Toast.LENGTH_SHORT).show();

        rv_activity_resultsList = findViewById(R.id.rv_activity_resultsList);

        rv_activity_resultsList.setLayoutManager(new LinearLayoutManager(this));

        resultsRecyclerViewAdapter = new ResultsRecyclerViewAdapter(this, resultsArrayList, this);

        rv_activity_resultsList.setAdapter(resultsRecyclerViewAdapter);

    }

    @Override
    public void clicked(Results result) {
        Toast.makeText(this, result.getAnswers().size() + " is the number of answers saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ResultDisplayActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
