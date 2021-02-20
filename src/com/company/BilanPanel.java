package com.company;

import java.util.Scanner;

public class BilanPanel implements DisplayPanel {
    Country country;
    BilanPanel(Country country) {
        this.country = country;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        int buyFood = 0;
        String anwser = "";
        boolean purchaseFood = false;
        boolean bribe = false;
        //TODO: afficher info country
        System.out.println(country);

        //TODO: afficher food needed
        System.out.println("Pour que personne ne meurt de faim il vous faut " + country.foodNeeded());

        //TODO: boucle do while
        // demander combien il veux acheter
        // si possible demander confirmation en précisant le prix
        // si confirmé après achat sortir de la boucle
        System.out.println("Combien de nourriture voulez-vous acheter ? (1 nourriture = " + country.foodPrice(1) + ")");
        buyFood = scanner.nextInt();
        while (purchaseFood) {
            if (buyFood == 0) {
                System.out.println("Etes-vous sûr de ne pas vouloir en acheter ?");
                anwser = scanner.next();
                if (anwser.equals("Oui") || anwser.equals("oui")) {
                    purchaseFood = true;
                }
            } else {
                System.out.println("Pour " + buyFood + " nourriture ça vous fera " + country.foodPrice(buyFood) + "€ à payer");
                System.out.println("Etes-vous toujours d'accord ?");
                anwser = scanner.next();
                if (anwser.equals("Oui") || anwser.equals("oui")) {
                    if (country.canBuyFood(buyFood)) {
                        country.buyFood(buyFood);
                        System.out.println("Vous avez acheté" + buyFood + "nourriture(s)");
                        purchaseFood = true;
                    }
                }
            }
        }

        //TODO:  boucle do while
        // afficher un menu de pot de vin avec chaque factions et option quitter
        // selectionner une option
        // demander combien de % acheter à la faction
        // si possible demander confirmation en précisant le prix

        //TODO: exécuter le bilan
        //TODO: vérifier isGameOver()
    }
}
