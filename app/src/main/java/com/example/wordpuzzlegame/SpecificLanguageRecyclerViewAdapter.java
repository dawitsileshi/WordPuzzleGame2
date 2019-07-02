package com.example.wordpuzzlegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wordpuzzlegame.model.Word;

import java.util.ArrayList;

public class SpecificLanguageRecyclerViewAdapter extends RecyclerView.Adapter<SpecificLanguageRecyclerViewAdapter.SpecificLanguageViewHolder> {

    ArrayList<Word> words;

    public SpecificLanguageRecyclerViewAdapter(ArrayList<Word> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public SpecificLanguageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SpecificLanguageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_specific_language, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificLanguageViewHolder specificLanguageViewHolder, int i) {

        Word word = words.get(i);

        byte[] image = word.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        specificLanguageViewHolder.tv_item_specific_language.setText(word.getWord());
        specificLanguageViewHolder.iv_item_specific_language.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class SpecificLanguageViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_item_specific_language;
        private ImageView iv_item_specific_language;

        public SpecificLanguageViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_specific_language = itemView.findViewById(R.id.tv_item_specific_language);
            iv_item_specific_language = itemView.findViewById(R.id.iv_item_specific_language);

        }
    }

}
