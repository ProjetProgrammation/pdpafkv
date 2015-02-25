/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jeremy
 */
public class SonGUI extends Parent {
          
    
    public SonGUI(){
        this.launchSonGUI();
    }

    private void launchSonGUI(){
        
        GridPane zoneSon = new GridPane();
        
        //Zone pour le son
        Rectangle fond_son = new Rectangle();
        fond_son.setWidth(300);
        fond_son.setHeight(200);
        fond_son.setArcWidth(50);
        fond_son.setArcHeight(50);
        fond_son.setFill(Color.WHITE);
        fond_son.setStroke(Color.BLACK);

        //création des boutons
        ToggleGroup groupe = new ToggleGroup();
        RadioButton b1 = new RadioButton();
        RadioButton b2 = new RadioButton();
        RadioButton b3 = new RadioButton();
        RadioButton b4 = new RadioButton();
        RadioButton b5 = new RadioButton();
        RadioButton b6 = new RadioButton();
        b1.setToggleGroup(groupe);
        b2.setToggleGroup(groupe);
        b3.setToggleGroup(groupe);
        b4.setToggleGroup(groupe);
        b5.setToggleGroup(groupe);
        b6.setToggleGroup(groupe);
        b1.setFocusTraversable(false);
        b2.setFocusTraversable(false);
        b3.setFocusTraversable(false);
        b4.setFocusTraversable(false);
        b5.setFocusTraversable(false);
        b6.setFocusTraversable(false);
        b1.setSelected(true);
        
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
        
        this.getChildren().add(fond_son);
        this.getChildren().add(zoneSon);
        
        
    }
}