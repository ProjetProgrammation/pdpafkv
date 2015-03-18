package GUI;


import BDD.DataBase;
import BDD.Language;
import Result.User;
import Controller.MediaSelected;
import Controller.SelectMedia;
import Result.Answer;
import Result.Extract;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;


public class TestGUI extends Parent{
        
    private User userSel;
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
    
    public TestGUI(Stage primaryStage,int nbQuest,int numCourant,SelectMedia selectMedia,User user){
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.numEnCours=numCourant;
        this.selMedia=selectMedia;
        this.userSel=user;
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
        
        //Loading Helvetica font
        Font.loadFont(UserGUI.class.getResource("HelveticaNeueLTStd-LtCn.ttf").toExternalForm(), 10);
        
        //Création du titre
        Label title = new Label();
        title.getStyleClass().add("leabel-header");
        
   
        //Création des boutons
        Button mix = new Button("Merge".toUpperCase());
        Button validate = new Button("Next".toUpperCase());
        mix.setDisable(true);
        validate.setDisable(true);
        
        //Organisation générale
        BorderPane global = new BorderPane();
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        
        //slider
        
               
        /*final ProgressBar pb = new ProgressBar();
        pb.setProgress((this.numEnCours/this.nbQuestion)F);
        
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(pb);*/
        
       // BorderPane mainborder = new BorderPane();
        
        //Definition des tailles des colonnes
        //root.setHgap(20);
        //root.setVgap(20);
        
        //root.setPadding(new Insets(20, 20, 20, 20));
        //root.setGridLinesVisible(false);
        //root.setAlignment(Pos.CENTER);
        
        //Creation de la zone pour les boutons mix et valide et slider
        /*GridPane mixValid = new GridPane();
        mixValid.setHgap(20);
        mixValid.setVgap(20);
        mixValid.setPadding(new Insets(20, 20, 20, 20));
        mixValid.add(mix,0,0);
        mixValid.add(validate,1,0);       
        mixValid.setGridLinesVisible(false);*/
        
        if ((this.selMedia.selectAudio()!=null)&&(this.selMedia.selectVideo()!=null)){
            mix.setDisable(false);
            validate.setDisable(false);
        };
        
        //Action bouton Validate
        validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numEnCours ++;
                if((numEnCours<=nbQuestion)&&(nbQuestion==2)){
                    mediaSel.addAnswer(new Answer(question.getQuestionSelected(), video.getVideoSelected(), son.getAudioSelected()));
                    new TestGUI(stage,nbQuestion,numEnCours,selMedia,mediaSel);
                }
                else if((numEnCours<=nbQuestion)&&(nbQuestion==5))
                    new TestGUI(stage,nbQuestion,numEnCours,selMedia,userSel);
                else if ((numEnCours==nbQuestion)&&(nbQuestion==2)){
                    Extract.Extract(mediaSel);
                    System.exit(0);
                }
                else if ((numEnCours==nbQuestion)&&(nbQuestion==5))
                    new ChooseGUI(stage,language,db,userSel);
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
        
        
        
        //Ajout des element à root
        root.add(question, 1, 1);
        root.add(video, 1, 2);
        root.add(son, 3, 2);
        root.add(mix, 2, 3);
        root.add(validate, 3, 3);
        
        //root.add(hb,0,4);
        
        //Positionnement des éléments dans chaque cellule de root
        /*GridPane.setHalignment(question, HPos.RIGHT);
        GridPane.setHalignment(son, HPos.LEFT);   
        GridPane.setHalignment(video, HPos.LEFT);
        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.RIGHT);*/
                
        //Remplissage de global
        global.setTop(title);
        global.setCenter(root);
        
        Scene scene = new Scene(global);       

        //Applying styles
        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("src/GUI/DarkStyle.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());
        
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.setTitle("The Prosodic Adventure");
          
        this.stage.setFullScreenExitHint("");

        this.stage.show();
        
    } 
}
