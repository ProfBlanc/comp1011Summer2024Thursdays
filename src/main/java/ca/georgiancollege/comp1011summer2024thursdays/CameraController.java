package ca.georgiancollege.comp1011summer2024thursdays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;

public class CameraController {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField color, name, make, model;

    @FXML
    private Label output, error;

    private ArrayList<Camera> cameraList = new ArrayList<>();
    private int indexTracker = -1;

    @FXML
    void clear(){

        make.clear();
        model.clear();
        color.clear();
        name.clear();
    }

    @FXML
    void onSubmit(ActionEvent event) {

        System.out.println("Submitted");

        Random random = new Random();
        int randomInt = random.nextInt();

        try{
//            if(randomInt % 2 == 1)
//                throw new Exception("Random Error");
//            Camera camera = new Camera();
//            camera.color = color.getText(); // from GUID
//            output.setText(name.getText() + "-" + camera.color);

//Camera camera = new Camera(name.getText(), make.getText(), model.getText(), color.getText());
//            //how do I add the camera object to my ArrayList?
//            output.setText(camera.toString());
//                    comboBox.getItems().add(camera.name + " -" + camera.color);
//            cameraList.add(camera);

                    //output.setText(camera.name + " - "  + camera.color);
                    cameraList.add(new Camera(name.getText(), make.getText(), model.getText(), color.getText()));
                    indexTracker++;
                    output.setText(cameraList.get(indexTracker).toString());

                    clear();

                    comboBox.getItems().add(cameraList.get(indexTracker).name
                            + " -" + cameraList.get(indexTracker).color);


        }
        catch (Exception e){

            error.setText(e.getMessage());
        }

    }

    @FXML
     void initialize(){
        error.setText("");
        output.setText("");

        //comboBox.getItems().add("Hi");
     }

     @FXML
    void onChange(ActionEvent e){

        int index = comboBox.getSelectionModel().getSelectedIndex();

        output.setText( cameraList.get(index).toString() );
     }

}
