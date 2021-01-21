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
    int agriculture;
    int minimalSatisfactionAllowed;

    Country(String name, ArrayList<Faction> listFaction, int treasury, int food, int industry, int agriculture, int minimalSatisfactionAllowed){
        this.name = name;
        this.listFaction = listFaction;
        this.treasury = treasury;
        this.food = food;
        this.agriculture = agriculture;
        this.industry = industry;
        this.minimalSatisfactionAllowed = minimalSatisfactionAllowed;
    }

    public Country newCountry(String name, int minimalSatisfactionAllowed) {
        return new Country(name, defaultCountryFaction(), 200, 0, 15, 15, minimalSatisfactionAllowed);
    }

    private ArrayList<Faction> defaultCountryFaction(){
        ArrayList<Faction> listFaction = new ArrayList<Faction>();
        listFaction.add(new Faction("Capitalistes",50,15));
        listFaction.add(new Faction("Commusnistes",50,15));
        listFaction.add(new Faction("liberaux",50,15));
        listFaction.add(new Faction("religieux",50,15));
        listFaction.add(new Faction("militaristes",50,15));
        listFaction.add(new Faction("ecologistes",50,15));
        listFaction.add(new Faction("natiolaliste",100,15));
        return listFaction;
    }

    public void EndOfYearPreBilan() {
        treasury += industry * 10;
        food += agriculture * 40;
    }

    public int getPopulation() {
        int population = 0;
        for(Faction faction : listFaction) {
            population += faction.partisant;
        }
        return population;
    }

    public int foodNeeded() {
        return getPopulation() * foodByPartisantByYear;
    }

    public void EndOfYearBilan() {
        Random r= new Random();
        int consumption = foodNeeded();
        int production = agriculture * 40;

        if(consumption < production) {
            int population = getPopulation();
            for(int i=0; i < (population * r.nextInt(11) / 100); i++) {
                listFaction.get(r.nextInt(listFaction.size())).partisant++;
            }
        }

        if(food < consumption) {
            int mort = (int) Math.floor((consumption - food) / 4);
            for(int i=0; i < mort; i++) {
                listFaction.get(r.nextInt(listFaction.size())).partisant--;
            }
            for(Faction faction : listFaction) {
                faction.satisfaction -= 2;
            }
            food = 0;
        }
        else {
            food -= consumption;
        }
    }

    public boolean isGameOver() {
        int population = 0;
        int satisfaction= 0;
        for(Faction faction : listFaction) {
            population += faction.partisant;
            satisfaction += faction.satisfaction;
        }
        return (satisfaction / population) < minimalSatisfactionAllowed;
    }
}
