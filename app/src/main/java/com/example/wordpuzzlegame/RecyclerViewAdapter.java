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

//import com.example.wordpuzzlegame.model.Words;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder> {
//
    private ArrayList<Word> englishWords;
    private ArrayList<Word> amharicWords;
    private ArrayList<Word> tigrignaWords;

    public RecyclerViewAdapter(ArrayList<Word> englishWords, ArrayList<Word> amharicWords, ArrayList<Word> tigrignaWords, ClickedLongClicked clickedLongClicked) {
        this.englishWords = englishWords;
        this.amharicWords = amharicWords;
        this.tigrignaWords = tigrignaWords;
        this.clickedLongClicked = clickedLongClicked;
    }

    public interface ClickedLongClicked {
        void clicked(long wordCode);
        void longClicked(long wordCode);
    }

    private ClickedLongClicked clickedLongClicked;
//    private ArrayList<Words> wordList;
//
//    public RecyclerViewAdapter(ArrayList<Words> words) {
//        wordList = words;
//    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_word, viewGroup, false);

        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {

        Word englishWord = englishWords.get(i);
        Word amharicWord = amharicWords.get(i);
        Word tigrignaWord = tigrignaWords.get(i);

//        Words words = wordList.get(i);

        String englishWordString = englishWords.get(i).getWord() + " (en)";
        String amharicWordString = amharicWords.get(i).getWord() + " (አማ)";
        String tigrignaWordString= tigrignaWords.get(i).getWord() + " (ትግ)";
        wordViewHolder.tv_item_word_english.setText(englishWordString);
        wordViewHolder.tv_item_word_amharic.setText(amharicWordString);
        wordViewHolder.tv_item_word_tigrigna.setText(tigrignaWordString);

//        wordViewHolder.tv_item_word_english.setText(words.getEnglishWord());
//        wordViewHolder.tv_item_word_amharic.setText(words.getAmharicWord());
//        wordViewHolder.tv_item_word_tigrigna.setText(words.getTigrignaWord());
//
        byte[] image = englishWord.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        wordViewHolder.iv_item_word.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {

        return englishWords.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView tv_item_word_english, tv_item_word_amharic, tv_item_word_tigrigna;
        private ImageView iv_item_word;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_word_english = itemView.findViewById(R.id.tv_item_word_english);
            tv_item_word_amharic = itemView.findViewById(R.id.tv_item_word_amharic);
            tv_item_word_tigrigna = itemView.findViewById(R.id.tv_item_word_tigrigna);

            iv_item_word = itemView.findViewById(R.id.iv_item_word);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickedLongClicked.clicked(amharicWords.get(getAdapterPosition()).getWord_code());
        }

        @Override
        public boolean onLongClick(View v) {
            clickedLongClicked.longClicked(amharicWords.get(getAdapterPosition()).getWord_code());
            return true;
        }
    }
}
