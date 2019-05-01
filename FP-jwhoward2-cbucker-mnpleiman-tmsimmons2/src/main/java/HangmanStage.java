import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

class HangmanStage {
    private SubStages subStages = new SubStages();
    private String currentCategory;
    private String word;
    private boolean checkTimer;
    private boolean isPause = false;
    Integer seconds = 0;
    private Integer totalScore = 0;
    private int firstRun = 0;
    private Stage hangmanStage = new Stage();
    private VBox mainArea = new VBox();
    private HBox imageArea = new HBox();
    private HBox topBar = new HBox();
    private Label timeText = new Label();
    private Label wordText = new Label();
    private Label currentScore = new Label("SCORE: " + totalScore.toString());
    private Timeline time = new Timeline();
    private ImageView imageView = new ImageView(new Image("/Hangman1.png"));
    private Button pause = new Button("Pause Game");
    private final Button aButton = new Button("A");
    private final Button bButton = new Button("B");
    private final Button cButton = new Button("C");
    private final Button dButton = new Button("D");
    private final Button eButton = new Button("E");
    private final Button fButton = new Button("F");
    private final Button gButton = new Button("G");
    private final Button hButton = new Button("H");
    private final Button iButton = new Button("I");
    private final Button jButton = new Button("J");
    private final Button kButton = new Button("K");
    private final Button lButton = new Button("L");
    private final Button mButton = new Button("M");
    private final Button nButton = new Button("N");
    private final Button oButton = new Button("O");
    private final Button pButton = new Button("P");
    private final Button qButton = new Button("Q");
    private final Button rButton = new Button("R");
    private final Button sButton = new Button("S");
    private final Button tButton = new Button("T");
    private final Button uButton = new Button("U");
    private final Button vButton = new Button("V");
    private final Button wButton = new Button("W");
    private final Button xButton = new Button("X");
    private final Button yButton = new Button("Y");
    private final Button zButton = new Button("Z");
    private LetterValue letterValue = new LetterValue();

    void showHangman(Boolean checkTimer, String category) throws IOException {
        this.checkTimer = checkTimer;
        topBar.setSpacing(700);
        currentCategory = category;
        RandomWord randomWord = new RandomWord();
        word = randomWord.getRandomWord(currentCategory);
        hangmanStage.setTitle("Hangman");
        connectionCheck(currentScore);
        pauseAction();
        timerCheck(this.checkTimer);
        if (firstRun == 0){
            hangmanStage.setResizable(false);
            hangmanStage.setScene(new Scene(mainArea,1000,550));
            firstRun = 1;
        }
        hangmanStage.show();
    }

    private void timerCheck(Boolean checkTimer) {
        if (checkTimer) {
            if (firstRun == 0) {
                doTime();
            }
            timeText.setFont(new Font("Times New Roman", 20));
            topBar.getChildren().add(0, timeText);
        }
    }

    private void doTime(){
        KeyFrame frame = new KeyFrame(Duration.seconds(1), event -> {
            seconds--;
            timeText.setText("Time Left: " + seconds.toString());
            if(seconds<=0){
                time.stop();
                hangmanStage.close();
                subStages.showEndScreen(word, totalScore);
            }
        });
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(frame);
        time.play();
    }

    private void pauseAction() {
        pause.setOnAction((ActionEvent value) -> {
            if (!isPause) {
                subStages.togglePauseScreen(isPause, pause);
                time.pause();
                isPause = true;
                seconds = seconds - 5;
                timeText.setText("Time Left: " + seconds.toString());
                hangmanStage.close();
            }
            else{
                subStages.togglePauseScreen(isPause, pause);
                mainArea.getChildren().add(1,pause);
                time.play();
                isPause = false;
                hangmanStage.show();
            }
        });
    }

    private void connectionCheck(Label currentScore) {
        Label connectionStatus = new Label();
        currentScore.setFont(Font.font("Times New Roman", 20));
        if (word != null){
            System.out.println(word);
            initializeWordText(word);
            VBox letterArea = setButtonArea(word);
            topBar.getChildren().add(currentScore);
            mainArea.getChildren().addAll(topBar,pause,imageArea,wordText,letterArea);
            mainArea.setStyle("-fx-background-color:#9193f7");
            mainArea.setSpacing(15);
            imageArea.getChildren().add(imageView);
            setFeatures();
        }
        else {
            connectionStatus.setText("There was no internet connection.\nPlease rerun the program when connected to the internet.");
            mainArea.getChildren().addAll(connectionStatus);
        }
    }

    private void initializeWordText(String word) {
        List<String> urlAndWord = letterValue.createWordLabel("",word);
        wordText.setText(urlAndWord.get(1));
    }

