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

    public Question getQuestSel() {
        return questSel;
    }

    public Video getVideoSel() {
        return videoSel;
    }

    public Audio getAudioSel() {
        return audioSel;
    }

    
    
    
}