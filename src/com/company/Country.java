package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Country {
    final int foodByPartisantByYear = 4;

    String name;
    ArrayList<Faction> listFaction;
    int treasury;
    int food;
    int industry;
    int farming;
    int minimalSatisfactionAllowed;

    Country(String name, ArrayList<Faction> listFaction, int treasury, int food, int industry, int farming, int minimalSatisfactionAllowed) {
        this.name = name;
        this.listFaction = listFaction;
        this.treasury = treasury;
        this.food = food;
        this.farming = farming;
        this.industry = industry;
        this.minimalSatisfactionAllowed = minimalSatisfactionAllowed;
    }

    public Country(String name, int minimalSatisfactionAllowed) {
        this(name, null, 200, 0, 15, 15, minimalSatisfactionAllowed);
        this.listFaction = defaultCountryFaction();
    }

    private ArrayList<Faction> defaultCountryFaction() {
        int DEFAULT_SATISFACTION = 50;
        int SPECIAL_SATISFACTION_LOYALISTS = 100;
        int PARTISANT_BY_FACTION = 15;
        ArrayList<Faction> listFaction = new ArrayList<Faction>();
        listFaction.add(new Faction("Capitalistes", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Communistes", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Libéraux", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Religieux", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Militaristes", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Ecologistes", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Nationalistes", DEFAULT_SATISFACTION, PARTISANT_BY_FACTION));
        listFaction.add(new Faction("Loyalistes", SPECIAL_SATISFACTION_LOYALISTS, PARTISANT_BY_FACTION));
        return listFaction;
    }

    public void EndOfYearPreBilan() {
        treasury += industry * 10;
        food += farming * 40;
    }

    public int getPopulation() {
        int population = 0;
        for (Faction faction : listFaction) {
            population += faction.partisant;
        }
        return population;
    }

    public int foodNeeded() {
        return getPopulation() * foodByPartisantByYear;
    }

    public void EndOfYearBilan() {
        Random r = new Random();
        int consumption = foodNeeded();
        int production = farming * 40;

        if (consumption < production) {
            int population = getPopulation();
            for (int i = 0; i < (population * r.nextInt(11) / 100); i++) {
                listFaction.get(r.nextInt(listFaction.size())).partisant++;
            }
        }

        if (food < consumption) {
            int mort = (int) Math.floor((consumption - food) / 4);
            for (int i = 0; i < mort; i++) {
                listFaction.get(r.nextInt(listFaction.size())).partisant--;
                for (Faction faction : listFaction) {
                    faction.satisfaction -= 2;
                }
            }
            food = 0;
        } else {
            food -= consumption;
        }
    }

    public boolean isGameOver() {
        int population = 0;
        int satisfaction = 0;
        for (Faction faction : listFaction) {
            population += faction.partisant;
            satisfaction += faction.satisfaction;
        }
        return (satisfaction / population) < minimalSatisfactionAllowed;
    }

    public int foodPrice(int foodBuy) {
        return foodBuy * 8;
    }

    public boolean canBuyFood(int foodBuy) {
        return treasury >= foodPrice(foodBuy);
    }

    public void buyFood(int foodBuy) {
        treasury -= foodPrice(foodBuy);
        food += foodBuy;
    }

    private int bribePriceFaction(int indexFaction, int satisfationBuy) {
        return listFaction.get(indexFaction).partisant * 15 * satisfationBuy;
    }

    public boolean canBribeFaction(int indexFaction, int satisfationBuy) {
        return treasury >= bribePriceFaction(indexFaction, satisfationBuy);
    }

    public void BribeFaction(int indexFaction, int satisfationBuy) {
        treasury -= bribePriceFaction(indexFaction, satisfationBuy);
        listFaction.get(indexFaction).satisfaction += satisfationBuy;
    }

    public String getName() {
        return this.name;
    }

    public int getTreasury() {
        return this.treasury;
    }

    public int getFood() {
        return this.food;
    }

    public int getIndustry() {
        return this.industry;
    }

    public int getFarming() {
        return this.farming;
    }

    public ArrayList<Faction> getListFaction() {
        return listFaction;
    }
}
