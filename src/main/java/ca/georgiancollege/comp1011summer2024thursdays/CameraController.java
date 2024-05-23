package ca.georgiancollege.comp1011summer2024thursdays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class CameraController {

    @FXML
    private ImageView photo;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField color, name, make, model;

    @FXML
    private Label output, error;

    private ArrayList<Camera> cameraList = new ArrayList<>();
    private int indexTracker = -1;

    private ArrayList<String> imagesPathString = new ArrayList<>();
    private ArrayList<Image> imagesPathImage = new ArrayList<>();

    private int imagesPathSelectedIndex = 2;

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

        imagesPathString.add( getClass().getResource("images/camera1.jpg").toString()  );
        imagesPathString.add( getClass().getResource("images/camera2.png").toString()  );
        imagesPathString.add( getClass().getResource("images/camera3.jpg").toString()  );
     }

     @FXML
    void onChange(ActionEvent e){

        int index = comboBox.getSelectionModel().getSelectedIndex();

        output.setText( cameraList.get(index).toString() );
     }

     @FXML
    void onArrowClick(ActionEvent e){
        Button btn = (Button)e.getSource();
         String direction = btn.getText();


         imagesPathSelectedIndex = direction.equals("<") ?
                 imagesPathSelectedIndex > 0 ? --imagesPathSelectedIndex : 2
                 : imagesPathSelectedIndex < 2 ? ++imagesPathSelectedIndex : 0;

                 photo.setImage(
                         new Image(imagesPathString.get(imagesPathSelectedIndex))
                 );

//         if(direction.equals("<")){
//             if(imagesPathSelectedIndex > 0){
//                 photo.setImage(
//                         new Image(imagesPathString.get(--imagesPathSelectedIndex))
//                 );
//             }
//             else{
//                 imagesPathSelectedIndex = 2;
//                 photo.setImage(
//                         new Image(imagesPathString.get(imagesPathSelectedIndex))
//                 );
//             }
//         }
//         else if (direction.equals(">")) {
//             if(imagesPathSelectedIndex < 2){
//                 photo.setImage(
//                         new Image(imagesPathString.get(++imagesPathSelectedIndex))
//                 );
//             }
//             else{
//                 imagesPathSelectedIndex = 0;
//                 photo.setImage(
//                         new Image(imagesPathString.get(imagesPathSelectedIndex))
//                 );
//             }

//         }
//         photo.setImage(
//     new Image(getClass().getResource("images/camera2.png").toString())
//         );

     }

}
