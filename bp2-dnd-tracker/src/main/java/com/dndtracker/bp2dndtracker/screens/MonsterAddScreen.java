package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MonsterAddScreen {
    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    // Constructor for MonsterAddScreen
    public MonsterAddScreen() {
        // Create instances of Database and Controller
        Database db = new Database();
        Controller cl = new Controller();

        // Create the root HBox for the scene
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);

        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sessionscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

// Pre-content section

        // Add the sidebar from components
        sidebar = new SidebarComponent();

        // StackPane to stack the content on top of the background
        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);

        // Add the background from components
        background = new BackgroundComponent();
        content.getChildren().add(background);

        // VBox for the content on top of the StackPane
        VBox contentOnStack = new VBox();
        contentOnStack.setPrefSize(1260, 750);
        contentOnStack.setAlignment(Pos.CENTER);

// Content section

        // Create a FlowPane for the main content
        FlowPane mainPane = new FlowPane(Orientation.VERTICAL);
        mainPane.setPrefSize(800, 600);
        mainPane.setMinSize(800, 600);
        mainPane.setMaxSize(800, 600);
        mainPane.setId("main-pane");

// children-section

        // add children
        mainPane.getChildren().addAll();
        content.getChildren().add(contentOnStack);
        contentOnStack.getChildren().addAll(mainPane);
        root.getChildren().addAll(sidebar, content);
    }
    public Scene getScene() {
        return scene;
    }
}
