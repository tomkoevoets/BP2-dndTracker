package com.dndtracker.bp2dndtracker;

import com.dndtracker.bp2dndtracker.screens.HomeScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;

public class Application extends javafx.application.Application {

    public static Stage mainStage;

    // this is an array list but with custom values instead of int
    public static HashMap<String, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {

        scenes.put("home",new HomeScreen().getScene());

        mainStage = new Stage();

        mainStage.setTitle("D&D Tracker");
        mainStage.setScene(scenes.get("home"));
        mainStage.setResizable(false);
        mainStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}

///////////////////////todo list////////////////////

// 1. TODO add folder structure to images
// 3. TODO finish update screens
// 4. TODO finish commenting all pages
// 5. TODO start encounter tracker
// 6. TODO finish encounter tracker
// 7. TODO if i have time finish styling all screens, and add fun stuff
// 8. TODO add testing jar file and execute tests
// 9. TODO figure out online database
// 10. TODO add readme file
// 11. TODO execute physical tests
// 12. TODO commit and push to masterbranch

