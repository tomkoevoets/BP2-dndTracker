package com.dndtracker.bp2dndtracker.components;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.screens.SessionInfoScreen;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import org.w3c.dom.events.MouseEvent;

public class GenerateItemComponent  {
    private Node node;

    public GenerateItemComponent(EventHandler<Event> event, String picture, String title) {//

        //TODO add stockphoto option

        // Create a VBox for sessionItem content
        VBox sessionItem = new VBox();
        sessionItem.setAlignment(Pos.CENTER);
        sessionItem.setMinSize(130, 232);
        sessionItem.setSpacing(5);
        sessionItem.setId("item-box");
        // Event handler for sessionItem click
        sessionItem.setOnMouseClicked(
                event
        );

        // Create a VBox for sessionPicture
        VBox sessionPicture = new VBox();
        sessionPicture.setPrefSize(120, 175);
        sessionPicture.setMinSize(120, 175);
        sessionPicture.setMaxSize(120, 175);
        sessionPicture.setId("picture-box");
        sessionPicture.setAlignment(Pos.CENTER);

        // rectangle to clip the picture to the size of the session item and give it a radius
        Rectangle clipRect = new Rectangle(120, 175);
        clipRect.setArcWidth(10);
        clipRect.setArcHeight(10);
        sessionPicture.setClip(clipRect);

        // Create an ImageView for the session picture
        ImageView sessionPictureImage = new ImageView();//
        sessionPictureImage.setSmooth(true);
        sessionPictureImage.setFitWidth(120);
        sessionPictureImage.setFitHeight(175);
//        sessionPictureImage.setImage(new Image(Application.class.getResource("images/userUploads/"+picture).toString()));
//        sessionPictureImage.setImage(new Image(Application.class.getResource("images/userUploads/"+ picture).toString()));//
        sessionPictureImage.setImage(new Image(Application.class.getResource("images/userUploads/monster.jpg").toString()));//
        sessionPictureImage.setId("picture-image");

        // Create a Label for the session title
        Label sessionTitle = new Label(title);//
        sessionTitle.setWrapText(true);
        sessionTitle.setMaxWidth(sessionPictureImage.getFitWidth());
        sessionTitle.setMaxHeight(50);
        sessionTitle.setMinHeight(50);
        sessionTitle.setContentDisplay(ContentDisplay.CENTER);
        sessionTitle.setTextAlignment(TextAlignment.CENTER);//TODO centre the titles
        sessionTitle.setId("title");

        // Add components to sessionItem
        sessionPicture.getChildren().add(sessionPictureImage);
        sessionItem.getChildren().addAll(sessionPicture, sessionTitle);

        this.node = sessionItem;
    }
    public Node getNode() {
        return this.node;
    }

}
