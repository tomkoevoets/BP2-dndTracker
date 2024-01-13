package com.dndtracker.bp2dndtracker.components;

import com.dndtracker.bp2dndtracker.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class BackgroundComponent extends Pane {
    public BackgroundComponent() {
        Image image = new Image(Application.class.getResource("images/background-5.jpg").toString());

        // Create a BackgroundSize instance with specific width and height
        BackgroundSize backgroundSize = new BackgroundSize(
                1260, // New width
                750, // New height
                false, // Width is not a percentage
                false, // Height is not a percentage
                false, // Not contain
                false // Not cover
        );

        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize
        );
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        this.setPrefSize(1260, 750);
        this.setMinSize(1260, 750);
    }
}


