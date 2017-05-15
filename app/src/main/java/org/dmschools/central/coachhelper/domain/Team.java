package org.dmschools.central.coachhelper.domain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {

    /*
    A team has a name and players
    Represent these in the Team class
     */

    // make collection of players

    // make team name

    // write a no-arg constructor for Team object

    String name;

    List players = new ArrayList();
    /*
    write getter and setters for the name and collection of players
     */

    // get players
    public List getPlayers(){
        return players;
    }

    // set players
    public void setPlayers(String name){
       players.add(name);
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

}
