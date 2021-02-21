package com.company;

import java.util.ArrayList;

public class Party {
    public int round;
    public ArrayList<Country> listCountry;
    public DifficultyLevel difficultyLevel;
    public ArrayList<Circumstance> listCircumstance;

    public Party() {
        round = 0;
        listCountry = new ArrayList<>();
        this.difficultyLevel = DifficultyLevel.EASY;
        listCircumstance = new ArrayList<>();
    }

    public void addCountry(Country country) {
        listCountry.add(country);
    }

    public void addCircumstance(Circumstance circumstance){
        listCircumstance.add(circumstance);
    }

    public boolean isPartyOver() {
        int leftCountries = listCountry.size();
        for (Country country : listCountry) {
            if (country.isGameOver()) {
                leftCountries--;
            }
        }
        if(listCircumstance.contains(Circumstance.MULTIPLAYER))
            return leftCountries <= 1;
        else
            return leftCountries <= 0;
    }
}
