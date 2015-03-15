/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.DataBase;
import BDD.Language;
import BDD.Question;
import Controller.SelectMedia;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Jeremy
 */
public class QuestionGUI extends Parent {

    private Text texte_entier = new Text();
    private SelectMedia selMedia;
    
    public QuestionGUI(SelectMedia select){
        this.selMedia = select;
        this.launchQuest();
    }
    
    private void launchQuest(){
        
        
        FlowPane fond_question = new FlowPane();
        fond_question.setVgap(8);
        fond_question.setHgap(4);
        fond_question.setPrefWrapLength(300);
        fond_question.setPadding(new Insets(15, 12, 15, 12));
        fond_question.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        fond_question.setAlignment(Pos.CENTER);


        //Question
        /*if (questions.size() == 0){
           texte_entier.setText("rien"); 
        }
        else{
             texte_entier.setText(select.SelectQuestion(questions).getContent());  
        }*/
        
        texte_entier.setText(this.selMedia.SelectQuestion().getContent());
        
        texte_entier.setFont(new Font(30));
        texte_entier.setFill(Color.GREY);
        
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-45.0);
        Lighting li = new Lighting();
        li.setLight(light);
        texte_entier.setEffect(li);
        
        
        fond_question.getChildren().add(texte_entier);//ajout du texte a la zone
        this.getChildren().add(fond_question);
    }
    
    public  Text getText(){
        return texte_entier;
    }
}
