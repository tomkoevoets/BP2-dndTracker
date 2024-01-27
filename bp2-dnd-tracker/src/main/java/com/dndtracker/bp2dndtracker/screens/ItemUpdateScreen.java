package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Item;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.collections.FXCollections;
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

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class ItemUpdateScreen {
    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    // Constructor for ItemAddScreen
    public ItemUpdateScreen(Item item) {
        // Create instances of Database and Controller
        Database db = new Database();
        Controller cl = new Controller();

        // Create the root HBox for the scene
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);

        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/itemscreen.css").toString());
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
        mainPane.setPrefSize(800, 600);
        mainPane.setMinSize(800, 600);
        mainPane.setMaxSize(800, 600);
        mainPane.setId("main-pane");

        // create flowpane for the content within the scrollPane
        FlowPane scrollContentPane = new FlowPane(Orientation.VERTICAL);
        scrollContentPane.setPrefSize(mainPane.getPrefWidth(), 600);
        scrollContentPane.setAlignment(Pos.BOTTOM_CENTER);
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

        // Create a TextField for the session title
        TextField titleField = new TextField(item.getName());
        titleField.setPrefSize(150, 40);
        titleField.setPromptText("Add item title");
        titleField.alignmentProperty().setValue(Pos.CENTER);
        titleField.setFocusTraversable(false);

        titlePane.getChildren().add(titleField);

        // type/rarity section

        // pane for the type/rarity section
        FlowPane trPane = new FlowPane(Orientation.HORIZONTAL);
        trPane.setPrefSize(mainPane.getPrefWidth(), 50);
        trPane.setAlignment(Pos.CENTER);
        trPane.setHgap(50);


        // cmb for the type
        ComboBox<String> cmbType = new ComboBox<>(FXCollections.observableArrayList("Weapon", "Armor", "Potion", "Ring", "Scroll", "Wand", "Amulet", "Tool", "Instrument", "Miscellaneous"));
        cmbType.setPromptText("type");
        cmbType.setValue(item.getType());
        cmbType.setPrefSize(150, 40);

        // cmb for the rarity
        ComboBox<String> cmbRarity = new ComboBox<>(FXCollections.observableArrayList("Common", "Uncommon", "Rare", "Very rare", "Legendary"));
        cmbRarity.setPromptText("rarity");
        cmbRarity.setValue(item.getRarity());
        cmbRarity.setPrefSize(150, 40);

        trPane.getChildren().addAll(cmbType, cmbRarity);

        // cost/weight section

        // pane for the cost/weight section
        FlowPane cwPane = new FlowPane(Orientation.HORIZONTAL);
        cwPane.setPrefSize(mainPane.getPrefWidth(), 50);
        cwPane.setAlignment(Pos.CENTER);
        cwPane.setHgap(50);
        cwPane.setPadding(new Insets(0, 60,0,0));

        // Label for the cost
        Label costLabel = new Label("add cost:  ");
        costLabel.setId("add-cost-label");

        // Create a Spinner with Integer values
        Spinner<Integer> numberBox = new Spinner<>();
        numberBox.setPrefSize(150, 40);
        numberBox.setId("add-number-box");
        // Set the range and initial value for the Spinner
        SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 1000, item.getCost());
        numberBox.setValueFactory(vf);

        // Hbox for cost components
        HBox costBox = new HBox();
        costBox.setAlignment(Pos.CENTER);
        costBox.getChildren().addAll(costLabel, numberBox);

        // textfield for the item weight
        TextField weightField = new TextField(Double.toString(item.getWeight()));
        weightField.setPrefSize(150, 40);
        weightField.setPromptText("Add Item weight...");
        weightField.alignmentProperty().setValue(Pos.CENTER);
        weightField.setFocusTraversable(false);

        cwPane.getChildren().addAll(costBox, weightField);

        // info section

        // pane for the info section
        FlowPane infoPane = new FlowPane(Orientation.HORIZONTAL);
        infoPane.setPrefSize(mainPane.getPrefWidth(), 320);
        infoPane.setAlignment(Pos.CENTER);
        infoPane.setHgap(50);
        infoPane.setPadding(new Insets(0, 0,0,0));

        // textarea for description
        TextArea descriptionArea = new TextArea(item.getDescription());
        descriptionArea.setPrefSize(300, 300);
        descriptionArea.setPromptText("Add Item description...");
        descriptionArea.setFocusTraversable(false);
        descriptionArea.setId("descripton-area");

        // textarea for description
        TextArea extraArea = new TextArea(item.getExtra());
        extraArea.setPrefSize(300, 300);
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

        // Set an event handler for the submit button
        submitBtn.setOnAction(click -> {
            if (titleField.getText().isEmpty() || cmbType.getValue() == null || cmbRarity.getValue() == null || weightField.getText().isEmpty()) {
                // Input validation: Check if required fields are empty
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("One or more required fields are empty");
                alert.showAndWait();

                // Change border colors and set prompts for empty fields
                if (titleField.getText().isEmpty()) {
                    titleField.setStyle("-fx-border-color: red");
                    titleField.promptTextProperty().setValue("Item title cannot be empty");
                    // set focus when empty
                    titleField.requestFocus();
                } else {
                    titleField.setStyle("-fx-border-color: black");
                }

                if (cmbType.getValue() == null) {
                    cmbType.setStyle("-fx-border-color: red");
                    cmbType.promptTextProperty().setValue("item type cannot be empty");
                    // set focus when empty
                    cmbType.requestFocus();
                } else {
                    cmbType.setStyle("-fx-border-color: black");
                }

                if (cmbRarity.getValue() == null) {
                    cmbRarity.setStyle("-fx-border-color: red");
                    cmbRarity.promptTextProperty().setValue("item rarity cannot be empty");
                    // set focus when empty
                    cmbRarity.requestFocus();
                } else {
                    cmbRarity.setStyle("-fx-border-color: black");
                }

                if (weightField.getText().isEmpty()) {
                    weightField.setStyle("-fx-border-color: red");
                    weightField.promptTextProperty().setValue("Item weight cannot be empty");
                    // set focus when empty
                    weightField.requestFocus();
                } else {
                    weightField.setStyle("-fx-border-color: black");
                }
                // Exit the method if any required field is empty
                return;
            }

            // Extract values from input fields
            String titleString = titleField.getText();
            String typeString = String.valueOf(cmbType.getValue());
            String rarityString = String.valueOf(cmbRarity.getValue());
            String weightString = weightField.getText().replace(",", ".");
            double weightDouble = Double.parseDouble(weightString);
            int costInt = numberBox.getValue();

            // Process and sanitize text areas
            String dString = descriptionArea.getText().replace("'", "`");
            String eString = extraArea.getText().replace("'", "`");

            // Handle empty text areas field
            String dVal = (dString.isEmpty()) ? "NULL" :   dString;
            String eVal = (eString.isEmpty()) ? "NULL" :   eString;

            // Update item object with new values
            item.setName(titleString);
            item.setDescription(dVal);
            item.setExtra(eVal);
            item.setType(typeString);
            item.setRarity(rarityString);
            item.setCost(costInt);
            item.setWeight(weightDouble);

            // Call controller to create a new item in the database
            cl.updateItem(item);

            // Switch back to ItemScreen
            ItemScreen itemscreen = new ItemScreen();
            mainStage.setScene(itemscreen.getScene()); // Navigate to the new screen
        });

// children-section

        // add children
        scrollContentPane.getChildren().addAll(titlePane, trPane, cwPane, infoPane, buttonPane);
        mainPane.getChildren().addAll(scrollPane);
        content.getChildren().add(contentOnStack);
        contentOnStack.getChildren().addAll(mainPane);
        root.getChildren().addAll(sidebar, content);
    }
    public Scene getScene() {
        return scene;
    }
}
