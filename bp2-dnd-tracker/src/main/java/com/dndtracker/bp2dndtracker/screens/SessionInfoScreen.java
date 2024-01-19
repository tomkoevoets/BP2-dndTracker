package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SessionInfoScreen {
    private final Scene scene;
    private double screenWidth;
    private double screenHeight;

    public SessionInfoScreen(Session session) {
        Database db = new Database();
        Controller cl = new Controller();
        Stage stage = new Stage();

        VBox root = new VBox();
        scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sessionscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

        screenWidth = root.getWidth();
        screenHeight = root.getHeight();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(session.getName());
        stage.show();

/////// background
        // Load the image
        Image backgroundImage = new Image(Application.class.getResource("images/session-info-background.jpg").toString());

        // Create a BackgroundImage with the image, set repeat properties
        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        // Create a Background with the BackgroundImage
        Background background = new Background(backgroundImg);

        // Set the background to the root Pane
        root.setBackground(background);

/////// content
/////// content title
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);

        ImageView titleIcon = new ImageView();
        titleIcon.setFitHeight(16);
        titleIcon.setPreserveRatio(true);
        titleIcon.setSmooth(true);
        titleIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/crossed-swords-black.png"))));

        Text title = new Text(session.getName());
        title.setId("sessionScreen-title");

        FlowPane titlePane = new FlowPane();
        titlePane.setPrefSize(screenWidth, screenHeight - 550);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPadding(new Insets(20,0,0,0));

        titleBox.getChildren().addAll(title, titleIcon);
        titlePane.getChildren().add(titleBox);

        //TODO make method instead of 2 seperate panes
/////// content info
        HBox bottomScreen = new HBox();
        bottomScreen.setPrefSize(screenWidth, screenHeight-100);

        FlowPane infoPane = new FlowPane();
        infoPane.setPrefSize(screenWidth/2, screenHeight - 100);
        infoPane.setAlignment(Pos.CENTER);

        VBox infoContentPane = new VBox();
        infoContentPane.setPrefSize(screenWidth/2 - 100,screenHeight - 200);
        infoContentPane.setAlignment(Pos.CENTER);
        infoContentPane.setId("session-info-content-pane");

        Text info = new Text(session.getInfo());
        info.setWrappingWidth(infoContentPane.getPrefWidth()-50);
        info.setId("session-info");

        Label infoTitle = new Label("Session-Info");
        infoTitle.setPadding(new Insets(0,0,10,0));
        infoTitle.setId("session-info-title");

        FlowPane infoTitlePane = new FlowPane();
        infoTitlePane.setPrefSize(infoContentPane.getPrefWidth(), 50);
        infoTitlePane.setAlignment(Pos.CENTER);

        ScrollPane infoTXT = new ScrollPane();
        infoTXT.setMaxSize(infoContentPane.getPrefWidth()-20, infoContentPane.getPrefHeight()-70);
        infoTXT.setMinSize(infoContentPane.getPrefWidth()-20, infoContentPane.getPrefHeight()-70);
        infoTXT.setPadding(new Insets(5,5,5,5));
        infoTXT.setId("scrollPane");

        infoTitlePane.getChildren().add(infoTitle);
        infoTXT.setContent(info);
        infoContentPane.getChildren().addAll(infoTitlePane, infoTXT);
        infoPane.getChildren().add(infoContentPane);

/////// content summary
        FlowPane summaryPane = new FlowPane();
        summaryPane.setPrefSize(screenWidth/2, screenHeight - 100);
        summaryPane.setAlignment(Pos.CENTER);

        VBox summaryContentPane = new VBox();
        summaryContentPane.setPrefSize(screenWidth/2 - 100,screenHeight - 200);
        summaryContentPane.setAlignment(Pos.CENTER);
        summaryContentPane.setId("session-summary-content-pane");

        Text summary = new Text(session.getSummary());
        summary.setWrappingWidth(infoContentPane.getPrefWidth()-50);
        summary.setId("session-summary");

        Label summaryTitle = new Label("Session-Summary");
        summaryTitle.setPadding(new Insets(0,0,10,0));
        summaryTitle.setId("session-summary-title");

        FlowPane summaryTitlePane = new FlowPane();
        summaryTitlePane.setPrefSize(infoContentPane.getPrefWidth(), 50);
        summaryTitlePane.setAlignment(Pos.CENTER);

        ScrollPane summaryTXT = new ScrollPane();
        summaryTXT.setMaxSize(infoContentPane.getPrefWidth()-20, infoContentPane.getPrefHeight()-70);
        summaryTXT.setMinSize(infoContentPane.getPrefWidth()-20, infoContentPane.getPrefHeight()-70);
        summaryTXT.setPadding(new Insets(5,5,5,5));
        summaryTXT.setId("scrollPane");

        summaryTitlePane.getChildren().add(summaryTitle);
        summaryTXT.setContent(summary);
        summaryContentPane.getChildren().addAll(summaryTitlePane, summaryTXT);
        summaryPane.getChildren().add(summaryContentPane);


// delete section
        HBox deleteBox = new HBox();
        deleteBox.setAlignment(Pos.CENTER);
        deleteBox.setPadding(new Insets(10,30,0,0));

        Pane deleteBtn = new Pane();
        deleteBtn.setPrefSize(20, deleteBox.getPrefHeight());
        deleteBtn.setCursor(Cursor.HAND);//TODO fix placement of the buttons

        ImageView deleteIcon = new ImageView();
        deleteIcon.setFitHeight(deleteBtn.getPrefWidth());
        deleteIcon.setPreserveRatio(true);
        deleteIcon.setSmooth(true);
        deleteIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/delete-btn.png"))));
        deleteIcon.setId("delete-icon");

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
//            TODO go to add session page
                });
        updateBtn.setId("update-btn");

        // event listener for delete icon functionality
        deleteBtn.setOnMouseClicked(e -> {
            cl.deleteSession(session.getId());
            stage.close();//TODO add warning
            //TODO reload sessionscreen
        });

        deleteBtn.getChildren().add(deleteIcon);
        deleteBox.getChildren().addAll(updateBtn, deleteBtn);

/////// add children
        bottomScreen.getChildren().addAll(infoPane, summaryPane);
        root.getChildren().addAll(titlePane, bottomScreen, deleteBox);

    }
}
