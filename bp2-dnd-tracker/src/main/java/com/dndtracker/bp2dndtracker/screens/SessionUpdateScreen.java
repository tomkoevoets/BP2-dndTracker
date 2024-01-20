package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class SessionUpdateScreen {
    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    public SessionUpdateScreen(Session session) {
        Database db = new Database();
        Controller cl = new Controller();

        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sessionscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

/////// pre-content-section

        // add the sidebar from components
        sidebar = new SidebarComponent();

        // stackpane to stack the content on top of the background
        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);

        // add the background from components
        background = new BackgroundComponent();

        content.getChildren().add(background);

        // Vbox for the content on top of the stackpane
        VBox contentOnStack = new VBox();
        contentOnStack.setPrefSize(1260, 750);
        contentOnStack.setAlignment(Pos.CENTER);
//        contentOnStack.setSpacing(100);

/////// content-section

        FlowPane mainPane = new FlowPane(Orientation.VERTICAL);
        mainPane.setPrefSize(800, 600);
        mainPane.setMinSize(800, 600);
        mainPane.setMaxSize(800, 600);
        mainPane.setId("main-pane");

        //// title-section
        FlowPane titlePane = new FlowPane();
        titlePane.setPrefSize(800, 100);
        titlePane.setAlignment(Pos.CENTER);

        TextField titleField = new TextField(session.getName());
        titleField.setPrefSize(150, 40);
        titleField.setPromptText("Add Session title");
        titleField.alignmentProperty().setValue(Pos.CENTER);
        titleField.setFocusTraversable(false);
        titleField.setId("session-title-field");

        titlePane.getChildren().add(titleField);

        //// info-section
        FlowPane infSumPane = new FlowPane(Orientation.HORIZONTAL);
        infSumPane.setPrefSize(800, 400);
//        infSumPane.setAlignment(Pos.CENTER);

        FlowPane infoContentPane = new FlowPane();
        infoContentPane.setPrefSize(infSumPane.getPrefWidth()/2, infSumPane.getPrefHeight());
        infoContentPane.setAlignment(Pos.CENTER);

        TextArea infoField = new TextArea(session.getInfo());
        infoField.setPrefSize(infSumPane.getPrefWidth()/2-50, infSumPane.getPrefHeight()-50);
        infoField.setPromptText("Add Session info");
        infoField.setFocusTraversable(false);
        infoField.setId("session-info-field");

        infoContentPane.getChildren().add(infoField);
        infSumPane.getChildren().add(infoContentPane);

        //// summary-section
        FlowPane summaryContentPane = new FlowPane();
        summaryContentPane.setPrefSize(infSumPane.getPrefWidth()/2, infSumPane.getPrefHeight());
        summaryContentPane.setAlignment(Pos.CENTER);

        TextArea summaryField = new TextArea(session.getSummary());
        summaryField.setPrefSize(infSumPane.getPrefWidth()/2-50, infSumPane.getPrefHeight()-50);
        summaryField.setPromptText("Add Session summary");
        summaryField.setFocusTraversable(false);
        summaryField.setId("session-summary-field");

        summaryContentPane.getChildren().add(summaryField);
        infSumPane.getChildren().add(summaryContentPane);

        //// submit-btn-section
        FlowPane submitPane = new FlowPane();
        submitPane.setPrefSize(800, 50);
        submitPane.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("submit");
        submitBtn.setCursor(Cursor.HAND);
        submitBtn.setId("submit-btn");

        submitPane.getChildren().add(submitBtn);
        submitBtn.setOnAction(click -> {
            //TODO add submit do db function
            if (titleField.getText().isEmpty() || infoField.getText().isEmpty()) {

                //  alert wanneer velden leeg zijn
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("one or more required fields are empty");
                alert.showAndWait();

                //   change border colors when empty
                if (titleField.getText().isEmpty()) {
                    titleField.setStyle("-fx-border-color: red");
                    titleField.promptTextProperty().setValue("Session title cannot be empty");
                    titleField.requestFocus();
                }else {
                    titleField.setStyle("-fx-border-color: black");
                }

                if (infoField.getText().isEmpty()) {
                    infoField.setStyle("-fx-border-color: red");
                    infoField.promptTextProperty().setValue("Session info cannot be empty");
                    infoField.requestFocus();
                }else {
                    infoField.setStyle("-fx-border-color: black");
                }return;
            }

            String titleSting = titleField.getText().replace("'", "`");
            String infoString = infoField.getText().replace("'", "`");
            String summaryString = summaryField.getText().replace("'", "`");

            // Handle empty summary field
            String summaryValue = (summaryString.isEmpty()) ? "NULL" : "'" + summaryString + "'";

//            Session uSession = new Session(session.getId(), titleSting, session.getInfo(), session.getSummary());
            session.setName(titleSting);
            session.setInfo(infoString);
            session.setSummary(summaryString);
            cl.updateSession(session);

            System.out.println();

            SessionScreen sessionscreen = new SessionScreen();
            mainStage.setScene(sessionscreen.getScene());
        });

/////// children-section
        mainPane.getChildren().addAll(titlePane, infSumPane, submitPane);
        content.getChildren().add(contentOnStack);
        contentOnStack.getChildren().addAll(mainPane);
        root.getChildren().addAll(sidebar, content);
    }
    public Scene getScene() {
        return scene;
    }
}
