package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.CharacterSuperclass;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class MonsterAddScreen {
    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    // Constructor for ItemAddScreen
    public MonsterAddScreen() {
        // Create instances of Database and Controller
        Database db = new Database();
        Controller cl = new Controller();

        // Create the root HBox for the scene
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);

        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/monsterscreen.css").toString());

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
        mainPane.setFocusTraversable(true);
        mainPane.setId("main-pane");

        // create flowpane for the content within the scrollPane
        FlowPane scrollContentPane = new FlowPane(Orientation.VERTICAL);
        scrollContentPane.setPrefSize(mainPane.getPrefWidth(), 600);
        scrollContentPane.setAlignment(Pos.TOP_CENTER);
        scrollContentPane.setPadding(new Insets(30,0,0,0));
        scrollContentPane.setId("scroll-content-pane");

        // Create for the abilaty to scroll
        ScrollPane scrollPane = new ScrollPane();
        // Set the FlowPane as the content of the ScrollPane
        scrollPane.setContent(scrollContentPane);
        // Hide the scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(mainPane.getPrefWidth(), mainPane.getPrefHeight());
        scrollPane.requestLayout();
        scrollPane.setId("add-scroll-pane");

    // title section

        // pane for the title section
        FlowPane titlePane = new FlowPane();
        titlePane.setPrefSize(mainPane.getPrefWidth(), 50);
        titlePane.setAlignment(Pos.CENTER);

        // Create a TextField for the monster title
        TextField titleField = new TextField();
        titleField.setPrefSize(150, 40);
        titleField.setPromptText("Add name...");
        titleField.alignmentProperty().setValue(Pos.CENTER);
        titleField.setFocusTraversable(false);

        titlePane.getChildren().add(titleField);

    // ac/hp/speed section

        // pane for the ac/hp/speed  section
        FlowPane ahsPane = new FlowPane(Orientation.HORIZONTAL);
        ahsPane.setPrefSize(mainPane.getPrefWidth(), 50);
        ahsPane.setAlignment(Pos.CENTER);
        ahsPane.setHgap(50);
        ahsPane.setVgap(10);

        // add textfields threw getTextfield method
        TextField acField = getTextField("Add Armor Class...");
        TextField hpField = getTextField("Add Hit Points...");
        TextField speedField = getTextField("Add Speed...");
        TextField strField = getTextField("Add Strength...");
        TextField dexField = getTextField("Add Dexterity...");
        TextField conField = getTextField("Add Constitution...");
        TextField intField = getTextField("Add Intelligence...");
        TextField wisField = getTextField("Add Wisdom...");
        TextField chaField = getTextField("Add Charisma...");
        TextField senseField = getTextField("Add Sense...");
        TextField languageField = getTextField("Add Language...");
        TextField skillField = getTextField("Add Skill...");
        TextField challangeField = getTextField("Add Challenge...");

    // children section

        ahsPane.getChildren().addAll(acField, hpField, speedField, strField, dexField, conField, intField, wisField,
                chaField, senseField, languageField, skillField, challangeField);

    // info section

        // pane for the info section
        FlowPane infoPane = new FlowPane(Orientation.HORIZONTAL);
        infoPane.setPrefSize(mainPane.getPrefWidth(), 220);
        infoPane.setAlignment(Pos.CENTER);
        infoPane.setHgap(50);

        // textarea for description
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefSize(300, 200);
        descriptionArea.setPromptText("Add Monster description");
        descriptionArea.setFocusTraversable(false);
        descriptionArea.setId("descripton-area");

    // extra section

        // textarea for description
        TextArea extraArea = new TextArea();
        extraArea.setPrefSize(300, 200);
        extraArea.setPromptText("Add extra information if necessary");
        extraArea.setFocusTraversable(false);
        extraArea.setId("extra-area");

        infoPane.getChildren().addAll(descriptionArea, extraArea);

    // button section

        // button section pane
        FlowPane buttonPane = new FlowPane(Orientation.HORIZONTAL);
        buttonPane.setPrefSize(mainPane.getPrefWidth(), 100);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setHgap(50);

        // Button for adding the item
        Button submitBtn = new Button("submit");
        submitBtn.setCursor(Cursor.HAND);
        submitBtn.setId("submit-btn");

        buttonPane.getChildren().add(submitBtn);

        // event section

        // event for adding a new character
        submitBtn.setOnAction(click -> {
            // check if fields are empty
            if(acField.getText().isEmpty() || hpField.getText().isEmpty() || speedField.getText().isEmpty() || extraArea.getText().isEmpty()
                    || descriptionArea.getText().isEmpty() || titleField.getText().isEmpty() || strField.getText().isEmpty() || dexField.getText().isEmpty()
                    || conField.getText().isEmpty() || intField.getText().isEmpty() || wisField.getText().isEmpty() ||
                    chaField.getText().isEmpty() || senseField.getText().isEmpty() || languageField.getText().isEmpty() ||
                    skillField.getText().isEmpty() || challangeField.getText().isEmpty()){
                // Input validation: Check if required fields are empty
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("One or more required fields are empty");
                alert.showAndWait();

                // Change border colors and set prompts for empty fields
                emptyCheck(titleField, "Title");
                emptyCheck(acField, "Armor Class");
                emptyCheck(hpField, "Hit Points");
                emptyCheck(speedField, "Speed");
                emptyCheck(strField, "Strength");
                emptyCheck(dexField, "Dexterity");
                emptyCheck(conField, "Constitution");
                emptyCheck(intField, "Intelligence");
                emptyCheck(wisField, "Wisdom");
                emptyCheck(chaField, "Charisma");
                emptyCheck(senseField, "sense");
                emptyCheck(languageField, "Language");
                emptyCheck(skillField, "Skill");
                emptyCheck(challangeField, "Challenge");
                if (descriptionArea.getText().isEmpty()) {
                    descriptionArea.setStyle("-fx-border-color: red");
                    descriptionArea.promptTextProperty().setValue("Description cannot be empty");
                    // set focus when empty
                    descriptionArea.requestFocus();
                } else {
                    descriptionArea.setStyle("-fx-border-color: black");
                }
                // Exit the method if any required field is empty
                return;
            }

            // create variables with the textfield text to add to the database
            String nameString = titleField.getText().replace("'", "`");
            String descriptionString = descriptionArea.getText().replace("'", "`");
            String extraString = extraArea.getText().replace("'", "`");
            String acString = acField.getText().replace("'", "`");
            String hpString = hpField.getText().replace("'", "`");
            String speedString = speedField.getText().replace("'", "`");
            String strString = strField.getText().replace("'", "`");
            String dexString = dexField.getText().replace("'", "`");
            String conString = conField.getText().replace("'", "`");
            String intString = intField.getText().replace("'", "`");
            String wisString = wisField.getText().replace("'", "`");
            String chaString = chaField.getText().replace("'", "`");
            String senseString = senseField.getText().replace("'", "`");
            String languageString = languageField.getText().replace("'", "`");
            String skillString = skillField.getText().replace("'", "`");
            String challangeString = challangeField.getText().replace("'", "`");

            // Exit the method if any required field is empty
            cl.createCharacter("Monster",nameString, descriptionString, extraString, acString, hpString, strString,
                    dexString, conString, intString, wisString, chaString, speedString, challangeString, senseString, languageString,
                    skillString);

            // Switch back to MonsterScreen
            MonsterScreen monsterscreen = new MonsterScreen();
            mainStage.setScene(monsterscreen.getScene());
        });

    // children section

        // add children
        scrollContentPane.getChildren().addAll(titlePane, ahsPane, infoPane, buttonPane);
        mainPane.getChildren().addAll(scrollPane);
        content.getChildren().add(contentOnStack);
        contentOnStack.getChildren().addAll(mainPane);
        root.getChildren().addAll(sidebar, content);
    }

// method area

    // textfield method
    public TextField getTextField(String prompt) {
        TextField txtField = new TextField();
        txtField.setPrefSize(150, 40);
        txtField.setPromptText(prompt);
        txtField.alignmentProperty().setValue(Pos.CENTER);
        txtField.setFocusTraversable(false);
        txtField.setId("txt-field");

        return txtField;
    }

    // emptycheck method
    public void emptyCheck(TextField txtField, String name) {
        if (txtField.getText().isEmpty()) {
            txtField.setStyle("-fx-border-color: red");
            txtField.promptTextProperty().setValue(name + " cannot be empty");
            // set focus when empty
            txtField.requestFocus();
        } else {
            txtField.setStyle("-fx-border-color: black");
        }
    }
    public Scene getScene() {
        return scene;
    }
}
