/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Parent;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Jeremy
 */
public class QuestionGUI extends Parent {
    private String text;//texte de la question, c'est une variable public pour qu'elle puisse être lue depuis les autres classe
    
    Text texte_entier = new Text();
    
    public QuestionGUI(){
        this.launchQuest();
    }
    
    private void launchQuest(){
        
        //Zone pour la question
        Rectangle fond_question = new Rectangle();
        fond_question.setWidth(800);
        fond_question.setHeight(15);
        fond_question.setArcWidth(50);
        fond_question.setArcHeight(50);
        fond_question.setFill(Color.WHITE);
        fond_question.setStroke(Color.BLACK);
        this.getChildren().add(fond_question);
        
        //Question
        this.text="Ici sera la question";
        this.texte_entier = new Text(text);
        this.texte_entier.setFont(new Font(25));
        this.texte_entier.setFill(Color.GREY);
        this.texte_entier.setX(25);
        this.texte_entier.setY(45);
        
        
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-45.0);
        Lighting li = new Lighting();
        li.setLight(light);
        this.texte_entier.setEffect(li);
        
        
        this.getChildren().add(texte_entier);//ajout de la lettre de la touche
    }
}
