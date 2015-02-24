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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Jeremy
 */
public class QuestionGUI extends Parent {
    public String text;//texte de la question, c'est une variable public pour qu'elle puisse être lue depuis les autres classe
    
    Text texte_entier = new Text();
    
    public QuestionGUI(String l){
        text =  new String(l);
        
        
        texte_entier = new Text(text);
        texte_entier.setFont(new Font(25));
        texte_entier.setFill(Color.GREY);
        texte_entier.setX(25);
        texte_entier.setY(45);
        
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-45.0);
        Lighting li = new Lighting();
        li.setLight(light);
        texte_entier.setEffect(li);
        
        this.getChildren().add(texte_entier);//ajout de la lettre de la touche

}
}
