package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public int getInteger(Scanner scanner){
        do{
            try{
                int input = scanner.nextInt();
                return input;
            } catch (Exception e){
                System.out.println("Format d'entré incorrect veuillez entrer un nombre entier");
            }
        }while(true);
    }

    public String getStringFromList(Scanner scanner, ArrayList<String> avalableResponses){
        do{
            try{
                String input = scanner.nextLine();
                if(avalableResponses.contains(input))
                    return input;
                else
                    System.out.println("La réponse proposée n'est pas une proposition valable, veuillez entrer une réponse valable");
            } catch (Exception e){
                System.out.println("Format d'entré incorrect, veuillez entrer un nombre entier");
            }
        }while(true);
    }
}
