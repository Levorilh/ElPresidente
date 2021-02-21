package com.company;

public class StartPanel implements DisplayPanel {
    public void show() {
        int input;
        do {
            System.out.println("Menu Principal");
            System.out.println("- 0 - Quitter");
            System.out.println("- 1 - Jouer");
            input = new Reader().getInteger();

            if (input == 1) {
                NewPartyPanel newPartyPanel = new NewPartyPanel();
                newPartyPanel.show();
            }
        } while (input != 0);
    }
}
