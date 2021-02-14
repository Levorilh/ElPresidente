package com.company;

public enum DifficultyLevel {
    EASY(20),
    MEDIUM(35),
    HARD(50);

    public int minimalSatisfaction;

    DifficultyLevel(int minimalSatisfaction) {
        this.minimalSatisfaction = minimalSatisfaction;
    }
}
