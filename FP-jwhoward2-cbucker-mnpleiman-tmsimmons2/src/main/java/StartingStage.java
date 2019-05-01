import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartingStage extends Application {
    private int seconds = 60;
    private Stage timeStage = new Stage();
    private HBox categories = new HBox();
    private HBox options = new HBox();
    private VBox area = new VBox();
    private ObservableList<Integer> secondsOptions = FXCollections.observableArrayList
            (30,60, 90, 120);
    private ObservableList<String> easyOptions = FXCollections.observableArrayList
            ("Animals","U.S. States","Colors");
    private ObservableList<String> mediumOptions = FXCollections.observableArrayList
            ("Superpowers","Celebrities");
    private ObservableList<String> hardOptions = FXCollections.observableArrayList
            ("U.S Cities","Countries");
    private final ChoiceBox<String> easyBox = new ChoiceBox<>(easyOptions);
    private final ChoiceBox<String> mediumBox = new ChoiceBox<>(mediumOptions);
    private final ChoiceBox<String> hardBox = new ChoiceBox<>(hardOptions);
    private final ChoiceBox<Integer> timeBox = new ChoiceBox<>(secondsOptions);
    private final Label categoryTitle = new Label("\tEasy\t\t\t\tMedium  \t\t\tHard");
    private final Label timer = new Label("Timer:");
    private final CheckBox timerCheck = new CheckBox();
    private final Button rulesButton = new Button("Show Rules");


    @Override
    public void start(Stage categoryStage) {
        SubStages subStages = new SubStages();
        setBoxes();
        setArea();
        setButtonAction();
        boxListen(categoryStage);
        categoryStage.setScene(new Scene(area, 350,150));
        categoryStage.setResizable(false);
        checkListen();
        categoryStage.show();
        subStages.showIntro();
    }

    private void setBoxes() {
        categories.setSpacing(10);
        categories.setPadding(new Insets(10,10,10,10));
        categories.setStyle("-fx-background-color: #0961e5");
        categories.getChildren().addAll(easyBox, mediumBox, hardBox);
    }

    private void setArea() {
        options.getChildren().addAll(timer, timerCheck, rulesButton);
        options.setSpacing(15);
        area.getChildren().addAll(categoryTitle, categories, options);
        area.setStyle("-fx-background-color:#1350ad");
        categoryTitle.setStyle("-fx-text-fill: white;" + "-fx-border-width: 2;");
        timer.setStyle("-fx-text-fill: white;"+ "-fx-border-width: 2;");
    }
    private void setButtonAction() {
        SubStages subStages = new SubStages();
        rulesButton.setOnAction(event -> subStages.showGameRules());
    }

    private void boxListen(Stage categoryStage)  {
        HangmanStage hangmanStage = new HangmanStage();
        easyBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            categoryStage.close();
            try {
                hangmanStage.showHangman(timerCheck.isSelected(),newValue);
                hangmanStage.seconds = seconds;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        mediumBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            categoryStage.close();
            try {
                hangmanStage.showHangman(timerCheck.isSelected(), newValue);
                hangmanStage.seconds = seconds;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        hardBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            categoryStage.close();
            try {
                hangmanStage.showHangman(timerCheck.isSelected(), newValue);
                hangmanStage.seconds = seconds;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timeBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            seconds = newValue;
            timeStage.close();
        });

    }

    private void checkListen() {
        timerCheck.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue) {
                showTimeScreen();
            }
        });
    }

    private void showTimeScreen(){
        VBox timeDisplay = new VBox();
        timeDisplay.setAlignment(Pos.TOP_CENTER);
        Stage timeStage = chooseTime(timeDisplay);
        timeStage.setResizable(false);
        timeStage.setScene(new Scene(timeDisplay, 325, 150));
        timeStage.show();
    }

    private Stage chooseTime(VBox timeDisplay){
        timeStage.setTitle("Choose Time");
        Label question = new Label("Choose your desired length of game (seconds): ");
        question.setStyle("-fx-text-fill: white;" + "-fx-border-width: 2;");
        timeDisplay.setStyle("-fx-background-color:#1350ad");
        timeDisplay.getChildren().addAll(question, timeBox);
        return timeStage;
    }
}
