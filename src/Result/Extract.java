/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

import Controller.MediaSelected;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write on file results of the user
 *
 * @author Thibaut
 */
public class Extract {

    /**
     * Constructs an Extract object.
     *
     * @param medSelected current occurence of mediaSelect.
     */
    public static void Extract(MediaSelected medSelected) {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-dd-MM-yy");
            String path = sdf.format(date) + "_" + medSelected.getUserSel().getNameToFile() + "_" + medSelected.getLangSel().getName() + ".txt";
            String workingDirectory = System.getProperty("user.dir");	
            File file = new File(workingDirectory, path);
            FileWriter fw = new FileWriter(file);                
            extractLanguageUser(medSelected,fw);
            extractAnswers(medSelected, fw);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error to create JSON file");
        }
    }

    /**
     * Extract information on user in Json format.
     *
     * @param medSelected current occurence of mediaSelect.
     * @param json current occurence of JsonWriter
     */
    private static void extractLanguageUser(MediaSelected medSelected, FileWriter fw) {
        try {
            fw.write("Langage:   " + medSelected.getLangSel().getName());
            fw.write(System.getProperty("line.separator"));
            fw.write("User:");
            fw.write(System.getProperty("line.separator"));
            fw.write("     First Name:   " + medSelected.getUserSel().getFirstName());
            fw.write(System.getProperty("line.separator"));
            fw.write("     Last Name:   "+ medSelected.getUserSel().getLastName());
            fw.write(System.getProperty("line.separator"));
            fw.write("     Birthday   " + medSelected.getUserSel().getBirthday());
            fw.write(System.getProperty("line.separator"));
            fw.write("     Mother Tongue   " + medSelected.getUserSel().getMotherTongue());
            fw.write(System.getProperty("line.separator"));
            fw.write("     Years learning tongue selected   " + medSelected.getUserSel().getYearStudying());
            fw.write(System.getProperty("line.separator"));
        } catch (Exception e) {
            System.out.println("Error to extract tongue and user information to JSON file");
        }
    }

    /**
     * Extract answers of the user in Json format.
     *
     * @param medSelected current occurence of mediaSelect.
     * @param json current occurence of JsonWriter
     */
    private static void extractAnswers(MediaSelected medSelected, FileWriter fw) {

        try {
            int i=1;
            fw.write("List of answers");
            fw.write(System.getProperty("line.separator"));
            for (Answer answer : medSelected.getAnswersList()) {
                fw.write("     Answer " + i + ":");
                fw.write(System.getProperty("line.separator"));
                fw.write("          Question:   " + answer.getQuestSel().getContent());
                fw.write(System.getProperty("line.separator"));
                fw.write("          Video   " + answer.getVideoSel().getName());
                fw.write(System.getProperty("line.separator"));
                fw.write("          Audio   " + answer.getAudioSel().getName());
                fw.write(System.getProperty("line.separator"));
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error to extract answers information to JSON file");
        }
    }
}
