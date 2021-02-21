package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private Scanner sc;


    public Reader(){
        sc = new Scanner(System.in);
    }

    public int getInteger(){
        do{
            try{
                int input = sc.nextInt();
                return input;
            } catch (Exception e){
                System.out.println("Format d'entré incorrect veuillez entrer un nombre entier");
                sc.nextLine();
            }
        }while(true);
    }

    public String getStringFromList(ArrayList<String> availableResponses){
        do{
            try{
                String input = sc.nextLine();
                if(availableResponses.contains(input))
                    return input;
                else
                    System.out.println("La réponse proposée n'est pas une proposition valable, veuillez entrer une réponse valable");
            } catch (Exception e){
                System.out.println("Format d'entré incorrect, veuillez entrer un nombre entier");
            }
        }while(true);
    }
}
