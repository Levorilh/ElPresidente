package com.company;

import java.util.ArrayList;

public class RandomEventManager {
    Season season;
    ArrayList<Circumstance> listCircumstance;



    public RandomEventManager(ArrayList<Circumstance> listCircumstance, Season season) {
        this.listCircumstance=listCircumstance;
        this.season=season;
    }

    public Event getEvent() {

    }

    private ArrayList<Event> getEvent(){
        return new ArrayList<Event>(
                new Event(new ArrayList<Circumstance>(Circumstance.ANYTIME),Season.MISCELLANEOUS,"Une tornade traverse votre ile !", new ArrayList<Choice>(
                        new Choice("Utiliser l'étentaille magique pour éloigner la tornade",null,0,0,0,0),
                        new Choice("Abandonner le femmes et les enfants", new ArrayList<Faction>(new Faction("Loyaliste",-20,-10)),0,0,0,0) ))
        );
    }
}
