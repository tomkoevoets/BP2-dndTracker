package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ItemScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    public ItemScreen() {
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/homescreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());

//        add the sidebar from components
        sidebar = new SidebarComponent();

        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);
        content.setAlignment(Pos.CENTER);

//        add the background from components
        background = new BackgroundComponent();


        content.getChildren().addAll(background);
        root.getChildren().addAll(sidebar, content);
    }

    public Scene getScene() {
        return scene;
    }
}
