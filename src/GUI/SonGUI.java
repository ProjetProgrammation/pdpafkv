package GUI;

import BDD.Audio;
import Controller.SelectMedia;
import java.io.File;
import java.util.ArrayList;
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
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Jeremy
 */
public class SonGUI extends Parent {
       
    private final SelectMedia selMedia;
    private Audio audioSelected;

    public SonGUI(SelectMedia selectMedia){     
        this.selMedia = selectMedia;
        this.launchSonGUI();       
    }

    private void launchSonGUI(){
        
        //Création + personnalisation FlowPane
        FlowPane fond_son = new FlowPane();
        fond_son.getStyleClass().add("box");
        //fond_son.setVgap(8);
        //fond_son.setHgap(4);
        fond_son.autosize();
        //fond_son.setPrefWrapLength(300);
        //fond_son.setPadding(new Insets(30, 24, 30, 24));
        //fond_son.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        fond_son.setAlignment(Pos.CENTER);

        //Création Button Play
        final Button playSound = new Button("Preview voice");
        playSound.setDisable(true);
                
        GridPane zoneSon = new GridPane();
        final ToggleGroup groupAudio = new ToggleGroup();
        ArrayList<RadioButton> listRB = new ArrayList<>();
        for (int i=0; i<10; i++){
            //Sélection d'un audio
            Audio audioTmp = this.selMedia.selectAudio();
            //Création RadioButton avec son texte
            RadioButton tmpRB = new RadioButton("Voice "+(i+1));
            //Ajout de l'objet audio dans tmpRB
            tmpRB.setUserData(audioTmp);
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
                    audioSelected = (Audio)groupAudio.getSelectedToggle().getUserData();
                }
            }
        });
        
        //Action du bouton
        playSound.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                    playAction(audioSelected);                   
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
    // Fonction pour jouer le fichier Audio
    private void playAction(Audio audio){
        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem(audio.getFilePath()));
        AudioPlayer.load(f.getAbsolutePath());
    } 
    
    public Audio getAudioSelected(){
        return audioSelected;
    }
    
    //Fonction pour l'extraction
    /*public String audio(){
        String audio  ="";
        Pattern p = Pattern .compile("\'.*\'");
        Matcher m = p.matcher(groupAudio.getSelectedToggle().toString());
        while (m.find()){
             audio = m.group();
        }   
        return audio;
    }*/
}
