package com.dndtracker.bp2dndtracker.components;

import com.dndtracker.bp2dndtracker.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SidebarComponent extends FlowPane {
    public SidebarComponent() {
        setStyle("-fx-background-color: #292c33");
        setOrientation(Orientation.VERTICAL);

        FlowPane logo = new FlowPane();
        logo.setPrefHeight(50);
        logo.setPrefWidth(140);
        logo.setMaxWidth(140);
        logo.setStyle("-fx-background-color: white");


        getChildren().addAll(logo, getMenuItem("images/home-regular-24.png", "Home"),
                getMenuItem("images/session-ic.png", "Sessions"),
                getMenuItem("images/monster-ic.png", "Monsters"),
                getMenuItem("images/npc-ic.png", "Npc's"),
                getMenuItem("images/wand-ic.png", "Items"),
                getMenuItem("images/encounter-ic.png", "Encounter Tracker"));
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        setPrefSize(140, getParent().getLayoutBounds().getHeight());
        setMaxSize(140, getParent().getLayoutBounds().getHeight());
    }

    public StackPane getMenuItem(String icon, String title){
        StackPane menuItem = new StackPane();
        menuItem.setPrefSize(140, 116);
        menuItem.setStyle("-fx-background-color: red; -fx-border-color: Black; -fx-border-width: 1 1 0 1;");
        menuItem.setId("menu-item");

        // Item Icon
        ImageView itemIcon = new ImageView();
        itemIcon.setPreserveRatio(true);
        itemIcon.setSmooth(true);
        itemIcon.setFitHeight(24);
        itemIcon.setImage(new Image(Application.class.getResource(icon).toString()));
        itemIcon.setId("menu-item-icon");
        itemIcon.setTranslateX(-50);
        itemIcon.setTranslateY(-40);


//        StackPane.setAlignment(itemIcon, Pos.TOP_LEFT);

        // Item Title
        Label menuItemLabel = new Label(title);
        menuItemLabel.setAlignment(Pos.CENTER);
        menuItemLabel.setId("menu-item-label");

        menuItem.getChildren().addAll(itemIcon, menuItemLabel);
        return menuItem;
    }

}


//    public Pane getMenuItem(String icon, String title){
////        FlowPane menuItem = new FlowPane();
////        menuItem.setOrientation(Orientation.HORIZONTAL);
//        Pane menuItem = new Pane();
//        menuItem.setPrefSize(140, 116);
//        menuItem.setStyle("-fx-background-color: red");
////        menuItem.setAlignment(Pos.CENTER);
//// item icon
//        FlowPane itemIconPane = new FlowPane();
//        itemIconPane.setStyle("-fx-background-color: green");
//        itemIconPane.setAlignment(Pos.CENTER);
//        itemIconPane.setPrefWidth(60);
//
//        ImageView itemIcon = new ImageView();
//        itemIcon.setPreserveRatio(true);
//        itemIcon.setSmooth(true);
//        itemIcon.setFitHeight(14);
//        itemIcon.setImage(new Image(Application.class.getResource(icon).toString()));
//        itemIcon.setId("menu-item-icon");
////item title
//        FlowPane itemLabelPane = new FlowPane();
//        itemLabelPane.setStyle("-fx-background-color: blue");
//        itemLabelPane.setAlignment(Pos.CENTER);
//        itemLabelPane.setPrefWidth(60);
//
//        Label menuItemLabel = new Label(title);
////        menuItemLabel.setPrefWidth(140);
//        menuItemLabel.setAlignment(Pos.CENTER);
//        menuItemLabel.setId("menu-item-label");
//
//        itemIconPane.getChildren().add(itemIcon);
//        itemLabelPane.getChildren().add(menuItemLabel);
//        menuItem.getChildren().addAll(itemIconPane, itemLabelPane);
//        return menuItem;
//    }

