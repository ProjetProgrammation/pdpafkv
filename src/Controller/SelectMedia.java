/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.*;
import java.util.ArrayList;

/**
 *
 * @author Thibaut
 */
public class SelectMedia {
    
    private ArrayList<Audio> listAudio;
    private ArrayList<Video> listVideo;
    private Question quest;
    private DataBase db;
    private Language langSel;

    public SelectMedia(DataBase db, Language langSel) {
        this.db = db;
        this.langSel = langSel;
    }
      
    public Audio SelectAudio(){
        Audio audio = null;
        this.listAudio = new ArrayList<>();
        
        do{
            audio = db.manageAudio(this.langSel);
            
        }while(this.listAudio.contains(audio)==false);
        
        if (this.listAudio.contains(audio)==false)
            this.listAudio.add(audio);
        
        return audio;
    }
    
    public Video SelectVideo(){
        Video video = null;
        this.listVideo = new ArrayList<>();
        
        do{
            video = this.db.manageVideo(this.langSel);
            
        }while(this.listVideo.contains(video)==false);
        
        if (this.listVideo.contains(video)==false)
            this.listVideo.add(video);
        
        return video;
    }
        
    public Question SelectQuestion(){
        Question question = null;
        this.listAudio = new ArrayList<>();
        
        //question = this.db.manageQuestion(this.langSel.getName());
        
        return question;
    }
    
    
}
