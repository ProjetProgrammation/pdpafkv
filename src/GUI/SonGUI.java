/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jeremy
 */
public class SonGUI extends Parent {
    
    final URL resource = getClass().getResource("mario.mp3");
    final Media media = new Media(resource.toString());
    final MediaPlayer mediaPlayer = new MediaPlayer(media);      
    
    public SonGUI(){
        this.launchSonGUI();
    }

    private void launchSonGUI(){
        
        //Zone pour le son
        /*Rectangle fond_son = new Rectangle();
        fond_son.setWidth(300);
        fond_son.setHeight(200);
        fond_son.setArcWidth(50);
        fond_son.setArcHeight(50);
        fond_son.setFill(Color.WHITE);
        fond_son.setStroke(Color.BLACK);*/
        
        FlowPane fond_son = new FlowPane();
        fond_son.setVgap(8);
        fond_son.setHgap(4);
        //fond_son.setPrefWrapLength(300);
        fond_son.setPadding(new Insets(15, 12, 15, 12));
        fond_son.setStyle("-fx-background-color: #336699; -fx-border-color: #000000;");

        //Fichier Aucio
        javafx.scene.control.Button b1 = new javafx.scene.control.Button();
        b1.setText("Read");
        
        //Action du bouton
        b1.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                    appuyer();
            }
        });
        
        //création des boutons
        ToggleGroup groupe = new ToggleGroup();
        RadioButton b4 = new RadioButton();
        RadioButton b2 = new RadioButton();
        RadioButton b3 = new RadioButton();
        RadioButton b5 = new RadioButton();
        RadioButton b6 = new RadioButton();
        b4.setToggleGroup(groupe);
        b2.setToggleGroup(groupe);
        b3.setToggleGroup(groupe);
        b5.setToggleGroup(groupe);
        b6.setToggleGroup(groupe);
        b1.setFocusTraversable(false);
        b2.setFocusTraversable(false);
        b3.setFocusTraversable(false);
        b4.setFocusTraversable(false);
        b5.setFocusTraversable(false);
        b6.setFocusTraversable(false);
        b4.setSelected(true);
        
        GridPane zoneSon = new GridPane();
                
        //on ajoute les boutons au layout
        zoneSon.setPrefSize(200, 200);
        zoneSon.add(b1, 0, 0);
        zoneSon.add(b2, 1, 0);
        zoneSon.add(b3, 2, 0);
        zoneSon.add(b4, 0, 1);
        zoneSon.add(b5, 1, 1);
        zoneSon.add(b6, 2, 1);
        zoneSon.setHgap(20);
        zoneSon.setAlignment(Pos.CENTER);
        
        fond_son.getChildren().add(zoneSon);
        this.getChildren().add(fond_son);
        //this.getChildren().add(zoneSon);
   }
         public void appuyer(){
            mediaPlayer.play();
    } 
}