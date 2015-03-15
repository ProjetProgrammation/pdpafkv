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
    private DataBase db;
    private Language langSel;

    public SelectMedia(DataBase db, Language langSel) {
        this.db = db;
        this.langSel = langSel;
        this.SelectAudioList();
        this.SelectQuestionList();
    }
      
    private void SelectAudioList(){
        Audio audio = null;
        this.listAudio = new ArrayList<>();           
        do{
            boolean check = false;
            audio = this.db.manageAudio(this.langSel);
            for (int i = 0; i < this.listAudio.size();i++){
                if (audio.getFilePath().equals(this.listAudio.get(i).getFilePath())) {
                  check = true;  
                }    
            }
            if (check == false){
                this.listAudio.add(audio);
            }
            
        }while(this.listAudio.size() != this.db.CountAudio(this.langSel.getId()));
    }
    
    public Audio SelectAudio(){
        Audio audio = null;
        Random r = new Random();
        int random = r.nextInt(this.listAudio.size());
        audio = this.listAudio.get(random);
        //this.listAudio.remove(audio);
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
        

    private void SelectQuestionList(){
        Question question = null;
        this.listQuestion = new ArrayList<>();
         do{
            boolean check = false;
            question = this.db.manageQuestion(this.langSel);
            for (int i = 0; i < this.listQuestion.size();i++){
                if (question.getContent().equals(this.listQuestion.get(i).getContent())) {
                  check = true;  
                }    
            }
            if (check == false){
                this.listQuestion.add(question);
            }
         }while(this.listQuestion.size() != this.db.CountQuestion(this.langSel.getId()));
         
    }


    public Question SelectQuestion(){
        Question question = null;
        Random r = new Random();
        int random = r.nextInt(this.listQuestion.size());
        question = this.listQuestion.get(random);
        //this.listQuestion.remove(question);
        return question ;
    }

}


