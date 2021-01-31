package com.company;

public class MainGamePanel implements DisplayPanel{
    Party party;
    MainGamePanel(Party party){
        this.party = party;
    }
    public void show(){
        Party.Round++;
        do{
            for(Country country : party.listCountry){
                if(!country.isGameOver()) {
                    //TODO: afficher info country
                    //TODO: Choisi un Event en fonction du scenario/saison(Party.Round % 4)
                    //TODO: Affiche Event
                    //TODO: Affiche choix
                    //TODO: selectionne choix
                    //TODO: applique choix
                    if (Party.Round % 4) {
                        BilanPanel bilanPanel = new BilanPanel(country);
                        bilanPanel.show();
                    }
                }
            }
        }while(!party.isPartyOver());
    }
}
