package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import javafx.scene.layout.StackPane;


public class HomeScreen {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;
//    private final TitleBar titleBar;

//    homescreen content
    public HomeScreen() {
        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/homescreen.css").toString());

// pre-content area

        // add the sidebar from components
        sidebar = new SidebarComponent();

        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);
        content.setAlignment(Pos.CENTER);

        // add the background from components
        background = new BackgroundComponent();

// content area

    // txt section

        // text pane
        FlowPane welcomeWord = new FlowPane(Orientation.VERTICAL);
        welcomeWord.setPrefSize(300, 400);
        welcomeWord.setMaxSize(300, 400);
        welcomeWord.setId("welcome-pane");
        welcomeWord.setAlignment(Pos.TOP_CENTER);

        // title
        Label welcome = new Label("Hail, O Esteemed Dungeon Master!");
        welcome.setId("welcome-label");

        // text using \n for breaks
        Label welcomeTxt = new Label("Within these digital halls, embark upon a journey to wield unparalleled control over your sagas. \n" +
                "\nBehold the D&D Tracker app, wherein thou shalt seamlessly weave the tapestry of thy sessions.\n" +
                "Populate thy realms with fearsome monsters, intriguing NPCs, and a trove of mystical items.\n" +
                "\nEnchant thy encounters with a tracker, where initiative and HP dance in harmony." +
                "  With this tool, let the epic tales unfold, and may thy campaigns be as legendary as the realms they traverse.\n" +
                "\nWelcome to a realm of boundless adventure and limitless possibilities!");
        welcomeTxt.setWrapText(true);
        welcomeTxt.setTextOverrun(OverrunStyle.WORD_ELLIPSIS);
        welcomeTxt.setMaxWidth(270);
        welcomeTxt.setId("welcome-text1");

        // set a margin of 10 pixels for the left, top,
        FlowPane.setMargin(welcomeTxt, new Insets(10, 0, 0, 10));

    // children section

        // children
        welcomeWord.getChildren().addAll(welcome, welcomeTxt);
        content.getChildren().addAll(background, welcomeWord);
        root.getChildren().addAll(sidebar, content);
    }

    public Scene getScene() {
        return scene;
    }
}
