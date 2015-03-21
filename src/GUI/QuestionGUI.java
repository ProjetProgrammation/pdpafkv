/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BDD.Question;
import Controller.SelectMedia;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Jeremy
 */
public class QuestionGUI extends Parent {
    private Question questionSelected;
    private SelectMedia selMedia;
    
    public QuestionGUI(SelectMedia select){
        this.selMedia = select;
        this.launchQuest();
    }
    
    private void launchQuest(){
        
        FlowPane fond_question = new FlowPane();
        fond_question.autosize();
        fond_question.setAlignment(Pos.CENTER);
        
        Label texte_entier = new Label();
        texte_entier.getStyleClass().add("label-header");
        
        //Question
        /*if (questions.size() == 0){
           texte_entier.setText("rien");    
        //If pour savoir si il reste des questions disponibles
        if (questions.isEmpty()){
           texte_entier.setText("Plus de questions disponibles"); 
        }
        else{
             texte_entier.setText(select.SelectQuestion(questions).getContent());  
        }*/
        this.questionSelected = this.selMedia.selectQuestion();        
        texte_entier.setText(this.questionSelected.getContent().toUpperCase());
        
        //ajout du texte a la zone
        fond_question.getChildren().add(texte_entier);
        this.getChildren().add(fond_question);
        fond_question.getStyleClass().add("div1");
    }
    
    //Fonction qui retourne le texte ( Utile ? )
    public  Question getQuestionSelected(){
        return questionSelected;
    }
}
