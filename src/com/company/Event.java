package com.company;

import java.util.ArrayList;

public class Event {

    ArrayList<Circumstance> circumstance;
    Season season;
    String description;
    ArrayList<Choice> choices;

    public Event(ArrayList<Circumstance> circumstance, Season season, String description,ArrayList<Choice> choices){
        this.circumstance=circumstance;
        this.season=season;
        this.description=description;
        this.choices=choices;
    }

}
