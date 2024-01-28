package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.CharacterSuperclass;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Optional;

public class EncounterScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    public EncounterScreen() {
        // Create instances of Database and Controller
        Database db = new Database();
        Controller cl = new Controller();

        // Create the root HBox for the scene
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);

        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/encounterscreen.css").toString());
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
        mainPane.setPrefSize(1200, 702);
        mainPane.setMinSize(1200, 702);
        mainPane.setMaxSize(1200, 702);
        mainPane.setFocusTraversable(true);
        mainPane.setId("main-pane");

    // top content section

        // top content pane
        FlowPane topPane = new FlowPane();
        topPane.setAlignment(Pos.BOTTOM_CENTER);
        topPane.setPrefSize(1200, 50);
//        topPane.setStyle("-fx-background-color: red");

        // split button for the ability to choose a session
        SplitMenuButton addBtn = new SplitMenuButton();
        addBtn.setText("Session");
        addBtn.setCursor(Cursor.HAND);
        addBtn.setId("add-btn");
        // adding to session functionality using a for loop to loop threw the existing sessions.
        for (Session session :cl.getSessions()) {
            MenuItem sessionItem = new MenuItem(session.getName());
            sessionItem.setOnAction(e -> {
                addBtn.setText(session.getName());
                // functionality to retrieve characters linked to the chosen session
                ArrayList<CharacterSuperclass> characterList = cl.getCharacterBySessionId(session.getId());
            });
            // add sessions to the button
            addBtn.getItems().addAll(sessionItem);
        }

        topPane.getChildren().add(addBtn);

    // middle content section

        // middle content pane
        FlowPane middlePane = new FlowPane(Orientation.HORIZONTAL);
        middlePane.setAlignment(Pos.CENTER);
        middlePane.setPrefSize(1200, 600);
//        middlePane.setStyle("-fx-background-color: blue");

        // left middle pane
        FlowPane leftPane = new FlowPane(Orientation.HORIZONTAL);
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.setPrefSize(600, 600);
//        leftPane.setStyle("-fx-background-color: yellow");

        // right middle pane
        FlowPane rightPane = new FlowPane();
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPrefSize(600, 600);
//        rightPane.setStyle("-fx-background-color: purple");

        middlePane.getChildren().addAll(leftPane, rightPane);

    // left middle screen section

        // arrow section

        // arrow pane
        FlowPane arrowPane = new FlowPane(Orientation.VERTICAL);
        arrowPane.setAlignment(Pos.CENTER);
        arrowPane.setPrefSize(50, 600);
//        arrowPane.setStyle("-fx-background-color: brown");
        arrowPane.setVgap(25);

        // arrow up button
        Button btnUp = new Button("\u2191");
        btnUp.setId("nav-btn");
        btnUp.setOnAction(e -> {
            // TODO add functionality
        });

        // arrow down button
        Button btnDown = new Button("\u2193");
        btnDown.setId("nav-btn");
        btnDown.setOnAction(e -> {
            // TODO add functionality
        });

        arrowPane.getChildren().addAll(btnUp, btnDown);
        leftPane.getChildren().addAll(arrowPane);

        // initiative section

        //initiative box
        VBox initiativeBox = new VBox();
        initiativeBox.setAlignment(Pos.TOP_CENTER);
        initiativeBox.setPrefWidth(500);
//        initiativeBox.setStyle("-fx-background-color: blue");

        // add characters pane
        FlowPane addPane = new FlowPane();
        addPane.setAlignment(Pos.CENTER);
        addPane.setPrefSize(450, 100);
        addPane.setMaxSize(450, 100);
        addPane.setId("add-pane");
        addPane.setOnMouseClicked(e -> {
            //TODO add functionality
            initiativeBox.getChildren().remove(addPane);
            initiativeBox.getChildren().addAll(getCharacterPane(), addPane);
        });

        // import icon
        ImageView Icon = new ImageView();
        Icon.setFitHeight(20);
        Icon.setPreserveRatio(true);
        Icon.setSmooth(true);
        Icon.setImage(new Image(String.valueOf(Application.class.getResource("images/icon/plus-icon-black.png"))));

        // initiative scrollPane
        ScrollPane scrollPane = new ScrollPane();
        // Set the VBox as the content of the ScrollPane
        scrollPane.setContent(initiativeBox);
        // Hide the scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(500, 550);
        scrollPane.setMaxSize(500, 550);
        scrollPane.requestLayout();
        scrollPane.setId("scroll-pane");

        addPane.getChildren().add(Icon);
        initiativeBox.getChildren().addAll(addPane);
        leftPane.getChildren().add(scrollPane);

    // right middle screen section

        // item box
        FlowPane itemPane = new FlowPane(Orientation.HORIZONTAL);
        itemPane.setAlignment(Pos.TOP_CENTER);
        itemPane.setPrefSize(500, 550);
        itemPane.setMaxSize(500, 550);
        itemPane.setId("item-pane");
