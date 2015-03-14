/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.*;
import java.util.ArrayList;
import java.util.Random;

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
        SelectAudioListe();
    }
      
    public void SelectAudioListe(){
        Audio audio = null;
        this.listAudio = new ArrayList<>();
        
         do{
             boolean check = false;
            audio = db.manageAudio(this.langSel);
            for (int i = 0; i < listAudio.size();i++){
                if (audio.getName().equals(listAudio.get(i).getName())) {
                  check = true;  
                }    
            }
            if (check == false){
                listAudio.add(audio);
            }
         }while(listAudio.size() != db.Count(langSel.getId()));
    }
    
    public Audio SelectAudio(){
        Audio audio = null;
         
        Random r = new Random();
        int random = r.nextInt(listAudio.size());
        audio = listAudio.get(random);
        listAudio.remove(audio);
        return audio ;
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
        
        //question = this.db.manageQuestion(this.langSel);
        
        return question;
    }
    
    
}


