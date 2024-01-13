package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;


public class HomeScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;
//    private final BackgroundComponent background;

//    homescreen content
    public HomeScreen() {
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());

//        add the sidebar from components
        sidebar = new SidebarComponent();

//        add the background from components
//        background = new BackgroundComponent();


        root.getChildren().addAll(sidebar);
    }

    public Scene getScene() {
        return scene;
    }
}
