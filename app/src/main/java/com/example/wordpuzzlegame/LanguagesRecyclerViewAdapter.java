package com.example.wordpuzzlegame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wordpuzzlegame.model.Languages;

import java.util.ArrayList;

public class LanguagesRecyclerViewAdapter extends RecyclerView.Adapter<LanguagesRecyclerViewAdapter.LanguageViewHolder> {

    private ArrayList<Languages> languages;
    private Context context;

    public LanguagesRecyclerViewAdapter(ArrayList<Languages> languages, Context context, Clicked clicked) {
        this.languages = languages;
        this.context = context;
        this.clicked = clicked;
    }

    public interface Clicked {
        void langClicked(Languages languages);
        void moreClcked(int position, Languages languages, ImageView imageView);
    }

    public Clicked clicked;

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LanguageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_language, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder languageViewHolder, int i) {

        Languages language = languages.get(i);

        languageViewHolder.tv_item_language.setText(language.getLanguage());

        String alphabet = language.getAlphabets();

        if(alphabet.equals("Latin")) {
            languageViewHolder.iv_item_language.setImageResource(R.drawable.latin_alphabets);
        } else {
            languageViewHolder.iv_item_language.setImageResource(R.drawable.geez);

        }

    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_item_language;
        private ImageView iv_item_language, iv_item_language_more;
        private CardView cv_item_laguage;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_language = itemView.findViewById(R.id.tv_item_language);
            iv_item_language = itemView.findViewById(R.id.iv_item_language);
            cv_item_laguage = itemView.findViewById(R.id.cv_item_language);
            iv_item_language_more = itemView.findViewById(R.id.iv_item_language_more);

            cv_item_laguage.setOnClickListener(this);
            iv_item_language_more.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == iv_item_language_more) {
                clicked.moreClcked(getAdapterPosition(), languages.get(getAdapterPosition()), iv_item_language_more);
            } else {
                clicked.langClicked(languages.get(getAdapterPosition()));
            }
        }
    }
}
