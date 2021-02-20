package com.company;

public class Choice {
    String description;
    int[] satisfactionReductions;
    int treasury;
    int food;
    int industry;
    int farm;

    public Choice(String description, int[] satisfactionReductions, int treasury, int food, int industry, int farm) {
        this.description = description;
        this.satisfactionReductions = satisfactionReductions;
        this.treasury = treasury;
        this.food = food;
        this.industry = industry;
        this.farm = farm;
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


    //TODO constructor from file/text/csv whatever

    //TODO applyChanges
}
