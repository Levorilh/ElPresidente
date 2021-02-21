package com.company;

import java.util.ArrayList;

public class BilanPanel implements DisplayPanel {
    Country country;

    BilanPanel(Country country) {
        this.country = country;
    }

    public void show() {

        System.out.println("Etat de votre île avant le bilan\n" +country);
        country.endOfYearPreBilan();

        System.out.println("Etat de votre île après recolte des champs et des impôts");
        System.out.println(country);

        askForFood();

        askForBribe();

        country.endOfYearBilan();

        if(country.isGameOver()){
            System.out.println("Fin de la partie de " + country.name);
        }
    }

    private void askForFood() {
        int buyFood;
        boolean purchaseFood = true;

        while (purchaseFood) {
            System.out.println("\nPour que personne ne meurt de faim il vous faut " + country.foodNeeded() + "de nourriture");
            System.out.println("Combien de nourriture voulez-vous acheter ? (1 nourriture = " + country.foodPrice(1) + "€)");
            buyFood = new Reader().getInteger();

            if (buyFood <= 0 && isUserSure()) {
                purchaseFood = false;
            } else {
                System.out.println("Pour " + buyFood + " nourriture ça vous fera " + country.foodPrice(buyFood) + "€ à payer");
                if (isUserSure()) {
                    if (country.canBuyFood(buyFood)) {
                        country.buyFood(buyFood);
                        System.out.println("Vous avez acheté " + buyFood + " nourriture(s)");
                        purchaseFood = false;
                    } else {
                        System.out.println("Vous n'avez pas assez de trésorerie pour effectuer cet achat");
                    }
                }
            }
        }
    }

    private boolean isUserSure() {
        String answer;

        System.out.println("Etes-vous sûr de votre choix ? OUI/NON");
        answer = new Reader().getStringFromList(new ArrayList<>() {{
            add("OUI");
            add("NON");
        }});
        return answer.equalsIgnoreCase("OUI");
    }

    private void askForBribe() {
        boolean end = false;
        do {
            if (wantsToBribe()) {
                Faction selected = selectFaction();
                boolean success = country.bribe(selected);
                if (!success) {
                    System.out.println("Le pot de vin de " + selected.getBribePrice() + "€ a échoué");
                } else {
                    System.out.println("Faction " + selected.name + " soudoyée avec succès!");
                }
            } else {
                end = true;
            }
        } while (!end);
    }

    private boolean wantsToBribe() {
        System.out.println("Souhaitez-vous soudoyer une faction? OUI/NON");
        String userChoice = new Reader().getStringFromList(new ArrayList<>() {{
            add("OUI");
            add("NON");
        }});

        if (userChoice.equalsIgnoreCase("OUI")) {
            return true;
        } else if (userChoice.equalsIgnoreCase("NON") && isUserSure()) {
            return false;
        }
        return wantsToBribe();
    }

    private Faction selectFaction() {
        System.out.println(country.getListFaction().getFactionsNames());
        System.out.println("Quelle faction voulez-vous soudoyer? ");
        Faction selected;
        try {
            int userChoice = new Reader().getInteger();
            selected = country.getListFaction().get(userChoice);
        } catch (IndexOutOfBoundsException aie) {
            System.out.println("n° de faction inconnu");
            return selectFaction();
        }
        return selected;
    }
}
