/*
 * Cette classe sert à tester la classe DataBase
 * Attention à bien ajouter les langues en premier
 */

package BDD;
import java.sql.*;

/**
 *
 * @author akervadec
 */
public class Test {

	public static void main(String[] args) {
		DataBase db = new DataBase();
		db.createTables();
		db.addLanguage("French");
		db.addLanguage("English");
		db.addAudio("seth","Audio\\seth.mp3","mp3","French");
                db.addAudio("Ska-P-Canabis","Audio\\Ska-P-Canabis.mp3", "mp3","French");
                db.addAudio("cosmo","Audio\\cosmo.mp3","mp3","French");
                db.addAudio("wasted","Audio\\wasted.mp3","mp3","French");
                db.addAudio("mark","Audio\\mark.mp3","mp3","French");
		db.addVideo("2013_3_19_S29_fr_L1_ADMI_B_ok","Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4","mp4","French");
		db.addVideo("YouTube-Compilation-des-repliques-de-Kadoc","Video\\YouTube-Compilation-des-repliques-de-Kadoc.flv","flv","English");
		Language tempLanguage = new Language(1, "French");
		Video tempVideo = new Video(db.manageVideo(tempLanguage));
		Audio tempAudio = new Audio(db.manageAudio(tempLanguage));
		//System.out.println(db.searchLanguageByName("French"));
		//System.out.println("Audio recherché :");
		//System.out.println(tempAudio);
		//System.out.println("Audio trouvé :");
		//System.out.println(db.searchAudioByNameFormat(tempAudio.getName(), tempAudio.getFormat()) + "\n");
		//System.out.println("Vidéo recherchée :");
		//System.out.println(tempVideo);
		//System.out.println("Vidéo trouvée :");
		//System.out.println(db.searchVideoByNameFormat(tempVideo.getName(), tempVideo.getFormat()));
		String tempQuestion1 = new String("C'est ici le ramonage de cheminée ?");
		String tempQuestion2 = new String("On dit merci qui ?");
		//System.out.println(db.manageVideo(tempLanguage));
		//System.out.println(db.manageAudio(tempLanguage));
		//System.out.println(db.manageQuestion(tempLanguage));
		System.out.println(db.getAllLanguages());
		System.out.println(db.getAllAudios());
		System.out.println(db.getAllVideos());
		System.out.println(db.getAllQuestions());
	}
}
