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
                    Event event = null; //TODO: Choisi un Event en fonction du scenario/saison(Party.Round % 4)
                    EventPanel eventPanel = new EventPanel(event);
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
    }
}
