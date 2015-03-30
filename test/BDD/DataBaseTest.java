/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test all DataBase fonctions.
 *
 * @author guillaume21
 */
public class DataBaseTest {

    private DataBase dataBaseTest;

    public DataBaseTest() {

    }

    @Before
    public void setUp() {
        dataBaseTest = new DataBase();
    }

    @After
    public void tearDown() {
        dataBaseTest = null;
    }

    /**
     * Test of Connexion method, of class DataBase.
     */
    public void testConnexion() {
        assertTrue("The connection failed", dataBaseTest.connexion() != null);
    }

    /**
     * Test of createTables method, of class DataBase.
     */
    public void testCreateTables() {

        Connection c;
        PreparedStatement stmt;
        String tablesName[] = {"Question", "sqlite_sequence", "Video", "Audio", "Language"};
        try {
            c = dataBaseTest.connexion();
            String sql = " SELECT name FROM sqlite_master WHERE type='table';";
            stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                if (rs.getString("name").contains(tablesName[0]) && !tablesName[0].isEmpty()) {
                    tablesName[0] = "";
                }
                if (rs.getString("name").contains(tablesName[1]) && !tablesName[1].isEmpty()) {
                    tablesName[1] = "";
                }
                if (rs.getString("name").contains(tablesName[2]) && !tablesName[2].isEmpty()) {
                    tablesName[2] = "";
                }
                if (rs.getString("name").contains(tablesName[3]) && !tablesName[3].isEmpty()) {
                    tablesName[3] = "";
                }

                if (rs.getString("name").contains(tablesName[4]) && !tablesName[4].isEmpty()) {
                    tablesName[4] = "";
                }
            }
            stmt.close();
            c.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("error in Question table", tablesName[0].isEmpty());
        assertTrue("error in Master table", tablesName[1].isEmpty());
        assertTrue("error in Video table", tablesName[2].isEmpty());
        assertTrue("error in Audio table", tablesName[3].isEmpty());
        assertTrue("error in Language table", tablesName[4].isEmpty());
    }

