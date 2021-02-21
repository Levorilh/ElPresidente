package com.company;

public class Faction {
    public static final float BRIBE_UPGRADE = 1.1f;
    public static int BRICE_COST_PER_PARTISANT = 15;

    String name;
    private int satisfaction;
    private int partisant;

    public Faction(String name, int satisfaction, int partisant) {
        this.name = name;
        this.satisfaction = satisfaction;
        this.partisant = partisant;
    }

    public void addPartisant(int partisant) {
        this.partisant += partisant;
        this.partisant = Math.max(0 , this.satisfaction);
    }

    public void addSatisfaction(int satisfaction) {
        if (this.satisfaction > 0) {
            this.satisfaction += satisfaction;
        }
        this.satisfaction = Math.min(Math.max(0, this.satisfaction) , 100);

    }

    public String getName() {
        return name;
    }

    public int getBribePrice() {
        return partisant * BRICE_COST_PER_PARTISANT;
    }

    public Faction bribe() {
        satisfaction *= BRIBE_UPGRADE;

        return this;
    }

    public boolean canBeBribed(int treasury) {
        return treasury >= getBribePrice();
    }


    public int getSatisfaction() {
        return satisfaction;
    }

    public static Faction from(String csv){
        String[] values = csv.split(";");
        return new Faction(values[0] , Integer.parseInt(values[1]) , Integer.parseInt(values[2]));
    }

    public int getPartisant() {
        return partisant;
    }
}
