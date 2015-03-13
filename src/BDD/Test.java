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
		//*********************
		// A commenter après la première utilisation de cette classe.
		db.createTables();
		//*********************
		db.addLanguage("French");
		db.addLanguage("English");
		db.addAudio("Joy","../../Media/Audios/Joy.mp3","mp3","French");
		db.addAudio("seth","../../pdpafkv/Audio/seth.mp3","mp3","French");
		db.addAudio("Exitation","../../Media/Audios/Exitation.mp3","mp3","French");
		db.addVideo("SM","../../Media/Videos/SM.mp4","mp4","French");
		db.addVideo("Threesome","../../Media/Videos/Threesome.mp4","mp4","French");
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
		db.addQuestion(tempQuestion1, tempVideo, tempAudio, tempLanguage.getName());
		db.addQuestion(tempQuestion2, tempVideo, tempAudio, tempLanguage.getName());
		//System.out.println(db.manageQuestion(tempLanguage));
		System.out.println(db.getAllLanguages());
		System.out.println(db.getAllAudios());
		System.out.println(db.getAllVideos());
		System.out.println(db.getAllQuestions());
	}
}