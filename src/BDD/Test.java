/*
 * Cette classe sert à tester la classe DataBase
 */

//package BDD;
import java.sql.*;

/**
 *
 * @author akervadec
 */
public class Test {

	public static void main(String[] args) {
		DataBase db = new DataBase();
		//db.createTables();
		//db.addAudio("Fear","../../Media/Audios/Fear.mp3","mp3","French");
		//db.addVideo("Bondage","../../Media/Videos/Bondage.mp4","mp4","French");
		Language tempLanguage = new Language(1, "French");
		//System.out.println(db.manageVideo(tempLanguage));
		System.out.println(db.manageAudio(tempLanguage));
	}
}