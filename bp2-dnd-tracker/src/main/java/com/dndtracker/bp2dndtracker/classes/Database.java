package com.dndtracker.bp2dndtracker.classes;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    //    instegating class variables
    private Connection connection;

    // Create a constructor for the Database class
    public Database() {
        try {
            // Update the URL, username, and password with the provided details
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://adainforma.tk:3306/bp2_dndtracker",
                    "dndtracker",
                    "4m76w7i#B"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


///////////////////////////////////////////////Campaign/////////////////////////////////////////////////////////////////

///////////////////////////////////////////////Session//////////////////////////////////////////////////////////////////

    // Method to retrieve all sessions from the 'session' table in the database
    public ArrayList<Session> getAllSessions() {
        // Create an ArrayList to store the retrieved sessions
        ArrayList<Session> sessions = new ArrayList<>();

        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // SQL query to select all columns from the 'session' table
            String query = "SELECT * FROM session";

            // Execute the SQL query and retrieve the result set
            ResultSet rs = stm.executeQuery(query);

            // Iterate through the result set to retrieve session details
            while (rs.next()) {
                // Extract session details from the result set
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String info = rs.getString("info");
                String summary = rs.getString("summary");

                // Create a new Session object with retrieved details and add it to the ArrayList
                sessions.add(new Session(id, name, info, summary));
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }

        // Return the ArrayList containing all retrieved sessions
        return sessions;
    }

    // Method to create a new session in the 'session' table of the database
    public void createSession(String name, String info, String summary) {
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // Check if summary is null and handle accordingly
            String summaryValue = (summary == null) ? "NULL" : "'" + summary + "'";

            // SQL query to insert a new session into the 'session' table with provided values
            stm.execute("INSERT INTO session (name, info, summary) VALUES ('" + name + "', '" + info + "', " + summaryValue + ")");
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

    // Method to delete a session from the 'session' table in the database by its ID
    public void deleteSession(int id){
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // SQL query to delete a session from the 'session' table by its ID
            stm.execute("DELETE FROM session Where id = " + id);
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

    // Method to update a session in the 'session' table in the database
    public void updateSession(Session session){
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // SQL query to update a session's name, info, and summary in the 'session' table by its ID
            stm.execute("UPDATE session SET name = '" + session.getName() + "', info = '" + session.getInfo() + "', summary = '" + session.getSummary() + "' WHERE id = " + session.getId());
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

///////////////////////////////////////////////Character////////////////////////////////////////////////////////////////

        // Method to retrieve all characters of a specific type from the 'character' table in the database
        public ArrayList<CharacterSuperclass> getAllCharacters(String type) {
        ArrayList<CharacterSuperclass> characters = new ArrayList<>();
            try {
                // Create a Statement object for executing SQL queries
                Statement stm = this.connection.createStatement();

                // SQL query to select all columns from the 'character' table with a specific character type
                String query = "SELECT * FROM `character` WHERE character_type='" + type + "'";

                // Execute the SQL query and obtain a ResultSet containing the result set of the query
                ResultSet rs = stm.executeQuery(query);

                // Iterate through the result set
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String image = rs.getString("picture");
                    String extra = rs.getString("extra");
                    String armorClass = rs.getString("armor_class");
                    String hitPoints = rs.getString("hit_points");
                    String strength = rs.getString("strength");
                    String dexterity = rs.getString("dexterity");
                    String constitution = rs.getString("constitution");
                    String intelligence = rs.getString("intelligence");
                    String wisdom = rs.getString("wisdom");
                    String charisma = rs.getString("charisma");
                    String speed = rs.getString("speed");
                    String challenge = rs.getString("challenge");
                    String sense = rs.getString("sense");
                    String languages = rs.getString("language");
                    String skills = rs.getString("skills");
                    // Adjust the constructor based on the actual attributes in Monster and Npc classes
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

    // Method to create a new character in the 'character' table
    public void createCharacter(String type, String name, String description, String image, String extra, String armorClass, String hitPoints,
                                String strength, String dexterity, String constitution, String intelligence, String wisdom,
                                String charisma, String speed, String challenge, String sense, String languages, String skills) {
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // SQL query to insert a new character into the 'character' table
            stm.execute("INSERT INTO `character` (character_type, name, description, picture, extra, armor_class, hit_points, strength," +
                    " dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, language, skills)" +
                    " VALUES ('" + type + "', '" + name + "', '" + description + "', '" + image + "', '" + extra + "', '" + armorClass + "'," +
                    " '" + hitPoints + "', '" + strength + "', '" + dexterity + "', '" + constitution + "'," +
                    " '" + intelligence + "', '" + wisdom + "', '" + charisma + "', '" + speed + "', '" + challenge + "'," +
                    " '" + sense + "', '" + languages + "', '" + skills + "')");
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

    // Method to delete a character from the 'character' table by its ID
    public void deleteCharacter(int id){
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // SQL query to delete a character from the 'character' table by its ID
            stm.execute("DELETE FROM `character` WHERE id = " + id);
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

    // UpdateCharacter method
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
            // SQL query to update item information in the database
            String sqlQuery = "UPDATE `character` SET character_type=?, name=?, description=?, extra=?, armor_class=?, hit_points=?, speed=?," +
                    " strength=?, dexterity=?, constitution=?, intelligence=?, wisdom=?, charisma=?, sense=?, language=?, " +
                    "challenge=?, skills=? WHERE id=?";

            try (PreparedStatement pstmt = this.connection.prepareStatement(sqlQuery)) {
                // Set values for the prepared statement based on the provided item object
                pstmt.setString(1, type); // update type
                pstmt.setString(2, character.getName()); // Update name
                pstmt.setString(3, character.getDescription()); // Update description
                pstmt.setString(4, character.getExtra()); // Update extra
                pstmt.setString(5, character.getArmorClass()); // Update armor class
                pstmt.setString(6, character.getHitPoints()); // Update hit points
                pstmt.setString(7, character.getSpeed()); // Update speed
                pstmt.setString(8, character.getStrength()); // Update strength
                pstmt.setString(9, character.getDexterity()); // Update dexterity
                pstmt.setString(10, character.getConstitution()); // Update constitution
                pstmt.setString(11, character.getIntelligence()); // Update intelligence
                pstmt.setString(12, character.getWisdom()); // Update wisdom
                pstmt.setString(13, character.getCharisma()); // Update charisma
                pstmt.setString(14, character.getSense()); // Update sense
                pstmt.setString(15, character.getLanguages()); // Update languages
                pstmt.setString(16, character.getChallenge()); // Update challenge
                pstmt.setString(17, character.getSkills()); // Update skills
                pstmt.setInt(18, character.getId()); // Identify the character by its unique ID

                // Execute the update query
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            // handle exception
            throw new RuntimeException(e);
        }
    }

    // add character to session method
    public void linkCharacterToSession(int sessionId, int characterId) {
        try {
            // SQL query to add a new association between character and session
            String sqlQuery = "INSERT INTO session_character (session_id, character_id) VALUES (?, ?)";

            try (PreparedStatement pstmt = this.connection.prepareStatement(sqlQuery)) {
                // Set the values for the prepared statement on the basis of the given id's
                pstmt.setInt(1, sessionId);
                pstmt.setInt(2, characterId);

                // Execute the query
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            // Handle exception
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CharacterSuperclass> getCharacterBySessionId(int sessionId) {
        // Create an ArrayList to store the retrieved characters
        ArrayList<CharacterSuperclass> characters = new ArrayList<>();

        // SQL query to retrieve characters linked to a specific session
        String sqlQuery = "SELECT c.* FROM `character` c " +
                "JOIN session_character sc ON c.id = sc.character_id " +
                "WHERE sc.session_id = ?";
        try (PreparedStatement ptsmt = this.connection.prepareStatement(sqlQuery)) {
            // Set the session_id parameter in the query
            ptsmt.setInt(1, sessionId);

            // Execute the query and get the result set
            try (ResultSet rs = ptsmt.executeQuery()) {
                // Iterate through the result set and populate the characters ArrayList
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String image = rs.getString("picture");
                    String extra = rs.getString("extra");
                    String armorClass = rs.getString("armor_class");
                    String hitPoints = rs.getString("hit_points");
                    String strength = rs.getString("strength");
                    String dexterity = rs.getString("dexterity");
                    String constitution = rs.getString("constitution");
                    String intelligence = rs.getString("intelligence");
                    String wisdom = rs.getString("wisdom");
                    String charisma = rs.getString("charisma");
                    String speed = rs.getString("speed");
                    String challenge = rs.getString("challenge");
                    String sense = rs.getString("sense");
                    String languages = rs.getString("language");
                    String skills = rs.getString("skills");
                    String type = rs.getString("character_type");
                    // Adjust the constructor based on the actual attributes in Monster and Npc classes
                    if (type.equals("Monster")) {
                        characters.add(new Monster(id, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills));
                    } else if (type.equals("Npc")) {
                        characters.add(new Npc(id, name, description, image, extra, armorClass, hitPoints, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills));
                    }
                }
            }
        } catch (SQLException e) {
            // Handle exception
            throw new RuntimeException(e);
        }
        return characters;
    }


///////////////////////////////////////////////Monster-specific/////////////////////////////////////////////////////////

///////////////////////////////////////////////Npc-specific/////////////////////////////////////////////////////////////

///////////////////////////////////////////////Item/////////////////////////////////////////////////////////////////////

// Method to retrieve all items from the 'item' table in the database
public ArrayList<Item> getAllItems() {
    // Create an ArrayList to store the retrieved items
    ArrayList<Item> items = new ArrayList<>();

    try {
        // Create a Statement object for executing SQL queries
        Statement stm = this.connection.createStatement();

        // SQL query to select all columns from the 'item' table
        String query = "SELECT * FROM item";

        // Execute the SQL query and obtain a ResultSet containing the result set of the query
        ResultSet rs = stm.executeQuery(query);

        // Iterate through the result set
        while (rs.next()) {
            // Retrieve attributes of each item from the result set
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String image = rs.getString("picture"); // Assuming there's an 'picture' column in the table
            String type = rs.getString("type");
            String rarity = rs.getString("rarity");
            int cost = rs.getInt("cost");
            double weight = rs.getDouble("weight");
            String extra = rs.getString("extra");

            // Create a new Item object with retrieved attributes and add it to the ArrayList
            items.add(new Item(id, name, description, image, type, rarity, cost, weight, extra));
        }
    } catch (SQLException e) {
        // Handle any SQL exceptions by throwing a runtime exception
        throw new RuntimeException(e);
    }
    // Return the ArrayList containing all retrieved items
    return items;
}


    // Method to create a new item in the database with the specified attributes
    public void createItem(String name, String description, String type, String rarity, int cost, double weight, String extra) {
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // Execute the SQL query to insert a new item into the 'item' table
            stm.execute("INSERT INTO item (name, description, type, rarity, cost, weight, extra) " +
                    "VALUES ('" + name + "', '" + description + "', '" + type + "', '" + rarity + "'" +
                    ", " + cost + ", " + weight + ", '" + extra + "')");
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

    // Method to delete an item from the database by its unique ID
    public void deleteItem(int id) {
        try {
            // Create a Statement object for executing SQL queries
            Statement stm = this.connection.createStatement();

            // Execute the SQL query to delete the item with the specified ID from the 'item' table
            stm.execute("DELETE FROM item WHERE id = " + id);
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

    // Method to update an item in the database
    public void updateItem(Item item) {
        try {
            // SQL query to update item information in the database
            String sqlQuery = "UPDATE item SET name=?, description=?, type=?, rarity=?, cost=?, weight=?, extra=? WHERE id=?";

            try (PreparedStatement pstmt = this.connection.prepareStatement(sqlQuery)) {
                // Set values for the prepared statement based on the provided item object
                pstmt.setString(1, item.getName()); // Update item name
                pstmt.setString(2, item.getDescription().replace("`", "'")); // Update item description
                // pstmt.setString(3, (item.getImage() == null) ? null : item.getImage()); // Uncomment and modify for image if needed
                pstmt.setString(3, item.getType()); // Update item type
                pstmt.setString(4, item.getRarity()); // Update item rarity
                pstmt.setInt(5, item.getCost()); // Update item cost
                pstmt.setDouble(6, item.getWeight()); // Update item weight
                pstmt.setString(7, item.getExtra().replace("`", "'")); // Update item extra information
                pstmt.setInt(8, item.getId()); // Identify the item by its unique ID

                // Execute the update query
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }

}


