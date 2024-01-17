package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class SessionScreen  {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    public SessionScreen() {
        Database db = new Database();

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

        content.getChildren().add(background);

        for (Session session : db.getAllSessions()) {
            content.getChildren().add(generateSessionItem(session));
        }

        root.getChildren().addAll(sidebar, content);
    }

    public HBox generateSessionItem(Session session) {
        //TODO make session items appear structured
        //TODO create black background with gold border?
        HBox sessionItem = new HBox();
        sessionItem.setAlignment(Pos.CENTER);
        sessionItem.setMinSize(130, 232);
//        sessionItem.setMaxSize(130, 232);
//        sessionItem.setSpacing(50);
        sessionItem.setId("film-item");
        sessionItem.setStyle("-fx-background-color: green");

        FlowPane sessionPicture = new FlowPane(Orientation.VERTICAL);
        sessionPicture.setPrefSize(120, 175);
        sessionPicture.setMinSize(120, 175);
        sessionPicture.setMaxSize(120, 175);
        sessionPicture.setId("session-picture");
        sessionPicture.setAlignment(Pos.CENTER);
        sessionPicture.setStyle("-fx-background-color: blue");
//        sessionPicture.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px;");

        // rectangle to clip the picture to the size of the session item and give it a radius
        //TODO get clipreact to be centered over the image
        Rectangle clipRect = new Rectangle(120, 175);
        clipRect.setArcWidth(10);
        clipRect.setArcHeight(10);
        sessionPicture.setClip(clipRect);

        ImageView sessionPictureImage = new ImageView();
//        sessionPictureImage.setPreserveRatio(true);
        sessionPictureImage.setSmooth(true);
        sessionPictureImage.setFitWidth(120);
        sessionPictureImage.setFitHeight(175);
        sessionPictureImage.setImage(new Image(Application.class.getResource("images/session-item-alt-pic.jpg").toString()));
        sessionPictureImage.setId("session-picture-image");

        sessionPictureImage.setStyle(
                        "-fx-background-size: cover;" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 20, 0, 0.0, 0.0);" +
                        "-fx-background-radius: 10px;"
        );

        //TODO create white text for the session title
        Label sessionTitle = new Label(session.getName());
        sessionTitle.setWrapText(true);
//        sessionTitle.setAlignment(Pos.TOP_LEFT);
        sessionTitle.setId("session-title");

        sessionPicture.getChildren().add(sessionPictureImage);
        sessionItem.getChildren().addAll(sessionPicture, sessionTitle);


        return sessionItem;
    }

    public Scene getScene() {
        return scene;
    }
}
