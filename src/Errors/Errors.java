/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author guillaume
 */
public class Errors extends Exception {
    
    public Errors(){}
    
    public Object ErrorsMessages(Object message){
        
      Object mess = null;
      
         if (message instanceof String){
            Pattern p = Pattern .compile("^[a-zA-Z]+$|[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
            Matcher m = p.matcher((String)message);
            while (m.find()){
                  return null;
            }
          mess="faux";
         }
         
         else if(message instanceof Integer){
              if ((int)message > 125){
                 mess="faux";
            }
            else{
                  return null;
            }
         
         }
        return mess;
    }
    
    public void ErrorsOs(){
        String name = System.getProperty ( "os.name" );
        String version = System.getProperty ( "os.version" );
        String java = System.getProperty("java.home");
        
        
         if (name.contains("Windows") || name.contains("MAC") || name.contains("linux")){}
         else{
                 System.out.println("Votre système d'exploitation n'est pas pris en charge");
         }
         
         
         
         Pattern p = Pattern .compile("jdk1.8.0_31");
         Matcher m = p.matcher(java);
         if (m.find()){
             System.out.println("a jour");;
         }
         else{
             System.out.println("Il faut que vous metiez votre java à jour");
         }
   }
    
}
