/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.*;
import Result.*;

import javafx.scene.text.Text;

/**
 *
 * @author guillaume21
 */
public class MediaSelected {
    
    Answers answers;
    
    public MediaSelected(User userSel,Language langSel){
        this.answers = new Answers(userSel, langSel);
    }
    
    public void addResultVideo(Video videoSelected){
        this.answers.addVideo(videoSelected);
    }   
    public void addResultAudio (Audio audioSelected){
        this.answers.addAudio(audioSelected);
    }
    public void addResultQuestion (Question questionSelected){
        this.answers.addQuestion(questionSelected);
    }
}
