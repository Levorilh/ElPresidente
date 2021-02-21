package com.company;

public class EventPanel implements DisplayPanel {
    Event event;

    public EventPanel(Event event)
    {
        this.event = event;
    }

    @Override
    public void show() {
        System.out.println(String.format("%s",event.description));
        for(int i = 0; i<event.choices.size(); i++){
                System.out.println(String.format("%d - %s", i, event.choices.get(i).description));
        }
    }
}
