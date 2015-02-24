/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

import java.io.BufferedReader;
import java.io.FileReader;
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
        String nomMedia = "";
        String Emotion = "";
        String Chemin = "";
        int nombre = 0;
        
        try{
           //Lecture fichier texte
           FileReader fichier = new FileReader(chemin);
                  BufferedReader br=new BufferedReader(fichier);
                  String ligne;
                   while ((ligne=br.readLine())!=null){              
                        if(nombre >= 5 && ligne.contains("\"")){
                            if (nomMedia == ""){
                               
                                nomMedia = ligne;
                                
                            }
                            else if(nomMedia != "" && Emotion ==""){
                                Emotion = ligne;
                            }
                            else if(nomMedia != "" && Emotion !="" && Chemin == ""){
                                Chemin = ligne;
                                System.out.println(nomMedia + Emotion + Chemin);
                                nomMedia = "";
                                Emotion = "";
                                Chemin = "";
                            }                                    
                      }
                        nombre ++;
		}
		br.close(); 
           }
           catch(Exception e){
               System.out.println("Erreur de chargement de fichier");
           }
    }
}
