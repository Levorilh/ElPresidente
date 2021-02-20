package com.company;

import java.util.ArrayList;

public class Choice {
    String description;
    ArrayList<Faction> factions;
    int treasury;
    int food;
    int industry;
    int farming;

    //TODO constructor from file/text/csv whatever

    public Country applyChange(Country country){
        country.treasury+=treasury;
        country.food+=food;
        country.industry+=industry;
        country.farming+=farming;

        for(Faction faction : factions){
            for(int i = 0; i < country.listFaction.size(); i++){
                if(country.listFaction.get(i).name.equals(faction.name)){
                    country.listFaction.get(i).addSatisfaction(faction.getSatisfaction());
                    country.listFaction.get(i).addPartisant(faction.partisant);
                }
            }
        }

        return country;
    }
}
