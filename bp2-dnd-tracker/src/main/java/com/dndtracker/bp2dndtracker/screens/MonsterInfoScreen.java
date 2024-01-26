package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.CharacterSuperclass;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Item;
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

public class MonsterInfoScreen {
    // Declare instance variables
    private final Scene scene;
    private double screenWidth;
    private double screenHeight;

    // Constructor for SessionInfoScreen with a Session parameter
    public MonsterInfoScreen(CharacterSuperclass cs) {
        // Create instances of Database and Controller and stage
        Database db = new Database();
        Controller cl = new Controller();
        Stage stage = new Stage();

        // Create the root VBox for the scene
        VBox root = new VBox();
        scene = new Scene(root, 800, 600);
        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/monsterscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

        // instegate the width and height of the root as a variable
        screenWidth = root.getWidth();
        screenHeight = root.getHeight();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(cs.getName());
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
        scrollContentPane.setPrefSize(root.getPrefWidth(), 700);

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
        titleBox.setFocusTraversable(true);

        // import icon for title
        ImageView titleIcon = new ImageView();
        titleIcon.setFitHeight(20);
        titleIcon.setPreserveRatio(true);
        titleIcon.setSmooth(true);
        titleIcon.setImage(new Image(String.valueOf(Application.class.getResource("images/icon/monster-black.png"))));

        // display the session title on screen
        Text title = new Text(cs.getName());
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
            alert.setTitle("Delete monster");
            alert.setHeaderText("Are you sure you want to delete this monster?");
            alert.setContentText("This action cannot be undone!");
            // instagate result variable
            Optional<ButtonType> result = alert.showAndWait();
            // check if the result is OK
            if (result.get() == ButtonType.OK) {
                // Delete session
                cl.deleteCharacter(cs.getId());
                // Close stage
                stage.close();
                // Create new instance of SessionScreen and pass in the deleted session
                MonsterScreen monsterscreen = new MonsterScreen();
                mainStage.setScene(monsterscreen.getScene());
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

// stat section///////////////////////////////////////////////////////////////////////

        // type, rarity, cost, weight pane
        HBox middleScreen = new HBox();
        middleScreen.setAlignment(Pos.TOP_CENTER);
        middleScreen.setPrefSize(screenWidth, screenHeight - 300);

        FlowPane middleContentPane = new FlowPane(Orientation.VERTICAL);
        middleContentPane.setAlignment(Pos.TOP_CENTER);
        middleContentPane.setPrefSize(screenWidth-100, screenHeight - 300);
        middleContentPane.setId("middle-content-pane");

    // title section

        // middleContentPane title
        FlowPane middleContentTitlePane = new FlowPane();
        middleContentTitlePane.setAlignment(Pos.CENTER);
        middleContentTitlePane.setPrefSize(middleContentPane.getPrefWidth(), 50);
        middleContentTitlePane.setMaxSize(middleContentPane.getPrefWidth(), 50);
        middleContentTitlePane.setId("middle-content-title-pane");

        // title label
        Label middleContentTitle = new Label("Monster Details");
        middleContentTitle.setId("middle-content-title");

    // ac/hp/speed section

        // ac/hp/speed pane
        FlowPane ahsPane = new FlowPane(Orientation.VERTICAL);
        ahsPane.setPrefSize(middleContentPane.getPrefWidth(), 70);
        ahsPane.setHgap(200);
        ahsPane.setAlignment(Pos.CENTER);
        ahsPane.setId("ahs-pane");

        // method section

        ahsPane.getChildren().addAll(infoBlock("Armor Class ", cs.getArmorClass()),
                infoBlock("Hit Points ", cs.getHitPoints()), infoBlock("Speed ", cs.getSpeed()));

    // base stat section

        FlowPane statPane = new FlowPane(Orientation.HORIZONTAL);
        statPane.setPrefSize(middleContentPane.getPrefWidth(), 70);
        statPane.setHgap(20);
        statPane.setAlignment(Pos.CENTER);
        statPane.setId("stat-pane");

        // method section

        statPane.getChildren().addAll(statBlock("STR", cs.getStrength()), statBlock("DEX", cs.getDexterity()), statBlock("CON", cs.getConstitution()),
                statBlock("INT", cs.getIntelligence()), statBlock("WIS", cs.getWisdom()), statBlock("CHA", cs.getCharisma()));

    // bottom stat section

        FlowPane bottomStatPane = new FlowPane(Orientation.VERTICAL);
        bottomStatPane.setPrefSize(middleContentPane.getPrefWidth(), 100);
        bottomStatPane.setAlignment(Pos.CENTER);
        bottomStatPane.setId("bottom-stat-pane");

        // method section

        bottomStatPane.getChildren().addAll(infoBlock("Senses ", cs.getSense()), infoBlock("Languages ", cs.getLanguages()),
                infoBlock("Skills ", cs.getSkills()), infoBlock("Challange ", cs.getChallenge()));


    // children section//////////////////////////////////////////////////////////////////////////////////

        middleContentTitlePane.getChildren().add(middleContentTitle);
        middleContentPane.getChildren().addAll(middleContentTitlePane, ahsPane, statPane, bottomStatPane);
        middleScreen.getChildren().add(middleContentPane);

// content info section

        // Content info hbox
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
        Text info = new Text(cs.getDescription().replace("`", "'"));
        info.setWrappingWidth(infoContentPane.getPrefWidth() - 50);
        info.setId("item-info");

    // info title section

        // Info Title
        Label infoTitle = new Label("Monster Description");
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
        Text summary = new Text(cs.getExtra().replace("`", "'"));
        summary.setWrappingWidth(infoContentPane.getPrefWidth() - 50);
        summary.setId("info-extra");

    // extra title section

        // Summary Title
        Label summaryTitle = new Label("Monster Extra");
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

        // update hbox
        HBox updateBox = new HBox();
        updateBox.setAlignment(Pos.CENTER);
        updateBox.setPadding(new Insets(25, 0, 0, 0));
        updateBox.setPrefSize(screenWidth, scrollContentPane.getPrefHeight() - 740);

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            // Navigate to updatescreen when the button is pressed
            MonsterUpdateScreen monsterscreen = new MonsterUpdateScreen(cs);
            mainStage.setScene(monsterscreen.getScene());
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

    // method for statblock
    public VBox statBlock(String textLabel, String getStat){
        // vbox for statblock
        VBox statBox = new VBox();
        statBox.setAlignment(Pos.CENTER);

        // text label
        Label strengthTxtLabel = new Label(textLabel);
        strengthTxtLabel.setId("txt-stat-label");

        // show strength label
        Label strengthViewLabel = new Label(getStat);
        strengthViewLabel.setId("view-stat-label");

        statBox.getChildren().addAll(strengthTxtLabel, strengthViewLabel);

        return statBox;
    }

    public HBox infoBlock(String textLabel, String getStat) {
        // hbox for labels
        HBox acBox = new HBox();
        acBox.setAlignment(Pos.CENTER_LEFT);
        acBox.setPrefWidth(400);
        acBox.setMaxWidth(400);

        // text label
        Label acTxtLabel = new Label(textLabel);
        acTxtLabel.setId("txt-label");

        // show armor class label
        Label acViewLabel = new Label(getStat);
        acViewLabel.setId("view-label");

        acBox.getChildren().addAll(acTxtLabel, acViewLabel);

        return acBox;
    }
    public Scene getScene() {
        return scene;
    }
}
