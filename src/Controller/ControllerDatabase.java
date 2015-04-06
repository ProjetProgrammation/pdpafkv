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
    
    /**
     * Initialise la DataBase
     */
    public ControllerDatabase(){
        DataBase.initiateDataBase();
    }
    
    /**
     * Return the result of the getLanguageList() of DataBase class
     * @return ArrayList
     */
    public ArrayList getLanguageList(){
        return(DataBase.getAllLanguages());
    }
    
    /**
     * Return the result of the manageAudio() of DataBase class
     * @param language
     * @return Audio
     */
    public Audio manageAudio(Language language){
        return(DataBase.manageAudio(language));
    }
    
    /**
     * Return the result of the manageVideo() of DataBase class
     * @param language
     * @return Video
     */
    public Video manageVideo(Language language){
        return(DataBase.manageVideo(language));
    }
    
    /**
     * Return the result of the manageQuestion() of DataBase class
     * @param language
     * @return Question
     */
    public Question manageQuestion(Language language){
        return(DataBase.manageQuestion(language));
    }
    
    /**
     * Return the result of the countAudio() of DataBase class
     * @param idLanguage Audios idLanguage to search.
     * @return Int
     */
    public int countAudio(int idLanguage){
        return(DataBase.countAudio(idLanguage));
    }
    /**
     * Return the result of the countVideo() of DataBase class
     * @param idLanguage
     * @return 
     */
    public int countVideo(int idLanguage){
        return(DataBase.countVideo(idLanguage));
    }
    /**
     * Return the result of the countQuestion() of DataBase class
     * @param idLanguage
     * @return int
     */
    public int countQuestion(int idLanguage){
        return(DataBase.countAudio(idLanguage));
    }
    
    /**
     * Return the result of the searchVideoById() of DataBase class
     * @param idVideo
     * @return Video
     */
    public Video searchVideoById (int idVideo){
        return(DataBase.searchVideoById(idVideo));
    }
    /**
     * Return the result of the searchAudioById() of DataBase class
     * @param idAudio
     * @return Audio
     */
    public Audio searchAudioById (int idAudio){
        return(DataBase.searchAudioById(idAudio));
    }
}
