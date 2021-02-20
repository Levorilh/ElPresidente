package com.company;

import java.util.ArrayList;

public class Choice {
    String description;
    ArrayList<Faction> factions;
    int treasury;
    int food;
    int industry;
    int farming;

    public Choice(String description, ArrayList<Faction> factions, int treasury, int food, int industry, int farming){
        this.description=description;
        this.factions=factions;
        this.treasury=treasury;
        this.food=food;
        this.industry=industry;
        this.farming=farming;
    }

    public Country applyChange(Country country){
        country.addTreasury(treasury);
        country.addFood(food);
        country.addIndustry(industry);
        country.addFarming(farming);

        if(factions != null) {
            for (Faction faction : factions) {
                for (int i = 0; i < country.listFaction.size(); i++) {
                    if (country.listFaction.get(i).name.equals(faction.name)) {
                        country.listFaction.get(i).addSatisfaction(faction.getSatisfaction());
                        country.listFaction.get(i).addPartisant(faction.partisant);
                    }
                }
            }
        }

        return country;
    }
}