    /**
     * Test of addVideo method, of class DataBase.
     */
    public void testAddVideo() {
        System.out.println("addVideo test");
        String name = "sethgecks";
        String filePath = "Audio\\\\sethsbree.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        boolean checkVideo = false;
        dataBaseTest.addVideo(name, filePath, format, nameLanguage);
        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Video where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                String nameDataBase = rs.getString("name");
                String filepathDataBase = rs.getString("file_path");
                String formatDataBase = rs.getString("format");
                if (name.equals(nameDataBase) && filePath.equals(filepathDataBase) && format.equals(formatDataBase) && nameLanguage.equals("French")) {
                    checkVideo = true;
                    System.out.println("the video was found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of audio file", checkVideo == true);
    }

    /**
     * Test of addAudio method, of class DataBase.
     */
    public void testAddAudio() {
        System.out.println("addAudio test");
        String name = "sethgeceeks";
        String filePath = "Audio\\\\sethsbreedd.mp3";
        String format = "mp3";
        String nameLanguage = "French";
        boolean checkAudio = false;
        dataBaseTest.addAudio(name, filePath, format, nameLanguage);

        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Audio where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                String nameDataBase = rs.getString("name");
                String filepathDataBase = rs.getString("file_path");
                String formatDataBase = rs.getString("format");
                if (name.equals(nameDataBase) && filePath.equals(filepathDataBase) && format.equals(formatDataBase) && nameLanguage.equals("French")) {
                    checkAudio = true;
                    System.out.println("the audio was found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of audio file", checkAudio == true);
    }

    /**
     * Test of addQuestion method, of class DataBase.
     */
    public void testAddQuestion() {
        System.out.println("addQuestion test");
        String content = "hello everybody how are you?";
        Audio audioTest = new Audio(2000, "sethgeceeks", "Audio\\sethsbreedd.mp3", "mp3", 1);
        Video videoTest = new Video(2000, "sethgecks", "Audio\\sethsbree.mp3", "mp3", 1);
        String nameLanguage = "French";
        boolean checkQuestion = false;

        dataBaseTest.addQuestion(content, videoTest, audioTest, nameLanguage);

        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Question where content='" + content + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                String contentDataBase = rs.getString("content");
                String languageBDD = "French";
                if (content.equals(contentDataBase) && languageBDD.equals(nameLanguage)) {
                    checkQuestion = true;
                    System.out.println("the question was found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of question file", checkQuestion == true);
    }

    /**
     * Test of addLanguage method, of class DataBase.
     */
    public void testAddLanguage() {
        System.out.println("addLanguage");
        String name = "Japanese";
        dataBaseTest.addLanguage(name);
        boolean checkLanguage = false;

        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Language where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                checkLanguage = true;
                System.out.println("the question was found");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        assertTrue("Error in the add of question file", checkLanguage == true);
    }

    /**
     * Test of searchLanguageByName method, of class DataBase.
     */
    public void testSearchLanguageByName() {
        System.out.println("searchLanguageByName");
        String englishLanguage = "English";
        String frenchLanguage = "French";
        String japaneseLanguage = "Japanese";
        int expResultEnglish = dataBaseTest.searchLanguageByName(frenchLanguage).getId();
        int expResultFrench = dataBaseTest.searchLanguageByName(englishLanguage).getId();
        int expResultJapanese = dataBaseTest.searchLanguageByName(japaneseLanguage).getId();
        assertNotSame("Error in Language id", expResultEnglish, expResultFrench);
        assertNotSame("Error in Language id", expResultEnglish, expResultJapanese);
        assertNotSame("Error in Language id", expResultJapanese, expResultFrench);
    }

    /**
     * Test of searchVideoByNameFormat method, of class DataBase.
     */
    public void testSearchVideoByNameFormat() {
        System.out.println("searchVideoByNameFormat");
        String name = "2013_3_19_S29_fr_L1_ADMI_B_ok";
        String filePath = "Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4";
        String format = "mp4";
        String nameLanguage = "French";

        int idLanguage = dataBaseTest.searchLanguageByName(nameLanguage).getId();

        Video expResult = new Video(1, name, filePath, format, idLanguage);
        Video result = dataBaseTest.searchVideoByNameFormat(name, format);

        assertTrue("Problem with search video by name format", expResult.getFilePath().equals(result.getFilePath()) && expResult.getName().equals(result.getName()) && expResult.getFormat().equals(result.getFormat()));
    }

    /**
     * Test of searchAudioByNameFormat method, of class DataBase.
     */
    public void testSearchAudioByNameFormat() {
        System.out.println("searchVideoByNameFormat");
        String name = "sethgeceeks";
        String filePath = "Audio\\\\sethsbreedd.mp3";
        String format = "mp3";
        String nameLanguage = "French";

        int idLanguage = dataBaseTest.searchLanguageByName(nameLanguage).getId();

        Audio expResult = new Audio(1, name, filePath, format, idLanguage);
        Audio result = dataBaseTest.searchAudioByNameFormat(name, format);

        assertTrue("Problem with audio search by name format", expResult.getFilePath().equals(result.getFilePath()) && expResult.getName().equals(result.getName()) && expResult.getFormat().equals(result.getFormat()));
    }

    /**
     * Test of SearchQuestionByContent method, of class DataBase.
     */
    public void testSearchQuestionByContent() {
        System.out.println("searchVideoByNameFormat");
        String content = "hello everybody how are you?";
        String nameLanguage = "French";

        int idLanguage = dataBaseTest.searchLanguageByName(nameLanguage).getId();

        Question result = dataBaseTest.searchQuestionByContent(content);

        assertTrue("Problem with question search by content", result.getContent().equals(content));
    }

    /**
     * Test of countAudio method, of class DataBase.
     */
    public void testCountAudio() {
        System.out.println("countAudio");

        int idLanguage = 1;
        int expResult = 4;
        int result = dataBaseTest.countAudio(idLanguage);

        assertEquals("problem with count audio", expResult, result);

    }

    /**
     * Test of countQuestion method, of class DataBase.
     */
    public void testCountQuestion() {
        System.out.println("countQuestion");

        int idLanguage = 1;
        int expResult = 4;
        int result = dataBaseTest.countQuestion(idLanguage);

        assertEquals("Problem with count question", expResult, result);
    }

    /**
     * Test of countVideo method, of class DataBase.
     */
    public void testCountVideo() {
        System.out.println("countVideo");

        int idLanguage = 1;
        int expResult = 2;
        int result = dataBaseTest.countVideo(idLanguage);

        assertEquals("Problem with count video", expResult, result);
    }

    /**
     * Test of rmVideo method, of class DataBase.
     */
    public void testRmVideo() {
        System.out.println("rmVideo");
        ArrayList<Video> videoListTest;
        videoListTest = dataBaseTest.getAllVideos();
        for (Video v : videoListTest) {
            System.out.println("The video name is:" + v.getName());
        }
        String name = "sethgecks";
        String format = "mp4";
        dataBaseTest.rmVideo(name, format);
        dataBaseTest.rmVideo(name, format);

        videoListTest = dataBaseTest.getAllVideos();
        for (Video v : videoListTest) {
            System.out.println("The video name is:" + v.getName());
            if (v.getName().equals(name) && v.getFormat().equals(format)) {
                throw new AssertionError("Video always in DataBase");
            }
        }
    }

    /**
     * Test of rmAudio method, of class DataBase.
     */
    public void testRmAudio() {
        System.out.println("rmAudio");
        ArrayList<Audio> audioListTest;
        audioListTest = dataBaseTest.getAllAudios();
        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
        }
        String name = "sethgeceeks";
        String format = "mp3";
        dataBaseTest.rmAudio(name, format);
        dataBaseTest.rmAudio(name, format);

        audioListTest = dataBaseTest.getAllAudios();
        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
            if (v.getName().equals(name) && v.getFormat().equals(format)) {
                throw new AssertionError("Audio always in DataBase");
            }
        }
    }
    
    /**
     * Test of rmLanguage method, of class DataBase.
     */
    public void testRmLanguage() {
        System.out.println("rmLanguage");
        ArrayList<Language> languageListTest;
        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName() + v.getId());
        }
        String name = "American";
        
