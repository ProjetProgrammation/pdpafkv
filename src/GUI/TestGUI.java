package GUI;

import BDD.DataBase;
import BDD.Language;
import Result.User;
import Controller.MediaSelected;
import Controller.SelectMedia;
import Result.Answer;
import Result.Extract;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Interface for the real test
 *
 * @author Jeremy
 */
public class TestGUI extends Parent {

    private User userSel;
    private int currentQuestionNumber = 1;
    private int nbQuestion;
    private final Stage stage;
    private MediaSelected mediaSel;
    private SelectMedia selMedia;
    private Language language;
    private DataBase db;

    /**
     * Constructs a new Scene with the test template.
     *
     * @param stage The interface's stage.
     * @param nbQuestion Number of questions. 
     * @param language User's choice of language.
     * @param db The BDD which contains media.
     * @param user The user who run the application
     */
    public TestGUI(Stage stage, int nbQuestion, Language language, DataBase db, User user) {
        this.stage = stage;
        this.nbQuestion = nbQuestion;
        this.db = db;
        this.language = language;
        this.mediaSel = new MediaSelected(user, this.language);
        this.selMedia = new SelectMedia(this.db, this.language);
        this.launchTestGUI();
    }

    /**
     * 
     * Constructs a new Scene with the test template.
     * 
     * @param stage The stage of the interface.
     * @param nbQuestion The number of questions.
     * @param currentQuestionNumber Current question number.
     * @param selMedia The current entity of selectMedia.
     * @param userSel The user who run the application.
     */
    public TestGUI(Stage stage, int nbQuestion, int currentQuestionNumber, SelectMedia selMedia, User userSel) {
        this.stage = stage;
        this.nbQuestion = nbQuestion;
        this.currentQuestionNumber = currentQuestionNumber;
        this.selMedia = selMedia;
        this.userSel = userSel;
        this.launchTestGUI();
    }

    /**
     *
     * Constructs a new Scene with the test template.
     * 
     * @param primaryStage The stage of the interface.
     * @param nbQuest The number of questions.
     * @param numCourant Current question number.
     * @param selectMedia The current entity of selectMedia.
     * @param mediaSelected The current entity of mediaSelected.
     */
    public TestGUI(Stage primaryStage, int nbQuest, int numCourant, SelectMedia selectMedia, MediaSelected mediaSelected) {
        this.stage = primaryStage;
        this.nbQuestion = nbQuest;
        this.currentQuestionNumber = numCourant;
        this.selMedia = selectMedia;
        this.mediaSel = mediaSelected;
        this.launchTestGUI();
    }

    private void launchTestGUI() {

        //Components design
        final QuestionGUI question = new QuestionGUI(this.selMedia);
        final SonGUI son = new SonGUI(this.selMedia);
        final VideoGUI video = new VideoGUI(this.selMedia);

        Label title = new Label("Prosodic Adventure".toUpperCase());

        Button mix = new Button("Merge".toUpperCase());
        Button validate = new Button("Next".toUpperCase());
        mix.setDisable(true);
        validate.setDisable(true);

        //Organize the interface
        BorderPane global = new BorderPane();
        GridPane subRoot = new GridPane();
        BorderPane root = new BorderPane();
        HBox questionBox = new HBox(question);
        HBox videoBox = new HBox(video);
        HBox sonBox = new HBox(son);

        //Add style classe
        title.getStyleClass().add("label-header");

        /* TODO
         Barre de progression  
         final ProgressBar pb = new ProgressBar();
         pb.setProgress((this.currentQuestionNumber/this.nbQuestion)F);
         HBox hb = new HBox();
         hb.setSpacing(5);
         hb.setAlignment(Pos.CENTER);
         hb.getChildren().add(pb);
         */
        if ((this.selMedia.selectAudio() != null) && (this.selMedia.selectVideo() != null)) {
            mix.setDisable(false);
            validate.setDisable(false);
        };

        //Managing events
        validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentQuestionNumber++;
                if ((currentQuestionNumber <= nbQuestion) && (nbQuestion == 3)) {
                    mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                    new TestGUI(stage, nbQuestion, currentQuestionNumber, selMedia, mediaSel);
                } else if ((currentQuestionNumber <= nbQuestion) && (nbQuestion == 5)) {
                    new TestGUI(stage, nbQuestion, currentQuestionNumber, selMedia, userSel);
                } else if ((currentQuestionNumber != nbQuestion) && (nbQuestion == 3)) {
                    mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                    Extract.Extract(mediaSel);
                } else if ((currentQuestionNumber != nbQuestion) && (nbQuestion == 5)) {
                    new ChooseGUI(stage, language, db, userSel);
                }
            }
        });

        mix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO Mix du son et de la vidéo
                MediaPlayer.load("E:\\Document\\NetBeansProject\\ProjectProg\\Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4");
            }
        });

        //Organize the interface
        root.setTop(questionBox);
        root.setCenter(subRoot);

        subRoot.add(videoBox, 1, 1);
        subRoot.add(sonBox, 2, 1);
        subRoot.add(mix, 2, 2, 1, 1);
        subRoot.add(validate, 2, 2, 2, 1);
        subRoot.setAlignment(Pos.CENTER);

        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.RIGHT);

        global.setTop(title);
        global.setCenter(root);

        //Add container to the scene
        this.stage.getScene().setRoot(global);

    }
}
