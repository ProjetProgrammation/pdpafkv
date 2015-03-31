
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
        this.mediaSel = mediaSelected;
        this.launchTestGUI();
    }
    
     public TestGUI(Stage primaryStage, int nbQuest, int numCourant, SelectMedia selectMedia, MediaSelected mediaSelected,Language language,User userSel, ControllerDatabase controller ) {
        this.stage = primaryStage;
        this.nbQuestion = nbQuest;
        this.db = controller;
        this.language = language;
        this.userSel = userSel;
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
        Label title2 = new Label("Prosodic Adventure train".toUpperCase());
        
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
        title2.getStyleClass().add("label-header");
        
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
                if ((video.getVideoSelected() != null) && (son.getAudioSelected() != null)) {
                    if ((currentQuestionNumber < nbQuestion) && nbQuestion == 5) {
                        currentQuestionNumber++;
                        mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                        new TestGUI(stage,nbQuestion,currentQuestionNumber,selMedia,userSel, mediaSel);
                    }else if ((currentQuestionNumber == nbQuestion) && nbQuestion == 3) {
                        System.out.println("[TestGUI]End of train");
                        new ChooseGUI(stage, language, db, userSel);
                    }else if ((currentQuestionNumber < nbQuestion) && nbQuestion == 3) {
                        currentQuestionNumber++;
                        new TestGUI(stage, nbQuestion, currentQuestionNumber,selMedia,mediaSel,language, userSel,db);
                    }else if ((currentQuestionNumber == nbQuestion) && nbQuestion == 5) {
                        System.out.println("[TestGUI]End of test");
                        mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                        Extract.Extract(mediaSel);
                        new EndGUI(stage);
                    }
                }
            }
        });

        mix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO Mix du son et de la vidéo
                //MediaPlayer.load("E:\\Document\\NetBeansProject\\ProjectProg\\Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4");
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
