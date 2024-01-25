package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Item;
import com.dndtracker.bp2dndtracker.classes.Session;
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
import javafx.stage.Stage;

import java.util.Optional;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class ItemInfoScreen {
    // Declare instance variables
    private final Scene scene;
    private double screenWidth;
    private double screenHeight;

    // Constructor for SessionInfoScreen with a Session parameter
    public ItemInfoScreen(Item item) {
        // Create instances of Database and Controller and stage
        Database db = new Database();
        Controller cl = new Controller();
        Stage stage = new Stage();

        // Create the root VBox for the scene
        VBox root = new VBox();
        scene = new Scene(root, 800, 600);
        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/itemscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

        // instegate the width and height of the root as a variable
        screenWidth = root.getWidth();
        screenHeight = root.getHeight();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(item.getName());
        stage.show();

// background section

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

// content section

        FlowPane scrollContentPane = new FlowPane(Orientation.VERTICAL);
        scrollContentPane.setAlignment(Pos.TOP_CENTER);
        scrollContentPane.setPrefSize(root.getPrefWidth(), 600);

        // Create for the abilaty to scroll
        ScrollPane scrollPane = new ScrollPane();
        // Set the FlowPane as the content of the ScrollPane
        scrollPane.setContent(scrollContentPane);
        // Hide the scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(root.getPrefWidth(), root.getPrefHeight());
        scrollPane.requestLayout();
        scrollPane.setId("info-scroll-pane");

    // title section

        // content title
        FlowPane titleBox = new FlowPane();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setHgap(10);
        titleBox.setPadding(new Insets(0,0,20,0));

        // import icon for title
        ImageView titleIcon = new ImageView();
        titleIcon.setFitHeight(20);
        titleIcon.setPreserveRatio(true);
        titleIcon.setSmooth(true);
        titleIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/icon/amulet-black.png"))));

        // display the session title on screen
        Text title = new Text(item.getName());
        title.setId("info-screen-title");

        VBox titlePane = new VBox();
        titlePane.setPrefSize(screenWidth, screenHeight - 625);
        titlePane.setPadding(new Insets(10,0,0,0));
        titlePane.setAlignment(Pos.CENTER);

    // delete section

        // pane for delete content
        FlowPane deletePane = new FlowPane();
        deletePane.setPrefSize(screenWidth, screenHeight - 625);
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
            alert.setTitle("Delete Item");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.setContentText("This action cannot be undone!");
            // instagate result variable
            Optional<ButtonType> result = alert.showAndWait();
            // check if the result is OK
            if (result.get() == ButtonType.OK) {
                // Delete session
                cl.deleteItem(item.getId());
                // Close stage
                stage.close();
                // Create new instance of SessionScreen and pass in the deleted session
                ItemScreen itemscreen = new ItemScreen();
                mainStage.setScene(itemscreen.getScene());
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

    // type, rarity, cost, weight section

        // type, rarity, cost, weight pane
        HBox middleScreen = new HBox();
        middleScreen.setAlignment(Pos.CENTER);
        middleScreen.setPrefSize(screenWidth, screenHeight - 400);

        FlowPane middleContentPane = new FlowPane(Orientation.VERTICAL);
        middleContentPane.setAlignment(Pos.CENTER);
        middleContentPane.setPrefSize(screenWidth-100, screenHeight - 400);
        middleContentPane.setId("middle-content-pane");

        // title section

        // middleContentPane title
        FlowPane middleContentTitlePane = new FlowPane();
        middleContentTitlePane.setAlignment(Pos.CENTER);
        middleContentTitlePane.setPrefSize(middleContentPane.getPrefWidth(), 50);

        // title label
        Label middleContentTitle = new Label("Item Details");
        middleContentTitle.setPadding(new Insets(0,0,10,0));
        middleContentTitle.setId("middle-content-title");

        // type/rarity section

        // type/rarity pane
        FlowPane trPane = new FlowPane(Orientation.HORIZONTAL);
        trPane.setPrefSize(middleContentPane.getPrefWidth(), 70);
        trPane.setHgap(200);
        trPane.setAlignment(Pos.CENTER);

        // textfield to display type
        TextField typeField = new TextField("Type:  " + item.getType());
        typeField.setEditable(false);
        typeField.setFocusTraversable(false);
        typeField.setAlignment(Pos.CENTER);
        typeField.setId("field");

        // textfield to display rarity
        TextField rarityField = new TextField("Rarity:  " + item.getRarity());
        rarityField.setEditable(false);
        rarityField.setFocusTraversable(false);
        rarityField.setAlignment(Pos.CENTER);
        rarityField.setId("field");

        // cost/weight section
        FlowPane cwPane = new FlowPane(Orientation.HORIZONTAL);
        cwPane.setPrefSize(middleContentPane.getPrefWidth(), 70);
        cwPane.setHgap(200);
        cwPane.setAlignment(Pos.CENTER);

        // textfield to display cost
        TextField costField = new TextField(String.valueOf("Cost:  " + item.getCost()));
        costField.setEditable(false);
        costField.setFocusTraversable(false);
        costField.setAlignment(Pos.CENTER);
        costField.setId("field");

        // textfield to display weight
        TextField weightField = new TextField(String.valueOf("Weight:  " + item.getWeight()));
        weightField.setEditable(false);
        weightField.setFocusTraversable(false);
        weightField.setAlignment(Pos.CENTER);
        weightField.setId("field");

        // children section

        cwPane.getChildren().addAll(costField, weightField);
        trPane.getChildren().addAll(typeField, rarityField);
        middleContentTitlePane.getChildren().add(middleContentTitle);
        middleContentPane.getChildren().addAll(middleContentTitlePane, trPane, cwPane);
        middleScreen.getChildren().add(middleContentPane);

    // content info section

        // Content info section
        HBox bottomScreen = new HBox();
        bottomScreen.setPrefSize(screenWidth, screenHeight - 375);

        // Info Pane
        FlowPane infoPane = new FlowPane();
        infoPane.setPrefSize(screenWidth / 2, screenHeight - 375);
        infoPane.setAlignment(Pos.CENTER);

        // Info Content Pane
        VBox infoContentPane = new VBox();
        infoContentPane.setPrefSize(screenWidth / 2 - 100, screenHeight - 400);
        infoContentPane.setAlignment(Pos.CENTER);
        infoContentPane.setId("info-content-pane");

        // Info Text
        Text info = new Text(item.getDescription().replace("`", "'"));
        info.setWrappingWidth(infoContentPane.getPrefWidth() - 50);
        info.setId("item-info");

        // Info Title
        Label infoTitle = new Label("Item-Description");
        infoTitle.setPadding(new Insets(0, 0, 10, 0));
        infoTitle.setId("info-title");

        // Info Title Pane
        FlowPane infoTitlePane = new FlowPane();
        infoTitlePane.setPrefSize(infoContentPane.getPrefWidth(), 50);
        infoTitlePane.setAlignment(Pos.CENTER);

        // Info Scroll Pane
        ScrollPane infoTXT = new ScrollPane();
        infoTXT.setMaxSize(infoContentPane.getPrefWidth() - 20, infoContentPane.getPrefHeight() - 70);
        infoTXT.setMinSize(infoContentPane.getPrefWidth() - 20, infoContentPane.getPrefHeight() - 70);
        infoTXT.setPadding(new Insets(5, 5, 5, 5));
        infoTXT.setId("scrollPane");

        // Info Children
        infoTitlePane.getChildren().add(infoTitle);
        infoTXT.setContent(info);
        infoContentPane.getChildren().addAll(infoTitlePane, infoTXT);
        infoPane.getChildren().add(infoContentPane);

    // Content extra section

        // content extra pane
        FlowPane summaryPane = new FlowPane();
        summaryPane.setPrefSize(screenWidth / 2, screenHeight - 375);
        summaryPane.setAlignment(Pos.CENTER);

        // Summary Content Pane
        VBox summaryContentPane = new VBox();
        summaryContentPane.setPrefSize(screenWidth / 2 - 100, screenHeight - 400);
        summaryContentPane.setAlignment(Pos.CENTER);
        summaryContentPane.setId("info-extra-content-pane");

        // Summary Text
        Text summary = new Text(item.getExtra().replace("`", "'"));
        summary.setWrappingWidth(infoContentPane.getPrefWidth() - 50);
        summary.setId("info-extra");

        // Summary Title
        Label summaryTitle = new Label("Item-Extra");
        summaryTitle.setPadding(new Insets(0, 0, 10, 0));
        summaryTitle.setId("extra-title");

        // Summary Title Pane
        FlowPane summaryTitlePane = new FlowPane();
        summaryTitlePane.setPrefSize(infoContentPane.getPrefWidth(), 50);
        summaryTitlePane.setAlignment(Pos.CENTER);

        // Summary Scroll Pane
        ScrollPane summaryTXT = new ScrollPane();
        summaryTXT.setMaxSize(infoContentPane.getPrefWidth() - 20, infoContentPane.getPrefHeight() - 70);
        summaryTXT.setMinSize(infoContentPane.getPrefWidth() - 20, infoContentPane.getPrefHeight() - 70);
        summaryTXT.setPadding(new Insets(5, 5, 5, 5));
        summaryTXT.setId("scrollPane");

        // Summary Children
        summaryTitlePane.getChildren().add(summaryTitle);
        summaryTXT.setContent(summary);
        summaryContentPane.getChildren().addAll(summaryTitlePane, summaryTXT);
        summaryPane.getChildren().add(summaryContentPane);

        // Update section
        HBox updateBox = new HBox();
        updateBox.setAlignment(Pos.CENTER);
        updateBox.setPadding(new Insets(25, 0, 0, 0));
        updateBox.setPrefSize(screenWidth, scrollContentPane.getPrefHeight() - 540);

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            // Navigate to updatescreen when the button is pressed
            ItemUpdateScreen itemscreen = new ItemUpdateScreen(item);
            mainStage.setScene(itemscreen.getScene());
            stage.close();
        });
        updateBtn.setId("update-btn");

        // Add children to update section
        deleteBtn.getChildren().add(deleteIcon);
        updateBox.getChildren().addAll(updateBtn);

        // Add children to bottomScreen and scrollContentPane
        bottomScreen.getChildren().addAll(infoPane, summaryPane);
        scrollContentPane.getChildren().addAll(titlePane, middleScreen, bottomScreen, updateBox);
        root.getChildren().addAll(scrollPane);
    }
    public Scene getScene() {
        return scene;
    }
}

