package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ListFaction extends ArrayList<Faction> {

    public ListFaction() {

    }

    public static ListFaction fromDefault(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/SandBox.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ListFaction lf = new ListFaction();

        while (scanner.hasNext()) {
            lf.add(Faction.from(scanner.nextLine()));
        }

        scanner.close();
        return lf;
    }


    public float getAverageSatisfaction() {
        int population = 0;
        int satisfaction = 0;

        for (Faction faction : this) {
            population += faction.partisant;
            satisfaction += faction.getSatisfaction()*faction.partisant;
        }
        return (float)satisfaction/population;
    }
}
