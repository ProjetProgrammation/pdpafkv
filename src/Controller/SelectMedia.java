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
    private ArrayList<Question> listQuestion;
    private Question quest;
    private DataBase db;
    private Language langSel;

    public SelectMedia(DataBase db, Language langSel) {
        this.db = db;
        this.langSel = langSel;
    }
      
    public void SelectAudioListe(){
        Audio audio = null;
        this.listAudio = new ArrayList<>();
                
         do{
            boolean check = false;
            audio = db.manageAudio(this.langSel);
            for (Audio a: listAudio){
                if (audio.getFilePath().equals(a.getFilePath())) {
                  check = true;  
                }    
            }
            if (check == false){
                listAudio.add(audio);
            }
         }while(listAudio.size()<=db.CountAudio(this.langSel.getId()));
    }
    
    public Audio SelectAudio(){
        Audio audio = null;
        this.SelectAudioListe();
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
        

   public void SelectQuestionList(){
        Question question = null;
        listQuestion = new ArrayList<>();

         do{
            boolean check = false;
            question = db.manageQuestion(this.langSel);
            for (int i = 0; i < listQuestion.size();i++){
                if (question.getContent().equals(listQuestion.get(i).getContent())) {
                  check = true;  
                }    
            }
            if (check == false){
                listQuestion.add(question);
            }
         }while(listQuestion.size() != db.CountQuestion(langSel.getId()));
         
         System.out.println(listQuestion.size());
    }


    public Question SelectQuestion(){
            Question question = null;

           Random r = new Random();
           int random = r.nextInt(listQuestion.size());
           question = listQuestion.get(random);
           this.listAudio.remove(question);
           return question ;
       }
    
    public ArrayList<Question> getListeQuestion(){
        return listQuestion;
    }
}


