package com.example.wordpuzzlegame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wordpuzzlegame.model.Results;

import java.util.ArrayList;

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ResultsViewHolder> {


    private ArrayList<Results> results;
    private Context context;

    public ResultsRecyclerViewAdapter(Context context, ArrayList<Results> results, Clcked clicked) {

        this.context = context;
        this.results = results;
        this.clcked = clicked;

    }

    public interface Clcked{
        void clicked(Results result);
    }

    public Clcked clcked;

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ResultsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_results, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder viewHolder, int i) {

        Results results = this.results.get(i);

        viewHolder.tv_item_results.setText(results.getTimeStamp());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_item_results;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_results = itemView.findViewById(R.id.tv_item_results);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clcked.clicked(results.get(getAdapterPosition()));
        }
    }
}
