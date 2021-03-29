package com.ash.word_it;

public class ScoreItem {

    public ScoreItem(int a, int b) {
        score_percentage = a;
        question_count   = b;
    }

    private int score_percentage;
    private int question_count;

    public int getQuestion_count() {
        return question_count;
    }

    public int getScore_percentage() {
        return score_percentage;
    }
}
