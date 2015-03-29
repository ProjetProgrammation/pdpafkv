/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.Audio;
import BDD.DataBase;
import BDD.Language;
import BDD.Question;
import BDD.Video;
import java.util.ArrayList;

/**
 * Use this class to create a database (if it doesn't exist) and get all languages available.
 * @author alexandre
 */
public class ControllerDatabase {
    
    private DataBase db;
    
    public ControllerDatabase(){
        db = new DataBase();
    }
    
    public ArrayList getLanguageList(){
        return(this.db.getAllLanguages());
    }
    
    public Audio manageAudio(Language language){
        return(this.db.manageAudio(language));
    }
    
    public Video manageVideo(Language language){
        return(this.db.manageVideo(language));
    }
    
    public Question manageQuestion(Language language){
        return(this.db.manageQuestion(language));
    }
    
    public int countAudio(int idLanguage){
        return(this.db.countAudio(idLanguage));
    }
    
    public int countVideo(int idLanguage){
        return(this.db.countVideo(idLanguage));
    }
    
    public int countQuestion(int idLanguage){
        return(this.db.countAudio(idLanguage));
    }
}