//        itemPane.setStyle("-fx-background-color: blue");

        // title pane
        FlowPane titlePane = new FlowPane();
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPrefSize(500, 50);

        // box title
        Label itemLAbel = new Label("Items");
        itemLAbel.setId("item-label");

        titlePane.getChildren().add(itemLAbel);
        itemPane.getChildren().addAll(titlePane, getItemPane(), getItemPane(), getItemPane(), getItemPane(), getItemPane(), getItemPane(), getItemPane(), getItemPane());
//        itemPane.getChildren().addAll(titlePane);
        rightPane.getChildren().add(itemPane);

    // bottom content section

        // top content pane
        FlowPane bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.TOP_CENTER);
        bottomPane.setPrefSize(1200, 50);
//        bottomPane.setStyle("-fx-background-color: green");

        Button resetBtn = new Button("Reset");
        resetBtn.setId("reset-btn");
        resetBtn.setOnAction(e -> {
            // TODO reset page functionality
            System.out.println(itemPane.getWidth());
        });

        bottomPane.getChildren().add(resetBtn);

// children section

        mainPane.getChildren().addAll(topPane, middlePane, bottomPane);
        contentOnStack.getChildren().add(mainPane);
        content.getChildren().addAll(contentOnStack);
        root.getChildren().addAll(sidebar, content);
    }

// method section

    // method for adding characters
    public FlowPane getCharacterPane() {
        // container for the characters name, hp and delete functionality
        FlowPane characterPane = new FlowPane(Orientation.HORIZONTAL);
        characterPane.setAlignment(Pos.CENTER);
        characterPane.setPrefSize(450, 100);

    // info section

        // pane for name and hp
        FlowPane displayPane = new FlowPane(Orientation.HORIZONTAL);
        displayPane.setAlignment(Pos.CENTER_RIGHT);
        displayPane.setPrefSize(400, 100);

        Text nameLabel = new Text();//todo add name as text
        nameLabel.setId("name-label");

        VBox hpBox = new VBox();
        hpBox.setAlignment(Pos.CENTER);

        Text hpLabel = new Text("HP");

        // Create a Spinner with Integer values
        Spinner<Integer> numberBox = new Spinner<>();
        numberBox.setPrefSize(150, 40);
        numberBox.setId("hp-spinner");
        // Set the range and initial value for the Spinner
        SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 1000, 0);
        numberBox.setValueFactory(vf);

        hpBox.getChildren().addAll(hpLabel, numberBox);
        displayPane.getChildren().addAll(nameLabel, hpBox);

    // delete section

        // pane for delete functionality
        FlowPane deletePane = new FlowPane();
        deletePane.setAlignment(Pos.TOP_RIGHT);
        deletePane.setPrefSize(40, 100);

        // button background to make pressing the delete icon easy
        FlowPane deleteBtn = new FlowPane();
        deleteBtn.setPrefSize(30, 30);
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setAlignment(Pos.CENTER);
        deleteBtn.setId("delete-btn");
        deleteBtn.setOnMouseClicked(e -> {
            // TODO delete functionality
        });

        // import delete icon
        ImageView deleteIcon = new ImageView();
        deleteIcon.setFitHeight(20);
        deleteIcon.setPreserveRatio(true);
        deleteIcon.setSmooth(true);
        deleteIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/delete-btn.png"))));

        deleteBtn.getChildren().add(deleteIcon);
        deletePane.getChildren().add(deleteBtn);
        characterPane.getChildren().addAll(displayPane, deletePane);

        return characterPane;
    }

    // method for adding item
    public FlowPane getItemPane() {
        FlowPane itemPane = new FlowPane();
        itemPane.setAlignment(Pos.CENTER);
        itemPane.setPrefSize(248, 125);

        FlowPane addItemPane = new FlowPane();
        addItemPane.setAlignment(Pos.CENTER);
        addItemPane.setPrefSize(100, 100);
        addItemPane.setId("add-item-pane");
        addItemPane.setOnMouseClicked(e -> {
            // TODO add functionality for existing item method
        });

        // import icon
        ImageView Icon = new ImageView();
        Icon.setFitHeight(20);
        Icon.setPreserveRatio(true);
        Icon.setSmooth(true);
        Icon.setImage(new Image(String.valueOf(Application.class.getResource("images/icon/plus-icon-black.png"))));

        addItemPane.getChildren().add(Icon);
        itemPane.getChildren().add(addItemPane);

        return itemPane;
    }

    public Scene getScene() {
        return scene;
    }
}

//todo add item change method
// add title above the characterpane
// add split menu button for characters and normal button for player
// todo character pane styling
// todo fix pane equality
// todo add up down button functionallities
// todo change character pane styling on 0 hp
// todo add reset function
