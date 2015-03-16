/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

import BDD.*;
import java.io.FileWriter;

/**
 *
 * @author Thibaut
 */
public class Extract {
    
    private String chemin;
    private int nombre = 2;
    private  FileWriter fichier;
    
    public Extract(String chemin){
        this.chemin = chemin;
        try{
            fichier  = new FileWriter(chemin);
        }
        catch(Exception e){
            System.out.println("erreur");
        }
    }
    
    public void startOfExtract(User user){
        try{
            
             fichier.write("{ \n");
             fichier.write("   \"User\": { \n");
             fichier.write("              \""+ user.getUserExtract() +"\": {\n");
        }
        catch(Exception e){
               System.out.println("Erreur de chargement de fichier");
        }
    }
    
    public void ExtractQuestion(Question question){
        
        int i =1;
        try{
             
             fichier.write("                    Question:" +  question.getContent() +  " \n");
             
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
