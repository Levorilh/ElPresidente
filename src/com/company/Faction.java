package com.company;

public class Faction {
    String name;
    int satisfaction;
    int partisant;
    public Faction(String name, int satisfaction, int partisant) {
        this.name=name;
        this.satisfaction=satisfaction;
        this.partisant=partisant;
    }

    public void addPartisant(int partisant){
        this.partisant += partisant;
    }

    public void addSatisfaction(int satisfaction){
        if(this.satisfaction > 0){
            this.satisfaction += satisfaction;
        }
    }
}
