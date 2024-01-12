package com.dndtracker.bp2dndtracker.classes;

import java.util.ArrayList;

public class Session {

    //    instegating class variables
    private int id;
    private String name;
    private String info;
    private String summary;
    private ArrayList<CharacterSuperclass> characterList;
    private ArrayList<Item> itemList;

    //    create class constructor
    public Session(int id, String name, String info, String summary) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.summary = summary;
        this.characterList = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }

    //    create get and set methods for class variables
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<CharacterSuperclass> getCharacterList() {
        return characterList;
    }

    public void addCharacter(CharacterSuperclass character){
        this.characterList.add(character);
    }

    public void removeCharacter(CharacterSuperclass character){
        this.characterList.remove(character);
    }

    public void setCharacterList(ArrayList<CharacterSuperclass> characterList) {
        this.characterList = characterList;
    }

    public void addItemList(Item item){
        this.itemList.add(item);
    }

    public void removeItemList(Item item){
        this.itemList.remove(item);
    }
}
