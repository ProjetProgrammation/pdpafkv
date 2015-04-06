
package GUI;

import BDD.Language;
import Controller.ControllerDatabase;
import Result.User;
import Controller.MediaSelected;
import Controller.SelectMedia;
import Result.Answer;
import Result.Extract;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private final int nbQuestion;
    private final Stage stage;
    private final MediaSelected mediaSel;
    private final SelectMedia selMedia;
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
    public TestGUI(Stage stage, int nbQuestion, Language language, ControllerDatabase db, User user) {
        this.stage = stage;
        this.nbQuestion = nbQuestion;
        this.db = db;
        this.language = language;
        this.selMedia = new SelectMedia(this.db, this.language);
        if (this.selMedia.isProblemDB()){
            System.out.println("[TestGUI]Problem whith de database");
            System.exit(0);
        }
        if (nbQuestion == 5)
            this.mediaSel = new MediaSelected(user, this.language);
        else
            this.mediaSel = null;
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
    public TestGUI(Stage stage, int nbQuestion, int currentQuestionNumber, SelectMedia selMedia, User userSel, MediaSelected mediaSelected) {
        this.stage = stage;
        this.nbQuestion = nbQuestion;
        this.currentQuestionNumber = currentQuestionNumber;
        this.selMedia = selMedia;
        this.selMedia.listAudioVideoZero();
        this.userSel = userSel;
        this.mediaSel=mediaSelected;
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
        this.selMedia.listAudioVideoZero();
        this.mediaSel = mediaSelected;
        this.launchTestGUI();
    }
    
     public TestGUI(Stage primaryStage, int nbQuest, int numCourant,SelectMedia selectMedia,Language language,User userSel,ControllerDatabase controller) {
        this.stage = primaryStage;
        this.nbQuestion = nbQuest;
        this.db = controller;
        this.language = language;
        this.userSel = userSel;
        this.currentQuestionNumber = numCourant;
        this.selMedia = selectMedia;
        this.selMedia.listAudioVideoZero();
        this.mediaSel = null;
        this.launchTestGUI();
    }

    private void launchTestGUI() {
        
        //Components design
        final QuestionGUI question = new QuestionGUI(this.selMedia);
        final SonGUI son = new SonGUI(this.selMedia);
        final VideoGUI video = new VideoGUI(this.selMedia);

        Label title = new Label("Prosodic Adventure".toUpperCase());
        Label title2 = new Label("Prosodic Adventure train".toUpperCase());
        final Label videoError = new Label("Please select a video");
        final Label soundError = new Label("Please Select a sound");
        videoError.setVisible(false);
        soundError.setVisible(false);
        
        Button mix = new Button("Merge".toUpperCase());
        Button validate = new Button("Next".toUpperCase());

        //Organize the interface
        BorderPane global = new BorderPane();
        GridPane subRoot = new GridPane();
        BorderPane root = new BorderPane();
        HBox questionBox = new HBox(question);
        final HBox videoBox = new HBox(video);
        final HBox sonBox = new HBox(son);

        //Add style classe
        title.getStyleClass().add("label-header");
        title2.getStyleClass().add("label-header");
        videoError.getStyleClass().add("label-error");
        soundError.getStyleClass().add("label-error");
        
        //Barre de progression
        ProgressBar pb = new ProgressBar();
        pb.getStyleClass().add("progress-bar");        
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
                if ((video.getVideoSelected() != null) && (son.getAudioSelected() != null)) {
                    if ((currentQuestionNumber < nbQuestion) && nbQuestion == 5) {
                        currentQuestionNumber++;
                        mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                        new TestGUI(stage,nbQuestion,currentQuestionNumber,selMedia,userSel,mediaSel);
                    }else if ((currentQuestionNumber == nbQuestion) && nbQuestion == 3) {
                        System.out.println("[TestGUI]End of train");
                        new ChooseGUI(stage, language, db, userSel);
                    }else if ((currentQuestionNumber < nbQuestion) && nbQuestion == 3) {
                        currentQuestionNumber++;
                        new TestGUI(stage, nbQuestion, currentQuestionNumber,selMedia,language,userSel,db);
                    }else if ((currentQuestionNumber == nbQuestion) && nbQuestion == 5) {
                        System.out.println("[TestGUI]End of test");
                        mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                        Extract.Extract(mediaSel,selMedia);
                        new EndGUI(stage);
                    }
                }
                else if ((video.getVideoSelected() == null) && (son.getAudioSelected() == null)){
                    videoError.setVisible(true);
                    soundError.setVisible(true);
                }else if ((video.getVideoSelected() == null) && (son.getAudioSelected() != null)){
                    videoError.setVisible(true);
                    soundError.setVisible(false);
                }else if ((video.getVideoSelected() != null) && (son.getAudioSelected() == null)){
                    videoError.setVisible(false);
                    soundError.setVisible(true);
                }
            }
        });

        mix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO Mix du son et de la vidéo
            }
        });

        //Organize the interface
        questionBox.setAlignment(Pos.CENTER);
        root.setTop(questionBox);
        root.setCenter(subRoot);

        subRoot.add(videoBox, 1, 1);
        subRoot.add(sonBox, 2, 1);
        subRoot.add(mix, 2, 2, 1, 1);
        subRoot.add(validate, 2, 2, 2, 1);
        subRoot.add(pb, 2, 2, 2, 2);
        subRoot.add(videoError, 1, 0);
        subRoot.add(soundError, 2, 0);
        
        subRoot.setAlignment(Pos.CENTER);
        GridPane.setValignment(videoBox, VPos.CENTER);
        GridPane.setValignment(sonBox, VPos.CENTER);
        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.RIGHT);
        
        if(nbQuestion == 3){
             global.setTop(title2);
        }
        else{
             global.setTop(title);
        }
       
        global.setCenter(root);

        //Add container to the scene
        this.stage.getScene().setRoot(global);

    }
}
