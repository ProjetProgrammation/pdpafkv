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
 * Allow to choose a question for the test interface
 *
 * @author Jeremy
 */
public class QuestionGUI extends Parent {

    private Question questionSelected;
    private SelectMedia selMedia;

    /**
     * Allow to load the media file
     *
     * @param select allow to load a entity of selectMedia which allow to choose
     * question after in the bdd
     */
    public QuestionGUI(SelectMedia select) {
        this.selMedia = select;
        this.launchQuest();
    }

    /**
     * Launch the question
     */
    private void launchQuest() {

        // Design for the question
        FlowPane flowQuestion = new FlowPane();
        Label textQuestion = new Label();

        flowQuestion.autosize();
        flowQuestion.setAlignment(Pos.CENTER);

        textQuestion.getStyleClass().add("label-header");

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
        textQuestion.setText(this.questionSelected.getContent().toUpperCase());

        flowQuestion.getChildren().add(textQuestion);
        this.getChildren().add(flowQuestion);
        flowQuestion.getStyleClass().add("div1");
    }

    //Allow to return the question
    public Question getQuestionSelected() {
        return questionSelected;
    }
}
