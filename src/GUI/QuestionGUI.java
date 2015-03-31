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
 * !use this class to choose a question for the test interface.
 *
 * @author Jeremy
 */
public class QuestionGUI extends Parent {

    private Question questionSelected;
    private final SelectMedia selMedia;

    /**
     * Loads the media file.
     *
     * @param select The media selected.
     */
    public QuestionGUI(SelectMedia select) {
        this.selMedia = select;
        this.launchQuest();
    }

    /**
     * Launches the Question interface.
     */
    private void launchQuest() {

        // Design for the question
        FlowPane flowQuestion = new FlowPane();
        Label textQuestion = new Label();

        flowQuestion.autosize();
        flowQuestion.setAlignment(Pos.CENTER);

        textQuestion.getStyleClass().add("label-header-2");

       
        this.questionSelected = this.selMedia.selectQuestion();
        textQuestion.setText(this.questionSelected.getContent().toUpperCase());

        flowQuestion.getChildren().add(textQuestion);
        this.getChildren().add(flowQuestion);
    }

    //Returns the question
    public Question getQuestionSelected() {
        return questionSelected;
    }
}
