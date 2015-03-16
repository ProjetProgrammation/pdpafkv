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
        this.selectAudioList();
        this.selectQuestionList();
        this.selectVideoList();
    }
      
    private void selectAudioList(){
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
        }while(this.listAudio.size() != this.db.countAudio(this.langSel.getId()));
    } 
    private void selectVideoList(){
        Video video = null;
        this.listVideo = new ArrayList<>();           
        do{
            boolean check = false;
            video = this.db.manageVideo(this.langSel);
            for (int i = 0; i < this.listVideo.size();i++){
                if (video.getFilePath().equals(this.listVideo.get(i).getFilePath())) {
                  check = true;  
                }    
            }
            if (check == false){
                this.listVideo.add(video);
            }       
        }while(this.listVideo.size() != this.db.countVideo(this.langSel.getId()));
    }        
    private void selectQuestionList(){
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
         }while(this.listQuestion.size() != this.db.countQuestion(this.langSel.getId()));
         
    }

    //Permet de choisir un fichier audio dans la liste précédemment créé
    public Audio selectAudio(){
        Audio audio = null;
        Random r = new Random();
        int random = r.nextInt(this.listAudio.size());
        audio = this.listAudio.get(random);
        //this.listAudio.remove(audio);
        return audio ;
    }
    public Video selectVideo(){
        Video video = null;
        Random r = new Random();
        int random = r.nextInt(this.listVideo.size());
        video = this.listVideo.get(random);
        //this.listVideo.remove(video);
        return video;
    }    
    public Question selectQuestion(){
        Question question = null;
        Random r = new Random();
        int random = r.nextInt(this.listQuestion.size());
        question = this.listQuestion.get(random);
        //this.listQuestion.remove(question);
        return question ;
    }

}


