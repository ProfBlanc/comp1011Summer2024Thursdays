package ca.georgiancollege.comp1011summer2024thursdays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class CameraController {

    @FXML
    private TextField color, name, make, model;

    @FXML
    private Label output, error;

    @FXML
    void onSubmit(ActionEvent event) {

        System.out.println("Submitted");

        Random random = new Random();
        int randomInt = random.nextInt();

        try{
            if(randomInt % 2 == 1)
                throw new Exception("Random Error");
            Camera camera = new Camera();
            camera.color = color.getText(); // from GUID
            output.setText(name.getText() + "-" + camera.color);

        }
        catch (Exception e){

            error.setText(e.getMessage());
        }

    }

    @FXML
     void initialize(){
        error.setText("");
        output.setText("");
     }

}
