/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author guillaume
 */
public class Errors extends Exception {
    
    public Errors(){}
    
    public String ErrorsMessages(Object message){
        
      String mess = null;
      
         if (message instanceof String){
            Pattern p = Pattern .compile("^[a-zA-Z]+$");
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
    
    public String errorDate(String date){
        String s = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        
        try {
            d = sdf.parse(date);
            String t = sdf.format(d);
            if(t.compareTo(date) !=  0)
                s="faux";
            else
                ;
        } catch (Exception e) {
                s= "faux";
        }
        
        return s;
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
