/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

import java.util.ArrayList;
import BDD.*;

/**
 *
 * @author Thibaut
 */
public class Answer {
    
    private Question questSel;
    private Video videoSel;
    private Audio audioSel;


    public Answer(Question questSelected, Video videoSelected, Audio audioSelected) {
        this.audioSel=audioSelected;
        this.questSel=questSelected;
        this.videoSel=videoSelected;
    }

    @Override
    public String toString() {
        return "\"Answer\": [\n"
                        + "\"Question asked\": \"" + this.questSel.getContent() + "\",\n"
                        + "\"Video selected\": \"" + this.videoSel.getName() + "\",\n"
                        + "\"Audio selected\": \"" + this.audioSel.getName() + "\"\n"
                + "],";
    }
    
    
}