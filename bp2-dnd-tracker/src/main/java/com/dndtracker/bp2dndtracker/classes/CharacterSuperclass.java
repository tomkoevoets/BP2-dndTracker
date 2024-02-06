package com.dndtracker.bp2dndtracker.classes;

public class CharacterSuperclass {

//    instegating class variables
    private int id;
    private String name;
    private String description;
    private String image;
    private String extra;
    private String armorClass;
    private String hitPoints;
    private String strength;
    private String dexterity;
    private String constitution;
    private String intelligence;
    private String wisdom;
    private String charisma;
    private String speed;
    private String challenge;
    private String sense;
    private String languages;
    private String skills;

    //    create class constructor
    public CharacterSuperclass(int id, String name, String description, String image, String extra, String armorClass, String hitPoints, String strength, String dexterity, String constitution, String intelligence, String wisdom, String charisma, String speed, String challenge, String sense, String languages, String skills) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.extra = extra;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.speed = speed;
        this.challenge = challenge;
        this.sense = sense;
        this.languages = languages;
        this.skills = skills;
    }

    //    create get and set methods for class variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage(String type) {
        if(type.equals("Monster")){
            return "monster.jpg";
        } else if (type.equals("Npc")) {
            return "Npc.jpg";//////////////////////////////////
        }
        return "";
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(String armorClass) {
        this.armorClass = armorClass;
    }

    public String getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(String hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDexterity() {
        return dexterity;
    }

    public void setDexterity(String dexterity) {
        this.dexterity = dexterity;
    }

    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getWisdom() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        this.wisdom = wisdom;
    }

    public String getCharisma() {
        return charisma;
    }

    public void setCharisma(String charisma) {
        this.charisma = charisma;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
