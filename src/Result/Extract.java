/*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
 */

package Result;

import BDD.*;
import Controller.MediaSelected;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonWriter;
import java.io.FileWriter;

/**
 *
 * @author Thibaut
 */
public class Extract {
    
    public static void Extract(MediaSelected medSelected){
        try{
            JsonWriter json = new JsonWriter(new FileWriter("..\\Result\\"+medSelected.getUserSel().getNameToFile()+"_"+medSelected.getLangSel().getName()+".json"));
            json.beginObject();
            extractLanguageUser(medSelected, json);
            extractAnswers(medSelected, json);
            json.endObject();
            json.close();
        }
        catch(Exception e){
            System.out.println("Error to create JSON file");
        }
    }
    
    private static void extractLanguageUser(MediaSelected medSelected,JsonWriter json){
        try{
            json.name("Language").value(medSelected.getLangSel().getName());
            json.name("User");
            json.beginArray();
            json.name("First Name").value(medSelected.getUserSel().getFirstName());
            json.name("Last Name").value(medSelected.getUserSel().getLastName());
            json.name("Birthday").value(medSelected.getUserSel().getBirthday());
            json.name("Mother Tongue").value(medSelected.getUserSel().getMotherTongue());
            json.name("Years learning tongue selected").value(medSelected.getUserSel().getYearStudying());
            json.endArray();
        }
        catch(Exception e){
               System.out.println("Error to extract tongue and user information to JSON file");
        }
    }
    
    private static void extractAnswers(MediaSelected medSelected,JsonWriter json){
        
        try{
            for(Answer answer:medSelected.getAnswersList()){
                json.name("Answers");
                json.beginArray();
                json.name("Question").value(answer.getQuestSel().getContent());
                json.name("Video").value(answer.getVideoSel().getName());
                json.name("Audio").value(answer.getAudioSel().getName());
                json.endArray();
            }
        }
        catch(Exception e){
            System.out.println("Error to extract answers information to JSON file");
        }
    }
}

