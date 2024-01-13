package com.dndtracker.bp2dndtracker.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TitleBar extends Pane {

    public TitleBar() {
        Pane titleBar = new Pane();
        titleBar.setPrefSize(1260, 25);
        titleBar.setMinSize(1260, 25);
        titleBar.setStyle("-fx-background-color: #3d3c5c");
        titleBar.setId("title-bar");

        Button min = new Button("-");
        min.setId("min");

        Button close = new Button("x");
        close.setId("max");

        titleBar.getChildren().addAll(min, close);
    }
}
