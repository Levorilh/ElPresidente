package com.company;

import java.util.ArrayList;

public class Choice {
    String description;
    ArrayList<Faction> factions;
    int treasury;
    int food;
    int industry;
    int farming;

    public Choice(String description, ArrayList<Faction> factions, int treasury, int food, int industry, int farming) {
        this.description = description;
        this.factions = factions;
        this.treasury = treasury;
        this.food = food;
        this.industry = industry;
        this.farming = farming;
    }

    public Choice fromText(String csvLine){
        String[] everything = csvLine.split(";");

        String description = everything[0];
        String[] allFactions = everything[1].split(",");
        ArrayList<Faction> factions = new ArrayList<Faction>();

        for (String oneInt: allFactions) {
            //TODO Faction from line csv;
            factions.add(new Faction("",0,0));
        }

        int treasury = Integer.parseInt(everything[2]);
        int food= Integer.parseInt(everything[3]);
        int industry = Integer.parseInt(everything[4]);
        int farm = Integer.parseInt(everything[5]);

        return new Choice(description, factions, treasury , food , industry , farm);

    }
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
