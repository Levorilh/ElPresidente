package com.company;

import java.util.ArrayList;
import java.util.Random;

public class RandomEventManager {
    Season season;
    ArrayList<Circumstance> listCircumstance;


    public RandomEventManager(ArrayList<Circumstance> listCircumstance, Season season) {
        this.listCircumstance = listCircumstance;
        this.season = season;
    }

    public Event getEvent() {
        ArrayList<Event> list = getEventListByCircumstanceAndSeason(listCircumstance, season);
        int random = new Random().nextInt(list.size());
        return list.get(random);
    }

    private ArrayList<Event> getEventListByCircumstanceAndSeason(ArrayList<Circumstance> listCircumstance, Season season) {
        ArrayList<Event> list = new ArrayList<>();
        for (Event event : getEventList()) {
            if (event.season.equals(Season.MISCELLANEOUS) || event.season.equals(season)) {
                boolean add = false;
                for (Circumstance circumstance1 : event.circumstance) {
                    for (Circumstance circumstance2 : listCircumstance) {
                        if (circumstance1.equals(circumstance2)) {
                            add = true;
                            break;
                        } else {
                            add = false;
                        }
                    }
                    if (!add){
                        break;
                    }
                }
                if (add) {
                    list.add(event);
                }
            }
        }
        return list;
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
                //--------------------------------------------------------------------------------------
            }
        };
    }
}