        dataBaseTest.rmLanguage(name);
        dataBaseTest.rmLanguage(name);
        
       languageListTest = dataBaseTest.getAllLanguages();
       for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName());
            if (v.getName().equals(name)) {
                throw new AssertionError("language always in DataBase");
            }
        }
    }

    /**
     * Test grey box of AddAudio, AddVideo, AddQuestion and AddLanguage of class
     * DataBase. We know these methods have to not add a video if it already
     * exist so we try it.
     */
    public void test2AddVideoGreyBox() {

        String name = "sethgecksss";
        String filePath = "Audio\\\\sethsbreesss.mp4";
        String format = "mp4";
        String nameLanguage = "French";

        dataBaseTest.addVideo(name, filePath, format, nameLanguage);
        dataBaseTest.addVideo(name, filePath, format, nameLanguage);
    }

    public void test2AddAudioGreyBox() {

        String name = "coupain";
        String filePath = "Audio\\\\coupain.mp4";
        String format = "mp3";
        String nameLanguage = "French";

        dataBaseTest.addAudio(name, filePath, format, nameLanguage);
        dataBaseTest.addAudio(name, filePath, format, nameLanguage);
    }

    public void test2AddQuestionGreyBox() {
        Audio audioTest = new Audio(55, "sethgeceekeds", "Audio\\sethsbreedd.mp3", "mp3", 1);
        Video videoTest = new Video(70, "sethgeckdes", "Video\\sethsbree.mp4", "mp4", 1);
        String nameLanguage = "French";
        String content = "we try this";

        dataBaseTest.addQuestion(content, videoTest, audioTest, nameLanguage);
        dataBaseTest.addQuestion(content, videoTest, audioTest, nameLanguage);
    }

    public void test2AddLanguageGreyBox() {
        String languageTest = "American";

        dataBaseTest.addLanguage(languageTest);
        dataBaseTest.addLanguage(languageTest);
    }
    
    @Test
    public void testSuite() {

        testConnexion();
        testCreateTables();
        testAddVideo();
        testAddAudio();
        testAddQuestion();
        testAddLanguage();
        testSearchLanguageByName();
        testSearchVideoByNameFormat();
        testSearchAudioByNameFormat();
        testSearchQuestionByContent();
        testCountAudio();
        testCountQuestion();
        testCountVideo();
        testRmVideo();
        testRmAudio();
       
        test2AddVideoGreyBox();
        test2AddQuestionGreyBox();
        test2AddAudioGreyBox();
        test2AddLanguageGreyBox();
        testRmLanguage();
    }

}
