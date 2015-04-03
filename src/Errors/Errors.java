/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Find different types of error on programs or system for example.
 *
 * @author guillaume
 */
public class Errors extends Exception {

    /**
     * Find errors on last name, first name or mother tongue.
     *
     * @param valueTested null if no errors.
     * @return boolean.
     */
    public static boolean errorsMessages(Object valueTested) {

        boolean boolError = false;

        if (valueTested instanceof String) {
            Pattern p = Pattern.compile("^[a-zA-Z]+$");
            Matcher m = p.matcher((String) valueTested);
            while (m.find()) {
                boolError = true;
            }
        }
        
        else if (valueTested instanceof Integer) {
            if ((int) valueTested < 100 && (int) valueTested > 0) {
               boolError = true;
            } 
        }
        return boolError;
    }

    /**
     * Errors on birthday.
     *
     * @param dateTested birthday of the user.
     * @return boolean.
     */
    public static boolean errorDate(String dateTested){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date d = sdf.parse(dateTested);
        } catch (ParseException ex) {
            System.out.println("[errorDate]Wrong birthday inform in UserGUI");
            return false;
        }
        return true;
    }

    /**
     * Find errors in your operating system.
     * 
     * @throws java.lang.Exception
     */
    public void nameOs() throws Exception {
        String nameOS = System.getProperty("os.name").toLowerCase();
        
        if (nameOS.contains("win") || nameOS.contains("mac") || nameOS.contains("nux") || nameOS.contains("nix") || nameOS.contains("aix")) {
        }
        else{
            throw new Exception("OS Problem");
        }
    }
   
    /**
     * Find errors in your java version.
     * 
     */
    public static void javaVersion() {
        
        String javaVersion = System.getProperty("java.home");

        Pattern p = Pattern.compile("jdk1.8.0_31");
        Matcher m = p.matcher(javaVersion);
        if (m.find()) {
            System.out.println("a jour");
        } else {
            System.out.println("Il faut que vous metiez votre java à jour");
        }
    }
        
        

}
