package com.company;

import java.util.Scanner;

public class NewPartyPanel implements DisplayPanel{
    public void show(){
        Scanner scanner = new Scanner(System.in);
        Party party = new Party();
        int input;

        System.out.println("Dans quel Scenario souhaitez vous jouer ?");
        System.out.println("-0- 1er guerre mondiale");
        System.out.println("-1- Révolution française");
        input = new Reader().getInteger();
        switch (input) {
            case (0) -> party.addCircumstance(Circumstance.WORLDWAR);
            case (1) -> party.addCircumstance(Circumstance.REVOLUTION);
        }
        party.addCircumstance(Circumstance.ANYTIME);

        System.out.println("Choisissez le niveau de difficulté");
        System.out.println("-0- Facile");
        System.out.println("-1- Normal");
        System.out.println("-2- Difficile");
        input = new Reader().getInteger();
        switch (input) {
            case (0) -> party.difficultyLevel = DifficultyLevel.EASY;
            case (1) -> party.difficultyLevel = DifficultyLevel.MEDIUM;
            case (2) -> party.difficultyLevel = DifficultyLevel.HARD;
        }

        System.out.println("Combien de joueur ?");
        input = new Reader().getInteger();

        for(int i = 0; i < input; i++){
            System.out.println("Joueur " + i + ":");
            System.out.println("Entrez le nom de votre pays :");
            String name = scanner.next();
            Country country = new Country(name, party.difficultyLevel.minimalSatisfaction);
            party.addCountry(country);
        }

        if(party.listCountry.size() > 1){
            party.addCircumstance(Circumstance.MULTIPLAYER);
        }

        MainGamePanel mainGamePanel = new MainGamePanel(party);
        mainGamePanel.show();
    }
}
