package com.dndtracker.bp2dndtracker.classes;

public class Item {

    //    instegating class variables
    private int id;
    private String name;
    private String description;
    private String image;
    private String type;
    private String rarity;
    private int cost;
    private double weight;
    private String extra;

    //    create class constructor
    public Item(int id, String name, String description, String image, String type, String rarity, int cost, double weight, String extra) {
        this.id = id;
        this.name = name;
        this.description = description;//textarea
        this.image = image;
        this.type = type;
        this.rarity = rarity;
        this.cost = cost;//spinner
        this.weight = weight;// textfield
        this.extra = extra;//area
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
        return "amulet.jpg";
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
