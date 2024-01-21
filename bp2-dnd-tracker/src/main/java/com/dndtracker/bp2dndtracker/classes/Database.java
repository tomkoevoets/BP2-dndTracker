package com.dndtracker.bp2dndtracker.classes;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    //    instegating class variables
    private Connection connection;


    // Create a constructor for the Database class
    public Database() {
        try {
            // Establish a connection to the MySQL database with the provided URL, username, and password
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/dnd_tracker_bp2", "root", "");
        } catch (SQLException e) {
            // If an SQL exception occurs, a RuntimeException is generated
            throw new RuntimeException(e);
        }
    }

///////////////////////////////////////////////Campaign/////////////////////////////////////////////////////////////////

//TODO Create GetAllCampaigns method

//TODO Create CreateCampaign method


//TODO Create DeleteCampaign method


//TODO Create UpdateCampaign method

///////////////////////////////////////////////Session//////////////////////////////////////////////////////////////////

//TODO Create GetAllSessions method

    public ArrayList<Session> getAllSessions() {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            Statement stm = this.connection.createStatement();
            String query = "SELECT * FROM session";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String info = rs.getString("info");
                String summary = rs.getString("summary");
                sessions.add(new Session(id, name, info, summary));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }


//TODO Create CreateSession method
    public void createSession(String name, String info, String summary) {
        try {
            Statement stm = this.connection.createStatement();
            // Check if summary is null and handle accordingly
            String summaryValue = (summary == null) ? "NULL" : "'" + summary + "'";
//            stm.execute("INSERT INTO session (name, info) VALUES (" + name + "', '" + info + ", '" + summaryValue + ")");
            stm.execute("INSERT INTO session (name, info, summary) VALUES ('" + name + "', '" + info + "', " + summaryValue + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//TODO Create DeleteSession method
    public void deleteSession(int id){
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("DELETE FROM session Where id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//TODO Create UpdateSession method

    public void updateSession(Session session){
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("UPDATE session SET name = '" + session.getName() + "', info = '" + session.getInfo() + "', summary = '" + session.getSummary() + "' WHERE id = " + session.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

///////////////////////////////////////////////Character////////////////////////////////////////////////////////////////
    //TODO Create GetAllCharacters method
        public ArrayList<CharacterSuperclass> getAllCharacters(String type) {
        ArrayList<CharacterSuperclass> characters = new ArrayList<>();
            try {
                Statement stm = this.connection.createStatement();
                String query = "SELECT * FROM character";
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String extra = rs.getString("extra");
                    int armorClass = rs.getInt("armorClass");
                    String hitPoints = rs.getString("hitPoints");
                    String strength = rs.getString("strength");
                    String dexterity = rs.getString("dexterity");
                    String constitution = rs.getString("constitution");
                    String intelligence = rs.getString("intelligence");
                    String wisdom = rs.getString("wisdom");
                    String charisma = rs.getString("charisma");
                    String speed = rs.getString("speed");
                    int challenge = rs.getInt("challenge");
                    String sense = rs.getString("sense");
                    String languages = rs.getString("languages");
                    String skills = rs.getString("skills");
                    if (type.equals("Monster")) {
                        characters.add(new Monster(id, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills));
                    } else if (type.equals("Npc")) {
                        characters.add(new Npc(id, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return characters;
        }


    //TODO Create CreateCharacter method
    public void createCharacter(String type, String name, String description, String image, String extra, int armorClass, String hitPoints,
                                String strength, String dexterity, String constitution, String intelligence, String wisdom,
                                String charisma, String speed, int challenge, String sense, String languages, String skills)
    {
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("INSERT INTO character (character_type, name, description, image, extra, armorClass, hitPoints, strength," +
                    " dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills)" +
                    " VALUES (" + type + "', '" + name + "', '" + description + "', '" + image + "', '" + extra + "', " + armorClass + "" +
                    ", '" + hitPoints + "', '" + strength + "', '" + dexterity + "', '" + constitution + "'," +
                    " '" + intelligence + "', '" + wisdom + "', '" + charisma + "', '" + speed + "', " + challenge + "," +
                    " '" + sense + "', '" + languages + "', '" + skills + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO Create DeleteCharacter method
    public void deleteCharacter(int id){
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("DELETE FROM character Where id = " +id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO Create UpdateCharacter method
    public void updateCharacter(CharacterSuperclass character){
        // create type variable
        String type = "";
        // assign type based on character class
        if (character instanceof Monster){
            type = "Monster";
        } if (character instanceof Npc){
            type = "Npc";
        }
        try {
            //  make sql statement object threw the database connection
            Statement stm = this.connection.createStatement();
            //  execute the sql statement
            stm.execute("UPDATE character SET character_type = '" + type + "', name = '" + character.getName() + "'" +
                    ", description = '" + character.getDescription() + "', image = '" + character.getImage() + "'" +
                    ", extra = '" + character.getExtra() + "', armorClass = " + character.getArmorClass() + "" +
                    ", hitPoints = '" + character.getHitPoints() + "', strength = '" + character.getStrength() + "', dex");
        } catch (SQLException e) {
            // handle exception
            throw new RuntimeException(e);
        }

    }


///////////////////////////////////////////////Monster-specific/////////////////////////////////////////////////////////



///////////////////////////////////////////////Npc-specific/////////////////////////////////////////////////////////////



///////////////////////////////////////////////Item/////////////////////////////////////////////////////////////////////

//TODO Create GetAllItems method
    public ArrayList<Item> getAllItems(){
        ArrayList<Item> items = new ArrayList<>();
        try {
            Statement stm = this.connection.createStatement();
            String query = "SELECT * FROM item";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("picture");
                String type = rs.getString("type");
                String rarity = rs.getString("rarity");
                int cost = rs.getInt("cost");
                double weight = rs.getDouble("weight");
                String extra = rs.getString("extra");
                items.add(new Item(id, name, description, image, type, rarity, cost, weight, extra));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }


//TODO Create CreateItem method
    public void createItem(String name, String description, String image, String type, String rarity, int cost, double weight, String extra){
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("INSERT INTO item (name, description, image, type, rarity, cost, weight, extra) " +
                    "VALUES (" + name + "', '" + description + "', '" + image + "', '" + type + "', '" + rarity + "'" +
                    ", " + cost + ", " + weight + ", '" + extra + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//TODO Create DeleteItem method
    public void deleteItem(int id){
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("DELETE FROM item Where id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//TODO Create UpdateItem method

    public void updateItem(Item item){
        try {
            Statement stm = this.connection.createStatement();
            stm.execute("UPDATE item SET name = '" + item.getName() + "', description = '" + item.getDescription() + "'" +
                    ", image = '" + item.getImage() + "', type = '" + item.getType() + "', rarity = '" + item.getRarity() + "'" +
                    ", cost = " + item.getCost() + ", weight = " + item.getWeight() + ", extra = '" + item.getExtra() + "' WHERE id = " + item.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


