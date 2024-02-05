package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

import static com.dndtracker.bp2dndtracker.Application.mainStage;


public class SessionInfoScreen {
    // Declare instance variables
    private final Scene scene;
    private double screenWidth;
    private double screenHeight;

    // Constructor for SessionInfoScreen with a Session parameter
    public SessionInfoScreen(Session session) {
        // Create instances of Database and Controller and stage
        Database db = new Database();
        Controller cl = new Controller();
        Stage stage = new Stage();

        // Create the root VBox for the scene
        VBox root = new VBox();
        scene = new Scene(root, 800, 600);
        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sessionscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

        // instegate the width and height of the root as a variable
        screenWidth = root.getWidth();
        screenHeight = root.getHeight();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(session.getName());
        stage.show();

// pre-content area

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

// content area

    // title section

        // content title
        FlowPane titleBox = new FlowPane();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setHgap(10);

        // import icon for title
        ImageView titleIcon = new ImageView();
        titleIcon.setFitHeight(16);
        titleIcon.setPreserveRatio(true);
        titleIcon.setSmooth(true);
        titleIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/icon/crossed-swords-black.png"))));

        // display the session title on screen
        Text title = new Text(session.getName());
        title.setId("sessionScreen-title");

        VBox titlePane = new VBox();
        titlePane.setPrefSize(screenWidth, screenHeight - 575);
        titlePane.setPadding(new Insets(10,0,0,0));
        titlePane.setAlignment(Pos.CENTER);

    // delete section

        // pane for delete content
        FlowPane deletePane = new FlowPane();
        deletePane.setPrefSize(screenWidth, screenHeight - 575);
        deletePane.setAlignment(Pos.CENTER_RIGHT);
        deletePane.setPadding(new Insets(0,10,0,0));

        // button background to make bressing the delete icon easy
        FlowPane deleteBtn = new FlowPane();
        deleteBtn.setPrefSize(30, 30);
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setAlignment(Pos.CENTER);
        deleteBtn.setId("delete-btn");

        // import delete icon
        ImageView deleteIcon = new ImageView();
        deleteIcon.setFitHeight(20);
        deleteIcon.setPreserveRatio(true);
        deleteIcon.setSmooth(true);
        deleteIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/delete-btn.png"))));
        deleteIcon.setId("delete-icon");

        // Delete session button clicked
        deleteBtn.setOnMouseClicked(e -> {
            // create alert when clicked
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Session");
            alert.setHeaderText("Are you sure you want to delete this session?");
            alert.setContentText("This action cannot be undone!");
            // instagate result variable
            Optional<ButtonType> result = alert.showAndWait();
            // check if the result is OK
            if (result.get() == ButtonType.OK) {
                // Delete session
                cl.deleteSession(session.getId());
                // Close stage
                stage.close();
                // Create new instance of SessionScreen and pass in the deleted session
                SessionScreen sessionscreen = new SessionScreen();
                mainStage.setScene(sessionscreen.getScene());
            }
            // check if the result is CANCEL
            else if (result.get() == ButtonType.CANCEL) {
                // do nothing
                return;
            }
        });

        // add delete icon to delete button
        deletePane.getChildren().add(deleteBtn);
        titleBox.getChildren().addAll(title, titleIcon);
        titlePane.getChildren().addAll(deletePane, titleBox);

    // content info section

        HBox bottomScreen = new HBox();
        bottomScreen.setPrefSize(screenWidth, screenHeight-100);

        FlowPane infoPane = new FlowPane();
        infoPane.setPrefSize(screenWidth/2, screenHeight - 100);
        infoPane.setAlignment(Pos.CENTER);

        VBox infoContentPane = new VBox();
        infoContentPane.setPrefSize(screenWidth/2 - 100,screenHeight - 200);
        infoContentPane.setAlignment(Pos.CENTER);
        infoContentPane.setId("session-info-content-pane");

        Text info = new Text(session.getInfo().replace("`", "'"));
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

        // children
        infoTitlePane.getChildren().add(infoTitle);
        infoTXT.setContent(info);
        infoContentPane.getChildren().addAll(infoTitlePane, infoTXT);
        infoPane.getChildren().add(infoContentPane);

    // content summary section

        FlowPane summaryPane = new FlowPane();
        summaryPane.setPrefSize(screenWidth/2, screenHeight - 100);
        summaryPane.setAlignment(Pos.CENTER);

        VBox summaryContentPane = new VBox();
        summaryContentPane.setPrefSize(screenWidth/2 - 100,screenHeight - 200);
        summaryContentPane.setAlignment(Pos.CENTER);
        summaryContentPane.setId("session-summary-content-pane");

        Text summary = new Text(session.getSummary().replace("`", "'"));
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

        // children
        summaryTitlePane.getChildren().add(summaryTitle);
        summaryTXT.setContent(summary);
        summaryContentPane.getChildren().addAll(summaryTitlePane, summaryTXT);
        summaryPane.getChildren().add(summaryContentPane);

    // update section

        HBox updateBox = new HBox();//////update?
        updateBox.setAlignment(Pos.CENTER);
        updateBox.setPadding(new Insets(0,0,0,0));

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            // navigate to updatescreen when button is pressed
            SessionUpdateScreen sessionscreen = new SessionUpdateScreen(session);
            mainStage.setScene(sessionscreen.getScene());
            stage.close();
                });
        updateBtn.setId("update-btn");

        deleteBtn.getChildren().add(deleteIcon);
        updateBox.getChildren().addAll(updateBtn);

        // add children
        bottomScreen.getChildren().addAll(infoPane, summaryPane);
        root.getChildren().addAll(titlePane, bottomScreen, updateBox);
    }
    public Scene getScene() {
        return scene;
    }
}
