package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

// Define the SessionAddScreen class
public class SessionAddScreen {

    // Declare instance variables
    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    // Constructor for SessionAddScreen
    public SessionAddScreen() {
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

// Pre-content area

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

// Content area

        // Create a FlowPane for the main content
        FlowPane mainPane = new FlowPane(Orientation.VERTICAL);
        mainPane.setPrefSize(800, 600);
        mainPane.setMinSize(800, 600);
        mainPane.setMaxSize(800, 600);
        mainPane.setId("main-pane");

    // Title section

        // Create a FlowPane for the title
        FlowPane titlePane = new FlowPane();
        titlePane.setPrefSize(800, 100);
        titlePane.setAlignment(Pos.CENTER);

        // Create a TextField for the session title
        TextField titleField = new TextField();
        titleField.setPrefSize(150, 40);
        titleField.setPromptText("Add Session title...");
        titleField.alignmentProperty().setValue(Pos.CENTER);
        titleField.setFocusTraversable(false);
        titleField.setId("session-title-field");

        titlePane.getChildren().add(titleField);

    // Info section

        // Create a FlowPane for information summary
        FlowPane infSumPane = new FlowPane(Orientation.HORIZONTAL);
        infSumPane.setPrefSize(800, 400);

        FlowPane infoContentPane = new FlowPane();
        infoContentPane.setPrefSize(infSumPane.getPrefWidth()/2, infSumPane.getPrefHeight());
        infoContentPane.setAlignment(Pos.CENTER);

        // Create a TextArea for session info
        TextArea infoField = new TextArea();
        infoField.setPrefSize(infSumPane.getPrefWidth()/2-50, infSumPane.getPrefHeight()-50);
        infoField.setPromptText("Add Session info");
        infoField.setFocusTraversable(false);
        infoField.setId("session-info-field");

        infoContentPane.getChildren().add(infoField);
        infSumPane.getChildren().add(infoContentPane);

    // Summary section

        // Create a FlowPane for summary content
        FlowPane summaryContentPane = new FlowPane();
        summaryContentPane.setPrefSize(infSumPane.getPrefWidth()/2, infSumPane.getPrefHeight());
        summaryContentPane.setAlignment(Pos.CENTER);

        // Create a TextArea for session summary
        TextArea summaryField = new TextArea();
        summaryField.setPrefSize(infSumPane.getPrefWidth()/2-50, infSumPane.getPrefHeight()-50);
        summaryField.setPromptText("Add Session summary");
        summaryField.setFocusTraversable(false);
        summaryField.setId("session-summary-field");

        summaryContentPane.getChildren().add(summaryField);
        infSumPane.getChildren().add(summaryContentPane);

    // Submit button section

        // Create a FlowPane for the submit button
        FlowPane submitPane = new FlowPane();
        submitPane.setPrefSize(800, 50);
        submitPane.setAlignment(Pos.CENTER);

        // Create a Button for submission
        Button submitBtn = new Button("submit");
        submitBtn.setCursor(Cursor.HAND);
        submitBtn.setId("submit-btn");

        submitPane.getChildren().add(submitBtn);

        // Set the action for the 'submitBtn' button
        submitBtn.setOnAction(click -> {

            // Check if the required fields ('titleField' and 'infoField') are empty
            if (titleField.getText().isEmpty() || infoField.getText().isEmpty()) {

                // Display a warning alert when required fields are empty
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("One or more required fields are empty");
                alert.showAndWait();

                // Change border colors and prompts when fields are empty
                if (titleField.getText().isEmpty()) {
                    titleField.setStyle("-fx-border-color: red");
                    titleField.promptTextProperty().setValue("Session title cannot be empty");
                    // Set focus to 'titleField' when it's empty
                    titleField.requestFocus();
                } else {
                    titleField.setStyle("-fx-border-color: black");
                }

                if (infoField.getText().isEmpty()) {
                    infoField.setStyle("-fx-border-color: red");
                    infoField.promptTextProperty().setValue("Session info cannot be empty");
                    // Set focus to 'infoField' when it's empty
                    infoField.requestFocus();
                } else {
                    infoField.setStyle("-fx-border-color: black");
                }

                // Stop execution if required fields are empty
                return;
            }

            // Retrieve values from text fields
            String titleSting = titleField.getText();
            String infoString = infoField.getText().replace("'", "`");
            String summaryString = summaryField.getText().replace("'", "`");

            // Handle empty summary field
            String summaryValue = (summaryString.isEmpty()) ? "NULL" : "'" + summaryString + "'";

            // Call controller ('cl') to create a new session in the database
            cl.createSession(titleSting, infoString, summaryString);

            // Switch back to the 'SessionScreen' after submitting the form
            SessionScreen sessionscreen = new SessionScreen();
            mainStage.setScene(sessionscreen.getScene());
        });

    // children-section

        // add children
        mainPane.getChildren().addAll(titlePane, infSumPane, submitPane);
        content.getChildren().add(contentOnStack);
        contentOnStack.getChildren().addAll(mainPane);
        root.getChildren().addAll(sidebar, content);
    }
    public Scene getScene() {
        return scene;
    }
}
