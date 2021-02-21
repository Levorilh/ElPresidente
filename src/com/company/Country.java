package com.company;

import java.util.Random;

public class Country {
    private final int FOOD_PRICE = 8;
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
        return ListFaction.fromDefault();
    }

    public void endOfYearPreBilan() {
        treasury += industry * 10;
        food += farming * 40;
    }

    public int getPopulation() {
        int population = 0;
        for (Faction faction : listFaction) {
            population += faction.getPartisant();
        }
        return population;
    }

    public int foodNeeded() {
        return getPopulation() * foodByPartisantByYear;
    }

    public void endOfYearBilan() {
        Random r = new Random();
        int consumption = foodNeeded();
        int production = farming * 40;

        if (consumption < production) {
            int population = getPopulation();
            for (int i = 0; i < (population * r.nextInt(11) / 100); i++) {
                listFaction.get(r.nextInt(listFaction.size())).addPartisant(1);
            }
        }

        if (food < consumption) {
            int mort = (int) Math.floor((consumption - food) / 4);
            for (int i = 0; i < mort; i++) {
                listFaction.get(r.nextInt(listFaction.size())).addPartisant(-1);
                for (Faction faction : listFaction) {
                    faction.addSatisfaction(-2);
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
        return foodBuy * FOOD_PRICE;
    }

    public boolean canBuyFood(int foodBuy) {
        return treasury >= foodPrice(foodBuy);
    }

    public void buyFood(int foodBuy) {
        treasury -= foodPrice(foodBuy);
        food += foodBuy;
    }

    public boolean bribe(Faction faction) {
        int treasuryToBribe;
        if (!faction.canBeBribed(this.treasury)) {
            return false;
        }

        treasuryToBribe = faction.getBribePrice();
        faction.bribe();
        treasury -= treasuryToBribe;

        corruptionSideEffect(treasuryToBribe);
        return true;
    }

    private void corruptionSideEffect(int paidValue) {
        int LOYALISTS_INDEX = listFaction.size() - 1;
        int CORRUPTION_RATIO = 10;
        listFaction.get(LOYALISTS_INDEX)
                .addSatisfaction(-(paidValue / CORRUPTION_RATIO));
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

    public void addIndustry(int newIndustry) {
        industry += newIndustry;
        if (isOverExploited()) {
            industry -= newIndustry;
        }
        industry = Math.max(0, industry);
    }

    public void addFarming(int newFarming) {
        farming += newFarming;
        if (isOverExploited()) {
            farming -= newFarming;
        }
        farming = Math.max(0, farming);
    }

    public void addTreasury(int value) {
        treasury += value;
    }

    public void addFood(int food) {
        this.food += food;
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

    public Event deleteImpossibleChoiceInEvent(Event event) {
        Event newEvent = new Event(event.circumstance, event.season,event.description, event.choices);
        for (int i = event.choices.size() - 1; i >= 0; i--) {
            if (event.choices.get(i).treasury + this.treasury < 0 || event.choices.get(i).food + this.food < 0) {
                event.choices.remove(i);
            }
        }
        return newEvent;
    }
}
