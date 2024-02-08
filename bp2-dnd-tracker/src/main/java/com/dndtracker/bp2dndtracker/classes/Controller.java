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
    // GetAllsessions method
    public ArrayList<Session> getSessions() {
        return db.getAllSessions();
    }

    // Createsession method
    public void createSession(String name, String info, String summary) {
        db.createSession(name, info, summary);
    }

    // Deletesession method
    public void deleteSession(int id) {
        db.deleteSession(id);
    }

    // Updatesession method
    public void updateSession(Session session) {
        db.updateSession(session);
    }


///////////////////////////////////////////////Character////////////////////////////////////////////////////////////////


    // CreateCharacter method
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
    // using a merging table
    public void linkCharacterToSession(int sessionId, int characterId) {
        db.linkCharacterToSession(sessionId, characterId);
    }

    // GetCharacterBySessionId method
    public ArrayList<CharacterSuperclass> getCharacterBySessionId(int sessionId) {
        return db.getCharacterBySessionId(sessionId);
    }


///////////////////////////////////////////////Monster-specific/////////////////////////////////////////////////////////
    //GetAllMonsters method
    // return only the characters with Monster type
    public ArrayList<CharacterSuperclass> getMonsters() {
        return db.getAllCharacters("Monster");
    }


///////////////////////////////////////////////Npc-specific/////////////////////////////////////////////////////////////
    //GetAllNpc's method
// return only the characters with Npc type
    public ArrayList<CharacterSuperclass> getNpcs() {
        return db.getAllCharacters("Npc");
    }


///////////////////////////////////////////////Item/////////////////////////////////////////////////////////////////////
    //GetAllItems method
    public ArrayList<Item> getItems() {
        return db.getAllItems();
    }

    //CreateItem method
    public void createItem(String name, String description, String type, String rarity, int cost, double weight, String extra) {
        db.createItem(name, description, type, rarity, cost, weight, extra);
    }


    //DeleteItem method
    public void deleteItem(int id) {
        db.deleteItem(id);
    }


    //UpdateItem method
    public void updateItem(Item item) {
        db.updateItem(item);
    }

}

