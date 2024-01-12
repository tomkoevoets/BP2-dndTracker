package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class HomeScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;

//    homescreen content
    public HomeScreen() {
        Pane root = new Pane();
        scene = new Scene(root, 1400, 750);

//        add the sidebar from components
        sidebar = new SidebarComponent();

        root.getChildren().add(sidebar);
    }



    public Scene getScene() {
        return scene;
    }
}
