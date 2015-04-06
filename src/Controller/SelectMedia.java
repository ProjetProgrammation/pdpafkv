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
 * Allows interfaces to choose media.
 *
 * @author Thibaut
 */
public class SelectMedia {

    private ArrayList<Audio> listAudio;
    private ArrayList<Audio> listAudioTest;
    private ArrayList<Video> listVideo;
    private ArrayList<Video> listVideoTest;
    private ArrayList<Question> listQuestion;
    private ControllerDatabase db;
    private Language langSel;
    private boolean problemDB=false;

    /**
     * Constructs a SelectMedia object.
     *
     * @param db The BDD which contains media.
     * @param langSel The language choose by the user.
     */
    public SelectMedia(ControllerDatabase db, Language langSel) {
        this.db = db;
        this.langSel = langSel;
        this.selectAudioList();
        this.selectQuestionList();
        this.selectVideoList();
    }

    /**
     *
     * List of audio files which contains all differents audio files of the
     * database.
     */
    public void selectAudioList() {
        Audio audio;
        this.listAudioTest = new ArrayList<>();
        this.listAudio = new ArrayList<>();
        //Fill listAudio with all Audio in db
        do {
            audio = this.db.manageAudio(this.langSel);
            //if Audio selected is not inside the list so we add it
            if (!this.listAudio.contains(audio)){
                this.listAudio.add(audio);
            }
        } while (this.listAudio.size() != this.db.countAudio(this.langSel.getId())&&audio.getId()!=0);
        //if no Audio in db, so problem = true
        if (audio.getId()==0){
            System.out.println("[selectAudioList]None audio in your database");
            problemDB=true;
        }
    }

    /**
     *
     * List of video files which contains all differents video files of the
     * database.
     */
    public void selectVideoList() {
        Video video;
        this.listVideoTest = new ArrayList<>();
        this.listVideo = new ArrayList<>();
        //Fill listVideo with all Video in db
        do {
            video = this.db.manageVideo(this.langSel);
            //if Video selected is not inside the list so we add it
            if (!this.listVideo.contains(video)){
                this.listVideo.add(video);
            }
        } while (this.listVideo.size() != this.db.countVideo(this.langSel.getId())&&video.getId()!=0);
        //if no Video in db, so problem = true
        if (video.getId()==0){
            System.out.println("[selectVideoList]None video in your database");
            problemDB=true;
        }   
    }

    /**
     *
     * List of questions which contains all differents questions of the
     * database.
     */
    public void selectQuestionList() {
        Question question = null;
        this.listQuestion = new ArrayList<>();
        //Fill listQuestion with all Video in db
        do {
            question = this.db.manageQuestion(this.langSel);
            //if Question selected is not inside the list so we add it.
            if(!this.listQuestion.contains(question)){
                this.listQuestion.add(question);
            }
        } while (this.listQuestion.size() != this.db.countQuestion(this.langSel.getId())&&question.getId()!=0);
        //if no Question in db, so problem = true
        if (question.getId()==0){
            System.out.println("[selectQuestionList]None question in your database");
            problemDB=true;
        }
    }
    
    public ArrayList<Question> getQuestionsList(){
        return listQuestion;
    }
    
     public ArrayList<Audio> getAudioList(){
        return listAudio;
    }
     
      public ArrayList<Video> getVideoList(){
        return listVideo;
    }

    /**
     *
     * Choose an audio file in the list.
     *
     * @return Audio
     */
    public Audio selectAudio() {
        Audio audio = null;
        if(this.listAudioTest.isEmpty()){
            Random r = new Random();
            int random = r.nextInt(this.listAudio.size());
            audio = this.listAudio.get(random);
        }
        else{
            do{
                Random r = new Random();
                int random = r.nextInt(this.listAudio.size());
                audio = this.listAudio.get(random);
            }while(this.listAudioTest.contains(audio));
        }
        this.listAudioTest.add(audio);
        return audio;
    }

    /**
     *
     * Choose a video file in the list.
     *
     * @return Video
     */
    public Video selectVideo() {
        Video video = null;
        if (this.listVideoTest.isEmpty()){
            Random r = new Random();
            int random = r.nextInt(this.listVideo.size());
            video = this.listVideo.get(random);
        }
        else{
            do{
                Random r = new Random();
                int random = r.nextInt(this.listVideo.size());
                video = this.listVideo.get(random);
            }while(this.listVideoTest.contains(video));
        }
        this.listVideoTest.add(video);
        return video;
    }

    /**
     *
     * Choose a question in the list.
     *
     * @return Question
     */
    public Question selectQuestion() {
        Question question = null;
        Random r = new Random();
        int random = r.nextInt(this.listQuestion.size());
        question = this.listQuestion.get(random);
        this.listQuestion.remove(question);
        return question;
    }
    
    /**
     * 
     * Clear lists of audio and video for the next test.
     */
    public void listAudioVideoZero(){
        this.listAudioTest.clear();
        this.listVideoTest.clear();
    }

     /**
     *
     * Return boolean value.
     * if true, the db is wrong.
     *
     * @return boolean
     */
    public boolean isProblemDB() {
        return problemDB;
    }

    /**
     * 
     * Get the ControllerDatabase
     * @return ControllerDatabase
     */
    public ControllerDatabase getDb() {
        return db;
    }
    
    
}
