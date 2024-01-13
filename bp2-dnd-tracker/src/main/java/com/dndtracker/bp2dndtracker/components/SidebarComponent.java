package com.dndtracker.bp2dndtracker.components;

import com.dndtracker.bp2dndtracker.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SidebarComponent extends FlowPane {
    public SidebarComponent() {
        setId("sidebar-component");
        setOrientation(Orientation.VERTICAL);

        FlowPane logo = new FlowPane();
        logo.setPrefHeight(40);
        logo.setPrefWidth(140);
        logo.setMaxWidth(140);
        logo.setId("logo");

        ImageView logoIcon = new ImageView();
        logoIcon.setPreserveRatio(true);
        logoIcon.setSmooth(true);
        logoIcon.setFitWidth(140);
        logoIcon.setImage(new Image(Application.class.getResource("images/logo-img.jpg").toString()));


        logo.getChildren().add(logoIcon);
        getChildren().addAll(logo, getMenuItem("menu-item-home","images/home-color.png", "Home"),
                getMenuItem("menu-item-session","images/session-color.png", "Sessions"),
                getMenuItem("menu-item-monster", "images/monster-color.png", "Monsters"),
                getMenuItem("menu-item-npc", "images/npc-color.png", "Npc's"),
                getMenuItem("menu-item-item", "images/item-color.png", "Items"),
                getMenuItem("menu-item-encounter", "images/encounter-color.png", "Encounter"));
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        setPrefSize(140, getParent().getLayoutBounds().getHeight());
        setMaxSize(140, getParent().getLayoutBounds().getHeight());
    }

    public StackPane getMenuItem(String id, String icon, String title){
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

        menuItem.getChildren().addAll(itemIcon, menuItemLabel);
        return menuItem;
    }

}

