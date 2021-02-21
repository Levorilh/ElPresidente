package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class MainGamePanel implements DisplayPanel {
    Party party;

    MainGamePanel(Party party) {
        this.party = party;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        party.round++;
        do {
            for (Country country : party.listCountry) {
                if (!country.isGameOver()) {
                    System.out.println(country);
                    Season season = Season.values()[party.round%4];
                    RandomEventManager randomEventManager = new RandomEventManager(party.listCircumstance,season);
                    Event event = randomEventManager.getEvent();
                    event = country.deleteImpossibleChoiceInEvent(event);
                    EventPanel eventPanel = new EventPanel(event);
                    eventPanel.show();
                    if(event.choices.size() > 0) {
                        userSelect(event.choices , country);
                    } else {
                        System.out.println("Vous n'avez pas assez de ressources pour régler ce problème votre peuple perd en confiance");
                        Choice choice = new Choice("", new ArrayList<>() {{
                            add(new Faction("Capitalistes", -20, 0));
                            add(new Faction("Communistes", -20, 0));
                            add(new Faction("Libéraux", -20, 0));
                            add(new Faction("Religieux", -20, 0));
                            add(new Faction("Militaristes", -20, 0));
                            add(new Faction("Ecologistes", -20, 0));
                            add(new Faction("Nationnalistes", -20, 0));
                            add(new Faction("Loyalistes", -20, 0));
                        }},0,0,0,0);
                        choice.applyChange(country);
                    }
                    if (party.round % 4 == 0) {
                        BilanPanel bilanPanel = new BilanPanel(country);
                        bilanPanel.show();
                    }
                }
            }
            party.round++;
        } while (!party.isPartyOver());
        System.out.println("fin de la partie");

        boolean winner = false;
        for (Country c: party.listCountry) {
            if(!c.isGameOver()){
                System.out.println(c.name + " a duré le plus longtemps... après " + party.round + "tours");
                winner = true;
            }
        }
        if(!winner) {
            System.out.println("Egalité des derniers joueurs...\n" +
                    "après " + party.round + " tours");
        }

    }

    private void userSelect(ArrayList<Choice> choices , Country country) {
        int input=0;
        boolean correct = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sélectionnez votre choix :");
        while(!correct) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                correct = true;
            } catch (NumberFormatException ignored){
                System.out.println("Ce n'est pas un nombre");
            }
        }
        Choice choice = choices.get(input);
        choice.applyChange(country);
    }
}
