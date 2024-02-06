package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.CharacterSuperclass;
import com.dndtracker.bp2dndtracker.classes.Controller;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Item;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.GenerateItemComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class NpcScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    public NpcScreen() {
        // Create a new Database instance
        Database db = new Database();
        Controller cl = new Controller();

        // Create a new HBox for the root
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        // Add stylesheets to the scene
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/npcscreen.css").toString());

// pre-content area

        // Create SidebarComponent and add it to the root HBox
        sidebar = new SidebarComponent();

        // Create stackpane for stacking things on the background
        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);
        content.setAlignment(Pos.CENTER);

        // Create BackgroundComponent
        background = new BackgroundComponent();
        // add it to the content StackPane
        content.getChildren().addAll(background);

// content area

        // Create a VBox for stacking content on top of the StackPane
        VBox contentOnStack = new VBox();
        contentOnStack.setPrefSize(1260, 750);
        contentOnStack.setAlignment(Pos.CENTER);

        // Create a FlowPane for generating item
        FlowPane ItemPane = new FlowPane(Orientation.HORIZONTAL);
        ItemPane.setMinSize(1200, 700);
        ItemPane.setPadding(new Insets(30, 0, 0, 60));
        ItemPane.setAlignment(Pos.TOP_LEFT);
        ItemPane.setHgap(50);
        ItemPane.setVgap(50);

        // Create for the abilaty to scroll
        ScrollPane scrollPane = new ScrollPane();
        // Set the FlowPane as the content of the ScrollPane
        scrollPane.setContent(ItemPane);
        // Hide the scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinSize(1200, 700);
        scrollPane.requestLayout();
        scrollPane.setId("scroll-pane");

        // Create a FlowPane for buttons
        FlowPane btnPane = new FlowPane(Orientation.HORIZONTAL);
        btnPane.setAlignment(Pos.CENTER);
        btnPane.setHgap(50);
        btnPane.setMinSize(1260, 50);

        // Create a button to add session
        Button btnAdd = new Button("add Npc");
        btnAdd.setAlignment(Pos.CENTER);
        btnAdd.setTextAlignment(TextAlignment.CENTER);
        btnAdd.setId("btn-add");

        // Set an event handler for the add session button
        btnAdd.setOnAction(e -> {
            NpcAddScreen npcaddscreen = new NpcAddScreen();
            mainStage.setScene(npcaddscreen.getScene());
        });

        // Retrieve sessions from the database and add them to ItemPane
        for (CharacterSuperclass cs : cl.getNpcs()) {
            ItemPane.getChildren().add(new GenerateItemComponent(e -> {
                NpcInfoScreen NpcInfoScreen = new NpcInfoScreen(cs);
            },cs.getImage("Npc"), cs.getName()).getNode());
        }

        // Add components to the contentOnStack VBox
        content.getChildren().addAll(contentOnStack);
        contentOnStack.getChildren().addAll(scrollPane, btnPane);
        btnPane.getChildren().add(btnAdd);
        // Add components to the root HBox
        root.getChildren().addAll(sidebar, content);
    }

    public Scene getScene() {
        return scene;
    }
}
