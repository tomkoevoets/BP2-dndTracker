package com.dndtracker.bp2dndtracker.classes;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventType;

public class Controller {

    private Database db;

    public Controller() {
        this.db = new Database();
    }
///////////////////////////////////////////////Campaign/////////////////////////////////////////////////////////////////
    //TODO Create GetAllCampaigns method

    //TODO Create CreateCampaign method


    //TODO Create DeleteCampaign method


    //TODO Create UpdateCampaign method


///////////////////////////////////////////////Session//////////////////////////////////////////////////////////////////
    //TODO Create GetAllsessions method
    public ArrayList<Session> getSessions() {
        return db.getAllSessions();
    }

    //TODO Create Createsession method
    public void createSession(String name, String info, String summary) {
        db.createSession(name, info, summary);
    }

    //TODO Create Deletesession method
    public void deleteSession(int id) {
        db.deleteSession(id);
    }

    //TODO Create Updatesession method
    public void updateSession(Session session) {
        db.updateSession(session);
    }


///////////////////////////////////////////////Character////////////////////////////////////////////////////////////////
    //TODO Create GetAllCharacter method


    //TODO Create CreateCharacter method
    public void createCharacter(String type, String name, String description, String image, String extra, int armorClass, String hitPoints,
                                String strength, String dexterity, String constitution, String intelligence, String wisdom,
                                String charisma, String speed, int challenge, String sense, String languages, String skills) {
        db.createCharacter(type, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills);
    }



    //TODO Create DeleteCharacter method
    public void deleteCharacter(int id) {
        db.deleteCharacter(id);
    }


    //TODO Create UpdateCharacter method
    public void updateCharacter(CharacterSuperclass character) {
        db.updateCharacter(character);
    }


///////////////////////////////////////////////Monster-specific/////////////////////////////////////////////////////////
    //TODO Create GetAllMonsters method
    public ArrayList<CharacterSuperclass> getMonsters() {
        return db.getAllCharacters("Monster");
    }


///////////////////////////////////////////////Npc-specific/////////////////////////////////////////////////////////////
    //TODO Create GetAllNpc's method
    public ArrayList<CharacterSuperclass> getNpcs() {
        return db.getAllCharacters("Npc");
    }


///////////////////////////////////////////////Item/////////////////////////////////////////////////////////////////////
    //TODO Create GetAllItems method
    public ArrayList<Item> getItems() {
        return db.getAllItems();
    }

    //TODO Create CreateItem method
    public void createItem(String name, String description, String image, String type, String rarity, int cost, double weight, String extra) {
        db.createItem(name, description, image, type, rarity, cost, weight, extra);
    }


    //TODO Create DeleteItem method
    public void deleteItem(int id) {
        db.deleteItem(id);
    }


    //TODO Create UpdateItem method
    public void updateItem(Item item) {
        db.updateItem(item);
    }

}

