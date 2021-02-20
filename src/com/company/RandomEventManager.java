package com.company;

import java.util.ArrayList;

public class RandomEventManager {
    Season season;
    ArrayList<Circumstance> listCircumstance;


    public RandomEventManager(ArrayList<Circumstance> listCircumstance, Season season) {
        this.listCircumstance = listCircumstance;
        this.season = season;
    }

    public Event getEvent() {
        return null;
    }

    private ArrayList<Event> getEventList() {
        return new ArrayList<Event>() {
            {
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Une tornade traverse votre ile !",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Utiliser l'étentaille magique pour éloigner la tornade", null, 0, 0, 0, 0));
                                add(new Choice("Abandonner le femmes et les enfants", new ArrayList<Faction>() {{
                                    add(new Faction("Loyaliste", -20, -10));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.WORLDWAR);
                }}, Season.MISCELLANEOUS, "Les Allier demande un soutient financier !",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Accepter", null, -200, 0, 0, 0));
                                add(new Choice("Refuser", new ArrayList<Faction>() {{
                                    add(new Faction("Loyaliste", -20, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
            }
        };
    }
}
