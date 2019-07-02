package com.example.wordpuzzlegame;

import android.content.ContentValues;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.ItemTables;
import com.example.wordpuzzlegame.model.Results;
import com.example.wordpuzzlegame.model.Word;

import java.util.ArrayList;
import java.util.HashMap;

public class Answer implements Parcelable {

    private long answer_id;
    private long word_id;
//    private int question_Number
    private long result_id;
    private boolean correct;

    private Results results;
    private Word word;

    private DataSource dataSource;
    private Context context;

    private HashMap<Long, Boolean> hashMap;

    public Answer(long answer_id, long word_id, long result_id, boolean correct) {
        this.answer_id = answer_id;
        this.word_id = word_id;
        this.result_id = result_id;
        this.correct = correct;
    }

    public Answer(long word_id, boolean correct, long result_id) {

        this.word_id = word_id;
        this.correct = correct;
        this.result_id = result_id;

    }

    public Answer(HashMap<Long, Boolean> hashMap) {

        this.hashMap = hashMap;

    }
    public Answer(Context context) {

        this.context = context;
        dataSource = new DataSource(context);

    }

    protected Answer(Parcel in) {
        answer_id = in.readLong();
        word_id = in.readLong();
        result_id = in.readLong();
        correct = in.readByte() != 0;
        results = in.readParcelable(Results.class.getClassLoader());
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(long answer_id) {
        this.answer_id = answer_id;
    }

    public long getWord_id() {
        return word_id;
    }

    public void setWord_id(long word_id) {
        this.word_id = word_id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public long getResult_id() {
        return result_id;
    }

    public void setResult_id(long result_id) {
        this.result_id = result_id;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public long insertAnswer(ArrayList<Long> wordIds, ArrayList<Boolean> answers, long result_id) {

        long result = 0;

        for (int i = 0; i < wordIds.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(ItemTables.WORD_ID, wordIds.get(i));
            if(answers.get(i)) {
                contentValues.put(ItemTables.CORRECT_INCORRECT, 1);
            } else {
                contentValues.put(ItemTables.CORRECT_INCORRECT, 0);
            }
            contentValues.put(ItemTables.RESULT_ID, result_id);
            contentValues.put(ItemTables.STRUGGLING, 0);

            result = dataSource.createItem(contentValues, ItemTables.ANSWER_TABLE);
            if(result == -1) {
                break;
            }
        }
        return result;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(answer_id);
        dest.writeLong(word_id);
        dest.writeLong(result_id);
        dest.writeByte((byte) (correct ? 1 : 0));
        dest.writeParcelable(results, flags);
    }

//    public ArrayList<Answer> listAnswers() {
//
//
//        Cursor answerCursor = dataSource.getAll(ItemTables.ANSWER_TABLE);
//
//        answerCursor.moveToFirst();
//
//        while(!answerCursor.isAfterLast()) {
//
//           long id = answerCursor.getLong(answerCursor.getColumnIndex(ItemTables.RESULT_ID));
//
//           Cursor resultCursor = dataSource.items(id, ItemTables.RESULT_ID, ItemTables.RESULTS_TABLE);
//
//           Results results = new Results()
//
//        }
//    }
}
