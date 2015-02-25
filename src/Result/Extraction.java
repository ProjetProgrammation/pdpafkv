/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Thibaut
 */
public class Extraction {
    
    String chemin;
    
    public Extraction(String chemin){
        this.chemin = chemin;
    }
    
    public void extraire(){
        String nomUtilisateur = "Jacquie";
        String Emotions[] = {"chaine1", "OuainOuains", "rire" , "joies"};
        String trouvé[] = {"non", "non", "oui" , "non"};
        int nombre = 2;
       
        try{
             FileWriter fichier = new FileWriter(chemin);
             fichier.write("{ \n");
             fichier.write("   \"Extraction\": { \n");
             fichier.write("              \""+nomUtilisateur + "\": {\n");
            
             for (int i = 1; i <= nombre ; i++){
             fichier.write("                    Question"+i+ ": [ \n");
             fichier.write("                      { \"Emotion\": \"" + Emotions[i] + "\" }\n");
             fichier.write("                      { \"Trouvé\": \"" + trouvé[i] + "\" }\n");
             fichier.write("                   ]\n");
             }
             
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
