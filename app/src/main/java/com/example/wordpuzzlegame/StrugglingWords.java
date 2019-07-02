package com.example.wordpuzzlegame;

public class StrugglingWords {

    private long id;
    private long word_id;
    private long lang_code;
    private String quiz_type;
    private int wrongly_answered;
    private int rightly_answered;

    public StrugglingWords() {
    }

    public StrugglingWords(String quiz_type) {
        this.quiz_type = quiz_type;
    }

    public StrugglingWords(long id, long word_id, long lang_code, String quiz_type, int wrongly_answered, int rightly_answered) {
        this.id = id;
        this.word_id = word_id;
        this.lang_code = lang_code;
        this.quiz_type = quiz_type;
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

    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
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
}
