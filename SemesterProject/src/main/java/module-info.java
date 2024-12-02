module assignment2.semesterproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires okhttp3;
    requires com.google.gson;

    opens cweatherapp to javafx.fxml;
    exports cweatherapp;
    exports cweatherapp.api;
    opens cweatherapp.api to javafx.fxml;
    exports cweatherapp.ui to javafx.graphics, javafx.fxml;
    opens cweatherapp.ui to javafx.fxml;
}