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
import static jdk.nashorn.internal.objects.NativeRegExp.test;

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
                   //tant qu'il este une ligne à lire
                   while ((ligne=br.readLine())!=null){  
                       //Si la ligne contient " et on enlève le début du fichier avec nombre
                        if(nombre >= 5 && ligne.contains("\"")){
                            //si le nom du média est nulle
                            if (nomMedia == ""){
                                //On split la ligne à l'endroit du :
                                String[] infos = ligne.split(":");
                                //On définit un pattern pour trouver l'info seule
                                Pattern pattern = Pattern.compile("([a-zA-Z1-9]| )+");
                                //On match avec la seconde partie de la ligne qui contient l'info
                                Matcher matcher = pattern.matcher(infos[1]);
                                //Si il y a un résultat
                                while(matcher.find()){
                                    //Alors on met dans nomMédia le groupe trouvé
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
            //On ferme le buffer
            br.close(); 
        }
        catch(Exception e){
               System.out.println("Erreur de chargement de fichier");
        }
    }
}
