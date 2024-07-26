module ca.georgiancollege.comp1011summer2024thursdays {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;


    opens ca.georgiancollege.comp1011summer2024thursdays to javafx.fxml, com.google.gson;
    exports ca.georgiancollege.comp1011summer2024thursdays;
}