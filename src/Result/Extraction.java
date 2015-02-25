/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

import java.io.BufferedReader;
import java.io.FileReader;
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
                                String[] infos = ligne.split(":");
                                Pattern pattern = Pattern.compile("([a-zA-Z1-9]| )+");
                                Matcher matcher = pattern.matcher(infos[1]);
                                while(matcher.find()){
                                    nomMedia = matcher.group();
                                }
                            }
                            else if(nomMedia != "" && Emotion ==""){
                                 String[] infos = ligne.split(":");
                                Pattern pattern = Pattern.compile("([a-zA-Z1-9]| )+");
                                Matcher matcher = pattern.matcher(infos[1]);
                                while(matcher.find()){
                                    Emotion = matcher.group();
                                }
                            }
                            else if(nomMedia !="" && Emotion !="" && Chemin ==""){
                                String[] infos = ligne.split(":");
                                Pattern pattern = Pattern.compile("([a-zA-Z1-9]| |\\.)+");
                                Matcher matcher = pattern.matcher(infos[1]);
                                while(matcher.find()){
                                    Chemin = matcher.group();
                                }
                         
                                System.out.println(nomMedia + "    " + Emotion+ "    " + Chemin + "\n");
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
