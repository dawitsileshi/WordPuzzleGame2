package com.example.wordpuzzlegame;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wordpuzzlegame.model.Kid;

import java.util.ArrayList;

public class KidRecyclerViewAdapter extends RecyclerView.Adapter<KidRecyclerViewAdapter.KidViewHolder> {

    ArrayList<Kid> kids;

    public KidRecyclerViewAdapter(ArrayList<Kid> kids) {
        this.kids = kids;
    }

    @NonNull
    @Override
    public KidViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new KidViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kid, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KidViewHolder kidViewHolder, int i) {

        Kid kid = kids.get(i);

        kidViewHolder.tv_item_kid.setText(kid.getKid_name());
        if(kid.getKid_gender().equals("Male")) {
            kidViewHolder.iv_item_kid.setImageResource(R.drawable.boy);
        } else {
            kidViewHolder.iv_item_kid.setImageResource(R.drawable.girl);

        }

    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_item_kid;
        private ImageView iv_item_kid;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_kid = itemView.findViewById(R.id.tv_item_kid);
            iv_item_kid = itemView.findViewById(R.id.iv_item_kid);

        }
    }
}
