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
                                    add(new Faction("Loyalistes", -20, -10));
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
                                    add(new Faction("Loyalistes", -20, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.WORLDWAR);
                }}, Season.MISCELLANEOUS, "Arriver d'étranger c'est etranger son deporter car leurs pays est en guerre\n" +
                        "                que faire ?",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("les accepter", new ArrayList<Faction>() {{
                                    add(new Faction("Communistes", +10, +10));
                                    add(new Faction("Nationnalistes", -10, 0));
                                }}, 0, 0, 0, 0));
                                add(new Choice("Refuser", new ArrayList<Faction>() {{
                                    add(new Faction("Communistes", -10, 0));
                                    add(new Faction("Nationnalistes", +10, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Une forte demande d'essence est demandé pour continuer à faire tourner les usines\n" +
                        "                Que faire ?",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Ne rien", new ArrayList<Faction>() {{
                                    add(new Faction("Capitalistes", -15, +10));
                                }}, 0, 0, -30, 0));
                                add(new Choice("Acheter a l'etranger", new ArrayList<Faction>() {{
                                    add(new Faction("Capitalistes", +10, +10));
                                    add(new Faction("Communistes", -15, 0));
                                }}, -50, 0, 0, 0));
                                add(new Choice("Produire plus", new ArrayList<Faction>() {{
                                    add(new Faction("Communistes", +5, 0));
                                    add(new Faction("Ecologistes", -20, 0));
                                }}, 0, 0, 0, +15));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Tempete sur l'ile!",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Acheter de la nourriture", null, -50, 0, 0, 0));
                                add(new Choice("Ne rien faire", null, 0, 0, 0, -30));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Arrestation du plus grand bandit !",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Condamner à mort", new ArrayList<Faction>() {{
                                    add(new Faction("Loyaliste", +10, 0));
                                }}, 0, 0, 0, 0));
                                add(new Choice("Emprisonner", new ArrayList<Faction>() {{
                                    add(new Faction("Loyaliste", -20, 0));
                                }}, 0, 0, 0, 0));
                                add(new Choice("Liberer", new ArrayList<Faction>() {{
                                    add(new Faction("Loyaliste", -50, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Les gilet jaune débarque !",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Mettre en place un prime d'activité", new ArrayList<Faction>() {{
                                    add(new Faction("Communiste", +20, 0));
                                }}, -500, 0, 0, 0));
                                add(new Choice("Les faire espèrer", new ArrayList<Faction>() {{
                                    add(new Faction("Communiste", 5, 0));
                                }}, 100, 0, 0, 0));
                                add(new Choice("Ne rien faire", new ArrayList<Faction>() {{
                                    add(new Faction("Communiste", -60, 0));
                                }}, 0, 0, -50, -50));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Epidemie de grippe!",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Acheter le vaccin", null, -2000, 0, 0, 0));
                                add(new Choice("Produire le vaccin", null, -1000, 0, 25, 0));
                                add(new Choice("Ne rien faire", null, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Pénurie de blé",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Produire plus", new ArrayList<Faction>() {{
                                    add(new Faction("Communistes" , -10 ,0));
                                }}, 0, 0, -20, 20));
                                add(new Choice("Acheter à l'étranger", new ArrayList<Faction>() {{
                                    add(new Faction("Capitalistes", 5, 0));
                                    add(new Faction("Communistes", -10, 0));
                                }}, -100, 0, 0, 0));
                                add(new Choice("Ne rien faire", new ArrayList<Faction>() {{
                                    add(new Faction("Capitalistes", -10, 0));
                                    add(new Faction("Communistes", -10, 0));
                                    add(new Faction("Libéraux", -10, 0));
                                    add(new Faction("Religieux", -10, 0));
                                    add(new Faction("Militaristes", -10, 0));
                                    add(new Faction("Ecologistes", -10, 0));
                                    add(new Faction("Nationalistes", -10, 0));
                                    add(new Faction("Loyalistes", -10, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Une église vient de se faire frapper par la foudre!",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Réparer", new ArrayList<Faction>() {{
                                    add(new Faction("Religieux" , 20 ,0));
                                    add(new Faction("Libéraux" , -20 ,0));
                                }}, 50, 0, 5, 0));
                                add(new Choice("Ne rien faire", new ArrayList<Faction>() {{
                                    add(new Faction("Religieux", -20, 0));
                                }}, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
                add(new Event(new ArrayList<Circumstance>() {{
                    add(Circumstance.ANYTIME);
                }}, Season.MISCELLANEOUS, "Un pays voisin entre en guerre",
                        new ArrayList<Choice>() {
                            {
                                add(new Choice("Combattre à leurs cotés", new ArrayList<Faction>() {{
                                    add(new Faction("Militaristes" , 10 ,0));
                                    add(new Faction("Ecologistes" , -20 ,0));
                                }}, -200, 0, 5, 0));
                                add(new Choice("Combattre contre eux", new ArrayList<Faction>() {{
                                    add(new Faction("Militaristes" , 10 ,0));
                                    add(new Faction("Ecologistes" , -20 ,0));
                                }}, -200, 0, 0, 0));
                                add(new Choice("Ne rien faire", null, 0, 0, 0, 0));
                            }
                        }
                ));
                //--------------------------------------------------------------------------------------
            }
        };
    }
}
