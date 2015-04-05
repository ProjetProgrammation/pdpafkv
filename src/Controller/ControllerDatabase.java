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
public final class ControllerDatabase {
    
    public ControllerDatabase(){
        DataBase.initiateDataBase();
    }
    
    public ArrayList getLanguageList(){
        return(DataBase.getAllLanguages());
    }
    
    public Audio manageAudio(Language language){
        return(DataBase.manageAudio(language));
    }
    
    public Video manageVideo(Language language){
        return(DataBase.manageVideo(language));
    }
    
    public Question manageQuestion(Language language){
        return(DataBase.manageQuestion(language));
    }
    
    public int countAudio(int idLanguage){
        return(DataBase.countAudio(idLanguage));
    }
    
    public int countVideo(int idLanguage){
        return(DataBase.countVideo(idLanguage));
    }
    
    public int countQuestion(int idLanguage){
        return(DataBase.countAudio(idLanguage));
    }
    
    public Video searchVideoById (int id){
        return(DataBase.searchVideoById(id));
    }
    
    public Audio searchAudioById (int id){
        return(DataBase.searchAudioById(id));
    }
}
