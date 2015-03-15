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
public class Answers {
    
    private final User user;
    private final Language language;
    private ArrayList<Question> questList;
    private ArrayList<Video> videoList;
    private ArrayList<Audio> audioList;


    public Answers(User userSel, Language langSel) {
        this.user = userSel;
        this.language=langSel;
        this.questList=new ArrayList<>();
        this.videoList=new ArrayList<>();
        this.audioList=new ArrayList<>();
    }
    
    //Function to add objects in different List
    public void addVideo(Video video){
        this.videoList.add(video);
    }   
    public void addAudio(Audio audio){
        this.audioList.add(audio);
    }   
    public void addQuestion(Question question){
        this.questList.add(question);
    }

    //Getters for the extraction
    public User getUser() {
        return user;
    }
    public Language getLanguage() {
        return language;
    }
    public ArrayList<Question> getQuestList() {
        return questList;
    }
    public ArrayList<Video> getVideoList() {
        return videoList;
    }

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }
    
    
   
}
