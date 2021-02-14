package com.company;

import java.util.Scanner;

public class NewPartyPanel implements DisplayPanel{
    public void show(){
        Scanner scanner = new Scanner(System.in);
        Party party = new Party();
        int input = 0;
        int minimalSatisfactionAllowed = 0;

        System.out.println("Dans quelle Scenario soihaitez vous jouer ?");
        //TODO: Afficher la liste des scénarios
        input = scanner.nextInt();
        //TODO: Charge le scénario dans la party

        System.out.println("Choisissez le niveau de difficulté");
        System.out.println("-0- Facile");
        System.out.println("-1- Normal");
        System.out.println("-2- Difficile");
        input = scanner.nextInt();
        switch (input){
            case (0):
                party.difficultyLevel = DifficultyLevel.EASY;
                break;
            case (1):
                party.difficultyLevel = DifficultyLevel.MEDIUM;
                break;
            case (2):
                party.difficultyLevel = DifficultyLevel.HARD;
                break;
        }

        System.out.println("Combien de joueur ?");
        input = scanner.nextInt();

        for(int i = 0; i < input; i++){
            System.out.println("Joueur " + i + ":");
            System.out.println("Entrez le nom de votre pays :");
            String name = scanner.next();
            Country country = new Country(name, party.difficultyLevel.minimalSatisfaction);
            party.addCountry(country);
        }

        MainGamePanel mainGamePanel = new MainGamePanel(party);
        mainGamePanel.show();
    }
}
