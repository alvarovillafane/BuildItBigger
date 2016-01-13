package com.alvarovm.provider;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JokeProvider {
    private ArrayList<String> jokeList;
    private int min = 0;
    private int max = 6;

    public JokeProvider(){
        jokeList = new ArrayList<String>(max);
        jokeList.add("What time did the man go to the dentist? Tooth hurt-y.");
        jokeList.add("Two peanuts were walking down the street. One was a salted.");
        jokeList.add("A ham sandwich walks into a bar and orders a beer. Bartender says, ‘Sorry we don’t serve food here.");
        jokeList.add("Why do chicken coops only have two doors? Because if they had four, they would be chicken sedans!");
        jokeList.add("Me: ‘Hey, I was thinking…’ My dad: ‘I thought I smelled something burning.");
        jokeList.add("How do you make a Kleenex dance? Put a little boogie in it!");

    }


    public String getJoke(){
        return jokeList.get(ThreadLocalRandom.current().nextInt(min, max + 1));
    }

}
