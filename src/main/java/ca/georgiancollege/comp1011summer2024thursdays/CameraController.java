package ca.georgiancollege.comp1011summer2024thursdays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class CameraController {

    @FXML
    private Button btnEdit, btnDelete;
    @FXML
    private Slider numPhotos;
    @FXML
    private ImageView photo, photoRight;
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

    private EventHandler<MouseEvent> onMouseEnter = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("You entered an Node ");
        }
    };


    private EventHandler<MouseEvent> onMouseMove = event-> System.out.println("You Moved Mouse");  //lambda expression

    private int cameraToEditIndex = - 1;

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
//                    cameraList.add(new Camera(name.getText(), make.getText(), model.getText(), color.getText()));

Camera camera = new Camera(name.getText(), make.getText(), model.getText(), color.getText(),
        imagesPathString.get(imagesPathSelectedIndex), (int) numPhotos.getValue());

if(cameraToEditIndex > -1){
    cameraList.set(cameraToEditIndex, camera);

    comboBox.getItems().set(cameraToEditIndex, camera.name
            + " -" + camera.color);

}
else {
    cameraList.add(camera);
    indexTracker++;
    comboBox.getItems().add(camera.name
            + " -" + camera.color);
}

            output.setText(camera.toString());
            photoRight.setImage(
                    new Image(camera.getImagePath())
            );

                    numPhotos.setValue(0);
                    imagesPathSelectedIndex = 0;
                    photo.setImage(
                            new Image(imagesPathString.getFirst())
                    );
                    clear();
        }
        catch (Exception e){
            error.setText(e.getMessage());
        }

    }

    @FXML
     void initialize(){
        error.setText("");
        output.setText("");

        btnDelete.setText("");
        btnDelete.setGraphic(new ImageView(new Image(getClass().getResource("images/delete.png").toString())));



        //comboBox.getItems().add("Hi");

        imagesPathString.add( getClass().getResource("images/camera1.jpg").toString()  );
        imagesPathString.add( getClass().getResource("images/camera2.png").toString()  );
        imagesPathString.add( getClass().getResource("images/camera3.jpg").toString()  );



        btnDelete.setOnMouseMoved(onMouseMove);
        btnDelete.setOnContextMenuRequested(e-> System.out.println("You right-clicked"));

        //EventHandler<ActionEvent>
        btnDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        System.out.println("You clicked Delete");
                    }
                }
        );

    btnEdit.setGraphic(
            new ImageView(
                    new Image(String.valueOf(getClass().getResource("images/edit.png")))
            )
    );
    btnEdit.setOnAction(e->{
        cameraToEditIndex = comboBox.getSelectionModel().getSelectedIndex();
       if(cameraToEditIndex > -1){

           make.setText(cameraList.get(cameraToEditIndex).getMake());
           model.setText(cameraList.get(cameraToEditIndex).getModel());
           color.setText(cameraList.get(cameraToEditIndex).color);
           name.setText(cameraList.get(cameraToEditIndex).name);
           numPhotos.setValue(cameraList.get(cameraToEditIndex).getNumberOfPhotos());
           photo.setImage(
                   new Image(cameraList.get(cameraToEditIndex).getImagePath())
           );

           int cameraToEditImagePathIndex = 0;
           String cameraToEditImagePathString = "";
           for(int i = 0; i < imagesPathString.size(); i++){
               if(imagesPathString.get(i).equals(cameraToEditImagePathString)){
                   cameraToEditImagePathIndex = i;
                   break;
               }
           }
           imagesPathSelectedIndex = cameraToEditImagePathIndex;

       }
    });

    btnDelete.setOnAction(e->{
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Are you sure that you want to delete Camera with name " +
                cameraList.get(selectedIndex).name + " ?" );
        Optional<ButtonType> value = alert.showAndWait();
        if(value.isPresent() && value.get().equals(ButtonType.OK)){
            cameraList.remove(selectedIndex);
            comboBox.getItems().remove(selectedIndex);
            comboBox.getSelectionModel().selectFirst();
        }
    });
     }

     @FXML
    void onChange(ActionEvent e){

        int index = comboBox.getSelectionModel().getSelectedIndex();

        output.setText( cameraList.get(index).toString() );

        photoRight.setImage(
                new Image(cameraList.get(index).getImagePath())
        );
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
