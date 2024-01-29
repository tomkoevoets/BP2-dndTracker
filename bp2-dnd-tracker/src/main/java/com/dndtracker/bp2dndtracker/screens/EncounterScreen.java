package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.*;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static com.dndtracker.bp2dndtracker.Application.mainStage;
import static javafx.scene.paint.Color.rgb;

public class EncounterScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;
    private ArrayList<CharacterSuperclass> characterList = new ArrayList<>();

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
                characterList = cl.getCharacterBySessionId(session.getId());
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

        // left middle pane
        FlowPane leftPane = new FlowPane(Orientation.HORIZONTAL);
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.setPrefSize(600, 600);

        // right middle pane
        FlowPane rightPane = new FlowPane();
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPrefSize(600, 600);

        middlePane.getChildren().addAll(leftPane, rightPane);

    // left middle screen section

        // arrow section

        // arrow pane
        FlowPane arrowPane = new FlowPane(Orientation.VERTICAL);
        arrowPane.setAlignment(Pos.CENTER);
        arrowPane.setPrefSize(50, 600);

        arrowPane.setVgap(25);

        // arrow up button
        Button btnUp = new Button("\u2191");
        btnUp.setId("nav-btn");

        // arrow down button
        Button btnDown = new Button("\u2193");
        btnDown.setId("nav-btn");

        arrowPane.getChildren().addAll(btnUp, btnDown);
        leftPane.getChildren().addAll(arrowPane);

        // initiative section

        // initiative box
        VBox initiativeBox = new VBox();
        initiativeBox.setAlignment(Pos.TOP_CENTER);
        initiativeBox.setPrefWidth(500);

        // initiative title pane
        FlowPane initiativeTitlePane = new FlowPane();
        initiativeTitlePane.setAlignment(Pos.CENTER);
        initiativeTitlePane.setPrefSize(500, 50);

        // box title
        Label initiativeLabel = new Label("Initiative");
        initiativeLabel.setId("item-label");

        initiativeTitlePane.getChildren().add(initiativeLabel);

        // add characters pane
        FlowPane addPane = new FlowPane();
        addPane.setAlignment(Pos.CENTER);
        addPane.setPrefSize(450, 100);
        addPane.setMaxSize(450, 100);
        addPane.setHgap(50);
        addPane.setId("add-pane");

        // add character button
        Button addCharacterBtn = new Button("Add Character");
        addCharacterBtn.setId("add-btn");
        addCharacterBtn.setOnAction(e -> {
            initiativeBox.getChildren().remove(addPane);
            initiativeBox.getChildren().addAll(getCharacterPane(initiativeBox, "Character"), addPane);
            addUpDownEvent(btnUp, btnDown, initiativeBox);
        });

        // add player button
        Button addPlayerBtn = new Button("Add Player");
        addPlayerBtn.setId("add-btn");
        addPlayerBtn.setOnAction(e -> {
            initiativeBox.getChildren().remove(addPane);
            initiativeBox.getChildren().addAll(getCharacterPane(initiativeBox, "Player"), addPane);
            addUpDownEvent(btnUp, btnDown, initiativeBox);
        });

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

        addPane.getChildren().addAll(addCharacterBtn, addPlayerBtn);
        initiativeBox.getChildren().addAll(initiativeTitlePane, addPane);
        leftPane.getChildren().add(scrollPane);

    // right middle screen section

        // item box
        FlowPane itemPane = new FlowPane(Orientation.HORIZONTAL);
        itemPane.setAlignment(Pos.TOP_CENTER);
        itemPane.setPrefSize(500, 550);
        itemPane.setMaxSize(500, 550);
        itemPane.setMinSize(500, 550);
        itemPane.setId("item-pane");

        // title pane
        FlowPane titlePane = new FlowPane();
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPrefSize(500, 50);

        // box title
        Label itemLAbel = new Label("Items");
        itemLAbel.setId("item-label");

        ArrayList<Item> items = cl.getItems();
        titlePane.getChildren().add(itemLAbel);
        itemPane.getChildren().addAll(titlePane, getItemPane(items), getItemPane(items), getItemPane(items),
                 getItemPane(items), getItemPane(items), getItemPane(items), getItemPane(items), getItemPane(items));
        rightPane.getChildren().add(itemPane);

    // bottom content section

        // top content pane
        FlowPane bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.TOP_CENTER);
        bottomPane.setPrefSize(1200, 50);

        Button resetBtn = new Button("Reset");
        resetBtn.setId("reset-btn");
        resetBtn.setOnAction(e -> {
            EncounterScreen es = new EncounterScreen();
            mainStage.setScene(es.getScene());
            System.out.println(scrollPane.getHeight() );
            System.out.println(itemPane.getHeight() + "  itembox");
        });

        bottomPane.getChildren().add(resetBtn);

