package GUI;


import BDD.Language;
import Controller.ControllerDatabase;
import Controller.MediaSelected;
import Controller.SelectMedia;
import GUI.MediaPlayer;
import GUI.QuestionGUI;
import GUI.SonGUI;
import GUI.TestGUI;
import GUI.VideoGUI;
import GUI.endTest;
import Result.Answer;
import Result.Extract;
import Result.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guillaume
 */
public class trainGUI {
     private User userSel;
    private int currentQuestionNumber = 1;
    private int nbQuestion;
    private Stage stage;
    private MediaSelected mediaSel;
    private SelectMedia selMedia;
    private Language language;
    private ControllerDatabase db;

    /**
     * Constructs a new Scene with the test template.
     *
     * @param stage The interface's stage.
     * @param nbQuestion Number of questions. 
     * @param language User's choice of language.
     * @param db The BDD which contains media.
     * @param user The user who run the application
     */
    public trainGUI(Stage stage,int nbQuestion, Language language, ControllerDatabase db, User user) {
        this.stage = stage;
        this.db = db;
        this.language = language;
        this.mediaSel = new MediaSelected(user, this.language);
        this.selMedia = new SelectMedia(this.db, this.language);
        this.nbQuestion = nbQuestion;
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
    public trainGUI(Stage primaryStage, int nbQuest, int numCourant, SelectMedia selectMedia, MediaSelected mediaSelected, ControllerDatabase db , Language language) {
        this.stage = primaryStage;
        this.nbQuestion = nbQuest;
        this.db = db;
        this.language = language;
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

        Label title = new Label("Prosodic Adventure Train test".toUpperCase());

        Button mix = new Button("Merge".toUpperCase());
        Button validate = new Button("Next".toUpperCase());

        //Organize the interface
        BorderPane global = new BorderPane();
        GridPane subRoot = new GridPane();
        BorderPane root = new BorderPane();
        HBox questionBox = new HBox(question);
        HBox videoBox = new HBox(video);
        HBox sonBox = new HBox(son);

        //Add style classe
        title.getStyleClass().add("label-header");

        //Barre de progression
        ProgressBar pb = new ProgressBar();
        double progress = (double) 1 / nbQuestion;
        double i = progress * currentQuestionNumber;
        pb.setProgress(i);
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(pb);

        //Managing events
        validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ((video.getVideoSelected() != null)&&(son.getAudioSelected() != null)){
                    
                    if ((currentQuestionNumber < nbQuestion)) {
                        currentQuestionNumber++;   
                        mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                        new trainGUI(stage, nbQuestion, currentQuestionNumber, selMedia, mediaSel, db, language);
                    }else if((currentQuestionNumber == nbQuestion)) {
                        System.out.println("fin test");
                        mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                        Extract.Extract(mediaSel); 
                        new TestGUI(stage, language, db, userSel);
                    } 
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
        subRoot.add(pb, 2, 2, 2, 2);
        subRoot.setAlignment(Pos.CENTER);

        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.RIGHT);

        global.setTop(title);
        global.setCenter(root);

        //Add container to the scene
        this.stage.getScene().setRoot(global);

    }
}
