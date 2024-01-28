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
    public void createCharacter(String type, String name, String description, String extra, String armorClass, String hitPoints,
                                String strength, String dexterity, String constitution, String intelligence, String wisdom,
                                String charisma, String speed, String challenge, String sense, String languages, String skills) {
        // assign image based on type
        String image = "";
        if(type.equals("Monster")){
             image ="monster.jpg";
        } else if (type.equals("Npc")) {
             image =  "Npc.jpg";
        }
        db.createCharacter(type, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills);
    }



    // DeleteCharacter method
    public void deleteCharacter(int id) {
        db.deleteCharacter(id);
    }


    // UpdateCharacter method
    public void updateCharacter(CharacterSuperclass character) {
        db.updateCharacter(character);
    }

    // LinkCharacterToSession method
    public void linkCharacterToSession(int sessionId, int characterId) {
        db.linkCharacterToSession(sessionId, characterId);
    }

    // GetCharacterBySessionId method
    public ArrayList<CharacterSuperclass> getCharacterBySessionId(int sessionId) {
        return db.getCharacterBySessionId(sessionId);
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
    public void createItem(String name, String description, String type, String rarity, int cost, double weight, String extra) {
        db.createItem(name, description, type, rarity, cost, weight, extra);
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

