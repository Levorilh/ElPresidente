package com.company;

import javax.swing.plaf.IconUIResource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Country {
    final int foodByPartisantByYear = 4;

    String name;
    ListFaction listFaction;
    int treasury;
    int food;
    int industry;
    int farming;
    int minimalSatisfactionAllowed;

    public Country(String name, ListFaction listFaction, int treasury, int food, int industry, int farming, int minimalSatisfactionAllowed) {
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

    private ListFaction defaultCountryFaction() {
        int DEFAULT_SATISFACTION = 50;
        int SPECIAL_SATISFACTION_LOYALISTS = 100;
        int PARTISANT_BY_FACTION = 15;

        return ListFaction.fromDefault();
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
                    faction.reduceSatisfaction(2);
                }
            }
            food = 0;
        } else {
            food -= consumption;
        }
    }

    public boolean isGameOver() {
        return listFaction.getAverageSatisfaction() < minimalSatisfactionAllowed;
    }

    public boolean isOverExploited() {
        int FULL_COVERAGE_PERCENTAGE = 100;
        return farming + industry > FULL_COVERAGE_PERCENTAGE;
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

    public void bribe(Faction faction) {
        int treasuryToBribe;
        if (!faction.canBeBribed(this.treasury)) {
            return;
        }

        treasuryToBribe = faction.getBribePrice();
        faction.bribe();
        treasury -= treasuryToBribe;

        corruptionSideEffect(treasuryToBribe);
    }

    private void corruptionSideEffect(int paidValue) {
        int LOYALISTS_INDEX = listFaction.size() - 1;
        int CORRUPTION_RATIO = 10;
        listFaction.get(LOYALISTS_INDEX)
                .reduceSatisfaction(paidValue / CORRUPTION_RATIO);
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

    public ListFaction getListFaction() {
        return listFaction;
    }

    public Country addIndustry(int newIndustry) {
        industry += newIndustry;
        if (isOverExploited()) {
            industry -= newIndustry;
        }
        return this;
    }

    public Country addFarming(int newFarming) {
        industry += newFarming;
        if (isOverExploited()) {
            industry -= newFarming;
        }
        return this;
    }

    public void addTreasury(int value) {
        treasury += value;
    }

    @Override
    public String toString() {
        return "Country de " + name +
                ",\n Satisfaction moyenne :" + listFaction.getAverageSatisfaction() +
                ",\n Trésorerie " + treasury +
                ",\n Réserves de nourriture " + food +
                ",\n Partie de l'île réservée à l'industrie " + industry + "%" +
                ",\n Partie de l'île réservée à l'agriculture " + farming + "%"
                ;
    }
}
