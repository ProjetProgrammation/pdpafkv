package GUI;

import BDD.Audio;
import BDD.DataBase;
import BDD.Language;
import Controller.SelectMedia;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jeremy
 */
public class SonGUI extends Parent {
       
    private final SelectMedia controlSM;
    private ToggleGroup groupAudio = new ToggleGroup();
    private Language langue;
    DataBase db ;
    public SonGUI(Language langSel, DataBase db){
       
        this.controlSM = new SelectMedia(db,langSel);
        langue = langSel;
        this.db = db;
         this.launchSonGUI();
        
    }

    private void launchSonGUI(){
        
        SelectMedia select = new SelectMedia(db, langue);
        //Création + personnalisation FlowPane
        FlowPane fond_son = new FlowPane();
        fond_son.setVgap(8);
        fond_son.setHgap(4);
        fond_son.autosize();
        //fond_son.setPrefWrapLength(300);
        //fond_son.setPadding(new Insets(30, 24, 30, 24));
        fond_son.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        fond_son.setAlignment(Pos.CENTER);

        //Création Button Play
        final Button playSound = new Button("Play Sound");
        playSound.setDisable(true);
                
        GridPane zoneSon = new GridPane();
       
        ArrayList<RadioButton> listRB = new ArrayList<>();
        for (int i=0; i<db.Count(langue.getId()); i++){
            //Sélection d'un audio
            //Audio audioTmp = this.controlSM.SelectAudio();
            //Création RadioButton avec son texte
            RadioButton tmpRB = new RadioButton("Sound n°"+(i+1));
            //Ajout de l'objet audio dans tmpRB
            //tmpRB.setUserData(audioTmp);
            
            tmpRB.setUserData(select.SelectAudio());
           // tmpRB.setUserData(null);
            //Ajout du tmpRB dans le groupe Toggle
            tmpRB.setToggleGroup(groupAudio);
            //Personnalisation de tmpRB
            tmpRB.setFocusTraversable(false);
            //Ajout tmpRB dans le GridPane
            if (i<5){
                zoneSon.add(tmpRB, i, 0);
            }
            else
                zoneSon.add(tmpRB, i-5, 2);
            listRB.add(tmpRB);
        }
                
        groupAudio.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (groupAudio.getSelectedToggle()!=null){
                    playSound.setDisable(false);
                }
            }
        });
        
        //Action du bouton
        playSound.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                   //System.out.println(groupAudio.getSelectedToggle().getUserData());
                    playAction((Audio)groupAudio.getSelectedToggle().getUserData());
                    
            }
        });
        
        
        //Personnalisation du GridPane
        zoneSon.autosize();
        zoneSon.setHgap(20);
        zoneSon.setVgap(20);
        zoneSon.setAlignment(Pos.CENTER);
        
        //Ajout à FlowPane
        fond_son.getChildren().add(zoneSon);
        fond_son.getChildren().add(playSound);
        this.getChildren().add(fond_son);
   }
    
    protected void playAction(Audio audio){
        File f = new File(System.getProperty("user.dir"),audio.getFilePath()); 
        
        final Media media = new Media(f.toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    } 
    
    public String audio(){
        String audio  ="";
        Pattern p = Pattern .compile("\'.*\'");
        Matcher m = p.matcher(groupAudio.getSelectedToggle().toString());
        while (m.find()){
             audio = m.group();
        }   
        return audio;
    }
}
