package oop_lab4;


import java.util.Arrays;

import java.util.List;

import java.util.Random;

public class Names
{ 
    static Random random = new Random();

    static List<String> namesChar = Arrays.asList(
        "Petyr Baelish",
        "Pyp",
        "Qotho",
        "Qyburn",
        "Qhorin Halfhand",
        "Renly Baratheon",
        "Rhaegar Targaryen",
        "Rickon Stark", 
        "Rodrik Cassel",
        "Samwell Tarly",
        "Sandor Clegane",
        "Sansa Stark",
        "Septa Mordane",
        "Syrio Forel",
        "Spice King",
        "Theon Greyjoy",
        "Tommen Baratheon",
        "Tyrion Lannister",
        "Tywin Lannister"
        );

    Names(){}

    public static String getName(){
        return namesChar.get(random.nextInt(0,namesChar.size()));
    }
    
}