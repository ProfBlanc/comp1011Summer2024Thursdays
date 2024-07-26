package ca.georgiancollege.comp1011summer2024thursdays;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DogController {

    @FXML
    TextField status;

    @FXML
    ImageView message;

    @FXML
    Label counter;

    @FXML
    Button go, stop;

    @FXML
    Slider seconds;

    @FXML
    void getRandomImage(){
        DogCeoAPI api = new DogCeoAPI();
        Dog dog = api.getRandomImage();

        status.setText(dog.getStatus());

        message.setImage(
                new Image(dog.getMessage())
        );

        System.out.println(dog);

        updateCounter();
    }

    @FXML
    void updateCounter(){

        int value = Integer.parseInt(counter.getText());
        value++;
        counter.setText(value + "");


    }
    @FXML
    void initialize(){
        getRandomImage();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        go.setOnAction(event -> {

            double numMillisconds = seconds.getValue();
            int numMS = (int) numMillisconds * 1000;
            //ExecutorService || ScheduledExecutorSevers
            //objects that manage tasks

            Runnable task = () -> getRandomImage();

            service.scheduleAtFixedRate(task, 0, numMS, TimeUnit.MILLISECONDS);

        });

        stop.setOnAction(e->service.shutdownNow());

    }
}
