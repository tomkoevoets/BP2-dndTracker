package com.dndtracker.bp2dndtracker.classes;

import java.util.ArrayList;

public class Campaign {

//    instegating class variables
    private int id;
    private String name;
    private String description;
    private String image;
    private String lore;
    private ArrayList<Session> sessions;

    //    create class constructor
    public Campaign(int id, String name, String description, String image, String lore) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.lore = lore;
        this.sessions = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public void addSession(Session session){
        this.sessions.add(session);
    }

    public void removeSession(Session session){
        this.sessions.remove(session);
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }
}