// children section

        mainPane.getChildren().addAll(topPane, middlePane, bottomPane);
        contentOnStack.getChildren().add(mainPane);
        content.getChildren().addAll(contentOnStack);
        root.getChildren().addAll(sidebar, content);
    }

// method section

    // Method for setting up down functionality after one was added to the initiative
    public void addUpDownEvent(Button btnUp, Button btnDown, VBox initiativeBox){
        // Up down buttons functionality
        // set index start at 1
        int[] initiativeIndexHolder = new int[]{1};
        // setting max count value - 2 because of the titlePane and addPane
        int initiativeMax = Math.max(initiativeBox.getChildren().size() - 2, 0);

        // event to change color of active character in an encounter
        btnDown.setOnAction(e -> {
            // Update the index to the next one, or wrap around to 0 if it reaches the maximum
            initiativeIndexHolder[0] = (initiativeIndexHolder[0] + 1) % (initiativeMax + 1);
            for (int i = 1; i <= initiativeMax; i++) {
                // Get the current child and cast it to a FlowPane
                FlowPane child = (FlowPane) initiativeBox.getChildren().get(i);
                // If the current index matches the updated index, highlight the FlowPane
                if (i == initiativeIndexHolder[0]) {
                    child.setStyle("-fx-background-color: rgb(255, 228, 180);");
                }
                // Otherwise, unhighlight the FlowPane
                else {
                    child.setStyle("-fx-background-color: rgb(231, 207, 165);");
                }
            }
        });

        btnUp.setOnAction(e -> {
            // Update the index to the previous one, or wrap around to maximum if it reaches 0
            initiativeIndexHolder[0] = (initiativeIndexHolder[0] - 1 + initiativeMax + 1) % (initiativeMax + 1);
            for (int i = 1; i <= initiativeMax; i++) {
                FlowPane child = (FlowPane) initiativeBox.getChildren().get(i);
                if (i == initiativeIndexHolder[0]) {
                    child.setStyle("-fx-background-color: rgb(255, 228, 180);");
                } else {
                    child.setStyle("-fx-background-color: rgb(231, 207, 165);");
                }
            }
        });
    }

    // method for adding characters
    public FlowPane getCharacterPane(VBox initiativeBox, String type) {
        // container for the characters name, hp and delete functionality
        FlowPane characterPane = new FlowPane(Orientation.HORIZONTAL);
        characterPane.setAlignment(Pos.CENTER);
        characterPane.setPrefSize(450, 100);
        characterPane.setMaxSize(450, 100);
        characterPane.setId("character-pane-method");

    // info section

        // pane for name and hp
        FlowPane displayPane = new FlowPane(Orientation.HORIZONTAL);
        displayPane.setAlignment(Pos.CENTER_RIGHT);
        displayPane.setHgap(50);
        displayPane.setPrefSize(400, 100);

        // split button for the ability to choose a character
        SplitMenuButton addCharacterBtn = new SplitMenuButton();
        addCharacterBtn.setText("Character");
        addCharacterBtn.setCursor(Cursor.HAND);
        addCharacterBtn.setId("add-btn");

        // adding to session functionality using a for loop to loop threw the existing characters linked to the choosen session.
        for (CharacterSuperclass csc : characterList) {
            MenuItem characterItem = new MenuItem(csc.getName());
            characterItem.setOnAction(e -> {
                addCharacterBtn.setText(csc.getName());
                characterPane.setOnMouseClicked(ev -> {
                    // load the character info screen using instance of when speaking to the superclass
                    if(csc instanceof Monster){
                        new MonsterInfoScreen(csc);
                    } else if(csc instanceof Npc){
                        new NpcInfoScreen(csc);
                    }
                });
            });
            // add sessions to the button
            addCharacterBtn.getItems().addAll(characterItem);
        }

    // player section

        // vbox for displaying the player textfield
        VBox playerBox = new VBox();
        playerBox.setAlignment(Pos.CENTER);

        // invisible label to align the playerField with the hp bar
        Text playerLabel = new Text("HP");
        playerLabel.setVisible(false);

        TextField playerField = new TextField();
        playerField.setPrefSize(150, 40);
        playerField.setPromptText("Player Name");
        playerField.setId("player-field");

    // hp section

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

        // added event listener to react when value is changed to 0
        numberBox.valueProperty().addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) {
                // Change the style of the spinner to red
                numberBox.setStyle("-fx-body-color: red; -fx-border-color: red; -fx-background-color: red; " +
                                   "-fx-progress-color: red; -fx-border-width: 2;");
            } else {
                // Reset the styling of the spinner
                numberBox.setStyle("-fx-body-color: black; -fx-border-color: black; -fx-background-color: transparent; " +
                                   "-fx-progress-color: black; -fx-border-width: 0.8;");
            }
        });

        playerBox.getChildren().addAll(playerLabel, playerField);
        hpBox.getChildren().addAll(hpLabel, numberBox);

        // add right element based on the type from the player or character button
        if(type.equals("Character")){
            displayPane.getChildren().addAll(addCharacterBtn, hpBox);
        } else if(type.equals("Player")){
            displayPane.getChildren().addAll(playerBox, hpBox);
        }

    // delete section

        // pane for delete functionality
        FlowPane deletePane = new FlowPane();
        deletePane.setAlignment(Pos.TOP_RIGHT);
        deletePane.setPrefSize(40, 100);

        // button background to make pressing the delete icon easy
        FlowPane deleteBtn = new FlowPane();
        deleteBtn.setPrefSize(20, 20);
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setAlignment(Pos.CENTER);
        deleteBtn.setId("delete-btn");
        deleteBtn.setOnMouseClicked(e -> {
            initiativeBox.getChildren().remove(characterPane);
        });

        // import delete icon
        ImageView deleteIcon = new ImageView();
        deleteIcon.setFitHeight(12);
        deleteIcon.setPreserveRatio(true);
        deleteIcon.setSmooth(true);
        deleteIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/delete-btn.png"))));

        deleteBtn.getChildren().add(deleteIcon);
        deletePane.getChildren().add(deleteBtn);
        characterPane.getChildren().addAll(displayPane, deletePane);

        return characterPane;
    }

    // method for adding item
    public FlowPane getItemPane(ArrayList<Item> items) {
        FlowPane itemPane = new FlowPane();
        itemPane.setAlignment(Pos.CENTER);
        itemPane.setPrefSize(248, 125);

        FlowPane addItemPane = new FlowPane();
        addItemPane.setAlignment(Pos.CENTER);
        addItemPane.setPrefSize(100, 100);
        addItemPane.setId("add-item-pane");

        //  split button for the ability to choose an item
        SplitMenuButton addItemBtn = new SplitMenuButton();
        addItemBtn.setText("Item");
        addItemBtn.setCursor(Cursor.HAND);
        addItemBtn.setId("add-btn");
        // adding a for each loop to add items to menu button
        for (Item item : items) {
            MenuItem menuItem = new MenuItem(item.getName());
            menuItem.setOnAction(e -> {
                addItemBtn.setText(item.getName());
                // event to open item info screen as a pop-up
                addItemPane.setOnMouseClicked(ev -> {
                    new ItemInfoScreen(item);
                });
            });

            // add items to the button
            addItemBtn.getItems().addAll(menuItem);
        }

        addItemPane.getChildren().add(addItemBtn);
        itemPane.getChildren().add(addItemPane);

        return itemPane;
    }

    public Scene getScene() {
        return scene;
    }
}
