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

        boolean boolError = true;

        if (valueTested instanceof String) {
            Pattern p = Pattern.compile("^[a-zA-Z]+$");
            Matcher m = p.matcher((String) valueTested);
            while (m.find()) {
                return boolError;
            }
            boolError = false;
        } else if (valueTested instanceof Integer) {
            if ((int) valueTested > 125) {
                boolError = false;
            } else {
                return boolError;
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
     * Find errors in your Java version, operating system and your operating
     * system version.
     *
     */
    public static void errorsOs() {
        String nameOS = System.getProperty("os.name");
        String Version = System.getProperty("os.version");
        String javaVersion = System.getProperty("java.home");

        if (nameOS.contains("Windows") || nameOS.contains("MAC") || nameOS.contains("Linux")) {
            
        } else {
            System.out.println("Votre système d'exploitation n'est pas pris en charge");
        }

        Pattern p = Pattern.compile("jdk1.8.0_31");
        Matcher m = p.matcher(javaVersion);
        if (m.find()) {
            System.out.println("a jour");
        } else {
            System.out.println("Il faut que vous metiez votre java à jour");
        }
    }

}
