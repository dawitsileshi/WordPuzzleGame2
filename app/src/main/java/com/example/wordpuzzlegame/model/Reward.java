package com.example.wordpuzzlegame.model;

import android.content.Context;

public class Reward {

    private long reward_id;
    private long kid_id;
    private String game_type;
    private int secondsOrHeart;

    private Context context;

    public Reward(Context context) {
        this.context = context;
    }

    private Reward(long kidId, int secondsOrHeart) {
        this.kid_id = kidId;
        this.secondsOrHeart = secondsOrHeart;
    }

    public long getReward_id() {
        return reward_id;
    }

    public void setReward_id(long reward_id) {
        this.reward_id = reward_id;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public int getSecondsOrHeart() {
        return secondsOrHeart;
    }

    public void setSecondsOrHeart(int secondsOrHeart) {
        this.secondsOrHeart = secondsOrHeart;
    }
}
