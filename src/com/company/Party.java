package com.company;

import java.util.ArrayList;

public class Party {
    public int round;
    public ArrayList<Country> listCountry;

    public Party() {
        round = 0;
        listCountry = new ArrayList<>();
    }

    public Party addCountry(Country country){
        listCountry.add(country);
        return this;
    }

    public boolean isPartyOver() {
        int leftCountries = listCountry.size();
        for (Country country: listCountry) {
            if(country.isGameOver()){
                leftCountries--;
            }
        }
        return leftCountries <= 1;
    }
}
