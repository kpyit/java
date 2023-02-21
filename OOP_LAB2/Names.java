package oop_lab2;


import java.util.Arrays;

import java.util.List;

import java.util.Random;

abstract class Names
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