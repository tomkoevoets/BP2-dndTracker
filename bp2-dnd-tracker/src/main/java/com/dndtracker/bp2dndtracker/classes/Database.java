package com.dndtracker.bp2dndtracker.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}
