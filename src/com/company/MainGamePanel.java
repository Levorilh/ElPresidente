package com.company;

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
                    EventPanel eventPanel = new EventPanel(event);
                    eventPanel.show();
                    System.out.println("Selectionnez votre choix :");
                    int input = scanner.nextInt();
                    Choice choice = event.choices.get(input);
                    choice.applyChange(country);
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
                System.out.println(c.name + "a duré le plus longtemps... après " + party.round + "tours");
                winner = true;
            }
        }
        if(!winner) {
            System.out.println("Egalité des derniers joueurs...\n" +
                    "après " + party.round + " tours");
        }


    }
}
