package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class Results implements Parcelable {

    private long id;
    private String quiz_type;
    private int score;
    private int total;
    private long lang_id;
    private long kidId;
    private String timeStamp;
    private String answered_word;
    private String unanswered_word;

    private ArrayList<Answer> answers;

    private Context context;
    private DataSource dataSource;

    public Results() {
    }

    public Results(Context context) {

        this.context = context;
        dataSource = new DataSource(context);

    }
    public Results(String quiz_type, int score, int total, long kidId) {

        this.quiz_type = quiz_type;
        this.score = score;
        this.total = total;
        this.kidId = kidId;

    }

    public Results(long id, String quiz_type, int score, int total, String timeStamp, ArrayList<Answer> answers) {

        this.id = id;
        this.quiz_type = quiz_type;
        this.score = score;
        this.total = total;
        this.timeStamp = timeStamp;
        this.answers = answers;

    }

    public Results(long id, String quiz_type, int score, int total, String timeStamp, String answered_word, String unanswered_word) {
        this.id = id;
        this.quiz_type = quiz_type;
        this.score = score;
        this.total = total;
        this.timeStamp = timeStamp;
        this.answered_word = answered_word;
        this.unanswered_word = unanswered_word;
    }

    protected Results(Parcel in) {
        id = in.readLong();
        quiz_type = in.readString();
        score = in.readInt();
        total = in.readInt();
        timeStamp = in.readString();
        answered_word = in.readString();
        unanswered_word = in.readString();
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAnswered_word() {
        return answered_word;
    }

    public void setAnswered_word(String answered_word) {
        this.answered_word = answered_word;
    }

    public String getUnanswered_word() {
        return unanswered_word;
    }

    public void setUnanswered_word(String unanswered_word) {
        this.unanswered_word = unanswered_word;
    }

//    public ArrayList<Answer> getAnswer() {
//        return answers;
//    }

//    public void setAnswer(ArrayList<Answer> answer) {
//        this.answers = answer;
//    }

    public long getKidId() {
        return kidId;
    }

    public void setKidId(long kidId) {
        this.kidId = kidId;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public long insertResult(Results results) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemTables.QUIZ_TYPE, results.getQuiz_type());
        contentValues.put(ItemTables.SCORE, results.getScore());
        contentValues.put(ItemTables.TOTAL, results.getTotal());
        contentValues.put(ItemTables.KID_ID, results.getKidId());

        return dataSource.createItem(contentValues, ItemTables.RESULTS_TABLE);

    }

    public ArrayList<Results> listResults() {

        ArrayList<Results> resultsArrayList = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();

        Cursor resultsCursor = dataSource.getAll(ItemTables.RESULTS_TABLE);
//        Cursor answerCursor = dataSource.getAll(ItemTables.ANSWER_TABLE);

        resultsCursor.moveToFirst();
//        answerCursor.moveToFirst();

        while(!resultsCursor.isAfterLast()) {

            long id = resultsCursor.getLong(resultsCursor.getColumnIndex(ItemTables.RESULT_ID));
            String gameType = resultsCursor.getString(resultsCursor.getColumnIndex(ItemTables.QUIZ_TYPE));
            int total=  resultsCursor.getInt(resultsCursor.getColumnIndex(ItemTables.TOTAL));
            int score = resultsCursor.getInt(resultsCursor.getColumnIndex(ItemTables.SCORE));
            String timestamp = resultsCursor.getString(resultsCursor.getColumnIndex(ItemTables.DAY));

            Answer answer = null;
//            Cursor answerCursor = dataSource.singleItem(id, ItemTables.ANSWER_TABLE);
            Cursor answerCursor = dataSource.items(id, ItemTables.RESULT_ID, ItemTables.ANSWER_TABLE);
            Log.i("answer", String.valueOf(answerCursor.getCount()));
            answerCursor.moveToFirst();
            while(!answerCursor.isAfterLast()) {
                long answerId = answerCursor.getLong(answerCursor.getColumnIndex(ItemTables.ANSWER_ID));
                long wordId = answerCursor.getLong(answerCursor.getColumnIndex(ItemTables.WORD_ID));
                int correct = answerCursor.getInt(answerCursor.getColumnIndex(ItemTables.CORRECT_INCORRECT));

                if(correct == 1) {
                    answer = new Answer(answerId, wordId, id, true);
                } else {
                    answer = new Answer(answerId, wordId, id, false);

                }
                answerCursor.moveToNext();
                answers.add(answer);
            }

//            Cursor wordCursor = dataSource.singleItem(wordId, ItemTables.WORD_TABLE);

            Results results = new Results(id, gameType, score, total, timestamp, answers);
            Log.i("size", String.valueOf(answers.size()));
            resultsArrayList.add(results);

            resultsCursor.moveToNext();

        }

        return resultsArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(quiz_type);
        dest.writeInt(score);
        dest.writeInt(total);
        dest.writeString(timeStamp);
        dest.writeString(answered_word);
        dest.writeString(unanswered_word);
    }
}
