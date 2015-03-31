/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

import BDD.*;

/**
 * Retrieve the question, the audio and video file choose for the question.
 *
 * @author Thibaut
 */
public class Answer {

    private Question questSel;
    private Video videoSel;
    private Audio audioSel;

    /**
     * Constructs a Answer object.
     *
     * @param questSelected Question's Video expected.
     * @param videoSelected Question's Audio expected.
     * @param audioSelected Question's Language.
     */
    public Answer(Question questSelected, Video videoSelected, Audio audioSelected) {
        this.audioSel = audioSelected;
        this.questSel = questSelected;
        this.videoSel = videoSelected;
    }

    /**
     *
     * Get the question selected.
     *
     * @return Question
     */
    public Question getQuestSel() {
        return questSel;
    }

    /**
     *
     * Get the video selected.
     *
     * @return Video
     */
    public Video getVideoSel() {
        return videoSel;
    }

    /**
     *
     * Get the audio selected.
     *
     * @return Audio
     */
    public Audio getAudioSel() {
        return audioSel;
    }

}
