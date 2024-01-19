package com.dndtracker.bp2dndtracker.screens;

import com.dndtracker.bp2dndtracker.Application;
import com.dndtracker.bp2dndtracker.classes.Database;
import com.dndtracker.bp2dndtracker.classes.Session;
import com.dndtracker.bp2dndtracker.components.BackgroundComponent;
import com.dndtracker.bp2dndtracker.components.SidebarComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import static com.dndtracker.bp2dndtracker.Application.mainStage;

public class SessionScreen  {

    private final Scene scene;
    private final SidebarComponent sidebar;
    private final BackgroundComponent background;

    public SessionScreen() {
        Database db = new Database();

        HBox root = new HBox();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sidebar.css").toString());
        scene.getStylesheets().add(Application.class.getResource("stylesheets/sessionscreen.css").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-regular.ttf").toString());
        scene.getStylesheets().add(Application.class.getResource("fonts/JosefinSlab-bold.ttf").toString());

//        add the sidebar from components
        sidebar = new SidebarComponent();

//          stackpane to stack the content on top of the background
        StackPane content = new StackPane();
        content.setPrefSize(1260, 750);
        content.setAlignment(Pos.TOP_CENTER);

//        add the background from components
        background = new BackgroundComponent();

        content.getChildren().add(background);

//        Vbox for the content on top of the stackpane
        VBox contentOnStack = new VBox();
        contentOnStack.setPrefSize(1260, 750);
        contentOnStack.setAlignment(Pos.CENTER);


//          Flowpane to generate the sessionItem
        FlowPane sessionItemPane = new FlowPane(Orientation.HORIZONTAL);
        sessionItemPane.setMinSize(1200, 700);
        sessionItemPane.setMaxSize(1200, 700);
        sessionItemPane.setPadding(new Insets(30, 30, 0, 30));
        sessionItemPane.setHgap(50);
        sessionItemPane.setVgap(50);


//        Flowpane for the buttons
        FlowPane btnPane = new FlowPane(Orientation.HORIZONTAL);
        btnPane.setAlignment(Pos.TOP_CENTER);
        btnPane.setHgap(50);
        btnPane.setMinSize(1260, 50);


//        button to add session
        Button btnAddSession = new Button("+");
        btnAddSession.setAlignment(Pos.CENTER);
        btnAddSession.setTextAlignment(TextAlignment.CENTER);
        btnAddSession.setId("btn-add-session");

//        TODO create button event to add session
        btnAddSession.setOnAction(e -> {
//            SessionInfoScreen sessionInfoScreen = new SessionInfoScreen();
        });

//        button to delete session

//          retrieve the session from the database and add it to the sessionItemPane in the form of an image and the session name
        for (Session session : db.getAllSessions()) {
            sessionItemPane.getChildren().add(generateSessionItem(session));
        }

        content.getChildren().addAll(contentOnStack);
        contentOnStack.getChildren().addAll(sessionItemPane, btnPane);
        btnPane.getChildren().add(btnAddSession);
        root.getChildren().addAll(sidebar, content);
    }

    //TODO make this a component
//    create method to generate the sessionItem onscreen with an image and the session name
    public VBox generateSessionItem(Session session) {//

//        vbox for the sessionItem content
        VBox sessionItem = new VBox();
        sessionItem.setAlignment(Pos.CENTER);
        sessionItem.setMinSize(130, 232);
        sessionItem.setSpacing(5);
        sessionItem.setId("session-item");
        sessionItem.setOnMouseClicked(e -> {
            SessionInfoScreen sessionInfoScreen = new SessionInfoScreen(session);
        });

//        flowpane to generate the sessionItem picture within the sessionItem
        FlowPane sessionPicture = new FlowPane(Orientation.VERTICAL);
        sessionPicture.setPrefSize(120, 175);
        sessionPicture.setMinSize(120, 175);
        sessionPicture.setMaxSize(120, 175);
        sessionPicture.setId("session-picture");
        sessionPicture.setAlignment(Pos.CENTER);

//         rectangle to clip the picture to the size of the session item and give it a radius
        Rectangle clipRect = new Rectangle(120, 175);
        clipRect.setArcWidth(10);
        clipRect.setArcHeight(10);
        sessionPicture.setClip(clipRect);

//        import the session (default) picture
        ImageView sessionPictureImage = new ImageView();//
        sessionPictureImage.setSmooth(true);
        sessionPictureImage.setFitWidth(120);
        sessionPictureImage.setFitHeight(175);
        sessionPictureImage.setImage(new Image(Application.class.getResource("images/session-item-alt-pic.jpg").toString()));
        sessionPictureImage.setId("session-picture-image");

//        Label to display the session name from the database
        Label sessionTitle = new Label(session.getName());//
        sessionTitle.setWrapText(true);
        sessionTitle.setMaxWidth(sessionItem.getMinWidth());
        sessionTitle.setMaxHeight(50);
        sessionTitle.setMinHeight(50);
        sessionTitle.setId("session-title");

        sessionPicture.getChildren().add(sessionPictureImage);
        sessionItem.getChildren().addAll(sessionPicture, sessionTitle);

        //TODO make clickon function

        return sessionItem;
    }

    public Scene getScene() {
        return scene;
    }
}
