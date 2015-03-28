/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

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
     * Constructs a Errors object.
     */
    public Errors() {
    }

    /**
     * Find errors on last name, first name or mother tongue.
     *
     * @param valueTested null if no errors.
     * @return String.
     */
    public String errorsMessages(Object valueTested) {

        String messageError = null;

        if (valueTested instanceof String) {
            Pattern p = Pattern.compile("^[a-zA-Z]+$");
            Matcher m = p.matcher((String) valueTested);
            while (m.find()) {
                return null;
            }
            messageError = "false";
        } else if (valueTested instanceof Integer) {
            if ((int) valueTested > 125) {
                messageError = "false";
            } else {
                return null;
            }

        }
        return messageError;
    }

    /**
     * Errors on birthday.
     *
     * @param dateTested birthday of the user.
     * @return String.
     */
    public String errorDate(String dateTested) {
        String errorMessage = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d;

        try {
            d = sdf.parse(dateTested);
            String t = sdf.format(d);
            if (t.compareTo(dateTested) != 0) {
                errorMessage = "false";
            } else
                ;
        } catch (Exception e) {
            errorMessage = "false";
        }

        return errorMessage;
    }

    /**
     * Find errors in your Java version, operating system and your operating
     * system version.
     *
     */
    public void errorsOs() {
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
            System.out.println("a jour");;
        } else {
            System.out.println("Il faut que vous metiez votre java à jour");
        }
    }

}
