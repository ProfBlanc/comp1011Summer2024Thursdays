package ca.georgiancollege.comp1011summer2024thursdays;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Time;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.*;

public class BallController {

    @FXML
    private ComboBox<String> cbSelect;

    @FXML
    Button btnSubmit, btnPause, btnRestart;

    @FXML
    private Label lblDisplay;

    @FXML
    ProgressBar loading;
    @FXML
    private Label lblTime;

    NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
    Number num;


    private BallDontLieFetchTeams result;

    private BallDontLieAPI api = new BallDontLieAPI();
    private ScheduledExecutorService scheduler, keyboardWait;

    private int searchTextLength;
    private String pastSerchText = "";

    private int API_COUNT = 0;
    @FXML
    private TextField txtSearch;
    private Runnable taskTimer = () -> {

        Platform.runLater(()->{
            try {
                getAndSetTimeValue();
            }
            catch (Exception e){
                System.err.println(e);
            }
        });

    };
    private Runnable taskFetchData = () -> {
        API_COUNT++;
        String endPoint = "";
        if (txtSearch.getText().toLowerCase().startsWith("east")) {
            endPoint = "?conference=East";
        } else if (txtSearch.getText().toLowerCase().startsWith("west")) {
            endPoint = "?conference=West";
        } else {
            endPoint = "?division=" + txtSearch.getText().toUpperCase().charAt(0) + txtSearch.getText().toLowerCase().substring(1);
        }
        result =  api.fetchTeams(endPoint);
        cbSelect.getItems().addAll(
                result.data.stream().map(s -> s.full_name).toList()
        );

    };
    private Runnable taskUpdateProgressBar = ()->{
        try {
            for(int i = 0; i < 20; i++){

                loading.setProgress(loading.getProgress() + 0.05);
                Thread.sleep(100);
            }
            cbSelect.setDisable(false);

            txtSearch.setDisable(false);
            loading.setVisible(false);
            loading.setProgress(0);

        }
        catch (Exception e){
            System.err.println(e);
        }
    };
    private Runnable taskKeyboardTimeout = () -> {

        if(searchTextLength > 0 && txtSearch.getText().length() == searchTextLength
                &&
                !txtSearch.getText().equalsIgnoreCase(pastSerchText)){
            System.out.println("fetch task called");
            pastSerchText = txtSearch.getText();
            runAPICall();

        }
        else{
            System.out.println("Updating search text");
            searchTextLength = txtSearch.getText().length();
        }

    };
    void getAndSetTimeValue() throws Exception{

        nf = NumberFormat.getInstance(Locale.ENGLISH);
        num = nf.parse(lblTime.getText());

        double value = num.doubleValue();
        //add .1
        value *= 1000;
        value += 100;
        value /= 1000;

        //fix precision-error
        int scale = (int) Math.pow(10, 2);
        value =  (double) Math.round(value * scale) / scale;


        String txtValue = value + "";
        int decimalPosition = txtValue.indexOf(".");

        txtValue = txtValue.substring(0, decimalPosition + 2);

        lblTime.setText(txtValue);

    }
    @FXML
    void onChange(ActionEvent event) {

        int selectedIndex = cbSelect.getSelectionModel().getSelectedIndex();
        if(result == null)
            return;

        if(selectedIndex <= 0)
            return;
        selectedIndex--;
        BallTeam selectedTeam = result.data.get(selectedIndex);
        String display = String.format(
                        "     id: %d,\n" +
                        "      conference: %s,\n" +
                        "      division: %s,\n" +
                        "      city: %s,\n" +
                        "      name: %s,\n" +
                        "      full_name: %s,\n" +
                        "      abbreviation: %s",
                        selectedTeam.id,
                selectedTeam.conference,
                selectedTeam.division,
                selectedTeam.city,
                selectedTeam.name,
                selectedTeam.full_name,
                selectedTeam.abbreviation
        );

            lblDisplay.setText(display);


    }

    @FXML
    void onPause(ActionEvent event) {
        btnRestart.setDisable(false);
        btnPause.setDisable(true);
        scheduler.shutdownNow();

    }

    @FXML
    void onRestart(ActionEvent event) {
        btnRestart.setDisable(true);
        btnPause.setDisable(false);
            Platform.runLater(()->runTimer());
    }
    @FXML
    void runTimer(){
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(taskTimer, 0, 100, TimeUnit.MILLISECONDS);

    }
    @FXML
    void initialize(){
        loading.setVisible(false);

        cbSelect.getSelectionModel().selectFirst();
        cbSelect.setDisable(true);
        lblDisplay.setText("");
        lblTime.setText("0");
        btnRestart.setDisable(true);
        runTimer();

        txtSearch.focusedProperty().addListener((observableValue, oldValue, newVal) ->{
            System.out.println("Go Time!");
            if(newVal){
                System.out.println("yes");
                keyboardWait = Executors.newSingleThreadScheduledExecutor();
                keyboardWait.scheduleAtFixedRate(taskKeyboardTimeout, 0, 2500, TimeUnit.MILLISECONDS);
            }
            else{
                System.out.println("no");
                keyboardWait.shutdownNow();
            }
        });

    }

    void runAPICall(){
        txtSearch.setDisable(true);
        loading.setVisible(true);
        loading.setProgress(0);

        cbSelect.getItems().clear();

        new Thread(taskUpdateProgressBar).start();
        //taskUpdateProgressBar.run();
        new Thread(taskFetchData).start();

    }

    @FXML
    void onSubmit(ActionEvent event) {

        runAPICall();

    }
}
