package com.dndtracker.bp2dndtracker.classes;

import java.sql.*;
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
                    String challenge = rs.getString("challange");
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
            stm.execute("INSERT INTO character (character_type, name, description, picture, extra, armorClass, hitPoints, strength," +
                    " dexterity, constitution, intelligence, wisdom, charisma, speed, challenge, sense, languages, skills)" +
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
            stm.execute("DELETE FROM character WHERE id = " + id);
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a runtime exception
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
                    ", description = '" + character.getDescription() + "', image = '" + character.getImage(type) + "'" +
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