    private VBox setButtonArea(String categoryWord) {
        HBox firstRowLetters = new HBox();
        HBox secondRowLetters = new HBox();
        HBox thirdRowLetters = new HBox();
        VBox letterArea = new VBox();
        firstRowLetters.getChildren().addAll(aButton, bButton, cButton, dButton,
                eButton, fButton, gButton, hButton, iButton, jButton);
        secondRowLetters.getChildren().addAll(kButton, lButton, mButton, nButton, oButton, pButton,
                qButton, rButton, sButton, tButton);
        thirdRowLetters.getChildren().addAll(uButton, vButton,
                wButton, xButton, yButton, zButton);
        firstRowLetters.setAlignment(Pos.CENTER);
        secondRowLetters.setAlignment(Pos.CENTER);
        thirdRowLetters.setAlignment(Pos.CENTER);
        letterArea.getChildren().addAll(firstRowLetters, secondRowLetters, thirdRowLetters);
        setLetterButtonAction(categoryWord);
        return letterArea;
    }

    private void setLetterButtonAction(String categoryWord) {
        setButtonColor();
        aButton.setOnAction(event -> {
            aButton.setStyle("-fx-background-color:#838c93; ");
            aButton.setDisable(true);
            actionOfLetterButtons("A", categoryWord);
        });
        bButton.setOnAction(event -> {
            bButton.setStyle("-fx-background-color:#838c93; ");
            bButton.setDisable(true);
            actionOfLetterButtons("B", categoryWord);
        });
        cButton.setOnAction(event -> {
            cButton.setStyle("-fx-background-color:#838c93; ");
            cButton.setDisable(true);
            actionOfLetterButtons("C", categoryWord);
        });
        dButton.setOnAction(event -> {
            dButton.setStyle("-fx-background-color:#838c93; ");
            dButton.setDisable(true);
            actionOfLetterButtons("D", categoryWord);
        });
        eButton.setOnAction(event -> {
            eButton.setStyle("-fx-background-color:#838c93; ");
            eButton.setDisable(true);
            actionOfLetterButtons("E", categoryWord);
        });
        fButton.setOnAction(event -> {
            fButton.setStyle("-fx-background-color:#838c93; ");
            fButton.setDisable(true);
            actionOfLetterButtons("F", categoryWord);
        });
        gButton.setOnAction(event -> {
            gButton.setStyle("-fx-background-color:#838c93; ");
            gButton.setDisable(true);
            actionOfLetterButtons("G", categoryWord);
        });
        hButton.setOnAction(event -> {
            hButton.setStyle("-fx-background-color:#838c93; ");
            hButton.setDisable(true);
            actionOfLetterButtons("H", categoryWord);
        });
        iButton.setOnAction(event -> {
            iButton.setStyle("-fx-background-color:#838c93; ");
            iButton.setDisable(true);
            actionOfLetterButtons("I", categoryWord);
        });
        jButton.setOnAction(event -> {
            jButton.setStyle("-fx-background-color:#838c93; ");
            jButton.setDisable(true);
            actionOfLetterButtons("J", categoryWord);
        });
        kButton.setOnAction(event -> {
            kButton.setStyle("-fx-background-color:#838c93; ");
            kButton.setDisable(true);
            actionOfLetterButtons("K", categoryWord);
        });
        lButton.setOnAction(event -> {
            lButton.setStyle("-fx-background-color:#838c93; ");
            lButton.setDisable(true);
            actionOfLetterButtons("L", categoryWord);
        });
        mButton.setOnAction(event -> {
            mButton.setStyle("-fx-background-color:#838c93; ");
            mButton.setDisable(true);
            actionOfLetterButtons("M", categoryWord);
        });
        nButton.setOnAction(event -> {
            nButton.setStyle("-fx-background-color:#838c93; ");
            nButton.setDisable(true);
            actionOfLetterButtons("N", categoryWord);
        });
        oButton.setOnAction(event -> {
            oButton.setStyle("-fx-background-color:#838c93; ");
            oButton.setDisable(true);
            actionOfLetterButtons("O", categoryWord);
        });
        pButton.setOnAction(event -> {
            pButton.setStyle("-fx-background-color:#838c93; ");
            pButton.setDisable(true);
            actionOfLetterButtons("P", categoryWord);
        });
        qButton.setOnAction(event -> {
            qButton.setStyle("-fx-background-color:#838c93; ");
            qButton.setDisable(true);
            actionOfLetterButtons("Q", categoryWord);
        });
        rButton.setOnAction(event -> {
            rButton.setStyle("-fx-background-color:#838c93; ");
            rButton.setDisable(true);
            actionOfLetterButtons("R", categoryWord);
        });
        sButton.setOnAction(event -> {
            sButton.setStyle("-fx-background-color:#838c93; ");
            sButton.setDisable(true);
            actionOfLetterButtons("S", categoryWord);
        });
        tButton.setOnAction(event -> {
            tButton.setStyle("-fx-background-color:#838c93; ");
            tButton.setDisable(true);
            actionOfLetterButtons("T", categoryWord);
        });
        uButton.setOnAction(event -> {
            uButton.setStyle("-fx-background-color:#838c93; ");
            uButton.setDisable(true);
            actionOfLetterButtons("U", categoryWord);
        });
        vButton.setOnAction(event -> {
            vButton.setStyle("-fx-background-color:#838c93; ");
            vButton.setDisable(true);
            actionOfLetterButtons("V", categoryWord);
        });
        wButton.setOnAction(event -> {
            wButton.setStyle("-fx-background-color:#838c93; ");
            wButton.setDisable(true);
            actionOfLetterButtons("W", categoryWord);
        });
        xButton.setOnAction(event -> {
            xButton.setStyle("-fx-background-color:#838c93; ");
            xButton.setDisable(true);
            actionOfLetterButtons("X", categoryWord);
        });
        yButton.setOnAction(event -> {
            yButton.setStyle("-fx-background-color:#838c93; ");
            yButton.setDisable(true);
            actionOfLetterButtons("Y", categoryWord);
        });
        zButton.setOnAction(event -> {
            zButton.setStyle("-fx-background-color:#838c93; ");
            zButton.setDisable(true);
            actionOfLetterButtons("Z", categoryWord);
        });
    }

