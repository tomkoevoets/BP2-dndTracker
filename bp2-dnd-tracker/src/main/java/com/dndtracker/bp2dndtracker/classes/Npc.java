package com.dndtracker.bp2dndtracker.classes;

//  use of super class CharacterSuperclass for npc class
public class Npc extends CharacterSuperclass{

    //    create class constructor
    public Npc(int id, String name, String description, String image, String extra, int armorClass, String hitPoints,
               String strength, String dexterity, String constitution, String intelligence, String wisdom,
               String charisma, String speed, int challenge, String sense, String languages, String skills) {
        super(id, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution,
                intelligence, wisdom, charisma, speed, challenge, sense, languages, skills);
    }
}
