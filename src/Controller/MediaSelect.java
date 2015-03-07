/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.DataBase;
import BDD.Language;
import GUI.QuestionGUI;
import Result.Extraction;
import javafx.scene.text.Text;

/**
 *
 * @author guillaume21
 */
public class MediaSelect {
    
    DataBase bdd;
    Language langue;
    Extraction extraction = new Extraction("..\\pdpafkv\\src\\Result\\Résultats.txt");
    
    public MediaSelect(DataBase bdd, Language langue){
        
        this.bdd = bdd;
        this.langue = langue;
    }
    
    public void MediaSelect(String nom, String prénom, Text question){
        extraction.DébutExtraction(nom, prénom);
        extraction.ExtractionRéponse(question);
        extraction.finExtraction();
    }
}
