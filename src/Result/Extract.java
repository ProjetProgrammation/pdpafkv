/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

import BDD.Audio;
import BDD.Video;
import Controller.ControllerDatabase;
import Controller.MediaSelected;
import Controller.SelectMedia;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

/**
 * Write on file results of the user
 *
 * @author Thibaut
 */
public class Extract {

    /**
     * Constructs an Extract object.
     *
     * @param medSelected current occurence of MediaSelected.
     * @param selMed current occurence of SelectMedia
     */
    public static void Extract(MediaSelected medSelected,SelectMedia selMed) {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-dd-MM-yy");
            String path = "\\"+ sdf.format(date) + "_" + medSelected.getUserSel().getNameToFile() + "_" + medSelected.getLangSel().getName() + ".txt";
            String workingDirectory = System.getProperty("user.dir");	
            System.out.println("extract file");
            File file = new File(workingDirectory+FilenameUtils.separatorsToSystem(path));
            FileWriter fw = new FileWriter(file);                
            extractLanguageUser(medSelected,fw);
            extractAnswers(medSelected,fw,selMed);
            fw.close();
        } catch (Exception e) {
            System.out.println("[Extract]Problem to create file");
        }
    }

    /**
     * Extract information on user in Json format.
     *
     * @param medSelected current occurence of mediaSelect.
     * @param fw current occurence of FileWriter
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
            System.out.println("[extractLanguageUser]Problem to extract user & language");
        }
    }

    /**
     * Extract answers of the user in Json format.
     *
     * @param medSelected current occurence of mediaSelect.
     * @param fw current occurence of FileWriter
     */
    private static void extractAnswers(MediaSelected medSelected, FileWriter fw, SelectMedia selMed) {
        try {
            int i=1;
            fw.write("List of answers");
            fw.write(System.getProperty("line.separator"));
            for (Answer answer : medSelected.getAnswersList()) {
                fw.write("     Answer " + i + ":");
                fw.write(System.getProperty("line.separator"));
                fw.write("          Question:   " + answer.getQuestSel().getContent());
                fw.write(System.getProperty("line.separator"));
                fw.write("              By the user ");
                fw.write(System.getProperty("line.separator"));
                fw.write("                  Video   " + answer.getVideoSel().getName());
                fw.write(System.getProperty("line.separator"));
                fw.write("                  Audio   " + answer.getAudioSel().getName());
                fw.write(System.getProperty("line.separator"));
                fw.write("              Expected ");
                fw.write(System.getProperty("line.separator"));
                Video video = selMed.getDb().searchVideoById(answer.getQuestSel().getIdVideo());
                fw.write("                  Video   " + video.getName());
                fw.write(System.getProperty("line.separator"));
                Audio audio = selMed.getDb().searchAudioById(answer.getQuestSel().getIdAudio());
                fw.write("                  Audio   " + audio.getName());
                fw.write(System.getProperty("line.separator"));
                i++;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
