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
}
