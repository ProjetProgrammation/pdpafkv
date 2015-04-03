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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests all DataBase fonctions.
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
     *Connexion method test for DataBase class.
     */
    public void testConnexion() {
        assertTrue("Connexion failed", dataBaseTest.connexion() != null);
    }

    /**
     *CreateTables method Test for DataBase Class.
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
     * Video methods Test for DataBase Class. White-box tests and
     * black-box tests.
     */
    public void testBlackBoxAddVideo() {
        System.out.println("addVideo test");

        String name = "sethgecks";
        String filePath = "Audio\\\\sethsbree.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        String thumbnail = "sethgecks.jpg";
        String thumbnailgif = "sethgecks.gif";
        boolean checkVideo = false;

        dataBaseTest.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
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
                    System.out.println("Video found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in BlackBoxAddVideo", checkVideo == true);
    }

    public void testBlackBoxSearchVideoByNameFormat() {
        System.out.println("searchVideoByNameFormat");

        String name = "2013_3_19_S29_fr_L1_ADMI_B_ok";
        String filePath = "Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        int idLanguage = dataBaseTest.searchLanguageByName(nameLanguage).getId();

        Video expResult = new Video(1, name, filePath, format, idLanguage);
        Video result = dataBaseTest.searchVideoByNameFormat(name, format);

        assertTrue("Problem BlackBoxSearchVideoByNameFormat", expResult.getFilePath().equals(result.getFilePath()) && expResult.getName().equals(result.getName()) && expResult.getFormat().equals(result.getFormat()));
    }

    public void testBlackBoxRmVideo() {
        System.out.println("BlackBoxrmVideo");

        ArrayList<Video> videoListTest;
        videoListTest = dataBaseTest.getAllVideos();
        for (Video v : videoListTest) {
            System.out.println("Video name :" + v.getName());
        }

        String name = "sethgecks";
        String format = "mp4";
        dataBaseTest.rmVideo(name, format);

        videoListTest = dataBaseTest.getAllVideos();
        for (Video v : videoListTest) {
            System.out.println("Video name :" + v.getName());
            if (v.getName().equals(name) && v.getFormat().equals(format)) {
                throw new AssertionError("Problem with BlackBoxRmVideo");
            }
        }
    }

    public void testBlackBoxCountVideo() {
        System.out.println("BlackBoxcountVideo");

        int idLanguage = 1;
        int expResult = 0;
        int result = dataBaseTest.countVideo(idLanguage);

        ArrayList<Video> videoListTest;
        videoListTest = dataBaseTest.getAllVideos();
        for (Video v : videoListTest) {
            if (v.getIdLanguage() == idLanguage) {
                expResult += 1;
            }
        }
        assertEquals("Problem with BlackBoxCountVideo", expResult, result);
    }

    public void testAddVideoWhiteBox() {
        System.out.println("VideoWhiteBox");

        String name = "sethgecksss";
        String filePath = "Audio\\\\sethsbreesss.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        String thumbnail = "sethgeeeeecks.jpg";
        String thumbnailgif = "sethgeeeeecks.gif";

        dataBaseTest.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
        dataBaseTest.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
    }

    public void testRmVideoWhiteBox() {
        System.out.println("RmVideoWhiteBox");

        String name = "sethgecks";
        String filePath = "Audio\\\\sethsbree.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        String thumbnail = "sethgecks.jpg";
        String thumbnailgif = "sethgecks.gif";

        dataBaseTest.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
        dataBaseTest.rmVideo(name, format);
        dataBaseTest.rmVideo(name, format);

        ArrayList<Question> questionListTest;
        questionListTest = dataBaseTest.getAllQuestions();
        int resulVideo = 0;
        int idVideo = -1;

        for (Question v : questionListTest) {
            if (resulVideo == 0) {
                idVideo = v.getIdVideo();
                resulVideo++;
            }
        }

        Video videoTest = dataBaseTest.searchVideoById(idVideo);
        dataBaseTest.rmVideo(videoTest.getName(), videoTest.format);

        //We add the video again in the data base to keep the same amount at the begin of tests
        String languageTest = dataBaseTest.getLanguageById(videoTest.getIdLanguage());
        dataBaseTest.addVideo(videoTest.getName(), videoTest.getFilePath(), videoTest.getFormat(), languageTest, videoTest.getThumbnailPicPath(), videoTest.getThumbnailGifPath());
    }

    public void testBlackBoxManageVideo() {
        System.out.println("BlackBoxManageVideo");

        Language languageTest = new Language(1, "French");
        Video video = dataBaseTest.manageVideo(languageTest);

        assertTrue("error on manageVideo", languageTest.getId() == video.getIdLanguage());
    }

    public void testBlackBoxSearchVideoById() {
        System.out.println("BlackBoxSearchVideoById");

        Video video = new Video(dataBaseTest.searchVideoById(1));
        ArrayList<Video> videoListTest;
        videoListTest = dataBaseTest.getAllVideos();

        for (Video v : videoListTest) {
            System.out.println("Video name:" + v.getName());
            if (!(v.getName().equals(video.getName())) && v.getId() == (video.getId())) {
                throw new AssertionError("BlackBoxSearchVideoById problem");
            }
        }
    }

    /**
     * Audio methods Test for DataBase class. White-box tests and
     * black-box tests.
     */
    public void testBlackBoxAddAudio() {
        System.out.println("BlackBoxAddAudio");

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
        assertTrue("Error in BlackBoxAddAudio", checkAudio == true);
    }

    public void testBlackBoxRmAudio() {
        System.out.println("BlackBoxRmAudio");

        ArrayList<Audio> audioListTest;
        audioListTest = dataBaseTest.getAllAudios();

        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
        }

        String name = "sethgeceeks";
        String format = "mp3";
        dataBaseTest.rmAudio(name, format);

        audioListTest = dataBaseTest.getAllAudios();

        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
            if (v.getName().equals(name) && v.getFormat().equals(format)) {
                throw new AssertionError("Problem with BlackBoxRmAudio");
            }
        }
    }

    public void testBlackBoxSearchAudioByNameFormat() {
        System.out.println("BlackBoxSearchAudioByNameFormat");

        String name = "sethgeceeks";
        String filePath = "Audio\\\\sethsbreedd.mp3";
        String format = "mp3";
        String nameLanguage = "French";

        int idLanguage = dataBaseTest.searchLanguageByName(nameLanguage).getId();

        Audio expResult = new Audio(1, name, filePath, format, idLanguage);
        Audio result = dataBaseTest.searchAudioByNameFormat(name, format);

        assertTrue("Problem with BlackBoxSearchAudioByNameFormat", expResult.getFilePath().equals(result.getFilePath()) && expResult.getName().equals(result.getName()) && expResult.getFormat().equals(result.getFormat()));
    }

    public void testBlackBoxCountAudio() {
        System.out.println("BlackBoxCountAudio");

        int idLanguage = 1;
        int expResult = 0;
        int result = dataBaseTest.countAudio(idLanguage);

        ArrayList<Audio> audioListTest;
        audioListTest = dataBaseTest.getAllAudios();
        for (Audio v : audioListTest) {
            if (v.getIdLanguage() == idLanguage) {
                expResult += 1;
            }
        }
        assertEquals("problem with BlackBoxCountAudio", expResult, result);
    }

    public void testAddAudioWhiteBox() {
        System.out.println("AddAudioWhiteBox");

        String name = "coupain";
        String filePath = "Audio\\\\coupain.mp4";
        String format = "mp3";
        String nameLanguage = "French";

        dataBaseTest.addAudio(name, filePath, format, nameLanguage);
        dataBaseTest.addAudio(name, filePath, format, nameLanguage);
    }

    public void testRmAudioWhiteBox() {
        System.out.println("RmAudioWhiteBox");

        String name = "coupain";
        String format = "mp3";

        dataBaseTest.rmAudio(name, format);
        dataBaseTest.rmAudio(name, format);

        ArrayList<Question> questionListTest;
        questionListTest = dataBaseTest.getAllQuestions();
        int resulAudio = 0;
        int idAudio = -1;

        for (Question v : questionListTest) {
            if (resulAudio == 0) {
                idAudio = v.getIdAudio();
                resulAudio++;
            }
        }

        Audio audioTest = dataBaseTest.searchAudioById(idAudio);
        dataBaseTest.rmAudio(audioTest.getName(), audioTest.format);

        String languageTest = dataBaseTest.getLanguageById(audioTest.getIdLanguage());
        dataBaseTest.addAudio(audioTest.getName(), audioTest.getFilePath(), audioTest.getFormat(), languageTest);
    }

    public void testBlackBoxManageAudio() {
        Language languageTest = new Language(1, "French");
        Audio audio = dataBaseTest.manageAudio(languageTest);

        assertTrue("error in BlackBoxManageAudio", languageTest.getId() == audio.getIdLanguage());
    }

    /**
     * Questions methods for DataBase class. White-box tests
     * and black-box tests.
     */
    public void testBlackBoxAddQuestion() {
        System.out.println("BlackBoxAddQuestion");

        String content = "hello everybody, how are you?";
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
                    System.out.println("Question found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in BlackBoxAddQuestion", checkQuestion == true);
    }

    public void testBlackBoxSearchAudioById() {
        System.out.println("BlackBoxSearchAudioById");

        Audio audio = new Audio(dataBaseTest.searchAudioById(1));

        ArrayList<Audio> audioListTest;
        audioListTest = dataBaseTest.getAllAudios();
        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
            if (!(v.getName().equals(audio.getName())) && v.getId() == (audio.getId())) {
                throw new AssertionError("BlackBoxSearchAudioById problem");
            }
        }
        dataBaseTest.searchAudioById(dataBaseTest.getAllAudios().size() + 1);
    }

    public void testBlackBoxRmQuestion() {
        System.out.println("BlackBoxRmQuestion");

        ArrayList<Question> questionsListTest;
        questionsListTest = dataBaseTest.getAllQuestions();

        for (Question v : questionsListTest) {
            System.out.println("The question content is:" + v.getContent());
        }

        String content = "hello everybody how are you?";
        dataBaseTest.rmQuestion(content);

        questionsListTest = dataBaseTest.getAllQuestions();
        for (Question v : questionsListTest) {
            System.out.println("Question name :" + v.getContent());
            if (v.getContent().equals(content)) {
                throw new AssertionError("Error in BlackBoxRmQuestion");
            }
        }
    }

    public void testBlackBoxSearchQuestionByContent() {
        System.out.println("BlackBoxSearchQuestionByContent");

        String content = "hello everybody how are you?";
        String nameLanguage = "French";
        int idLanguage = dataBaseTest.searchLanguageByName(nameLanguage).getId();
        Question result = dataBaseTest.searchQuestionByContent(content);

        assertTrue("Problem with BlackBoxSearchQuestionByContent", result.getContent().equals(content));
    }

    public void testBlackBoxCountQuestion() {
        System.out.println("BlackBoxCountQuestion");

        int idLanguage = 1;
        int expResult = 0;
        int result = dataBaseTest.countQuestion(idLanguage);

        ArrayList<Question> questionListTest;
        questionListTest = dataBaseTest.getAllQuestions();
        for (Question v : questionListTest) {
            if (v.getIdLanguage() == idLanguage) {
                expResult += 1;
            }
        }
        assertEquals("Problem with BlackBoxCountQuestion", expResult, result);

        dataBaseTest.countQuestion(-1);
    }

    public void testAddQuestionWhiteBox() {
        System.out.println("AddQuestionWhiteBox");

        Audio audioTest = new Audio(55, "sethgeceekeds", "Audio\\sethsbreedd.mp3", "mp3", 1);
        Video videoTest = new Video(70, "sethgeckdes", "Video\\sethsbree.mp4", "mp4", 1);
        String nameLanguage = "French";
        String content = "we try this";

        dataBaseTest.addQuestion(content, videoTest, audioTest, nameLanguage);
        dataBaseTest.addQuestion(content, videoTest, audioTest, nameLanguage);
    }

    public void testRmQuestionWhiteBox() {
        System.out.println("RmQuestionWhiteBox");

        String content = "hello everybody how are you?";
        Audio audioTest = new Audio(2000, "sethgeceeks", "Audio\\sethsbreedd.mp3", "mp3", 1);
        Video videoTest = new Video(2000, "sethgecks", "Audio\\sethsbree.mp3", "mp3", 1);
        String nameLanguage = "French";

        dataBaseTest.addQuestion(content, videoTest, audioTest, content);
        dataBaseTest.rmQuestion(content);
        dataBaseTest.rmQuestion(content);
    }

    public void testBlackBoxManageQuestion() {
        System.out.println("BlackBoxManageQuestion");

        Language languageTest = new Language(1, "French");
        Question question = dataBaseTest.manageQuestion(languageTest);

        assertTrue("error with BlackBoxManageQuestion", languageTest.getId() == question.getIdLanguage());
    }

    /**
     * Language methods test for DataBase class. White-box tests
     * and black-box tests.
     */
    public void testBlackBoxAddLanguage() {
        System.out.println("BlackBoxAddLanguage");

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
                System.out.println("the language was found");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        assertTrue("Error with BlackBoxAddLanguage", checkLanguage == true);
    }

    public void testBlackBoxSearchLanguageByName() {
        System.out.println("BlackBoxSearchLanguageByName");

        ArrayList<Language> languageListTest;
        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            if (v.getId() != dataBaseTest.searchLanguageByName(v.getName()).getId()) {
                Assert.fail("error in BlackBoxSearchLanguageByName");
            }
        }
    }

    public void testBlackBoxRmLanguage() {
        System.out.println("BlackBoxRmLanguage");
        ArrayList<Language> languageListTest;
        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName() + v.getId());
        }
        String name = "Japanese";

        dataBaseTest.rmLanguage(name);

        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName());
            System.out.println("The language name is:" + v.getId());
            if (v.getName().equals(name)) {
                throw new AssertionError("Problem with BlackBoxRmLanguage");
            }
        }
    }

    public void testRmLanguageWhiteBox() {
        System.out.println("RmLanguageWhiteBox");

        String name = "American";
        int languageName = 0;

        dataBaseTest.addLanguage(name);
        dataBaseTest.rmLanguage(name);
        dataBaseTest.rmLanguage(name);

        ArrayList<Language> languageListTest;
        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            if (languageName == 0) {
                dataBaseTest.rmLanguage(v.getName());
                languageName++;
            }
        }
    }

    public void testAddLanguageWhiteBox() {
        System.out.println("AddLanguageWhiteBox");

        String languageTest = "American";
        dataBaseTest.addLanguage(languageTest);
        dataBaseTest.addLanguage(languageTest);
    }

    public void testBlackBoxGetLanguageById() {
        System.out.println("BlackBoxGetLanguageById");

        Language langueTest = new Language(1, dataBaseTest.getLanguageById(1));

        ArrayList<Language> languageListTest;
        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("Language name:" + v.getName());
            if (!(v.getName().equals(langueTest.getName())) && v.getId() == langueTest.getId()) {
                throw new AssertionError("BlackBoxGetLanguageById have a problem");
            }
        }
    }

    public void testBlackBoxGetLanguageByName() {
        System.out.println("BlackBoxGetLanguageByName");

        Language langueTest = new Language(dataBaseTest.getLanguageByName("French"), "French");

        ArrayList<Language> languageListTest;
        languageListTest = dataBaseTest.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName());
            if (v.getName().equals(langueTest.getName()) && !(v.getId() == langueTest.getId())) {
                throw new AssertionError("BlackBoxGetLanguageByName have a problem");
            }
        }

    }

    @Test
    public void testSuite() {

        testConnexion();
        testCreateTables();

        testBlackBoxAddVideo();
        testBlackBoxAddAudio();
        testBlackBoxAddQuestion();
        testBlackBoxAddLanguage();

        testBlackBoxSearchLanguageByName();
        testBlackBoxSearchVideoByNameFormat();
        testBlackBoxSearchAudioByNameFormat();
        testBlackBoxSearchQuestionByContent();

        testBlackBoxRmVideo();
        testBlackBoxRmAudio();
        testBlackBoxRmLanguage();
        testBlackBoxRmQuestion();

        testBlackBoxCountAudio();
        testBlackBoxCountQuestion();
        testBlackBoxCountVideo();

        testAddVideoWhiteBox();
        testAddQuestionWhiteBox();
        testAddAudioWhiteBox();
        testAddLanguageWhiteBox();

        testBlackBoxManageVideo();
        testBlackBoxManageQuestion();
        testBlackBoxManageAudio();

        testRmVideoWhiteBox();
        testRmAudioWhiteBox();
        testRmQuestionWhiteBox();
        testRmLanguageWhiteBox();

        testBlackBoxGetLanguageById();
        testBlackBoxGetLanguageByName();
        testBlackBoxSearchVideoById();
        testBlackBoxSearchAudioById();
    }

}
