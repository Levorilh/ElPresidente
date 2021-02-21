package com.company;

import java.util.Scanner;

public class StartPanel implements DisplayPanel {
    public void show() {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println("Menu Principal");
            System.out.println("- 0 - Quitter");
            System.out.println("- 1 - Jouer");
            input = scanner.nextInt();

            switch (input) {
                case (1):
                    NewPartyPanel newPartyPanel = new NewPartyPanel();
                    newPartyPanel.show();
                    break;
            }
        } while (input != 0);
    }
}
