/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.Audio;
import BDD.DataBase;
import BDD.Language;
import Controller.SelectMedia;
import java.net.URL;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    
    public SonGUI(Language langSel, DataBase db){
        this.launchSonGUI();
        this.controlSM = new SelectMedia(db,langSel);
    }

    private void launchSonGUI(){
        
        //Création + personnalisation FlowPane
        FlowPane fond_son = new FlowPane();
        fond_son.setVgap(8);
        fond_son.setHgap(4);
        fond_son.autosize();
        //fond_son.setPrefWrapLength(300);
        //fond_son.setPadding(new Insets(30, 24, 30, 24));
        fond_son.setStyle("-fx-background-color: #336699; -fx-border-color: #000000;");
        fond_son.setAlignment(Pos.CENTER);

        //Création Button Play
        final Button playSound = new Button("Play Sound");
        playSound.setDisable(true);
                
        GridPane zoneSon = new GridPane();
        final ToggleGroup groupAudio = new ToggleGroup();
        ArrayList<RadioButton> listRB = new ArrayList<>();
        for (int i=0; i<10; i++){
            //Sélection d'un audio
            //Audio audioTmp = this.controlSM.SelectAudio();
            //Création RadioButton avec son texte
            RadioButton tmpRB = new RadioButton("Sound "+i);
            //Ajout de l'objet audio dans tmpRB
            //tmpRB.setUserData(audioTmp);
            tmpRB.setUserData(null);
            //Ajout du tmpRB dans le groupe Toggle
            tmpRB.setToggleGroup(groupAudio);
            //Personnalisation de tmpRB
            tmpRB.setFocusTraversable(false);
            //Ajout tmpRB dans le GridPane
            if (i<5)
                zoneSon.add(tmpRB, i, 0);
            else
                zoneSon.add(tmpRB, i-5, 1);
            listRB.add(tmpRB);
        }
                
        groupAudio.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (groupAudio.getSelectedToggle()!=null){
                    playSound.setDisable(false);
                    System.out.println("ici");
                }
            }
        });
        
        //Action du bouton
        playSound.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
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
        final Media media = new Media(audio.getFilePath());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    } 
}
