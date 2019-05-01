import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SubStages {
    private StartingStage startingStage = new StartingStage();
    private String word;
    private int score;
    private Stage pauseScreen = new Stage();
    private Stage endStage = new Stage();
    private VBox lastDisplay = new VBox();
    private Label gameOver = new Label();
    private Label finalScore = new Label();
    private Label displayWord = new Label();
    private Button categorySelect = new Button("Title Screen");
    private Button quit = new Button("quit");


    void showIntro() {
        Stage introStage = new Stage();
        VBox introBox = new VBox();
        introStage.setTitle("Rules and Intro");
        RulesAndIntro intro = new RulesAndIntro();
        Label label = new Label(intro.printIntroduction());
        label.setStyle("-fx-text-fill: white;" + "-fx-border-width: 2;");
        introBox.setAlignment(Pos.TOP_CENTER);
        introBox.getChildren().add(label);
        introBox.setStyle("-fx-background-color:#1350ad");
        introStage.setResizable(false);
        introStage.setScene(new Scene(introBox,300,110));
        introStage.show();
    }

    void showGameRules() {
        Stage introStage = new Stage();
        VBox introBox = new VBox();
        introStage.setTitle("Rules");
        RulesAndIntro intro = new RulesAndIntro();
        Label label = new Label(intro.printRules());
        label.setStyle("-fx-text-fill: white;" + "-fx-border-width: 2;");
        introBox.setAlignment(Pos.TOP_CENTER);
        introBox.getChildren().add(label);
        introBox.setStyle("-fx-background-color:#1350ad");
        introStage.setResizable(false);
        introStage.setScene(new Scene(introBox,300,150));
        introStage.show();
    }

    public void togglePauseScreen(Boolean isPause, Button pause) {
        if (!isPause) {
            VBox pauseBox = new VBox();
            HBox pauseButtons = addPauseStageButtons(pause);
            pauseButtons.setAlignment(Pos.CENTER);
            pauseButtons.setSpacing(10);
            pauseBox.setSpacing(20);
            pauseBox.setStyle("-fx-background-color: #9193f7");
            pauseBox.setAlignment(Pos.CENTER);
            pauseBox.getChildren().add(pauseButtons);
            pauseScreen.setTitle("PAUSED");
            pauseScreen.setResizable(false);
            pauseScreen.setScene(new Scene(pauseBox,400,100));
            pause.setText("Resume");
            pauseScreen.show();
        } else {
            pauseScreen.close();
            pause.setText("Pause Game");
        }

    }

    private HBox addPauseStageButtons(Button pause) {
        setStyles();
        HBox pauseButtons = new HBox();
        buttonActions();
        pauseButtons.getChildren().addAll(pause,categorySelect,quit);
        return pauseButtons;
    }

    public void showEndScreen(String word, int score) {
        this.word = word;
        this.score = score;
        lastDisplay.setSpacing(10);
        endScreenStyle();
        endStage.setResizable(false);
        endStage.setScene(new Scene(lastDisplay,325,150));
        endStage.show();
    }

    private void endScreenStyle() {
        HBox endButtonBox = new HBox();
        buttonActions();
        VBox lastDisplay = setStyles();
        endButtonBox.setSpacing(15);
        endButtonBox.setAlignment(Pos.CENTER);
        endButtonBox.getChildren().addAll(categorySelect,quit);
        lastDisplay.getChildren().addAll(gameOver,displayWord,finalScore,endButtonBox);
        endStage.setTitle("GAME OVER");
    }

    private VBox setStyles() {
        gameOver.setText("GAME OVER, THE WORD:");
        finalScore.setText("Final Score: " + score);
        displayWord.setText(word);
        gameOver.setFont(Font.font("Times New Roman", 20));
        gameOver.setMaxWidth(Double.MAX_VALUE);
        gameOver.setAlignment(Pos.CENTER);
        gameOver.setStyle("-fx-background-color: #9193f7");
        displayWord.setFont(Font.font("Times New Roman", 20));
        displayWord.setMaxWidth(Double.MAX_VALUE);
        displayWord.setAlignment(Pos.CENTER);
        displayWord.setStyle("-fx-background-color: #9193f7");
        finalScore.setFont(Font.font("Times New Roman", 20));
        finalScore.setMaxWidth(Double.MAX_VALUE);
        finalScore.setAlignment(Pos.CENTER);
        finalScore.setStyle("-fx-background-color: #9193f7");
        lastDisplay.setStyle("-fx-background-color: #9193f7");
        categorySelect.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        quit.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        return lastDisplay;
    }

    private void buttonActions() {
        quit.setOnAction(event -> {{
            pauseScreen.close();
            endStage.close();
        }});
        categorySelect.setOnAction(event -> {{
            startingStage.start(new Stage());
            pauseScreen.close();
            endStage.close();
        }});
    }
}