    private void setButtonColor() {
        pause.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        aButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        bButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        cButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        dButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        eButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        fButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        gButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        hButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        iButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        jButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        kButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        lButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        mButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        nButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        oButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        pButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        qButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        rButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        sButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        tButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        uButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        vButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        wButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        xButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        yButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
        zButton.setStyle("-fx-background-color:#1d67a5; " + "-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-text-fill: white;");
    }

    private void actionOfLetterButtons(String letter, String categoryWord) {
        Score score = new Score();
        List<String> urlAndWord = letterValue.createWordLabel(letter,categoryWord);
        wordText.setText(urlAndWord.get(1));
        if (imageView.getImage().getUrl().contains(urlAndWord.get(0))) {
            totalScore += score.addToScore(currentCategory, letter, word);
            currentScore.setText("SCORE: " + totalScore.toString());
        }
        setImageArea(urlAndWord.get(0));
        checkNewGame(categoryWord);
    }

    private void setImageArea(String location) {
        Image image = new Image(location);
        imageView.setImage(image);
        imageArea.getChildren().set(0,imageView);
        if (location.equals("/HangmanEnd.png")){
            time.stop();
            hangmanStage.close();
            subStages.showEndScreen(word, totalScore);
        }
    }

    private void checkNewGame(String categoryWord){
        categoryWord = categoryWord.replaceAll("\\s+","");
        categoryWord = categoryWord.replace("\u00A0","");
        if (wordText.getText().replaceAll("\\s+","").equalsIgnoreCase(categoryWord)) {
            startNewGame();
        }
    }

    private void startNewGame(){
        cleanHangman();
        try{
            showHangman(checkTimer, currentCategory);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void cleanHangman(){
        mainArea.getChildren().clear();
        imageArea.getChildren().clear();
        topBar.getChildren().clear();
        initNewLetterValue();
        enableButtons();
    }

    private void initNewLetterValue(){
        int errors = letterValue.getErrors();
        String pictureLocation = letterValue.getPictureLocation();
        letterValue = new LetterValue();
        letterValue.setPictureLocation(pictureLocation);
        letterValue.setErrors(errors);
    }

    private void enableButtons() {
        aButton.setDisable(false);
        bButton.setDisable(false);
        cButton.setDisable(false);
        dButton.setDisable(false);
        eButton.setDisable(false);
        fButton.setDisable(false);
        gButton.setDisable(false);
        hButton.setDisable(false);
        iButton.setDisable(false);
        jButton.setDisable(false);
        kButton.setDisable(false);
        lButton.setDisable(false);
        mButton.setDisable(false);
        nButton.setDisable(false);
        oButton.setDisable(false);
        pButton.setDisable(false);
        qButton.setDisable(false);
        rButton.setDisable(false);
        sButton.setDisable(false);
        tButton.setDisable(false);
        uButton.setDisable(false);
        vButton.setDisable(false);
        wButton.setDisable(false);
        xButton.setDisable(false);
        yButton.setDisable(false);
        zButton.setDisable(false);
    }

    private void setFeatures() {
        imageArea.setAlignment(Pos.CENTER);
        wordText.setFont(Font.font("Times New Roman", 40));
        wordText.setMaxWidth(Double.MAX_VALUE);
        wordText.setAlignment(Pos.CENTER);
    }
}
