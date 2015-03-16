/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.*;
import Result.*;
import java.util.ArrayList;

import javafx.scene.text.Text;

/**
 *
 * @author guillaume21
 */
public class MediaSelected {
    
    private User userSel;
    private Language langSel;
    private ArrayList<Answer> answersList;

    public MediaSelected(User userSel, Language langSel) {
        this.userSel = userSel;
        this.langSel = langSel;
        this.answersList = new ArrayList<>();
    }
    
    public void addAnswer(Answer answerToAdd){
        this.answersList.add(answerToAdd);
    }

    public User getUserSel() {
        return userSel;
    }

    public Language getLangSel() {
        return langSel;
    }
    
    public ArrayList<Answer> getAnswersList() {
        return answersList;
    }

    
}
