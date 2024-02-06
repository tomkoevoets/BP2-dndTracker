package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

// Define the SessionScreen class
public class SessionScreen  {
    // Declare instance variables
    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    // Constructor for SessionScreen
    public SessionScreen() {
        // Create a new Database instance
        Database db = new Database();

        // Create the root HBox for the scene
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sessionscreen.css").toString());

// pre-content area

        // Create SidebarComponent and add it to the root HBox
        sidebar = new SidebarComponent();

        // Create stackpane for stacking things on the background
        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);
        content.setAlignment(Pos.TOP_CENTER);

        // Create BackgroundComponent and add it to the content StackPane
        background = new BackgroundComponent();

        content.getChildren().add(background);

// content area

        // Create a VBox for stacking content on top of the StackPane
        VBox contentOnStack = new VBox();
        contentOnStack.setPrefSize(1260, 750);
        contentOnStack.setAlignment(Pos.CENTER);

        // Create a FlowPane for generating sessionItem
        FlowPane sessionItemPane = new FlowPane(Orientation.HORIZONTAL);
        sessionItemPane.setMinSize(1200, 700);
        sessionItemPane.setPadding(new Insets(30, 0, 0, 60));
        sessionItemPane.setAlignment(Pos.TOP_LEFT);
        sessionItemPane.setHgap(50);
        sessionItemPane.setVgap(50);

        // Create for the abilaty to scroll
        ScrollPane scrollPane = new ScrollPane();
        // Set the FlowPane as the content of the ScrollPane
        scrollPane.setContent(sessionItemPane);
        // Hide the scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinSize(1200, 700);
        scrollPane.requestLayout();
        scrollPane.setId("session-scroll-pane");

    // button section

        // Create a FlowPane for buttons
        FlowPane btnPane = new FlowPane(Orientation.HORIZONTAL);
        btnPane.setAlignment(Pos.CENTER);
        btnPane.setHgap(50);
        btnPane.setMinSize(1260, 50);

        // Create a button to add session
        Button btnAddSession = new Button("add session");
        btnAddSession.setAlignment(Pos.CENTER);
        btnAddSession.setTextAlignment(TextAlignment.CENTER);
        btnAddSession.setId("btn-add-session");

        // Set an event handler for the add session button
        btnAddSession.setOnAction(e -> {
            SessionAddScreen sessionAddScreen = new SessionAddScreen();
            mainStage.setScene(sessionAddScreen.getScene());
        });

    // method calling section

        // Retrieve sessions from the database and add them to sessionItemPane
        for (Session session : db.getAllSessions()) {
            sessionItemPane.getChildren().add(generateSessionItem(session));
        }

    // children section

        // Add components to the contentOnStack VBox
        content.getChildren().addAll(contentOnStack);
        contentOnStack.getChildren().addAll(scrollPane, btnPane);
        btnPane.getChildren().add(btnAddSession);
        // Add components to the root HBox
        root.getChildren().addAll(sidebar, content);
    }

// method area

    //  create method to generate the sessionItem onscreen with an image and the session name
    public VBox generateSessionItem(Session session) {//

        // Create a VBox for sessionItem content
        VBox sessionItem = new VBox();
        sessionItem.setAlignment(Pos.CENTER);
        sessionItem.setMinSize(130, 232);
        sessionItem.setSpacing(5);
        sessionItem.setId("session-item");
        // Event handler for sessionItem click
        sessionItem.setOnMouseClicked(e -> {
            SessionInfoScreen sessionInfoScreen = new SessionInfoScreen(session);//
        });

        // Create a VBox for sessionPicture
        VBox sessionPicture = new VBox();
        sessionPicture.setPrefSize(120, 175);
        sessionPicture.setMinSize(120, 175);
        sessionPicture.setMaxSize(120, 175);
        sessionPicture.setId("session-picture");
        sessionPicture.setAlignment(Pos.CENTER);

        // rectangle to clip the picture to the size of the session item and give it a radius
        Rectangle clipRect = new Rectangle(120, 175);
        clipRect.setArcWidth(10);
        clipRect.setArcHeight(10);
        sessionPicture.setClip(clipRect);

        // Create an ImageView for the session picture
        ImageView sessionPictureImage = new ImageView();//
        sessionPictureImage.setSmooth(true);
        sessionPictureImage.setFitWidth(120);
        sessionPictureImage.setFitHeight(175);
        sessionPictureImage.setImage(new Image(Application.class.getResource("images/session-item-alt-pic.jpg").toString()));//
        sessionPictureImage.setId("session-picture-image");

        // Create a Label for the session title
        Label sessionTitle = new Label(session.getName());//
        sessionTitle.setWrapText(true);
        sessionTitle.setMaxWidth(sessionPictureImage.getFitWidth());
        sessionTitle.setMaxHeight(50);
        sessionTitle.setMinHeight(50);
        sessionTitle.setContentDisplay(ContentDisplay.CENTER);
        sessionTitle.setTextAlignment(TextAlignment.CENTER);
        sessionTitle.setId("session-title");

        // Add components to sessionItem
        sessionPicture.getChildren().add(sessionPictureImage);
        sessionItem.getChildren().addAll(sessionPicture, sessionTitle);

        return sessionItem;
    }
    // Getter method for the scene
    public Scene getScene() {
        return scene;
    }
}
