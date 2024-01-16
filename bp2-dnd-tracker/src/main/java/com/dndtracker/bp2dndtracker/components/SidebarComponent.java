package com.dndtracker.bp2dndtracker.components;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.screens.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class SidebarComponent extends FlowPane {
    public SidebarComponent() {
        setId("sidebar-component");
        setOrientation(Orientation.VERTICAL);

        // pane
        FlowPane logo = new FlowPane();
        logo.setPrefHeight(40);
        logo.setPrefWidth(140);
        logo.setMaxWidth(140);
        logo.setId("logo");

        // logo import
        ImageView logoIcon = new ImageView();
        logoIcon.setPreserveRatio(true);
        logoIcon.setSmooth(true);
        logoIcon.setFitWidth(140);
        logoIcon.setImage(new Image(Application.class.getResource("images/logo-img.jpg").toString()));

        // children -> instegate parameters of menuItems
        logo.getChildren().add(logoIcon);
        getChildren().addAll(logo, getMenuItem("menu-item-home","images/home-color.png", "Home", "HomeScreen"),
                getMenuItem("menu-item-session","images/session-color.png", "Sessions", "SessionScreen"),
                getMenuItem("menu-item-monster", "images/monster-color.png", "Monsters", "MonsterScreen"),
                getMenuItem("menu-item-npc", "images/npc-color.png", "Npc's", "NpcScreen"),
                getMenuItem("menu-item-item", "images/item-color.png", "Items", "ItemsScreen"),
                getMenuItem("menu-item-encounter", "images/encounter-color.png", "Encounter", "EncounterScreen"));
    }

    //  sidebar size  for children
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        setPrefSize(140, getParent().getLayoutBounds().getHeight());
        setMaxSize(140, getParent().getLayoutBounds().getHeight());
    }

    //  menu item method
    public StackPane getMenuItem(String id, String icon, String title, String sceneNav){
        StackPane menuItem = new StackPane();
        menuItem.setPrefSize(140, 40);
        menuItem.setId(id);

        // Item Icon
        ImageView itemIcon = new ImageView();
        itemIcon.setPreserveRatio(true);
        itemIcon.setSmooth(true);
        itemIcon.setFitHeight(18);
        itemIcon.setImage(new Image(Application.class.getResource(icon).toString()));
        itemIcon.setId("menu-item-icon");
        itemIcon.setTranslateX(-50);

        // Item Title
        Label menuItemLabel = new Label(title);
        menuItemLabel.setAlignment(Pos.CENTER);
        menuItemLabel.setId("menu-item-label");

        // navigate to new scene
        // use switch case statement to get the right scene
        // it links because of the parameters in the getMenuItem method
        menuItem.setOnMouseClicked(e -> {
                    switch (sceneNav) {
                        case "HomeScreen":
                            HomeScreen homescreen = new HomeScreen();
                            mainStage.setScene(homescreen.getScene());
                            break;
                        case "SessionScreen":
                            SessionScreen sessionscreen = new SessionScreen();
                            mainStage.setScene(sessionscreen.getScene());
                            break;
                        case "MonsterScreen":
                            MonsterScreen monsterscreen = new MonsterScreen();
                            mainStage.setScene(monsterscreen.getScene());
                            break;
                        case "NpcScreen":
                            NpcScreen npcscreen = new NpcScreen();
                            mainStage.setScene(npcscreen.getScene());
                            break;
                        case "ItemsScreen":
                            ItemScreen itemscreen = new ItemScreen();
                            mainStage.setScene(itemscreen.getScene());
                            break;
                        case "EncounterScreen":
                            EncounterScreen encounterscreen = new EncounterScreen();
                            mainStage.setScene(encounterscreen.getScene());
                            break;
                    }
        });

        menuItem.getChildren().addAll(itemIcon, menuItemLabel);
        return menuItem;
    }

}

