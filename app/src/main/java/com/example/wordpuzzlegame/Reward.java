package com.example.wordpuzzlegame;

public class Reward {

    private long reward_id;
    private String game_type;
    private int seconds;
    private int heart;

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

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }
}
