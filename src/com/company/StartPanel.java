package com.company;

import java.util.Scanner;

public class StartPanel implements DisplayPanel {
    public void show() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        do {
            System.out.println("Menu Principal");
            System.out.println("- 0 - Quitter");
            System.out.println("- 1 - Jouer");
            input = new Reader().getInteger(scanner);

            if (input == 1) {
                NewPartyPanel newPartyPanel = new NewPartyPanel();
                newPartyPanel.show();
            }
        } while (input != 0);
    }
}
