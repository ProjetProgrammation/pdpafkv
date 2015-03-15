/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

import GUI.QuestionGUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.text.Text;

/**
 *
 * @author Thibaut
 */
public class Extraction {
    
    private String chemin;
    private int nombre = 2;
    private  FileWriter fichier;
    
    public Extraction(String chemin){
        this.chemin = chemin;
        try{
            fichier  = new FileWriter(chemin);
        }
        catch(Exception e){
            System.out.println("erreur");
        }
    }
    
    public void DébutExtraction(String nomUtilisateur, String prénomUtilisateur){
        try{
            
             fichier.write("{ \n");
             fichier.write("   \"Utilisateur\": { \n");
             fichier.write("              \""+nomUtilisateur +" " + prénomUtilisateur + "\": {\n");
        }
        catch(Exception e){
               System.out.println("Erreur de chargement de fichier");
        }
    }
    
    public void ExtractionRéponse(Text question, String audio){
        
        int i =1;
        try{
             
             fichier.write("                    Question:" +  question.getText() +  " \n");
             fichier.write("                      { \"Audio choisi\":" +  audio +  " \n");
             fichier.write("                   ]\n");
             
         }
        catch(Exception e){
            System.out.println("erreur");
        }
    }
    
    public void finExtraction(){
        
        try{
             fichier.write("            }\n");
             fichier.write("    }\n");
             fichier.write("}\n");
                     fichier.close(); 
        }
        catch(Exception e){
               System.out.println("Erreur de chargement de fichier");
        }
        
    }
}

