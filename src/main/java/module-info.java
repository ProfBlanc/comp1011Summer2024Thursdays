module ca.georgiancollege.comp1011summer2024thursdays {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.georgiancollege.comp1011summer2024thursdays to javafx.fxml;
    exports ca.georgiancollege.comp1011summer2024thursdays;
}