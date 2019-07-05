package com.example.wordpuzzlegame.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import static com.example.wordpuzzlegame.model.ItemTables.ANSWER_TABLE;
import static com.example.wordpuzzlegame.model.ItemTables.CORRECT_INCORRECT;
import static com.example.wordpuzzlegame.model.ItemTables.KID_ID;
import static com.example.wordpuzzlegame.model.ItemTables.LANG_ID;
import static com.example.wordpuzzlegame.model.ItemTables.QUIZ_TYPE;
import static com.example.wordpuzzlegame.model.ItemTables.RESULT_ID;
import static com.example.wordpuzzlegame.model.ItemTables.STRUGGLING;
import static com.example.wordpuzzlegame.model.ItemTables.WORD;
import static com.example.wordpuzzlegame.model.ItemTables.WORD_ID;
import static com.example.wordpuzzlegame.model.ItemTables.WORD_TABLE;

public class StrugglingWords {

    private long id;
    private long word_id;
    private long lang_code;
    private String game_type;
    private int wrongly_answered;
    private int rightly_answered;

    private Context context;
    private DataSource dataSource;

    public StrugglingWords() {
    }

    public StrugglingWords(Context context) {
        this.context = context;
        dataSource = new DataSource(context);
    }
    public StrugglingWords(String game_type) {
        this.game_type = game_type;
    }

    public StrugglingWords(long id, long word_id, long lang_code, String game_type, int wrongly_answered, int rightly_answered) {
        this.id = id;
        this.word_id = word_id;
        this.lang_code = lang_code;
        this.game_type = game_type;
        this.wrongly_answered = wrongly_answered;
        this.rightly_answered = rightly_answered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWord_id() {
        return word_id;
    }

    public void setWord_id(long word_id) {
        this.word_id = word_id;
    }

    public long getLang_code() {
        return lang_code;
    }

    public void setLang_code(long lang_code) {
        this.lang_code = lang_code;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public int getWrongly_answered() {
        return wrongly_answered;
    }

    public void setWrongly_answered(int wrongly_answered) {
        this.wrongly_answered = wrongly_answered;
    }

    public int getRightly_answered() {
        return rightly_answered;
    }

    public void setRightly_answered(int rightly_answered) {
        this.rightly_answered = rightly_answered;
    }

    public void checkFrequency(String gameType, long langId, long kidId) {

        Cursor resultCursor = dataSource.getSqLiteDatabase().query(ItemTables.RESULTS_TABLE, null, QUIZ_TYPE + " = ? AND " + LANG_ID + " = ? AND " + ItemTables.KID_ID  + " = ? ",
                new String[]{gameType, String.valueOf(langId), String.valueOf(kidId)}, null, null, null);
        resultCursor.moveToFirst();
        Log.i("size of result", String.valueOf(resultCursor.getCount()));

        long resultId = resultCursor.getLong(resultCursor.getColumnIndex(ItemTables.RESULT_ID));
        Cursor answerCursor = dataSource.getSqLiteDatabase().query(ItemTables.ANSWER_TABLE, null, RESULT_ID + " = ? AND " + ItemTables.STRUGGLING + " = ? ",
                new String[]{String.valueOf(resultId), String.valueOf(0)}, null, null, null);

        answerCursor.moveToFirst();

        resultCursor.close();
//        answerCursor.close();

        Log.i("size of answer", String.valueOf(resultCursor.getCount()));

        while(!answerCursor.isAfterLast()) {
            long wordId = answerCursor.getLong(answerCursor.getColumnIndex(WORD_ID));
            int correct = answerCursor.getInt(answerCursor.getColumnIndex(CORRECT_INCORRECT));

            Cursor frequencyChecker = dataSource.getSqLiteDatabase().query(ANSWER_TABLE, null, WORD_ID + " = ? AND " +  CORRECT_INCORRECT + " = ? AND " + STRUGGLING + " = ? ",
                    new String[]{String.valueOf(wordId), String.valueOf(0), String.valueOf(0)}, null, null, null);
            frequencyChecker.moveToFirst();
            Log.i("frequency", String.valueOf(frequencyChecker.getCount()) + " " + wordId);
            Cursor wordCursor = dataSource.items(wordId, WORD_ID, WORD_TABLE);
            wordCursor.moveToFirst();
            String word = wordCursor.getString(wordCursor.getColumnIndex(WORD));
            wordCursor.close();
            answerCursor.moveToNext();
            if(correct == 1) {
                Log.i("words correct answer", word + " " + wordId);
            } else {
                Log.i("words incorrect answer", word);
//
            }
        }





//        Cursor resultCursor = dataSource.items(answer.getResult_id(), ItemTables.RESULT_ID, ItemTables.ANSWER_TABLE);
//        resultCursor.moveToFirst();
//
//        long langId = resultCursor.getLong(resultCursor.getColumnIndex(ItemTables.LANG_ID));
//        String gameType = resultCursor.getString(resultCursor.getColumnIndex(QUIZ_TYPE));
//        long kidId = resultCursor.getLong(resultCursor.getColumnIndex(KID_ID));
//
//        Cursor answerCursor = dataSource.getSqLiteDatabase().query(ItemTables.ANSWER_TABLE, null, QUIZ_TYPE + " = ? AND " + ItemTables.LANG_ID + " = ? AND " + ItemTables.KID_ID + " = ?",
//                new String[]{gameType, String.valueOf(langId), String.valueOf(kidId), }, null, null, null)

    }
}
