package GUI;


import BDD.DataBase;
import BDD.Language;
import BDD.Question;
import Result.User;
import Controller.MediaSelected;
import Controller.SelectMedia;
import java.io.File;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class TestGUI extends Parent{
        
    
    private int numEnCours = 1;
    private int nbQuestion;
    private final Stage stage;
    private MediaSelected mediaSel;
    private SelectMedia selMedia;
    private Language language;
    private DataBase db;
            
    public TestGUI(Stage primaryStage,int nbQuest,Language langSel,DataBase daba,User user){
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.db=daba;
        this.language=langSel;
        this.mediaSel = new MediaSelected(user, this.language);
        this.selMedia = new SelectMedia(this.db, this.language);
        this.launchTestGUI();
    }
    
    public TestGUI(Stage primaryStage,int nbQuest,int numCourant,SelectMedia selectMedia){
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.numEnCours=numCourant;
        this.selMedia=selectMedia;
        this.launchTestGUI();
    }
    
    public TestGUI(Stage primaryStage,int nbQuest,int numCourant,SelectMedia selectMedia,MediaSelected mediaSelected){
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.numEnCours=numCourant;
        this.selMedia=selectMedia;
        this.mediaSel=mediaSelected;
        this.launchTestGUI();
    }
        
    private void launchTestGUI(){
            
        final QuestionGUI question = new QuestionGUI(this.selMedia);
        final SonGUI son = new SonGUI(this.selMedia);
        final VideoGUI video = new VideoGUI(this.selMedia);
        
   
        //Zone pour les boutons
        Image imageMix = new Image(getClass().getResourceAsStream("mixer8.png"));
        ImageView imageValid = new ImageView(new Image(getClass().getResourceAsStream("confirmation.png")));
        Button mix = new Button("Mix", new ImageView(imageMix));
        Button validate = new Button("Validate", imageValid);
        
        //slider
        
               
        /*final ProgressBar pb = new ProgressBar();
        pb.setProgress((this.numEnCours/this.nbQuestion)F);
        
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(pb);*/
        
       // BorderPane mainborder = new BorderPane();        
        GridPane root = new GridPane();
        
        //Definition des tailles des collones
        root.setHgap(20);
        root.setVgap(20);
        root.prefWidth(100.0);
        
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setGridLinesVisible(false);
        root.setAlignment(Pos.CENTER);
        
        //Creation de la zone pour les bouton mix et valide et slider
        GridPane mixValid = new GridPane();
        mixValid.setHgap(20);
        mixValid.setVgap(20);
        mixValid.setPadding(new Insets(20, 20, 20, 20));
        mixValid.add(mix,0,0);
        mixValid.add(validate,1,0);       
        mixValid.setGridLinesVisible(false);
        
        //Action bouton Validate
        validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numEnCours ++;
                if((numEnCours<=nbQuestion)&&(nbQuestion==20)){
                    mediaSel.addResultQuestion(question.getQuestionSelected());
                    mediaSel.addResultAudio(son.getAudioSelected());
                    new TestGUI(stage,nbQuestion,numEnCours,selMedia,mediaSel);
                }
                else if((numEnCours<=nbQuestion)&&(nbQuestion==5))
                    new TestGUI(stage,nbQuestion,numEnCours,selMedia);
                else
                    System.exit(0);
            }
        });
        
        //Action bouton Mix
        mix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    MediaPlayer.load("E:\\Document\\NetBeansProject\\ProjectProg\\Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4");
            }
        });
        
        
        
        //ajout des element au gridpane
        root.add(question,0,0,3,1);
        root.add(video,0,1,1,3);
        root.add(son,2,1,1,3);
        //root.add(hb,0,4);
        root.add(mixValid,2,4);
        

        //Style GUI
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%,lightgrey, white) ; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2");
        mix.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        validate.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        
        GridPane.setHalignment(question, HPos.CENTER);
        GridPane.setHalignment(son, HPos.CENTER);   
        GridPane.setHalignment(video, HPos.CENTER);
        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.CENTER);
                
        //Scene scene = new Scene(root,1400,800);
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
          
        this.stage.setFullScreenExitHint("");

        this.stage.show();
        
    } 
}
