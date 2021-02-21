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
        return new ArrayList<>() {
            {
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.WINTER, "Une tempête de neige traverse votre ile !",
                        new ArrayList<>() {
                            {
                                add(new Choice("Utiliser l'éventail magique pour éloigner la tempête", null, 0, 15, 0, 0));
                                add(new Choice("Abandonner les femmes et les enfants", new ArrayList<>() {{
                                    add(new Faction("Loyalistes", -20, -10));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.WORLDWAR);
                }}, Season.MISCELLANEOUS, "Les Alliés demandent un soutien financier !",
                        new ArrayList<>() {
                            {
                                add(new Choice("Accepter", null, -200, 0, 0, 0));
                                add(new Choice("Refuser", new ArrayList<>() {{
                                    add(new Faction("Loyalistes", -20, 0));
                                }}, 50, 50, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.WORLDWAR);
                }}, Season.MISCELLANEOUS, "Arrivée d'étrangers ces étrangers sont déportés car leur pays est en guerre\n" +
                        "                que faire ?",
                        new ArrayList<>() {
                            {
                                add(new Choice("les accepter", new ArrayList<>() {{
                                    add(new Faction("Communistes", +10, +10));
                                    add(new Faction("Nationnalistes", -10, 0));
                                }}, 0, 0, 0, 0));
                                add(new Choice("Refuser", new ArrayList<>() {{
                                    add(new Faction("Communistes", -10, 0));
                                    add(new Faction("Nationnalistes", +10, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Une forte demande d'essence est demandée pour continuer à faire tourner les usines\n" +
                        "                Que faire ?",
                        new ArrayList<>() {
                            {
                                add(new Choice("Ne rien faire", new ArrayList<>() {{
                                    add(new Faction("Capitalistes", -15, +10));
                                }}, 0, 0, -30, 0));
                                add(new Choice("Acheter a l'etranger", new ArrayList<>() {{
                                    add(new Faction("Capitalistes", +10, +10));
                                    add(new Faction("Communistes", -15, 0));
                                }}, -50, 0, 0, 0));
                                add(new Choice("Produire plus", new ArrayList<>() {{
                                    add(new Faction("Communistes", +10, 0));
                                    add(new Faction("Ecologistes", -20, -3));
                                }}, 100, 0, +10, +25));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Tempête sur l'ile!",
                        new ArrayList<>() {
                            {
                                add(new Choice("Acheter de la nourriture", null, -50, 100, 0, 15));
                                add(new Choice("Ne rien faire", null, 200, 0, -8, -8));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Arrestation du plus grand bandit !",
                        new ArrayList<>() {
                            {
                                add(new Choice("Condamner à mort", new ArrayList<>() {{
                                    add(new Faction("Loyalistes", +10, 5));
                                    add(new Faction("Communistes", +10, 5));
                                }}, 100, 0, 5, 5));
                                add(new Choice("Emprisonner", new ArrayList<>() {{
                                    add(new Faction("Loyalistes", -20, 0));
                                }}, 0, 0, 0, 0));
                                add(new Choice("Liberer", new ArrayList<>() {{
                                    add(new Faction("Loyalistes", -50, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Les gilets jaunes débarquent !",
                        new ArrayList<>() {
                            {
                                add(new Choice("Mettre en place une prime d'activité", new ArrayList<>() {{
                                    add(new Faction("Communistes", +20, 0));
                                }}, -500, 0, 0, 0));
                                add(new Choice("Les faire espérer", new ArrayList<>() {{
                                    add(new Faction("Communistes", 5, 0));
                                }}, 100, 0, 0, 0));
                                add(new Choice("Ne rien faire", new ArrayList<>() {{
                                    add(new Faction("Communistes", -60, 0));
                                }}, 0, 0, -50, -50));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Epidemie de grippe!",
                        new ArrayList<>() {
                            {
                                add(new Choice("Acheter le vaccin", null, -2000, 0, 0, 0));
                                add(new Choice("Produire le vaccin", null, -1000, 0, 25, 0));
                                add(new Choice("Ne rien faire", null, -500, 0, 10, 10));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Pénurie de blé",
                        new ArrayList<>() {
                            {
                                add(new Choice("Produire plus", new ArrayList<>() {{
                                    add(new Faction("Communistes", -10, 0));
                                    add(new Faction("Ecologistes", 20, 5));
                                }}, 0, 0, -10, 20));
                                add(new Choice("Acheter à l'étranger", new ArrayList<>() {{
                                    add(new Faction("Capitalistes", 5, 0));
                                    add(new Faction("Communistes", -10, 0));
                                    add(new Faction("Ecologistes", -15, 0));
                                }}, -100, 0, 0, 0));
                                add(new Choice("Ne rien faire", new ArrayList<>() {{
                                    add(new Faction("Capitalistes", -10, 0));
                                    add(new Faction("Communistes", -10, 0));
                                    add(new Faction("Libéraux", -10, 0));
                                    add(new Faction("Religieux", -10, 0));
                                    add(new Faction("Militaristes", -10, 0));
                                    add(new Faction("Ecologistes", -10, 0));
                                    add(new Faction("Nationnalistes", -10, 0));
                                    add(new Faction("Loyalistes", -10, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Une église vient de se faire frapper par la foudre!",
                        new ArrayList<>() {
                            {
                                add(new Choice("Réparer", new ArrayList<>() {{
                                    add(new Faction("Religieux", 20, 0));
                                    add(new Faction("Libéraux", -20, 0));
                                }}, 50, 0, 5, 0));
                                add(new Choice("Ne rien faire", new ArrayList<>() {{
                                    add(new Faction("Religieux", -20, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Un pays voisin entre en guerre",
                        new ArrayList<>() {
                            {
                                add(new Choice("Combattre à leurs cotés", new ArrayList<>() {{
                                    add(new Faction("Militaristes", 10, 0));
                                    add(new Faction("Ecologistes", -20, 0));
                                }}, -200, 0, 5, 0));
                                add(new Choice("Combattre contre eux", new ArrayList<>() {{
                                    add(new Faction("Militaristes", 10, 0));
                                    add(new Faction("Ecologistes", -20, 0));
                                }}, -200, 0, 0, 0));
                                add(new Choice("Ne rien faire", null, 0, 0, 0, 0));
                                add(new Choice("PopCorn!", new ArrayList<>() {{
                                    add(new Faction("Capitalistes", 10, 0));
                                    add(new Faction("Communistes", 10, 0));
                                    add(new Faction("Libéraux", 10, 0));
                                    add(new Faction("Religieux", 10, 0));
                                    add(new Faction("Militaristes", 10, 0));
                                    add(new Faction("Ecologistes", 10, 0));
                                    add(new Faction("Nationnalistes", 10, 0));
                                    add(new Faction("Loyalistes", 10, 0));
                                }}, 0, 0, 5, 5));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
            }
        };
    }
}