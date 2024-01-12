package com.dndtracker.bp2dndtracker;

import com.dndtracker.bp2dndtracker.screens.HomeScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Application extends javafx.application.Application {

    public static Stage mainStage;

    // this is an array list but with custom values instead of int
    public static HashMap<String, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {

        scenes.put("home",new HomeScreen().getScene());

        mainStage = stage;

        mainStage.setTitle("D&D Tracker");
        mainStage.setScene(scenes.get("home"));
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}