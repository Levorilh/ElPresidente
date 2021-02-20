package com.company;

import java.util.ArrayList;

public class Choice {
    String description;
    int[] satisfactionReductions;
    int treasury;
    int food;
    int industry;
    int farming;

    public Choice(String description, int[] satisfactionReductions, int treasury, int food, int industry, int farming) {
        this.description = description;
        this.satisfactionReductions = satisfactionReductions;
        this.treasury = treasury;
        this.food = food;
        this.industry = industry;
        this.farming = farming;
    }

    public Choice fromText(String csvLine){
        String[] everything = csvLine.split(";");

        String description = everything[0];
        String[] satisfactionReductions = everything[1].split(",");
        int[] satisfactionsReductionsConverted = new int[8];

        int i = 0;
        for (String oneInt: satisfactionReductions) {
            satisfactionsReductionsConverted[i] = Integer.parseInt(oneInt);
            i ++;
        }



        int treasury = Integer.parseInt(everything[2]);
        int food= Integer.parseInt(everything[3]);
        int industry = Integer.parseInt(everything[4]);
        int farm = Integer.parseInt(everything[5]);

        return new Choice(description, satisfactionsReductionsConverted, treasury , food , industry , farm);

    }

    public void executeOn(Country c){
        c.setFarming(c.getFarming() - farming);
        c.setFood(c.getFood() - food);
        c.setIndustry(c.getIndustry() - industry);
        c.setTreasury(c.getTreasury() - treasury);

        ArrayList<Faction> listFactions = c.getListFaction();

        for (int i = 0; i < listFactions.size() ; i++) {
            listFactions.get(i).reduceSatisfaction(satisfactionReductions[i]);
        }
    }
}
