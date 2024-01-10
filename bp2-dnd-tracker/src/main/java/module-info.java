module com.dndtracker.bp2dndtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.dndtracker.bp2dndtracker to javafx.fxml;
    exports com.dndtracker.bp2dndtracker;
}